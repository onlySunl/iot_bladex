#!/usr/bin/env python3
"""
FINAL CLEAN MIGRATION - single pass, all fixes applied correctly.
"""
import os, re, shutil

SRC_BIZ = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
SRC_CTRL = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-controller\src\main\java\com\mqttsnet\thinglinks"
SRC_COMMON = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-public\thinglinks-common\src\main\java\com\mqttsnet\thinglinks\common"
SRC_XML_BASE = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\resources"

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"
DST_XML = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\resources\mapper"

SKIP_DIRS = {'entity', 'dto', 'vo', 'enumeration', 'enums', 'constant', 'converter', 'config', 'manager'}
SKIP_FILES = {'DeviceExportData.java', 'DeviceImportData.java', 'package-info.java'}

def should_skip(rel):
    parts = rel.replace('\\', '/').split('/')
    for p in parts:
        if p in SKIP_DIRS:
            return True
    return os.path.basename(rel) in SKIP_FILES

def transform(content):
    """Apply all BladeX transformations in correct order"""
    
    # === 1. Package declaration ===
    content = re.sub(r'^package com\.mqttsnet\.thinglinks\.(\w+)', r'package org.springblade.modules.iot.\1', content, flags=re.MULTILINE)
    content = re.sub(r'^package com\.mqttsnet\.thinglinks', r'package org.springblade.modules.iot', content, flags=re.MULTILINE)
    
    # === 2. Replace base package everywhere ===
    content = content.replace('com.mqttsnet.thinglinks', 'org.springblade.modules.iot')
    
    # === 3. Fix com.mqttsnet.basic imports (these are NOT under thinglinks) ===
    basic_replacements = [
        ('com.mqttsnet.basic.base.service.impl.SuperServiceImpl', 'org.springblade.core.mp.base.BaseServiceImpl'),
        ('com.mqttsnet.basic.base.service.SuperService', 'org.springblade.core.mp.base.BaseService'),
        ('com.mqttsnet.basic.base.mapper.SuperMapper', 'org.springblade.core.mp.mapper.BladeMapper'),
        ('com.mqttsnet.basic.base.controller.SuperController', 'org.springblade.core.boot.ctrl.BladeController'),
        ('com.mqttsnet.basic.base.manager.SuperManager', 'org.springblade.core.mp.base.BaseService'),
        ('com.mqttsnet.basic.base.request.PageParams', 'org.springblade.core.mp.support.Query'),
        ('com.mqttsnet.basic.base.R', 'org.springblade.core.tool.api.R'),
        ('com.mqttsnet.basic.context.ContextUtil', 'org.springblade.core.secure.utils.AuthUtil'),
        ('com.mqttsnet.basic.utils.BeanPlusUtil', 'org.springblade.core.tool.utils.BeanUtil'),
        ('com.mqttsnet.basic.utils.StrPool', 'org.springblade.core.tool.utils.StringPool'),
        ('com.mqttsnet.basic.jackson.JsonUtil', 'org.springblade.core.tool.jackson.JsonUtil'),
        ('com.mqttsnet.basic.database.mybatis.conditions.Wraps', 'com.baomidou.mybatisplus.core.toolkit.Wrappers'),
        ('com.mqttsnet.basic.annotation.log.WebLog', ''),
        ('com.mqttsnet.basic.easyexcel.EasyExcelListener', ''),
        ('com.mqttsnet.basic.easyexcel.EasyExcelUtils', ''),
        ('com.mqttsnet.basic.easyexcel.ExcelCheckManager', ''),
        ('com.mqttsnet.basic.easyexcel.ExcelImportErrDto', ''),
        ('com.mqttsnet.basic.interfaces.echo.EchoService', ''),
        ('com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap', ''),
        ('com.mqttsnet.basic.converter.Builder', ''),
        ('com.mqttsnet.basic.model.cache.CacheKey', ''),
        ('com.mqttsnet.basic.cache.lock.DistributedLock', ''),
        ('com.mqttsnet.basic.cache.lock.LockRunResult', ''),
        ('com.mqttsnet.basic.utils.ArgumentAssert', ''),
        ('com.mqttsnet.basic.utils.SnowflakeIdUtil', ''),
        ('com.mqttsnet.basic.utils.TenantUtil', ''),
        ('com.mqttsnet.basic.base.entity.Entity', 'org.springblade.core.mp.base.BaseEntity'),
    ]
    for old, new in basic_replacements:
        content = content.replace(old, new)
    
    # Remove any remaining com.mqttsnet.basic imports (thinglinks-specific ones not handled above)
    content = re.sub(r'^import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # === 4. Class inheritance fixes ===
    content = re.sub(r'extends\s+SuperController\b', 'extends BladeController', content)
    content = re.sub(r'extends\s+SuperService\s*<\s*Long\s*,\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    # SuperServiceImpl<Mapper, Long, Entity> → BaseServiceImpl<Mapper, Entity>
    content = re.sub(r'extends\s+SuperServiceImpl\s*<\s*(\w+)\s*,\s*Long\s*,\s*(\w+)\s*>', r'extends BaseServiceImpl<\1, \2>', content)
    # SuperServiceImpl<Mapper, Entity> → BaseServiceImpl<Mapper, Entity>
    content = re.sub(r'extends\s+SuperServiceImpl\s*<\s*(\w+)\s*,\s*(\w+)\s*>', r'extends BaseServiceImpl<\1, \2>', content)
    content = re.sub(r'extends\s+SuperMapper\s*<\s*(\w+)\s*>', r'extends BladeMapper<\1>', content)
    content = re.sub(r'extends\s+SuperManager\s*<\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    
    # === 5. Method call fixes ===
    content = content.replace('ContextUtil.', 'AuthUtil.')
    content = content.replace('BeanPlusUtil.', 'BeanUtil.')
    content = content.replace('Wraps.', 'Wrappers.')
    content = content.replace('StrPool.', 'StringPool.')
    
    # === 6. Fix PageParams → Query (before Query<Xxx> fix) ===
    content = content.replace('PageParams<', 'Query<')
    
    # === 7. Fix Query<Xxx> → Query ===
    content = re.sub(r'Query<[^>]+>', 'Query', content)
    
    # === 8. Remove @DS annotations ===
    content = re.sub(r'^\s*@DS\s*\(\s*"[^"]*"\s*\)\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*@DS\s*\(\s*[^)]+\s*\)\s*\n', '', content, flags=re.MULTILINE)
    
    # === 9. Remove @WebLog ===
    content = re.sub(r'^\s*@WebLog\s*\([^)]*\)\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*@WebLog\s*\n', '', content, flags=re.MULTILINE)
    
    # === 10. @RequiredArgsConstructor → @AllArgsConstructor ===
    content = content.replace('import lombok.RequiredArgsConstructor;', 'import lombok.AllArgsConstructor;')
    content = content.replace('@RequiredArgsConstructor', '@AllArgsConstructor')
    
    # === 11. Remove static R.ok import ===
    content = re.sub(r'^import static org\.springblade\.core\.tool\.api\.R\.ok;\s*\n', '', content, flags=re.MULTILINE)
    
    # === 12. Remove any remaining com.mqttsnet imports ===
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # === 13. Remove empty/broken imports ===
    content = re.sub(r'^import\s+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import\s+\.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # === 14. Clean up ===
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    return content

def main():
    # Clear targets
    for d in [DST_BIZ, DST_CTRL]:
        if os.path.exists(d):
            shutil.rmtree(d)
    
    # Copy BIZ files
    n1 = 0
    for root, dirs, files in os.walk(SRC_BIZ):
        for f in files:
            if not f.endswith('.java'):
                continue
            src = os.path.join(root, f)
            rel = os.path.relpath(src, SRC_BIZ)
            if should_skip(rel):
                continue
            dst = os.path.join(DST_BIZ, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            with open(src, 'r', encoding='utf-8') as fh:
                content = fh.read()
            content = transform(content)
            with open(dst, 'w', encoding='utf-8') as fh:
                fh.write(content)
            n1 += 1
    
    # Copy Controller files
    n2 = 0
    for root, dirs, files in os.walk(SRC_CTRL):
        for f in files:
            if not f.endswith('.java'):
                continue
            src = os.path.join(root, f)
            rel = os.path.relpath(src, SRC_CTRL)
            dst = os.path.join(DST_CTRL, rel)
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            with open(src, 'r', encoding='utf-8') as fh:
                content = fh.read()
            content = transform(content)
            with open(dst, 'w', encoding='utf-8') as fh:
                fh.write(content)
            n2 += 1
    
    # Copy XML files
    n3 = 0
    xml_dirs = [d for d in os.listdir(SRC_XML_BASE) if d.startswith('mapper_')]
    for xml_dir in xml_dirs:
        src_dir = os.path.join(SRC_XML_BASE, xml_dir)
        for root, dirs, files in os.walk(src_dir):
            for f in files:
                if f.endswith('.xml'):
                    src = os.path.join(root, f)
                    rel = os.path.relpath(src, src_dir)
                    dst = os.path.join(DST_XML, rel)
                    os.makedirs(os.path.dirname(dst), exist_ok=True)
                    with open(src, 'r', encoding='utf-8') as fh:
                        content = fh.read()
                    content = content.replace('com.mqttsnet.thinglinks', 'org.springblade.modules.iot')
                    with open(dst, 'w', encoding='utf-8') as fh:
                        fh.write(content)
                    n3 += 1
    
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
        with open(dst, 'w', encoding='utf-8') as f:
            f.write(content)
    
    # Copy extra needed files (config/converter that were skipped)
    EXTRA = [
        "ota/service/statemachine/config/OtaUpgradeStateMachineConfig.java",
        "productversion/converter/ProductSnapshotConverter.java",
    ]
    for rel in EXTRA:
        src = os.path.join(SRC_BIZ, rel)
        dst = os.path.join(DST_BIZ, rel)
        os.makedirs(os.path.dirname(dst), exist_ok=True)
        with open(src, 'r', encoding='utf-8') as f:
            content = f.read()
        content = transform(content)
        with open(dst, 'w', encoding='utf-8') as f:
            f.write(content)
    
    print(f"BIZ: {n1}, CTRL: {n2}, XML: {n3}, COMMON: {len(COMMON_NEEDED)}, EXTRA: {len(EXTRA)}")
    print("Done!")

if __name__ == "__main__":
    main()
