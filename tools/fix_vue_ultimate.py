#!/usr/bin/env python3
"""终极修复：扫描所有 Vue 文件，移除任何包含乱码字节的行"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr"

count = 0
for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".vue"):
            continue
        path = os.path.join(root, f)
        with open(path, "rb") as fh:
            data = fh.read()
        
        text = data.decode("utf-8", errors="replace")
        lines = text.split("\n")
        new_lines = []
        changed = False
        
        for line in lines:
            # 检测乱码：行中是否有 0x80-0xFF 范围的字符（排除合法的 UTF-8 多字节序列的 continuation bytes）
            has_garbled = False
            i = 0
            while i < len(line):
                ch = line[i]
                cp = ord(ch)
                if cp >= 0x80:
                    if 0xC0 <= cp <= 0xDF and i+1 < len(line):
                        i += 2  # 2-byte UTF-8
                        continue
                    elif 0xE0 <= cp <= 0xEF and i+2 < len(line):
                        i += 3  # 3-byte UTF-8 (CJK)
                        continue
                    elif 0xF0 <= cp <= 0xF7 and i+3 < len(line):
                        i += 4  # 4-byte UTF-8
                        continue
                    else:
                        # 孤立的 continuation byte 或非法字节
                        has_garbled = True
                        break
                i += 1
            
            if has_garbled:
                stripped = line.strip()
                indent = line[:len(line) - len(line.lstrip())]
                # 根据内容替换
                if '<el-' in stripped or '</el-' in stripped or '<div' in stripped or '<template' in stripped or '<span' in stripped:
                    # 保留标签结构，替换属性值
                    cleaned = re.sub(r'"[^"]*[\x80-\xff]+[^"]*"', '"fixed"', line)
                    cleaned = re.sub(r"'[^']*[\x80-\xff]+[^']*'", "'fixed'", cleaned)
                    new_lines.append(cleaned)
                else:
                    new_lines.append(indent + "<!-- garbled -->")
                changed = True
            else:
                new_lines.append(line)
        
        if changed:
            text = "\n".join(new_lines)
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(text)
            print(f"Fixed: {os.path.relpath(path, BASE)}")
            count += 1

print(f"\nTotal fixed: {count}")
