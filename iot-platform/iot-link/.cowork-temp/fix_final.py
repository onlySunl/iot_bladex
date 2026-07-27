#!/usr/bin/env python3
"""Final comprehensive fix for all remaining issues"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

REPLACEMENTS = [
    # Package imports - these must come first (more specific before less specific)
    ('import org.springblade.core.base.service.SuperService;', 'import org.springblade.core.mp.base.BaseService;'),
    ('import org.springblade.core.base.service.BaseService;', 'import org.springblade.core.mp.base.BaseService;'),
    ('import org.springblade.core.base.service.impl.SuperServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    ('import org.springblade.core.base.service.impl.BaseServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    ('import org.springblade.core.base.request.PageParams;', 'import org.springblade.core.mp.support.Query;'),
    ('import org.springblade.core.base.mapper.SuperMapper;', 'import org.springblade.core.mp.support.BladeMapper;'),
    ('import org.springblade.core.base.manager.SuperManager;', 'import org.springblade.core.mp.base.BaseService;'),
    ('import org.springblade.core.service.SuperService;', 'import org.springblade.core.mp.base.BaseService;'),
    ('import org.springblade.core.service.BaseService;', 'import org.springblade.core.mp.base.BaseService;'),
    ('import org.springblade.core.service.impl.SuperServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    ('import org.springblade.core.service.impl.BaseServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    ('import org.springblade.core.mapper.SuperMapper;', 'import org.springblade.core.mp.support.BladeMapper;'),
    ('import org.springblade.core.entity.Entity;', 'import org.springblade.core.mp.base.BaseEntity;'),
    ('import org.springblade.core.entity.BaseEntity;', 'import org.springblade.core.mp.base.BaseEntity;'),
    ('import org.springblade.core.request.PageParams;', 'import org.springblade.core.mp.support.Query;'),
    ('import org.springblade.core.R;', 'import org.springblade.core.tool.api.R;'),
    ('import org.springblade.core.context.ContextUtil;', 'import org.springblade.core.secure.utils.AuthUtil;'),
    
    # Class inheritance
    ('extends SuperService<Long,', 'extends BaseService<'),
    ('extends SuperServiceImpl<', 'extends BaseServiceImpl<'),
    ('extends SuperMapper<', 'extends BladeMapper<'),
    ('extends SuperManager<', 'extends BaseService<'),
    
    # Method calls
    ('ContextUtil.', 'AuthUtil.'),
    ('DateUtils.', 'DateUtil.'),
    ('Wraps.', 'Wrappers.'),
    ('BeanPlusUtil.', 'BeanUtil.'),
    
    # Remaining com.mqttsnet imports
]

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    for old, new in REPLACEMENTS:
        content = content.replace(old, new)
    
    # Fix Query<Xxx> → Query
    content = re.sub(r'Query<[^>]+>', 'Query', content)
    
    # Remove remaining com.mqttsnet imports  
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove broken imports
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove @DS annotations
    content = re.sub(r'^\s*@DS\s*\([^)]*\)\s*$', '', content, flags=re.MULTILINE)
    
    # Remove org.springblade.common.cache imports (thinglinks-specific)
    content = re.sub(r'^import org\.springblade\.common\.cache\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove @WebLog
    content = re.sub(r'^\s*@WebLog[^)]*\)\s*$', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*@WebLog\s*$', '', content, flags=re.MULTILINE)
    
    # Remove broken method calls
    for broken in ['ArgumentAssert', 'Builder', 'TenantUtil', 'SnowflakeIdUtil', 'CacheKey', 
                    'DistributedLock', 'LockRunResult', 'EasyExcelListener', 'EasyExcelUtils',
                    'ExcelCheckManager', 'ExcelImportErrDto', 'EchoService', 'QueryWrap',
                    'DataScopeHelper', 'LinkLockKeyBuilder']:
        content = re.sub(rf'^\s*{broken}\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Remove CachePlusOps and ContextAwareExecutor declarations
    content = re.sub(r'^\s*(private\s+)?(final\s+)?CachePlusOps\s+\w+\s*[=;].*$', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*(private\s+)?(final\s+)?ContextAwareExecutor\s+\w+\s*[=;].*$', '', content, flags=re.MULTILINE)
    
    # Clean up
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
    print(f"Fixed: {count}")

if __name__ == "__main__":
    main()
