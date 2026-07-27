#!/usr/bin/env python3
"""
自动修复 thinglinks 源码中文乱码。
原理：thinglinks 源文件本身是 UTF-8，但在复制过程中被错误地以 Latin-1/GBK 解码后重新编码。
这里尝试将当前内容按 Latin-1 编码回字节，再按 UTF-8 解码来还原中文。
"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform"

def fix_mojibake(text):
    """尝试修复 Latin-1 误读 UTF-8 中文的乱码"""
    try:
        # 将当前字符串按 Latin-1 编码回字节
        raw = text.encode("latin-1")
        # 再按 UTF-8 解码
        return raw.decode("utf-8")
    except (UnicodeEncodeError, UnicodeDecodeError):
        return text

def has_mojibake(text):
    """检测是否包含乱码特征"""
    # 乱码特征：包含大量 Latin-1 补充字符（如 Ã © 等）
    count = sum(1 for c in text if '\u0080' <= c <= '\u00ff')
    return count > 10

count = 0
fixed_files = 0

for root, dirs, files in os.walk(BASE):
    # 跳过 target 目录
    dirs[:] = [d for d in dirs if d != "target"]
    
    for f in files:
        if not f.endswith(".java"):
            continue
        
        path = os.path.join(root, f)
        
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            content = fh.read()
        
        if not has_mojibake(content):
            continue
        
        # 尝试修复
        fixed = fix_mojibake(content)
        
        if fixed != content:
            # 验证修复后是否包含合法中文
            if has_mojibake(fixed):
                # 仍然有乱码，尝试只修复注释部分
                # 分割为行处理
                lines = content.split("\n")
                new_lines = []
                for line in lines:
                    if has_mojibake(line):
                        fixed_line = fix_mojibake(line)
                        # 检查修复后是否包含非ASCII字符（中文等）
                        if any('\u4e00' <= c <= '\u9fff' for c in fixed_line):
                            new_lines.append(fixed_line)
                        else:
                            new_lines.append(line)
                    else:
                        new_lines.append(line)
                fixed = "\n".join(new_lines)
            
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(fixed)
            fixed_files += 1
            print(f"Fixed: {os.path.relpath(path, BASE)}")
        
        count += 1

print(f"\nScanned: {count} files with potential issues, Fixed: {fixed_files}")
