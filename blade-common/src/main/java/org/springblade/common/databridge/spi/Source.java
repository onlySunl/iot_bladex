package org.springblade.common.databridge.spi;
import org.springblade.common.databridge.model.SourceMessage;
public interface Source {
    void start();
    void stop();
}
