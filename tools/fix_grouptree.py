#!/usr/bin/env python3
"""修复 GroupTree.vue 特定行"""
path = r"D:\workspace\IOT\iot_bladex_web_v1.0\src\views\nvr\components\common\GroupTree.vue"
with open(path, "rb") as f:
    data = f.read()

# 第14行: label="label"index-checkbox" → label="label" class="index-checkbox"
old1 = b'label="label"index-checkbox"'
new1 = b'label="label" class="index-checkbox"'
if old1 in data:
    data = data.replace(old1, new1)
    print("Fixed line 14")

# 第18行: title="title"!searchStr" → title="refresh" />
old2 = b'title="title"!searchStr"'
new2 = b'title="refresh" />'
if old2 in data:
    data = data.replace(old2, new2)
    print("Fixed line 18")

with open(path, "wb") as f:
    f.write(data)
print("Done")
