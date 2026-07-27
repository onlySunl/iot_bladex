#!/usr/bin/env python3
import os

path = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot\ota\service\statemachine\strategy\executor\impl\DeviceVersionFilterStrategyImpl.java"

with open(path, "rb") as f:
    raw = f.read()

# 找第一个注释块
idx = raw.find(b"DeviceVersionFilterStrategy")
start = max(0, idx - 300)
chunk = raw[start:idx+30]

# 逐字节打印
for i, b in enumerate(chunk):
    c = chr(b) if 32 <= b < 127 else "."
    print(f"{i:4d}: 0x{b:02x} ({b:3d}) {c}")

print()
# 尝试 UTF-8 解码
try:
    decoded = chunk.decode("utf-8")
    print("UTF-8:", decoded[:200])
except Exception as e:
    print("UTF-8 error:", e)
