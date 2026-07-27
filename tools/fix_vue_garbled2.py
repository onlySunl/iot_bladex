#!/usr/bin/env python3
"""二进制方式修复 Vue 模板中因乱码导致引号丢失的属性"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_web_v1.0\src"

count = 0
for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".vue"):
            continue
        path = os.path.join(root, f)
        with open(path, "rb") as fh:
            data = fh.read()
        
        if data.startswith(b"\xef\xbb\xbf"):
            data = data[3:]
        
        original = data
        
        # 修复模式：placeholder="...乱码字节...\r\n → placeholder="search"\r\n
        # 匹配 placeholder=" 后包含非ASCII字节且以 \r\n 结尾（缺少闭合引号）
        data = re.sub(
            rb'placeholder="[^"\r\n]*[\x80-\xff]+[^"\r\n]*\r?\n',
            b'placeholder="search"\r\n',
            data
        )
        data = re.sub(
            rb'content="[^"\r\n]*[\x80-\xff]+[^"\r\n]*\r?\n',
            b'content="tooltip"\r\n',
            data
        )
        data = re.sub(
            rb'title="[^"\r\n]*[\x80-\xff]+[^"\r\n]*\r?\n',
            b'title="title"\r\n',
            data
        )
        data = re.sub(
            rb'label="[^"\r\n]*[\x80-\xff]+[^"\r\n]*\r?\n',
            b'label="label"\r\n',
            data
        )
        data = re.sub(
            rb'message="[^"\r\n]*[\x80-\xff]+[^"\r\n]*\r?\n',
            b'message="message"\r\n',
            data
        )
        
        if data != original:
            with open(path, "wb") as fh:
                fh.write(data)
            print(f"Fixed: {os.path.basename(path)}")
            count += 1

print(f"\nTotal fixed: {count}")
