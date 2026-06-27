package org.springblade.modules.iot.domain.gb28181;

import org.springblade.modules.iot.domain.gb28181.domain.Device;
import org.dom4j.Element;

import javax.sip.RequestEvent;
import java.io.Serializable;

/**
 * @author lin
 */
public class HandlerCatchData implements Serializable {
    private RequestEvent evt;
    private Device device;
    private Element rootElement;

    public HandlerCatchData(RequestEvent evt, Device device, Element rootElement) {
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
