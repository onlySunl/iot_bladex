#!/usr/bin/env python3
"""Apply essential fixes using Python only (no PowerShell)"""
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
            
            # Remove DS import
            content = content.replace('import com.baomidou.dynamic.datasource.annotation.DS;\n', '')
            
            # Remove empty imports
            content = re.sub(r'^import\s+;\s*\n', '', content, flags=re.MULTILINE)
            
            # Remove broken imports (import .Something;)
            content = re.sub(r'^import\s+\.\w+;\s*\n', '', content, flags=re.MULTILINE)
            
            # Remove remaining com.mqttsnet imports
            content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
            
            # Clean up
            content = re.sub(r'\n{3,}', '\n\n', content)
            
            if content != original:
                with open(filepath, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                count += 1

print(f"Fixed {count} files")
