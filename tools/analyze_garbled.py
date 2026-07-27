#!/usr/bin/env python3
"""分析乱码字节的精确编码"""
import os

path = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot\ota\service\statemachine\strategy\executor\impl\DeviceVersionFilterStrategyImpl.java"

with open(path, "rb") as f:
    raw = f.read()

# 找注释块
idx = raw.find(b"/**")
end = raw.find(b"*/", idx)
chunk = raw[idx:end+2]

# 逐字节分析
print("Byte analysis of Javadoc:")
for i in range(0, len(chunk)):
    b = chunk[i]
    if b >= 0x80:
        # 检查是否是 UTF-8 多字节序列
        if b >= 0xE0 and b <= 0xEF and i+2 < len(chunk):
            seq = chunk[i:i+3]
            try:
                ch = seq.decode("utf-8")
                cp = ord(ch)
                name = f"U+{cp:04X}"
                if 0x4E00 <= cp <= 0x9FFF:
                    name += " (CJK)"
                elif 0xE000 <= cp <= 0xF8FF:
                    name += " (PUA)"
                print(f"  [{i:4d}] 3-byte UTF-8: {seq.hex()} → {ch} {name}")
                i += 2
                continue
            except:
                pass
        print(f"  [{i:4d}] SINGLE: 0x{b:02x}")
