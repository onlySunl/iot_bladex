package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.Pointer;

/**
 * 报警消息处理结构
 */
public class CFG_ALARM_MSG_HANDLE extends SdkStructure {
    /**
     * 录像(定时录像、自定义录像)
     */
    public int[] abRecord = new int[16];
    /**
     * 录像通道是否有效
     */
    public int[] bRecord = new int[16];
    /**
     * 报警上传
     */
    public int abAlarmUpload;
    /**
     * 报警上传是否有效
     */
    public int bAlarmUpload;
    /**
     * 报警通道
     */
    public int[] abChannel = new int[16];
    /**
     * 报警通道是否有效
     */
    public int[] bChannel = new int[16];
    /**
     * 联动云台
     */
    public int[] abPtzLink = new int[16];
    /**
     * 联动云台是否有效
     */
    public int[] bPtzLink = new int[16];
    /**
     * 发送邮件
     */
    public int abMail;
    /**
     * 发送邮件是否有效
     */
    public int bMail;
    /**
     * 图片上传
     */
    public int abSnapshot;
    /**
     * 图片上传是否有效
     */
    public int bSnapshot;
    /**
     * 联动蜂鸣器
     */
    public int abBuzzer;
    /**
     * 联动蜂鸣器是否有效
     */
    public int bBuzzer;
    /**
     * 联动语音
     */
    public int abVoice;
    /**
     * 联动语音是否有效
     */
    public int bVoice;
    /**
     * 联动上传FTP
     */
    public int abFtpUpload;
    /**
     * 联动上传FTP是否有效
     */
    public int bFtpUpload;
    /**
     * 联动消息中心
     */
    public int abMessageCenter;
    /**
     * 联动消息中心是否有效
     */
    public int bMessageCenter;
    /**
     * 联动报警输出
     */
    public int[] abAlarmOut = new int[4];
    /**
     * 联动报警输出是否有效
     */
    public int[] bAlarmOut = new int[4];
    /**
     * 联动云图
     */
    public int abMatrixRestart;
    /**
     * 联动云图是否有效
     */
    public int bMatrixRestart;
    /**
     * 联动短信
     */
    public int abSms;
    /**
     * 联动短信是否有效
     */
    public int bSms;
    /**
     * 联动电话
     */
    public int abDial;
    /**
     * 联动电话是否有效
     */
    public int bDial;
    /**
     * 自动抓拍
     */
    public int abAutoSnapshot;
    /**
     * 自动抓拍是否有效
     */
    public int bAutoSnapshot;
    /**
     * 录像(扩展)
     */
    public int abRecordEx;
    /**
     * 录像扩展是否有效
     */
    public int bRecordEx;
    /**
     * 本地报警输出扩展
     */
    public int abLocalAlarm;
    /**
     * 本地报警输出扩展是否有效
     */
    public int bLocalAlarm;
    /**
     * 报警解码器输出
     */
    public int abAlarmDec;
    /**
     * 报警解码器输出是否有效
     */
    public int bAlarmDec;

    public CFG_ALARM_MSG_HANDLE() {
    }
}
