package org.springblade.common.databridge.spi;
import org.springblade.common.databridge.model.SendResult;
import org.springblade.common.databridge.model.ConnectorPayload;
public interface Sink {
    SendResult send(ConnectorPayload payload);
}
