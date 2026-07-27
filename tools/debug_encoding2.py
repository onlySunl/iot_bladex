#!/usr/bin/env python3
"""查看文件开头的注释字节"""
import os

path = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot\ota\service\statemachine\strategy\executor\impl\DeviceVersionFilterStrategyImpl.java"

with open(path, "rb") as f:
    raw = f.read()

# 找第一个 Javadoc 注释
idx = raw.find(b"/**")
if idx >= 0:
    end = raw.find(b"*/", idx)
    chunk = raw[idx:end+2]
    print(f"Javadoc bytes ({len(chunk)} bytes):")
    print(chunk[:200].hex())
    print()
    # 尝试解码
    try:
        print("UTF-8:", chunk.decode("utf-8"))
    except Exception as e:
        print("UTF-8 error:", e)
