package org.springblade.modules.iot.haikangisup.handler;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单的 MPEG PS (Program Stream) 解复用器
 * 用于从海康ISUP SDK返回的PS格式数据中提取H.264 NAL单元
 */
@Slf4j
public class PSStreamDemuxer {
    
    // MPEG PS 起始码
    private static final byte[] PACK_START_CODE = {0x00, 0x00, 0x01, (byte) 0xBA};
    private static final byte[] SYSTEM_HEADER_START_CODE = {0x00, 0x00, 0x01, (byte) 0xBB};
    private static final byte[] PES_START_CODE_PREFIX = {0x00, 0x00, 0x01};
    
    // 视频PES流ID范围: 0xE0 - 0xEF
    private static final int VIDEO_PES_START = 0xE0;
    private static final int VIDEO_PES_END = 0xEF;
    
    // 私有流1 (可能包含H.264数据)
    private static final int PRIVATE_STREAM_1 = 0xBD;
    
    private ByteArrayOutputStream nalBuffer = new ByteArrayOutputStream();
    private boolean isFirstPacket = true;
    
    /**
     * 处理PS数据块，提取H.264 NAL单元
     * @param psData PS格式的数据
     * @return 提取到的H.264 NAL单元列表
     */
    public List<byte[]> processPSData(byte[] psData) {
        List<byte[]> nalUnits = new ArrayList<>();
        
        if (psData == null || psData.length < 4) {
            return nalUnits;
        }
        
        int offset = 0;
        while (offset < psData.length - 3) {
            // 查找起始码 00 00 01
            if (psData[offset] == 0x00 && psData[offset + 1] == 0x00 && psData[offset + 2] == 0x01) {
                int streamId = psData[offset + 3] & 0xFF;
                
                // 跳过Pack Header
                if (streamId == 0xBA) {
                    offset += 4;
                    // Pack header长度可变，需要解析
                    if (offset < psData.length) {
                        int packHeaderLen = getPackHeaderLength(psData, offset);
                        offset += packHeaderLen;
                    }
                    continue;
                }
                
                // 跳过System Header
                if (streamId == 0xBB) {
                    offset += 4;
                    if (offset + 2 <= psData.length) {
                        int sysHeaderLen = ((psData[offset] & 0xFF) << 8) | (psData[offset + 1] & 0xFF);
                        offset += 2 + sysHeaderLen;
                    }
                    continue;
                }
                
                // 处理PES包
                if ((streamId >= VIDEO_PES_START && streamId <= VIDEO_PES_END) || streamId == PRIVATE_STREAM_1) {
                    offset += 4;
                    if (offset + 2 > psData.length) break;
                    
                    // PES包长度
                    int pesPacketLen = ((psData[offset] & 0xFF) << 8) | (psData[offset + 1] & 0xFF);
                    offset += 2;
                    
                    // 解析PES头
                    int pesHeaderLen = parsePESHeader(psData, offset, Math.min(pesPacketLen, psData.length - offset));
                    offset += pesHeaderLen;
                    
                    // 提取PES负载数据
                    int payloadLen = pesPacketLen - pesHeaderLen;
                    if (payloadLen > 0 && offset + payloadLen <= psData.length) {
                        byte[] payload = new byte[payloadLen];
                        System.arraycopy(psData, offset, payload, 0, payloadLen);
                        
                        // 从payload中提取NAL单元
                        List<byte[]> nals = extractNALUnits(payload);
                        nalUnits.addAll(nals);
                        
                        offset += payloadLen;
                    } else {
                        break;
                    }
                    continue;
                }
                
                // 其他起始码，跳过
                offset += 4;
            } else {
                offset++;
            }
        }
        
        return nalUnits;
    }
    
    /**
     * 获取Pack Header长度
     */
    private int getPackHeaderLength(byte[] data, int offset) {
        if (offset >= data.length) return 0;
        
        int marker = (data[offset] & 0xC0) >> 6;
        if (marker == 0x01) {
            // MPEG-2: 固定10字节
            return 10;
        } else {
            // MPEG-1: 固定8字节
            return 8;
        }
    }
    
    /**
     * 解析PES头，返回PES头长度
     */
    private int parsePESHeader(byte[] data, int offset, int maxLen) {
        if (offset + 3 > data.length || maxLen < 3) return 0;
        
        // PES头标志 (2字节)
        // byte1: 10 xx xxxx
        // byte2: x xxx x xxx (PTS/DTS标志等)
        int ptsFlag = (data[offset + 1] >> 7) & 0x01;
        int dtsFlag = (data[offset + 1] >> 6) & 0x01;
        
        // PES头数据长度
        int pesHeaderDataLen = data[offset + 2] & 0xFF;
        
        // PES头总长度 = 3 (标志+长度) + pesHeaderDataLen
        return 3 + pesHeaderDataLen;
    }
    
    /**
     * 从PES负载中提取H.264 NAL单元
     */
    private List<byte[]> extractNALUnits(byte[] payload) {
        List<byte[]> nalUnits = new ArrayList<>();
        
        if (payload == null || payload.length < 4) {
            return nalUnits;
        }
        
        // 查找H.264起始码: 00 00 00 01 或 00 00 01
        int offset = 0;
        int nalStart = -1;
        
        while (offset < payload.length - 3) {
            boolean foundStartCode = false;
            int startCodeLen = 0;
            
            // 检查4字节起始码: 00 00 00 01
            if (offset + 3 < payload.length &&
                payload[offset] == 0x00 && payload[offset + 1] == 0x00 &&
                payload[offset + 2] == 0x00 && payload[offset + 3] == 0x01) {
                foundStartCode = true;
                startCodeLen = 4;
            }
            // 检查3字节起始码: 00 00 01
            else if (payload[offset] == 0x00 && payload[offset + 1] == 0x00 && payload[offset + 2] == 0x01) {
                foundStartCode = true;
                startCodeLen = 3;
            }
            
            if (foundStartCode) {
                // 如果之前有NAL单元，保存它
                if (nalStart >= 0) {
                    int nalLen = offset - nalStart;
                    if (nalLen > 0) {
                        byte[] nal = new byte[nalLen];
                        System.arraycopy(payload, nalStart, nal, 0, nalLen);
                        nalUnits.add(nal);
                    }
                }
                
                // 新的NAL单元从起始码后开始
                nalStart = offset + startCodeLen;
                offset = nalStart;
            } else {
                offset++;
            }
        }
        
        // 处理最后一个NAL单元
        if (nalStart >= 0 && nalStart < payload.length) {
            int nalLen = payload.length - nalStart;
            if (nalLen > 0) {
                byte[] nal = new byte[nalLen];
                System.arraycopy(payload, nalStart, nal, 0, nalLen);
                nalUnits.add(nal);
            }
        }
        
        // 如果没有找到起始码，可能是裸数据或数据不完整
        // 将数据缓存，等待下一个包
        if (nalUnits.isEmpty() && payload.length > 0) {
            // 检查数据是否以00 00 00或00 00开头（可能是跨包的起始码）
            nalBuffer.write(payload, 0, payload.length);
        }
        
        return nalUnits;
    }
    
    /**
     * 重置解复用器状态
     */
    public void reset() {
        nalBuffer.reset();
        isFirstPacket = true;
    }
}
