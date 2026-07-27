#!/usr/bin/env python3
"""
Step 1: Migrate essential common files from thinglinks-public/thinglinks-common
Step 2: Apply comprehensive fixes to all migrated files
"""
import os, re, shutil

SRC_COMMON = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-public\thinglinks-common\src\main\java\com\mqttsnet\thinglinks\common"
DST_COMMON = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot\common"

# Only migrate files that link module actually references
NEEDED_FILES = [
    "constant/DsConstant.java",
    "constant/CommonIotConstants.java",
    "constant/QrcodeConstant.java",
    "constant/JobConstant.java",
    "constant/DefValConstants.java",
    "lock/link/LinkLockKeyBuilder.java",
    "lock/LockKeyTable.java",
    "enums/DeviceActionTypeEnum.java",
    "cache/link/device/DeviceCacheKeyBuilder.java",
    "cache/link/device/DeviceAclRuleCacheKeyBuilder.java",
    "cache/link/product/ProductCacheKeyBuilder.java",
    "cache/link/product/ProductModelCacheKeyBuilder.java",
    "cache/link/product/ProductModelSuperTableCacheKeyBuilder.java",
    "cache/link/ota/OtaTaskExecutorOffsetCacheKeyBuilder.java",
    "cache/link/ota/OtaUpgradeRecordsCacheKeyBuilder.java",
    "cache/link/collectionpool/DeviceActionCollectionPoolCacheKeyBuilder.java",
    "cache/link/counter/UpLinkDataCounterCacheKeyBuilder.java",
    "cache/link/counter/DownLinkDataCounterCacheKeyBuilder.java",
    "cache/CacheKeyModular.java",
    "cache/CacheKeyTable.java",
]

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"

def migrate_common_file(rel_path):
    src = os.path.join(SRC_COMMON, rel_path)
    dst = os.path.join(DST_COMMON, rel_path)
    
    if not os.path.exists(src):
        print(f"  SKIP (not found): {rel_path}")
        return
    
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    
    with open(src, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Replace package
    content = content.replace("package com.mqttsnet.thinglinks.common", "package org.springblade.modules.iot.common")
    
    # Replace imports
    content = content.replace("com.mqttsnet.thinglinks.common", "org.springblade.modules.iot.common")
    content = content.replace("com.mqttsnet.basic.cache", "org.springblade.common.cache")
    content = content.replace("com.mqttsnet.basic.utils", "org.springblade.core.tool.utils")
    content = content.replace("com.mqttsnet.basic.context", "")
    
    # Remove broken imports
    content = re.sub(r'^import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    with open(dst, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"  OK: {rel_path}")

def main():
    print("=== Migrating common files ===")
    for f in NEEDED_FILES:
        migrate_common_file(f)
    
    print("\nDone!")

if __name__ == "__main__":
    main()
