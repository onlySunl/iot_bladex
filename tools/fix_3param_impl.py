#!/usr/bin/env python3
"""修复三个泛型参数的 BaseServiceImpl → BladeServiceImpl<Mapper, Entity>"""
import os, re

DST = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link"

fixed = 0
for root, dirs, files in os.walk(DST):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            text = fh.read()
        
        original = text
        
        # extends BaseServiceImpl<XxxManager, Long, XxxEntity> → extends BladeServiceImpl<XxxMapper, XxxEntity>
        # 例: extends BaseServiceImpl<DeviceAclRuleManager, Long, DeviceAclRule>
        #   → extends BladeServiceImpl<DeviceAclRuleMapper, DeviceAclRule>
        pattern = r'extends\s+BaseServiceImpl<(\w+)Manager,\s*Long,\s*(\w+)>'
        replacement = r'extends BladeServiceImpl<\1Mapper, \2>'
        text = re.sub(pattern, replacement, text)
        
        # 修复 import
        if "import org.springblade.core.mp.base.BaseServiceImpl;" in text:
            text = text.replace(
                "import org.springblade.core.mp.base.BaseServiceImpl;",
                "import org.springblade.core.mp.service.impl.BladeServiceImpl;")
        
        if text != original:
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(text)
            fixed += 1
            print(f"  {f}: {re.search(pattern, original).group(0) if re.search(pattern, original) else 'N/A'} → {re.search(pattern, text).group(0) if re.search(pattern, text) else 'N/A'}")

print(f"\nFixed: {fixed} files")
