#!/usr/bin/env python3
"""Fix imports placed before package declaration"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Check if first line is import (not package)
    lines = content.split('\n')
    imports_to_move = []
    pkg_idx = -1
    
    for i, line in enumerate(lines):
        if line.startswith('package '):
            pkg_idx = i
            break
        if line.startswith('import '):
            imports_to_move.append(line)
    
    if imports_to_move and pkg_idx > 0:
        # Remove imports from before package
        new_lines = []
        for i, line in enumerate(lines):
            if i < pkg_idx and line.startswith('import '):
                continue
            new_lines.append(line)
            if i == pkg_idx:
                # Add imports after package
                for imp in imports_to_move:
                    new_lines.append(imp)
        
        content = '\n'.join(new_lines)
    
    # Clean up
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    if content != original:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    count = 0
    for root_dir in [DST_BIZ, DST_CTRL]:
        for root, dirs, files in os.walk(root_dir):
            for f in files:
                if f.endswith(".java"):
                    filepath = os.path.join(root, f)
                    if fix_file(filepath):
                        count += 1
    print(f"Fixed: {count}")

if __name__ == "__main__":
    main()
