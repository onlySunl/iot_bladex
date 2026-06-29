package org.springblade.modules.iot.zlm;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
