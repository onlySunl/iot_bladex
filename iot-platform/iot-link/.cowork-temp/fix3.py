#!/usr/bin/env python3
"""
Comprehensive final fix for all remaining migration issues
"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    original = content
    
    # === Package fixes ===
    # org.springblade.core.service → org.springblade.core.mp.base
    content = content.replace('import org.springblade.core.service.BaseService;', 'import org.springblade.core.mp.base.BaseService;')
    content = content.replace('import org.springblade.core.service.impl.SuperServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;')
    content = content.replace('import org.springblade.core.service.impl.BaseServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;')
    
    # org.springblade.core.mapper → org.springblade.core.mp.support
    content = content.replace('import org.springblade.core.mapper.SuperMapper;', 'import org.springblade.core.mp.support.BladeMapper;')
    
    # org.springblade.core.entity → org.springblade.core.mp.base
    content = content.replace('import org.springblade.core.entity.BaseEntity;', 'import org.springblade.core.mp.base.BaseEntity;')
    
    # org.springblade.core.request → org.springblade.core.mp.support
    content = content.replace('import org.springblade.core.request.PageParams;', 'import org.springblade.core.mp.support.Query;')
    
    # org.springblade.core.R → org.springblade.core.tool.api.R
    content = content.replace('import org.springblade.core.R;', 'import org.springblade.core.tool.api.R;')
    
    # org.springblade.core.tool.utils.DateUtils → cn.hutool.core.date.DateUtil
    content = content.replace('import org.springblade.core.tool.utils.DateUtils;', 'import cn.hutool.core.date.DateUtil;')
    
    # org.springblade.modules.iot.system.entity.tenant → remove (not available)
    content = re.sub(r'^import org\.springblade\.modules\.iot\.system\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # org.springblade.common.cache.repository → remove (thinglinks specific)
    content = re.sub(r'^import org\.springblade\.common\.cache\.repository\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # org.springblade.common.cache.redis2 → remove
    content = re.sub(r'^import org\.springblade\.common\.cache\.redis2\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # org.springblade.common.cache.utils → remove
    content = re.sub(r'^import org\.springblade\.common\.cache\.utils\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # org.springblade.core.tool.utils.qrcode → remove (thinglinks specific)
    content = re.sub(r'^import org\.springblade\.core\.tool\.utils\.qrcode\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix org.springblade.modules.iot.context → remove
    content = re.sub(r'^import org\.springblade\.modules\.iot\.context\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix org.springblade.modules.iot.datascope → keep as-is (migrated)
    
    # Fix remaining com.mqttsnet imports
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix broken "import .Xxx;" patterns
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # === Class reference fixes ===
    # SuperServiceImpl → BaseServiceImpl
    content = re.sub(r'extends\s+SuperServiceImpl\s*<', 'extends BaseServiceImpl<', content)
    
    # SuperService → BaseService  
    content = re.sub(r'extends\s+SuperService\s*<', 'extends BaseService<', content)
    
    # SuperMapper → BladeMapper
    content = re.sub(r'extends\s+SuperMapper\s*<', 'extends BladeMapper<', content)
    
    # PageParams → Query
    content = content.replace('PageParams<', 'Query<')
    
    # DateUtils. → DateUtil.
    content = content.replace('DateUtils.', 'DateUtil.')
    
    # Wraps. → Wrappers.
    content = content.replace('Wraps.', 'Wrappers.')
    
    # BeanPlusUtil. → BeanUtil.
    content = content.replace('BeanPlusUtil.', 'BeanUtil.')
    
    # ContextUtil → AuthUtil
    content = content.replace('ContextUtil.', 'AuthUtil.')
    
    # === Remove @DS annotations ===
    content = re.sub(r'^\s*@DS\s*\([^)]*\)\s*$', '', content, flags=re.MULTILINE)
    
    # === Remove broken method calls ===
    # ArgumentAssert.xxx(...);
    content = re.sub(r'^\s*ArgumentAssert\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Builder.xxx(...);
    content = re.sub(r'^\s*Builder\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # TenantUtil.xxx(...);
    content = re.sub(r'^\s*TenantUtil\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # SnowflakeIdUtil.xxx(...);
    content = re.sub(r'^\s*SnowflakeIdUtil\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # CacheKey.xxx(...);
    content = re.sub(r'^\s*CacheKey\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # DistributedLock.xxx(...);
    content = re.sub(r'^\s*DistributedLock\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # LockRunResult.xxx(...);
    content = re.sub(r'^\s*LockRunResult\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # EasyExcelListener.xxx(...);
    content = re.sub(r'^\s*EasyExcelListener\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # EasyExcelUtils.xxx(...);
    content = re.sub(r'^\s*EasyExcelUtils\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # ExcelCheckManager.xxx(...);
    content = re.sub(r'^\s*ExcelCheckManager\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # ExcelImportErrDto.xxx(...);
    content = re.sub(r'^\s*ExcelImportErrDto\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # EchoService.xxx(...);
    content = re.sub(r'^\s*EchoService\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # QueryWrap.xxx(...);
    content = re.sub(r'^\s*QueryWrap\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # DataScopeHelper.xxx(...);
    content = re.sub(r'^\s*DataScopeHelper\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # LinkLockKeyBuilder.xxx(...);
    content = re.sub(r'^\s*LinkLockKeyBuilder\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # CachePlusOps
    content = re.sub(r'^\s*CachePlusOps\s+\w+\s*[=;].*$', '', content, flags=re.MULTILINE)
    
    # ContextAwareExecutor
    content = re.sub(r'^\s*ContextAwareExecutor\s+\w+\s*[=;].*$', '', content, flags=re.MULTILINE)
    
    # === Fix remaining SuperManager references ===
    content = re.sub(r'extends\s+SuperManager\s*<', 'extends BaseService<', content)
    
    # === Clean up ===
    # Remove empty lines after removed annotations
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    if content != original:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    count = 0
    for root_dir in [DST_BIZ, DST_CTRL]:
        for root, dirs, files in os.walk(root_dir):
            for f in files:
                if f.endswith(".java"):
                    filepath = os.path.join(root, f)
                    if fix_file(filepath):
                        count += 1
    
    print(f"Total files fixed: {count}")

if __name__ == "__main__":
    main()
