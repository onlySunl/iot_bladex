#!/usr/bin/env python3
"""Remove all manager imports and references from service impls"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"

count = 0
for root, dirs, files in os.walk(DST_BIZ):
    for f in files:
        if not f.endswith('.java'):
            continue
        filepath = os.path.join(root, f)
        with open(filepath, 'r', encoding='utf-8') as fh:
            content = fh.read()
        original = content
        
        # Remove manager imports
        content = re.sub(r'^import org\.springblade\.modules\.iot\.\w+\.manager\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
        
        # Remove manager field declarations (e.g., "private final DeviceManager deviceManager;")
        content = re.sub(r'^\s*(private|protected|public)\s+(final\s+)?\w+Manager\s+\w+Manager\s*;\s*$', '', content, flags=re.MULTILINE)
        
        # Remove manager constructor parameters
        # Pattern: "DeviceManager deviceManager," in constructor
        content = re.sub(r',\s*\w+Manager\s+\w+Manager', '', content)
        content = re.sub(r'\w+Manager\s+\w+Manager,\s*', '', content)
        
        # Clean up
        content = re.sub(r'\n{3,}', '\n\n', content)
        
        if content != original:
            with open(filepath, 'w', encoding='utf-8') as fh:
                fh.write(content)
            count += 1

print(f"Fixed {count} files")
