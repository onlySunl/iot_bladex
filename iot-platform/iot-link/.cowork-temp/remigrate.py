#!/usr/bin/env python3
"""
COMPLETE RE-MIGRATION: Start from scratch, apply all fixes at once.
This script reads every source file, applies all transformations, and writes to target.
"""
import os, re, shutil

SRC_BIZ_BASE = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
SRC_CTRL_BASE = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-controller\src\main\java\com\mqttsnet\thinglinks"
SRC_XML_BASE = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\resources"
SRC_COMMON = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-public\thinglinks-common\src\main\java\com\mqttsnet\thinglinks\common"

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"
DST_XML = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\resources\mapper"
DST_COMMON = os.path.join(DST_BIZ, "common")

# Patterns for files to SKIP (entity-related)
SKIP_DIRS = {'entity', 'dto', 'vo', 'enumeration', 'enums', 'constant', 'converter', 'config'}
SKIP_FILES = {'DeviceExportData.java', 'DeviceImportData.java', 'package-info.java'}

def should_skip_path(rel_path):
    parts = rel_path.replace('\\', '/').split('/')
    for p in parts:
        if p in SKIP_DIRS:
            return True
    fname = os.path.basename(rel_path)
    if fname in SKIP_FILES:
        return True
    return False

def transform(content, is_controller=False):
    """Apply all BladeX transformations"""
    
    # === Package declaration ===
    content = re.sub(r'^package com\.mqttsnet\.thinglinks\.(\w+)', r'package org.springblade.modules.iot.\1', content, flags=re.MULTILINE)
    content = re.sub(r'^package com\.mqttsnet\.thinglinks', r'package org.springblade.modules.iot', content, flags=re.MULTILINE)
    
    # === Imports: com.mqttsnet.thinglinks → org.springblade.modules.iot ===
    content = content.replace('com.mqttsnet.thinglinks', 'org.springblade.modules.iot')
    
    # === Specific import fixes ===
    imports_to_fix = [
        # Base classes
        ('import org.springblade.core.base.service.SuperService;', 'import org.springblade.core.mp.base.BaseService;'),
        ('import org.springblade.core.base.service.BaseService;', 'import org.springblade.core.mp.base.BaseService;'),
        ('import org.springblade.core.base.service.impl.SuperServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
        ('import org.springblade.core.base.service.impl.BaseServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
        ('import org.springblade.core.service.SuperService;', 'import org.springblade.core.mp.base.BaseService;'),
        ('import org.springblade.core.service.BaseService;', 'import org.springblade.core.mp.base.BaseService;'),
        ('import org.springblade.core.service.impl.SuperServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
        ('import org.springblade.core.service.impl.BaseServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
        ('import org.springblade.core.base.request.PageParams;', 'import org.springblade.core.mp.support.Query;'),
        ('import org.springblade.core.request.PageParams;', 'import org.springblade.core.mp.support.Query;'),
        ('import org.springblade.core.base.mapper.SuperMapper;', 'import org.springblade.core.mp.support.BladeMapper;'),
        ('import org.springblade.core.mapper.SuperMapper;', 'import org.springblade.core.mp.support.BladeMapper;'),
        ('import org.springblade.core.base.manager.SuperManager;', 'import org.springblade.core.mp.base.BaseService;'),
        ('import org.springblade.core.entity.Entity;', 'import org.springblade.core.mp.base.BaseEntity;'),
        ('import org.springblade.core.entity.BaseEntity;', 'import org.springblade.core.mp.base.BaseEntity;'),
        ('import org.springblade.core.R;', 'import org.springblade.core.tool.api.R;'),
        ('import org.springblade.core.context.ContextUtil;', 'import org.springblade.core.secure.utils.AuthUtil;'),
        ('import org.springblade.core.tool.utils.DateUtils;', 'import cn.hutool.core.date.DateUtil;'),
        ('import org.springblade.core.tool.utils.BeanPlusUtil;', 'import org.springblade.core.tool.utils.BeanUtil;'),
        ('import org.springblade.core.tool.utils.ArgumentAssert;', ''),
        # Controller
        ('import org.springblade.core.base.controller.SuperController;', 'import org.springblade.core.boot.ctrl.BladeController;'),
        # Context
        ('import org.springblade.core.context.ContextConstants;', ''),
        # Annotations
        ('import org.springblade.core.annotation.log.WebLog;', ''),
        # Thinglinks-specific
        ('import org.springblade.core.cache.lock.DistributedLock;', ''),
        ('import org.springblade.core.cache.lock.LockRunResult;', ''),
        ('import org.springblade.core.interfaces.echo.EchoService;', ''),
        ('import org.springblade.core.easyexcel.EasyExcelListener;', ''),
        ('import org.springblade.core.easyexcel.EasyExcelUtils;', ''),
        ('import org.springblade.core.easyexcel.ExcelCheckManager;', ''),
        ('import org.springblade.core.easyexcel.ExcelImportErrDto;', ''),
        ('import org.springblade.core.jackson.JsonUtil;', 'import org.springblade.core.tool.utils.JsonUtil;'),
        ('import org.springblade.core.model.cache.CacheKey;', ''),
        ('import org.springblade.core.converter.Builder;', ''),
        ('import conditions.mybatis.org.springblade.basic.database.Wraps;', 'import com.baomidou.mybatisplus.core.toolkit.Wrappers;'),
        ('import query.conditions.mybatis.org.springblade.basic.database.QueryWrap;', ''),
        ('import org.springblade.core.utils.TenantUtil;', ''),
        ('import org.springblade.core.utils.SnowflakeIdUtil;', ''),
        ('import org.springblade.core.utils.StrPool;', 'import org.springblade.core.tool.utils.StringPool;'),
        # Cache
        ('import org.springblade.common.cache.redis2.', '// import org.springblade.common.cache.redis2.'),
        ('import org.springblade.common.cache.repository.', '// import org.springblade.common.cache.repository.'),
        ('import org.springblade.common.cache.utils.', '// import org.springblade.common.cache.utils.'),
        # Remove remaining com.mqttsnet.basic imports
    ]
    
    for old, new in imports_to_fix:
        content = content.replace(old, new)
    
    # Remove any remaining com.mqttsnet.basic imports
    content = re.sub(r'^import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove any remaining com.mqttsnet imports
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove broken imports
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove static import of R.ok
    content = re.sub(r'^import static org\.springblade\.core\.tool\.api\.R\.ok;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import static com\.mqttsnet\.basic\.base\.R\.ok;\s*\n', '', content, flags=re.MULTILINE)
    
    # === Class inheritance fixes ===
    content = re.sub(r'extends\s+SuperController\b', 'extends BladeController', content)
    content = re.sub(r'extends\s+SuperService\s*<\s*Long\s*,\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    content = re.sub(r'extends\s+SuperService\s*<\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    content = re.sub(r'extends\s+SuperServiceImpl\s*<\s*(\w+)\s*,\s*(\w+)\s*>', r'extends BaseServiceImpl<\1, \2>', content)
    content = re.sub(r'extends\s+SuperMapper\s*<\s*(\w+)\s*>', r'extends BladeMapper<\1>', content)
    content = re.sub(r'extends\s+SuperManager\s*<\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    
    # === Method call fixes ===
    content = content.replace('ContextUtil.', 'AuthUtil.')
    content = content.replace('DateUtils.', 'DateUtil.')
    content = content.replace('Wraps.', 'Wrappers.')
    content = content.replace('BeanPlusUtil.', 'BeanUtil.')
    content = content.replace('StrPool.', 'StringPool.')
    
    # === Fix Query<Xxx> → Query ===
    content = re.sub(r'Query<[^>]+>', 'Query', content)
    
    # === Fix PageParams<Xxx> → Query ===
    content = re.sub(r'PageParams<[^>]+>', 'Query', content)
    
    # === Remove @DS annotations ===
    content = re.sub(r'^\s*@DS\s*\(\s*"[^"]*"\s*\)\s*\n', '', content, flags=re.MULTILINE)
    
    # === Remove @WebLog annotations ===
    content = re.sub(r'^\s*@WebLog\s*\([^)]*\)\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*@WebLog\s*\n', '', content, flags=re.MULTILINE)
    
    # === Remove @RequiredArgsConstructor → @AllArgsConstructor ===
    content = content.replace('import lombok.RequiredArgsConstructor;', 'import lombok.AllArgsConstructor;')
    content = content.replace('@RequiredArgsConstructor', '@AllArgsConstructor')
    
    # === Fix @RequestMapping paths for controllers ===
    if is_controller:
        content = re.sub(r'@RequestMapping\("/device"\)', '@RequestMapping("/iot/device")', content)
        content = re.sub(r'@RequestMapping\("/product"\)', '@RequestMapping("/iot/product")', content)
        content = re.sub(r'@RequestMapping\("/ota"\)', '@RequestMapping("/iot/ota")', content)
        content = re.sub(r'@RequestMapping\("/cacert"\)', '@RequestMapping("/iot/cacert")', content)
        content = re.sub(r'@RequestMapping\("/dashboard"\)', '@RequestMapping("/iot/dashboard")', content)
    
    # === Remove broken standalone method calls ===
    for broken in ['ArgumentAssert', 'Builder', 'TenantUtil', 'SnowflakeIdUtil', 'CacheKey',
                    'DistributedLock', 'LockRunResult', 'EasyExcelListener', 'EasyExcelUtils',
                    'ExcelCheckManager', 'ExcelImportErrDto', 'EchoService', 'QueryWrap',
                    'DataScopeHelper', 'LinkLockKeyBuilder']:
        content = re.sub(rf'^\s*{broken}\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # === Remove CachePlusOps and ContextAwareExecutor field declarations ===
    content = re.sub(r'^\s*(private\s+)?(final\s+)?CachePlusOps\s+\w+\s*[=;].*$', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*(private\s+)?(final\s+)?ContextAwareExecutor\s+\w+\s*[=;].*$', '', content, flags=re.MULTILINE)
    
    # === Remove org.springblade.common.cache imports ===
    content = re.sub(r'^// import org\.springblade\.common\.cache\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # === Clean up ===
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    return content

def copy_files(src_base, dst_base, is_controller=False):
    """Copy all Java files from src to dst, applying transformations"""
    count = 0
    for root, dirs, files in os.walk(src_base):
        for f in files:
            if not f.endswith('.java'):
                continue
            src_path = os.path.join(root, f)
            rel = os.path.relpath(src_path, src_base)
            
            if should_skip_path(rel):
                continue
            
            dst_path = os.path.join(dst_base, rel)
            os.makedirs(os.path.dirname(dst_path), exist_ok=True)
            
            with open(src_path, 'r', encoding='utf-8') as fh:
                content = fh.read()
            
            content = transform(content, is_controller)
            
            with open(dst_path, 'w', encoding='utf-8') as fh:
                fh.write(content)
            
            count += 1
    
    return count

def copy_xml_files():
    """Copy XML mapper files"""
    count = 0
    xml_dirs = [d for d in os.listdir(SRC_XML_BASE) if d.startswith('mapper_')]
    for xml_dir in xml_dirs:
        src_dir = os.path.join(SRC_XML_BASE, xml_dir)
        for root, dirs, files in os.walk(src_dir):
            for f in files:
                if f.endswith('.xml'):
                    src_path = os.path.join(root, f)
                    rel = os.path.relpath(src_path, src_dir)
                    dst_path = os.path.join(DST_XML, rel)
                    os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                    
                    with open(src_path, 'r', encoding='utf-8') as fh:
                        content = fh.read()
                    
                    content = content.replace('com.mqttsnet.thinglinks', 'org.springblade.modules.iot')
                    
                    with open(dst_path, 'w', encoding='utf-8') as fh:
                        fh.write(content)
                    count += 1
    return count

def copy_common_files():
    """Copy needed common files from thinglinks-public"""
    needed = [
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
    
    count = 0
    for rel in needed:
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
        count += 1
    
    return count

def main():
    # Clear target directories first
    for d in [DST_BIZ, DST_CTRL]:
        if os.path.exists(d):
            shutil.rmtree(d)
    
    print("=== Step 1: Copy BIZ files ===")
    n1 = copy_files(SRC_BIZ_BASE, DST_BIZ)
    print(f"  {n1} files")
    
    print("=== Step 2: Copy Controller files ===")
    n2 = copy_files(SRC_CTRL_BASE, DST_CTRL, is_controller=True)
    print(f"  {n2} files")
    
    print("=== Step 3: Copy XML files ===")
    n3 = copy_xml_files()
    print(f"  {n3} files")
    
    print("=== Step 4: Copy Common files ===")
    n4 = copy_common_files()
    print(f"  {n4} files")
    
    print(f"\n=== Total: {n1 + n2 + n3 + n4} files ===")

if __name__ == "__main__":
    main()
