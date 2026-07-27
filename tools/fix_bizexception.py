#!/usr/bin/env python3
"""替换所有 BizException.wrap 为 new ServiceException"""
import os, re

DST = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link"

fixed = 0
for root, dirs, files in os.walk(DST):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            text = fh.read()
        
        if "BizException.wrap" not in text:
            continue
        
        original = text
        
        # 1. throw BizException.wrap("msg") → throw new ServiceException("msg")
        text = re.sub(
            r'throw\s+BizException\.wrap\("([^"]*)"\)',
            r'throw new ServiceException("\1")',
            text
        )
        
        # 2. throw BizException.wrap(msg) → throw new ServiceException(msg)
        text = re.sub(
            r'throw\s+BizException\.wrap\((\w+)\)',
            r'throw new ServiceException(\1)',
            text
        )
        
        # 3. BizException.wrap("msg") 在其他上下文 → new ServiceException("msg")
        text = re.sub(
            r'BizException\.wrap\("([^"]*)"\)',
            r'new ServiceException("\1")',
            text
        )
        text = re.sub(
            r'BizException\.wrap\((\w+)\)',
            r'new ServiceException(\1)',
            text
        )
        
        # 4. 移除 import com.mqttsnet.basic.exception.BizException
        text = text.replace(
            "import com.mqttsnet.basic.exception.BizException;",
            "")
        
        if text != original:
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(text)
            fixed += 1

print(f"Fixed: {fixed} files")
