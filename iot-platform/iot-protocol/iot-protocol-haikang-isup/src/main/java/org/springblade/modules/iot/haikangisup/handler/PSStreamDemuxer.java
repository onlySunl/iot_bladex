package org.springblade.modules.iot.haikangisup.handler;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * MPEG PS Program Stream 解复用器
 * 适配海康ISUP SDK PS流，自动提取H.264/H.265 NAL单元
 * 解决分包跨起始码、分片缓存、OOM防护、日志降噪、GC优化
 */
@Slf4j
public class PSStreamDemuxer {
    // ===================== PS 标准起始码常量 =====================
    /** Pack起始码 0x000001BA */
    private static final byte PACK_START_CODE_ID = (byte) 0xBA;
    /** System Header起始码 0x000001BB */
    private static final byte SYSTEM_HEADER_ID = (byte) 0xBB;
    /** PES公共前缀 00 00 01 */
    private static final int PES_PREFIX = 0x000001;
    /** PES流ID掩码 */
    private static final int STREAM_ID_MASK = 0xFF;

    // ===================== PES 流类型 =====================
    /** 视频PES起始ID */
    private static final int VIDEO_PES_ID_START = 0xE0;
    /** 视频PES结束ID */
    private static final int VIDEO_PES_ID_END = 0xEF;
    /** 私有流1（海康ISUP音视频常用） */
    private static final int PRIVATE_STREAM_1 = 0xBD;

    // ===================== H264/H265 起始码 =====================
    /** 3字节起始码 00 00 01 */
    private static final int START_CODE_3BYTE = 0x000001;
    /** 4字节起始码 00 00 00 01 */
    private static final int START_CODE_4BYTE = 0x00000001;

    // ===================== 防护阈值 =====================
    /** PES负载最大允许长度，防止异常流OOM */
    private static final int MAX_PAYLOAD_SIZE = 2 * 1024 * 1024;
    /** 分片缓存最大容量，超过自动清空 */
    private static final int BUFFER_MAX_CAPACITY = 1024 * 1024;
    /** 日志打印字节上限，避免超长十六进制日志 */
    private static final int LOG_HEX_LIMIT = 32;

    // 跨包分片缓存：存储被切割的NAL片段，拼接下一包数据
    private final ByteArrayOutputStream fragmentBuffer = new ByteArrayOutputStream();

    /**
     * 处理单次PS数据包，输出完整NAL列表
     * @param psData 原始PS二进制数据
     * @return 完整H264/H265 NAL数组列表
     */
    public List<byte[]> processPSData(byte[] psData) {
        List<byte[]> nalResult = new ArrayList<>(16);
        if (psData == null || psData.length < 4) {
            return nalResult;
        }

        int globalOffset = 0;
        int dataLen = psData.length;

        while (globalOffset <= dataLen - 4) {
            // 匹配 00 00 01 起始码前缀
            int prefixVal = ((psData[globalOffset] & 0xFF) << 16)
                    | ((psData[globalOffset + 1] & 0xFF) << 8)
                    | (psData[globalOffset + 2] & 0xFF);
            if (prefixVal != PES_PREFIX) {
                globalOffset++;
                continue;
            }

            // 取出流ID
            byte streamId = psData[globalOffset + 3];
            int streamIdInt = streamId & STREAM_ID_MASK;
            int codeHeadPos = globalOffset;
            globalOffset += 4;

            // 1. 跳过 Pack Header
            if (streamIdInt == PACK_START_CODE_ID) {
                int packHeaderLen = parsePackHeaderLen(psData, globalOffset, dataLen);
                globalOffset += packHeaderLen;
                continue;
            }

            // 2. 跳过 System Header
            if (streamIdInt == SYSTEM_HEADER_ID) {
                if (globalOffset + 2 > dataLen) break;
                int sysHeaderLen = ((psData[globalOffset] & 0xFF) << 8) | (psData[globalOffset + 1] & 0xFF);
                globalOffset += 2 + sysHeaderLen;
                continue;
            }

            // 3. 只处理视频PES / 私有流1
            boolean targetStream = (streamIdInt >= VIDEO_PES_ID_START && streamIdInt <= VIDEO_PES_ID_END)
                    || streamIdInt == PRIVATE_STREAM_1;
            if (!targetStream) {
                continue;
            }

            // PES包长度校验
            if (globalOffset + 2 > dataLen) break;
            int pesTotalLen = ((psData[globalOffset] & 0xFF) << 8) | (psData[globalOffset + 1] & 0xFF);
            globalOffset += 2;

            // 解析PES头长度
            int pesHeaderLen = parsePesHeaderLen(psData, globalOffset, dataLen);
            globalOffset += pesHeaderLen;

            // 负载边界校验，防越界+超大包OOM
            int payloadRawLen = pesTotalLen - pesHeaderLen;
            if (payloadRawLen <= 0 || globalOffset >= dataLen) {
                continue;
            }
            int realPayloadLen = Math.min(payloadRawLen, dataLen - globalOffset);
            realPayloadLen = Math.min(realPayloadLen, MAX_PAYLOAD_SIZE);

            // 截取PES负载
            byte[] pesPayload = new byte[realPayloadLen];
            System.arraycopy(psData, globalOffset, pesPayload, 0, realPayloadLen);
            globalOffset += realPayloadLen;

            // 提取NAL单元并合并到结果
            List<byte[]> nalList = extractNalFromPayload(pesPayload);
            nalResult.addAll(nalList);
        }

        return nalResult;
    }

    /**
     * 解析Pack Header长度，区分MPEG1/MPEG2
     */
    private int parsePackHeaderLen(byte[] data, int offset, int totalLen) {
        if (offset >= totalLen) return 0;
        byte flag = data[offset];
        // MPEG2 PackHeader固定10字节，MPEG1固定8字节
        return (flag & 0xC0) == 0x40 ? 10 : 8;
    }

    /**
     * 解析PES头部占用字节长度
     */
    private int parsePesHeaderLen(byte[] data, int offset, int totalLen) {
        if (offset + 3 > totalLen) return 0;
        // PES头部固定3字节标志位 + 可变扩展长度
        int extLen = data[offset + 2] & 0xFF;
        return 3 + extLen;
    }

    /**
     * 从PES负载提取完整NAL，自动拼接分片缓存，解决跨包起始码断裂
     */
    private List<byte[]> extractNalFromPayload(byte[] payload) {
        List<byte[]> nalUnits = new ArrayList<>(8);
        if (payload.length == 0) return nalUnits;

        // 写入分片缓存，拼接上一包残留片段
        fragmentBuffer.write(payload, 0, payload.length);
        // 缓存超限自动清空，防止内存堆积
        if (fragmentBuffer.size() > BUFFER_MAX_CAPACITY) {
            log.warn("[PS解复用] 分片缓存超限自动清空，可能丢帧");
            fragmentBuffer.reset();
            fragmentBuffer.write(payload, 0, payload.length);
        }

        byte[] combinedData = fragmentBuffer.toByteArray();
        int dataLen = combinedData.length;
        int ptr = 0;
        int lastNalStart = -1;

        while (ptr <= dataLen - 4) {
            int fourByteCode = ((combinedData[ptr] & 0xFF) << 24)
                    | ((combinedData[ptr + 1] & 0xFF) << 16)
                    | ((combinedData[ptr + 2] & 0xFF) << 8)
                    | (combinedData[ptr + 3] & 0xFF);
            int threeByteCode = ((combinedData[ptr] & 0xFF) << 16)
                    | ((combinedData[ptr + 1] & 0xFF) << 8)
                    | (combinedData[ptr + 2] & 0xFF);

            int codeSkipLen = 0;
            boolean hitStartCode = false;

            // 4字节起始码 00000001
            if (fourByteCode == START_CODE_4BYTE) {
                hitStartCode = true;
                codeSkipLen = 4;
            }
            // 3字节起始码 000001
            else if (threeByteCode == START_CODE_3BYTE) {
                hitStartCode = true;
                codeSkipLen = 3;
            }

            if (hitStartCode) {
                // 存在上一段完整NAL，保存
                if (lastNalStart != -1) {
                    int nalByteLen = ptr - lastNalStart;
                    byte[] nal = new byte[nalByteLen];
                    System.arraycopy(combinedData, lastNalStart, nal, 0, nalByteLen);
                    nalUnits.add(nal);
                }
                // 新NAL起始位置
                lastNalStart = ptr + codeSkipLen;
                ptr = lastNalStart;
            } else {
                ptr++;
            }
        }

        // 处理尾部未结束的分片：重新写入缓存，等待下一包拼接
        if (lastNalStart == -1) {
            // 无任何起始码，整段保留在缓存
            return nalUnits;
        } else if (lastNalStart < dataLen) {
            // 截断缓存，只保留未完成的分片
            fragmentBuffer.reset();
            fragmentBuffer.write(combinedData, lastNalStart, dataLen - lastNalStart);
        } else {
            // 所有数据都已组成完整NAL，清空缓存
            fragmentBuffer.reset();
        }

        // 调试日志仅在debug级别输出，线上默认关闭
        if (log.isDebugEnabled() && nalUnits.isEmpty()) {
            int printLen = Math.min(LOG_HEX_LIMIT, payload.length);
            StringBuilder hexSb = new StringBuilder(printLen * 3);
            for (int i = 0; i < printLen; i++) {
                hexSb.append(String.format("%02X ", payload[i] & 0xFF));
            }
            log.debug("[PS解复用] PES负载未解析出NAL，前{}字节:{}", printLen, hexSb.toString().trim());
        }
        return nalUnits;
    }

    /**
     * 重置解复用器缓存，切换设备/流时必须调用
     */
    public void reset() {
        fragmentBuffer.reset();
    }
}