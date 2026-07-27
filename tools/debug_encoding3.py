#!/usr/bin/env python3
import os, sys
sys.stdout.reconfigure(encoding="utf-8")

path = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot\ota\service\statemachine\strategy\executor\impl\DeviceVersionFilterStrategyImpl.java"

with open(path, "rb") as f:
    raw = f.read()

idx = raw.find(b"/**")
end = raw.find(b"*/", idx)
chunk = raw[idx:end+2]
text = chunk.decode("utf-8")

# 写入输出文件
out = r"D:\workspace\IOT\iot_bladex_v1.0\tools\debug_output.txt"
with open(out, "w", encoding="utf-8") as f:
    f.write(text)

print(f"Output written to {out}")
print(f"First 200 chars: {text[:200]}")
