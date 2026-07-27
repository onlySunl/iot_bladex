#!/usr/bin/env python3
"""Fix Builder.of → new Xxx() in ALL files"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

count = 0
for root_dir in [DST_BIZ, DST_CTRL]:
    for root, dirs, files in os.walk(root_dir):
        for f in files:
            if not f.endswith('.java'):
                continue
            filepath = os.path.join(root, f)
            with open(filepath, 'r', encoding='utf-8') as fh:
                content = fh.read()
            original = content
            
            # Fix Builder.of(Xxx::new) → new Xxx()
            content = re.sub(r'Builder\.of\((\w+)::new\)', r'new \1()', content)
            # Fix Builder references
            content = re.sub(r'^import\s+\S+\.Builder;\s*\n', '', content, flags=re.MULTILINE)
            
            if content != original:
                with open(filepath, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                count += 1

print(f"Fixed {count} files")
