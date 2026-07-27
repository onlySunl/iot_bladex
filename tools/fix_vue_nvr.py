#!/usr/bin/env python3
"""修复 GroupTree.vue 乱码"""
import re

path = r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\GroupTree.vue"
with open(path, "rb") as f:
    data = f.read()

# 修复所有包含乱码字节的属性值
data = re.sub(rb'content="[^"]*[\x80-\xff]+[^"]*"', b'content="tooltip"', data)
data = re.sub(rb'label="[^"]*[\x80-\xff]+[^"]*"', b'label="label"', data)
data = re.sub(rb'title="[^"]*[\x80-\xff]+[^"]*"', b'title="title"', data)
data = re.sub(rb'placeholder="[^"]*[\x80-\xff]+[^"]*"', b'placeholder="search"', data)

with open(path, "wb") as f:
    f.write(data)
print("Fixed GroupTree.vue")

# 同样修复其他 nvr 组件
files = [
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\channelCode.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\RegionTree.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\chooseCivilCode.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\UnusualGroupDeviceSelect.vue",
    r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\UnusualRegionDeviceSelect.vue",
]

for path in files:
    try:
        with open(path, "rb") as f:
            data = f.read()
        data = re.sub(rb'content="[^"]*[\x80-\xff]+[^"]*"', b'content="tooltip"', data)
        data = re.sub(rb'label="[^"]*[\x80-\xff]+[^"]*"', b'label="label"', data)
        data = re.sub(rb'title="[^"]*[\x80-\xff]+[^"]*"', b'title="title"', data)
        data = re.sub(rb'placeholder="[^"]*[\x80-\xff]+[^"]*"', b'placeholder="search"', data)
        with open(path, "wb") as f:
            f.write(data)
        print(f"Fixed: {path.split(chr(92))[-1]}")
    except:
        pass

print("Done")
