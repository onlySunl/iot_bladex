#!/usr/bin/env python3
"""Fix BaseServiceImpl<XxxManager, ...> → BaseServiceImpl<XxxMapper, ...>"""
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
            
            # Fix: BaseServiceImpl<XxxManager, Long, Entity> → BaseServiceImpl<XxxMapper, Entity>
            content = re.sub(
                r'extends\s+BaseServiceImpl\s*<\s*(\w+)Manager\s*,\s*Long\s*,\s*(\w+)\s*>',
                r'extends BaseServiceImpl<\1Mapper, \2>',
                content
            )
            # Fix: BaseServiceImpl<XxxManager, Entity> → BaseServiceImpl<XxxMapper, Entity>  
            content = re.sub(
                r'extends\s+BaseServiceImpl\s*<\s*(\w+)Manager\s*,\s*(\w+)\s*>',
                r'extends BaseServiceImpl<\1Mapper, \2>',
                content
            )
            
            if content != original:
                with open(filepath, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                count += 1

print(f"Fixed {count} files")
