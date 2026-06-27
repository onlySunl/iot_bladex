package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import org.dom4j.Element;

import javax.sip.RequestEvent;
import java.io.Serializable;

public class SipMsgInfo implements Serializable {
    private RequestEvent evt;
    private Device device;
    private Element rootElement;

    public SipMsgInfo(RequestEvent evt, Device device, Element rootElement) {
        this.evt = evt;
        this.device = device;
        this.rootElement = rootElement;
    }

    public RequestEvent getEvt() {
        return evt;
    }

    public void setEvt(RequestEvent evt) {
        this.evt = evt;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Element getRootElement() {
        return rootElement;
    }

    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }
}
