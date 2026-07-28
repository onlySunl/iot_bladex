package org.springblade.modules.iot.productversion.event;

import org.springblade.modules.iot.productversion.event.source.ProductVersionLifecycleEventSource;
import org.springframework.context.ApplicationEvent;

/**
 * 产品物模型版本回滚事件。
 *
 * @author mqttsnet
 */
public class ProductVersionRolledBackEvent extends ApplicationEvent {

    public ProductVersionRolledBackEvent(ProductVersionLifecycleEventSource source) {
        super(source);
    }

    @Override
    public ProductVersionLifecycleEventSource getSource() {
        return (ProductVersionLifecycleEventSource) super.getSource();
    }
}
