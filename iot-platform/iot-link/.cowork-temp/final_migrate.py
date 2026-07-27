#!/usr/bin/env python3
"""Re-copy all files from source to fix encoding issues, applying all fixes at once"""
import os, re, shutil

SRC_BIZ = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
SRC_CTRL = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-controller\src\main\java\com\mqttsnet\thinglinks"
SRC_COMMON = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-public\thinglinks-common\src\main\java\com\mqttsnet\thinglinks\common"

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

SKIP_DIRS = {'entity', 'dto', 'vo', 'enumeration', 'enums', 'constant', 'converter', 'config', 'manager'}
SKIP_FILES = {'DeviceExportData.java', 'DeviceImportData.java', 'package-info.java'}

def should_skip(rel):
    parts = rel.replace('\\', '/').split('/')
    for p in parts:
        if p in SKIP_DIRS:
            return True
    return os.path.basename(rel) in SKIP_FILES

def transform(content):
    # Package
    content = re.sub(r'^package com\.mqttsnet\.thinglinks\.(\w+)', r'package org.springblade.modules.iot.\1', content, flags=re.MULTILINE)
    content = re.sub(r'^package com\.mqttsnet\.thinglinks', r'package org.springblade.modules.iot', content, flags=re.MULTILINE)
    
    # Replace thinglinks package
    content = content.replace('com.mqttsnet.thinglinks', 'org.springblade.modules.iot')
    
    # Fix basic imports
    content = content.replace('com.mqttsnet.basic.base.service.SuperService', 'org.springblade.core.mp.base.BaseService')
    content = content.replace('com.mqttsnet.basic.base.service.impl.SuperServiceImpl', 'org.springblade.core.mp.base.BaseServiceImpl')
    content = content.replace('com.mqttsnet.basic.base.mapper.SuperMapper', 'org.springblade.core.mp.support.BladeMapper')
    content = content.replace('com.mqttsnet.basic.base.controller.SuperController', 'org.springblade.core.boot.ctrl.BladeController')
    content = content.replace('com.mqttsnet.basic.base.manager.SuperManager', 'org.springblade.core.mp.base.BaseService')
    content = content.replace('com.mqttsnet.basic.base.request.PageParams', 'org.springblade.core.mp.support.Query')
    content = content.replace('com.mqttsnet.basic.base.R', 'org.springblade.core.tool.api.R')
    content = content.replace('com.mqttsnet.basic.context.ContextUtil', 'org.springblade.core.secure.utils.AuthUtil')
    content = content.replace('com.mqttsnet.basic.utils.BeanPlusUtil', 'org.springblade.core.tool.utils.BeanUtil')
    content = content.replace('com.mqttsnet.basic.utils.ArgumentAssert', '')
    content = content.replace('com.mqttsnet.basic.utils.SnowflakeIdUtil', '')
    content = content.replace('com.mqttsnet.basic.utils.TenantUtil', '')
    content = content.replace('com.mqttsnet.basic.utils.StrPool', 'org.springblade.core.tool.utils.StringPool')
    content = content.replace('com.mqttsnet.basic.jackson.JsonUtil', 'org.springblade.core.tool.utils.JsonUtil')
    content = content.replace('com.mqttsnet.basic.converter.Builder', '')
    content = content.replace('com.mqttsnet.basic.database.mybatis.conditions.Wraps', 'com.baomidou.mybatisplus.core.toolkit.Wrappers')
    
    # Remove remaining com.mqttsnet.basic imports
    content = re.sub(r'^import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    # Remove remaining com.mqttsnet imports
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix class inheritance
    content = re.sub(r'extends\s+SuperController\b', 'extends BladeController', content)
    content = re.sub(r'extends\s+SuperService\s*<\s*Long\s*,\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    content = re.sub(r'extends\s+SuperService\s*<\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    content = re.sub(r'extends\s+SuperServiceImpl\s*<\s*(\w+)\s*,\s*(\w+)\s*>', r'extends BaseServiceImpl<\1, \2>', content)
    content = re.sub(r'extends\s+SuperMapper\s*<\s*(\w+)\s*>', r'extends BladeMapper<\1>', content)
    content = re.sub(r'extends\s+SuperManager\s*<\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    
    # Fix method calls
    content = content.replace('ContextUtil.', 'AuthUtil.')
    content = content.replace('BeanPlusUtil.', 'BeanUtil.')
    content = content.replace('Wraps.', 'Wrappers.')
    content = content.replace('StrPool.', 'StringPool.')
    content = content.replace('PageParams<', 'Query<')
    
    # Fix Query<Xxx> → Query
    content = re.sub(r'Query<[^>]+>', 'Query', content)
    
    # Remove @DS
    content = re.sub(r'^\s*@DS\s*\(\s*"[^"]*"\s*\)\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove @WebLog
    content = re.sub(r'^\s*@WebLog\s*\([^)]*\)\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*@WebLog\s*\n', '', content, flags=re.MULTILINE)
    
    # @RequiredArgsConstructor → @AllArgsConstructor
    content = content.replace('import lombok.RequiredArgsConstructor;', 'import lombok.AllArgsConstructor;')
    content = content.replace('@RequiredArgsConstructor', '@AllArgsConstructor')
    
    # Fix static R.ok import
    content = re.sub(r'^import static com\.mqttsnet\.basic\.base\.R\.ok;\s*\n', '', content, flags=re.MULTILINE)
    
    # Clean up
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    return content

def copy_files(src_base, dst_base):
    count = 0
    for root, dirs, files in os.walk(src_base):
        for f in files:
            if not f.endswith('.java'):
                continue
            src = os.path.join(root, f)
            rel = os.path.relpath(src, src_base)
            if should_skip(rel):
                continue
            dst = os.path.join(dst_base, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            with open(src, 'r', encoding='utf-8') as fh:
                content = fh.read()
            content = transform(content)
            with open(dst, 'w', encoding='utf-8') as fh:
                fh.write(content)
            count += 1
    return count

# Clear and re-copy
for d in [DST_BIZ, DST_CTRL]:
    if os.path.exists(d):
        shutil.rmtree(d)

n1 = copy_files(SRC_BIZ, DST_BIZ)
n2 = copy_files(SRC_CTRL, DST_CTRL)

# Copy common files
COMMON_NEEDED = [
    "constant/DsConstant.java", "constant/CommonIotConstants.java",
    "constant/QrcodeConstant.java", "constant/JobConstant.java", "constant/DefValConstants.java",
    "lock/link/LinkLockKeyBuilder.java", "lock/LockKeyTable.java",
    "enums/DeviceActionTypeEnum.java",
    "cache/link/device/DeviceCacheKeyBuilder.java", "cache/link/device/DeviceAclRuleCacheKeyBuilder.java",
    "cache/link/product/ProductCacheKeyBuilder.java", "cache/link/product/ProductModelCacheKeyBuilder.java",
    "cache/link/product/ProductModelSuperTableCacheKeyBuilder.java",
    "cache/link/ota/OtaTaskExecutorOffsetCacheKeyBuilder.java", "cache/link/ota/OtaUpgradeRecordsCacheKeyBuilder.java",
    "cache/link/collectionpool/DeviceActionCollectionPoolCacheKeyBuilder.java",
    "cache/link/counter/UpLinkDataCounterCacheKeyBuilder.java", "cache/link/counter/DownLinkDataCounterCacheKeyBuilder.java",
    "cache/CacheKeyModular.java", "cache/CacheKeyTable.java",
]
DST_COMMON = os.path.join(DST_BIZ, "common")
for rel in COMMON_NEEDED:
    src = os.path.join(SRC_COMMON, rel)
    dst = os.path.join(DST_COMMON, rel)
    if not os.path.exists(src):
        continue
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    with open(src, 'r', encoding='utf-8') as f:
        content = f.read()
    content = content.replace('package com.mqttsnet.thinglinks.common', 'package org.springblade.modules.iot.common')
    content = content.replace('com.mqttsnet.thinglinks.common', 'org.springblade.modules.iot.common')
    content = content.replace('com.mqttsnet.basic.cache', 'org.springblade.common.cache')
    content = content.replace('com.mqttsnet.basic.utils', 'org.springblade.core.tool.utils')
    content = re.sub(r'^import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    with open(dst, 'w', encoding='utf-8') as f:
        f.write(content)

# Also copy the config and converter files that were skipped
EXTRA_NEEDED = [
    "ota/service/statemachine/config/OtaUpgradeStateMachineConfig.java",
    "productversion/converter/ProductSnapshotConverter.java",
]
for rel in EXTRA_NEEDED:
    src = os.path.join(SRC_BIZ, rel)
    dst = os.path.join(DST_BIZ, rel)
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    with open(src, 'r', encoding='utf-8') as f:
        content = f.read()
    content = transform(content)
    with open(dst, 'w', encoding='utf-8') as f:
        f.write(content)

print(f"BIZ: {n1}, CTRL: {n2}, COMMON: {len(COMMON_NEEDED)}, EXTRA: {len(EXTRA_NEEDED)}")
print("Done!")
