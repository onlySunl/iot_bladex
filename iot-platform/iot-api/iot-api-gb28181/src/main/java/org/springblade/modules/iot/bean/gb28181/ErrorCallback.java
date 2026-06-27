package org.springblade.modules.iot.bean.gb28181;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
