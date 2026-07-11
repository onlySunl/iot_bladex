package org.springblade.modules.nvr.bean;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
