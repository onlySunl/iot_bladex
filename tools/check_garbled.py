#!/usr/bin/env python3
"""检测 iot-link-biz 乱码"""
import os, sys
sys.stdout.reconfigure(encoding="utf-8")

base = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java"

count = 0
for root, dirs, files in os.walk(base):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, "rb") as fh:
            raw = fh.read()
        if raw.startswith(b"\xef\xbb\xbf"):
            raw = raw[3:]
        try:
            text = raw.decode("utf-8")
        except:
            print(f"DECODE ERROR: {os.path.relpath(path, base)}")
            count += 1
            continue
        
        # 检测乱码：连续出现 0x80-0xFF 范围内的 Latin-1 字符
        garbled_count = 0
        for c in text:
            if '\u0080' <= c <= '\u00ff':
                garbled_count += 1
        
        if garbled_count > 10:
            rel = os.path.relpath(path, base)
            print(f"GARBLED ({garbled_count}): {rel}")
            count += 1

print(f"\nTotal garbled files: {count}")
