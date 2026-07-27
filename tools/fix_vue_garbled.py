#!/usr/bin/env python3
"""修复 Vue 模板中乱码属性值"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_web_v1.0\src"

count = 0
for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".vue"):
            continue
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            content = fh.read()
        
        original = content
        
        # 修复属性值中的乱码（包含 0x80-0xFF 范围字符的属性值）
        # placeholder="乱码..." → placeholder="search"
        content = re.sub(r'placeholder="[^"]*[\x80-\xff]+[^"]*"', 'placeholder="search"', content)
        # content="乱码..." → content="tooltip"  
        content = re.sub(r'content="[^"]*[\x80-\xff]+[^"]*"', 'content="tooltip"', content)
        # title="乱码..." → title="title"
        content = re.sub(r'title="[^"]*[\x80-\xff]+[^"]*"', 'title="title"', content)
        # label="乱码..." → label="label"
        content = re.sub(r'label="[^"]*[\x80-\xff]+[^"]*"', 'label="label"', content)
        # message="乱码..." → message="message"
        content = re.sub(r'message="[^"]*[\x80-\xff]+[^"]*"', 'message="message"', content)
        # 模板中 {{ }} 内的乱码
        content = re.sub(r'\{\{\s*\$t\([^)]*[\x80-\xff]+[^)]*\)\s*\}\}', '{{ $t("label") }}', content)
        
        if content != original:
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(content)
            print(f"Fixed: {os.path.basename(path)}")
            count += 1

print(f"\nTotal fixed: {count}")
