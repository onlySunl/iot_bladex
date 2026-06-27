package org.springblade.modules.iot.bean;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
