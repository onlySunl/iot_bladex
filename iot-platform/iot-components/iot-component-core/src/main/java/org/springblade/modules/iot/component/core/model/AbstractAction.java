package org.springblade.modules.iot.component.core.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author sjg
 */
@Data
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public abstract class AbstractAction implements IDeviceAction, Serializable {

    protected String id;

    protected ActionType type;

    protected String productKey;

    protected String deviceName;

    protected Long time;

    public AbstractAction() {
    }

    public AbstractAction(ActionType type) {
        this.type = type;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public ActionType getType() {
        return type;
    }

    @Override
    public void setType(ActionType type) {
        this.type = type;
    }

    @Override
    public String getProductKey() {
        return productKey;
    }

    @Override
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public Long getTime() {
        return time;
    }

    @Override
    public void setTime(Long time) {
        this.time = time;
    }
}
