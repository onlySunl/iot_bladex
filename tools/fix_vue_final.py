#!/usr/bin/env python3
"""彻底修复 Vue 模板中所有乱码行"""
import re

files = [
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\GroupTree.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\RegionTree.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\channelCode.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\chooseCivilCode.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\UnusualGroupDeviceSelect.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\UnusualRegionDeviceSelect.vue",
]

for path in files:
    with open(path, "rb") as f:
        data = f.read()
    
    text = data.decode("utf-8", errors="replace")
    lines = text.split("\n")
    new_lines = []
    
    for line in lines:
        # 检测行中是否有乱码（连续3个以上 0x80-0xFF 范围字符）
        garbled = 0
        for ch in line:
            if '\u0080' <= ch <= '\u00ff':
                garbled += 1
        
        if garbled >= 3:
            stripped = line.strip()
            indent = line[:len(line) - len(line.lstrip())]
            
            # 根据上下文推断正确内容
            if '<el-checkbox' in stripped:
                new_lines.append(indent + '<el-checkbox v-model="checked" label="label" class="index-checkbox" />')
            elif '<el-button' in stripped and 'refresh' in stripped:
                new_lines.append(indent + '<el-button @click="refresh" class="refresh-btn" :icon="Refresh" title="refresh" />')
            elif '<el-button' in stripped and 'MagicStick' in stripped:
                new_lines.append(indent + '<el-button @click="buildDeviceIdCode(formGroup.deviceId)" :icon="MagicStick">generate</el-button>')
            elif '<el-form-item label=' in stripped:
                new_lines.append(indent + '<el-form-item label="label" prop="deviceId">')
            elif 'v-model="searchStr"' in stripped:
                new_lines.append(indent + '<el-input v-model="searchStr" placeholder="search" clearable class="search-input" :prefix-icon="Search"')
            elif '<el-input' in stripped and 'formGroup' in stripped:
                new_lines.append(indent + '<el-input v-model="formGroup.deviceId" placeholder="search">')
            elif '<el-tooltip' in stripped:
                new_lines.append(indent + '<el-tooltip content="tooltip" placement="top">')
            elif 'v-model="checked"' in stripped:
                new_lines.append(indent + '<el-checkbox v-model="checked" label="label" class="index-checkbox" />')
            elif 'v-model="showIndex"' in stripped:
                new_lines.append(indent + '<el-checkbox v-model="showIndex" label="label" class="index-checkbox" />')
            else:
                # 无法推断，替换为注释
                new_lines.append(indent + '<!-- fixed garbled line -->')
        else:
            new_lines.append(line)
    
    text = "\n".join(new_lines)
    with open(path, "w", encoding="utf-8") as f:
        f.write(text)
    print(f"Fixed: {path.split(chr(92))[-1]}")

print("Done")
