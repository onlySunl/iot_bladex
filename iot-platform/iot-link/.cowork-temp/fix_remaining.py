#!/usr/bin/env python3
"""Fix remaining package-not-found errors"""
import os, re, shutil

SRC_BIZ = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"

# 1. Copy skipped config/converter files
SKIPPED_BUT_NEEDED = [
    "productversion/converter/ProductSnapshotConverter.java",
    "ota/service/statemachine/config/OtaUpgradeStateMachineConfig.java",
]

for rel in SKIPPED_BUT_NEEDED:
    src = os.path.join(SRC_BIZ, rel)
    dst = os.path.join(DST_BIZ, rel)
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    with open(src, 'r', encoding='utf-8') as f:
        content = f.read()
    content = content.replace('com.mqttsnet.thinglinks', 'org.springblade.modules.iot')
    content = content.replace('com.mqttsnet.basic', 'org.springblade.core')
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    # Fix BaseEntity
    content = content.replace('import org.springblade.core.entity.Entity;', 'import org.springblade.core.mp.base.BaseEntity;')
    content = content.replace('import org.springblade.core.entity.BaseEntity;', 'import org.springblade.core.mp.base.BaseEntity;')
    with open(dst, 'w', encoding='utf-8') as f:
        f.write(content)
    print(f"Copied: {rel}")

# 2. Fix remaining broken imports in all files
FIXES = [
    ('import org.springblade.modules.iot.system.entity.tenant.', '// import org.springblade.modules.iot.system.entity.tenant.'),
    ('import org.springblade.modules.iot.system.facade.', '// import org.springblade.modules.iot.system.facade.'),
    ('import org.springblade.modules.iot.context.', '// import org.springblade.modules.iot.context.'),
]

count = 0
for root, dirs, files in os.walk(DST_BIZ):
    for f in files:
        if not f.endswith('.java'):
            continue
        filepath = os.path.join(root, f)
        with open(filepath, 'r', encoding='utf-8') as fh:
            content = fh.read()
        original = content
        for old, new in FIXES:
            content = content.replace(old, new)
        # Remove commented-out imports
        content = re.sub(r'^// import org\.springblade\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
        content = re.sub(r'\n{3,}', '\n\n', content)
        if content != original:
            with open(filepath, 'w', encoding='utf-8') as fh:
                fh.write(content)
            count += 1

print(f"Fixed {count} files")
