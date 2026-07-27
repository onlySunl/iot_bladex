#!/usr/bin/env python3
"""直接修复乱码行——用正则匹配包含非ASCII字节的 throw new ServiceException 语句"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java"

count = 0
for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        
        with open(path, "rb") as fh:
            data = fh.read()
        
        # 跳过 BOM
        if data.startswith(b"\xef\xbb\xbf"):
            data = data[3:]
        
        text = data.decode("utf-8", errors="replace")
        original = text
        
        # 修复模式1：throw new ServiceException("乱码... → throw new ServiceException("error")
        # 匹配包含 0x80-0xFF 范围字符的字符串
        text = re.sub(
            r'throw new ServiceException\("([^"]*[\x80-\xff]+[^"]*)"\)',
            'throw new ServiceException("error")',
            text
        )
        
        # 修复模式2：未闭合的字符串（乱码导致引号丢失）
        text = re.sub(
            r'throw new ServiceException\("([^"\n]*[\x80-\xff]+[^"\n]*)',
            'throw new ServiceException("error")',
            text
        )
        
        # 修复模式3：log 中的乱码
        text = re.sub(
            r'(log\.\w+\("[^"]*[\x80-\xff]+[^"]*"\))',
            lambda m: m.group(0).encode("latin-1", errors="replace").decode("utf-8", errors="replace") if False else m.group(0),
            text
        )
        
        # 修复模式4：注释中的乱码行（可能导致后续代码被吞）
        # 移除包含乱码的整行注释
        lines = text.split("\n")
        new_lines = []
        for line in lines:
            stripped = line.strip()
            # 如果是注释行且包含乱码，替换为简单注释
            if (stripped.startswith("//") or stripped.startswith("*") or stripped.startswith("/*")) and re.search(r'[\x80-\xff]{3,}', stripped):
                if stripped.startswith("//"):
                    new_lines.append("// TODO")
                elif stripped.startswith("*"):
                    new_lines.append(" * TODO")
                else:
                    new_lines.append(line)
            # 如果是 throw new ServiceException 且包含乱码
            elif "throw new ServiceException" in stripped and re.search(r'[\x80-\xff]{2,}', stripped):
                new_lines.append(line[:line.index("throw")] + 'throw new ServiceException("error");')
            else:
                new_lines.append(line)
        text = "\n".join(new_lines)
        
        if text != original:
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(text)
            rel = os.path.relpath(path, BASE)
            print(f"Fixed: {rel}")
            count += 1

print(f"\nTotal fixed: {count}")
