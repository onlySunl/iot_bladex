#!/usr/bin/env python3
"""修复 Vue SFC 中 <script setup name="xxx" lang="ts"> 的兼容性问题
改为 <script setup lang="ts"> + <script>export default { name: 'xxx' }</script>
"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_web_v1.0\src"

count = 0
for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".vue"):
            continue
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            content = fh.read()
        
        original = content
        
        # 模式: <script setup name="Xxx" lang="ts">
        m = re.search(r'<script setup name="([^"]+)" lang="ts">', content)
        if m:
            name = m.group(1)
            content = content.replace(
                f'<script setup name="{name}" lang="ts">',
                f'<script setup lang="ts">'
            )
            # 在 </script> 后添加 name 定义
            content = content.replace(
                '</script>',
                f'</script>\n<script>export default {{ name: "{name}" }};</script>',
                1  # 只替换第一个
            )
            print(f"Fixed: {os.path.basename(path)} (name={name})")
            count += 1
        
        if content != original:
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(content)

print(f"\nTotal fixed: {count}")
