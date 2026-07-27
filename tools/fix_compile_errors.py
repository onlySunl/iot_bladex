#!/usr/bin/env python3
"""批量修复 iot-link-biz：BOM + 未结束的字符串文字（中文乱码导致换行符被吞）"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java"

# 需要从 thinglinks 源重新复制的文件（乱码严重）
RECOPY_FILES = {
    "device/service/impl/DeviceSyncInnerServiceImpl.java": "device/service/impl/DeviceSyncInnerServiceImpl.java",
    "productversion/draft/ProductSnapshotMaintainer.java": "productversion/draft/ProductSnapshotMaintainer.java",
    "cache/helper/LinkCacheDataHelper.java": "cache/helper/LinkCacheDataHelper.java",
    "ota/service/statemachine/context/OtaUpgradeContext.java": "ota/service/statemachine/context/OtaUpgradeContext.java",
}

SRC_BASE = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"

# 包名替换
REPLACEMENTS = [
    ("com.mqttsnet.thinglinks", "org.springblade.modules.iot"),
    ("com.mqttsnet.basic.utils", "org.springblade.common.utils"),
    ("com.mqttsnet.basic.exception", "org.springblade.core.log.exception"),
    ("com.mqttsnet.basic.context", "org.springblade.modules.iot.common.context"),
    ("com.mqttsnet.basic.cache", "org.springblade.common.cache"),
    ("import com.baomidou.dynamic.datasource.annotation.DS;", ""),
    ("@DS(", "// @DS("),
]

count = 0
for rel_src, rel_dst in RECOPY_FILES.items():
    src = os.path.join(SRC_BASE, rel_src)
    dst = os.path.join(BASE, rel_dst)
    
    if not os.path.exists(src):
        print(f"NOT FOUND: {src}")
        continue
    
    with open(src, "rb") as f:
        raw = f.read()
    if raw.startswith(b"\xef\xbb\xbf"):
        raw = raw[3:]
    
    text = raw.decode("utf-8", errors="replace")
    
    for old, new in REPLACEMENTS:
        text = text.replace(old, new)
    
    # 移除 @DS 注解行
    text = re.sub(r'\s*// @DS\([^)]+\)\s*\n', '\n', text)
    
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    with open(dst, "w", encoding="utf-8") as f:
        f.write(text)
    print(f"Recopied: {os.path.basename(dst)}")
    count += 1

# 清除所有 BOM
for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, "rb") as fh:
            raw = fh.read()
        if raw.startswith(b"\xef\xbb\xbf"):
            with open(path, "wb") as fh:
                fh.write(raw[3:])
            print(f"BOM removed: {f}")
            count += 1

print(f"\nTotal fixed: {count}")
