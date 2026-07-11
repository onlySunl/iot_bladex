package org.springblade.modules.nvr.service;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
