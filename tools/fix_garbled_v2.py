#!/usr/bin/env python3
"""
修复 thinglinks 中文乱码。
原理：原始 UTF-8 中文被错误地以 Latin-1 解码后重新编码为 UTF-8。
修复方法：当前 UTF-8 字节 → Latin-1 字符串 → Latin-1 字节 → UTF-8 解码
"""
import os, sys

BASE = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform"

def fix_file(path):
    with open(path, "rb") as f:
        raw = f.read()
    
    # 跳过 BOM
    if raw.startswith(b"\xef\xbb\xbf"):
        raw = raw[3:]
    
    try:
        # Step 1: 当前字节按 UTF-8 解码
        text = raw.decode("utf-8")
    except UnicodeDecodeError:
        return False
    
    # Step 2: 检测是否包含乱码（大量 Latin-1 补充字符）
    garbled_count = sum(1 for c in text if '\u0080' <= c <= '\u00ff')
    if garbled_count < 10:
        return False
    
    try:
        # Step 3: 将乱码字符串按 Latin-1 编码回字节
        latin1_bytes = text.encode("latin-1")
        # Step 4: 按 UTF-8 解码（还原中文）
        fixed = latin1_bytes.decode("utf-8")
    except (UnicodeEncodeError, UnicodeDecodeError):
        return False
    
    # Step 5: 验证修复后中文比例
    chinese_count = sum(1 for c in fixed if '\u4e00' <= c <= '\u9fff')
    if chinese_count < 3:
        return False
    
    with open(path, "w", encoding="utf-8") as f:
        f.write(fixed)
    return True

count = 0
for root, dirs, files in os.walk(BASE):
    dirs[:] = [d for d in dirs if d != "target"]
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        if fix_file(path):
            count += 1
            print(f"Fixed: {os.path.relpath(path, BASE)}")

print(f"\nTotal fixed: {count}")
