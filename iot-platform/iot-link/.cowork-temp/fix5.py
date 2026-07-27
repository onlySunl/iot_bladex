#!/usr/bin/env python3
"""Fix imports placed before package declaration"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Move any import before package to after package
    # Pattern: import ... \n package ...
    match = re.search(r'^(import\s+[^;]+;\s*\n)+package\s', content, re.MULTILINE)
    if match:
        # Extract the imports before package
        before_pkg = content[:match.start()]
        imports_before = re.findall(r'^import\s+[^;]+;\s*$', before_pkg, re.MULTILINE)
        
        # Remove them from before package
        for imp in imports_before:
            content = content.replace(imp + '\n', '', 1)
        
        # Add them after package line
        pkg_line_end = content.index('\n', content.index('package ')) + 1
        for imp in reversed(imports_before):
            content = content[:pkg_line_end] + imp + '\n' + content[pkg_line_end:]
    
    # Fix: remove empty lines between import and package (caused by removal)
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
