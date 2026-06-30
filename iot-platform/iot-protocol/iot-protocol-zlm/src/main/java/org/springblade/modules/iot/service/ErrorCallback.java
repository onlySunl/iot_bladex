package org.springblade.modules.iot.service;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
