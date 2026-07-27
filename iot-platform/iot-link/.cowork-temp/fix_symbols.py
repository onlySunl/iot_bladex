#!/usr/bin/env python3
"""
Fix all remaining "找不到符号" errors by:
1. Fixing import paths that were incorrectly transformed
2. Removing references to thinglinks-specific classes
3. Adding stub classes where needed
"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"

# Missing symbols and their fixes
# Format: (old_import_pattern, new_import_or_action)
IMPORT_FIXES = [
    # BaseService - fix incorrect path
    ('import org.springblade.core.base.service.BaseService;', 'import org.springblade.core.mp.base.BaseService;'),
    # BladeMapper - fix incorrect path
    ('import org.springblade.core.base.mapper.BladeMapper;', 'import org.springblade.core.mp.support.BladeMapper;'),
    # Query - fix incorrect path
    ('import org.springblade.core.base.request.Query;', 'import org.springblade.core.mp.support.Query;'),
    # R - fix incorrect path
    ('import org.springblade.core.base.R;', 'import org.springblade.core.tool.api.R;'),
    # SuperServiceImpl - fix incorrect path
    ('import org.springblade.core.base.service.impl.SuperServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    # Entity - fix
    ('import org.springblade.core.entity.Entity;', 'import org.springblade.core.mp.base.BaseEntity;'),
    # JsonUtil - fix
    ('import org.springblade.core.jackson.JsonUtil;', 'import org.springblade.core.tool.utils.JsonUtil;'),
    # StrPool - fix
    ('import org.springblade.core.utils.StrPool;', 'import org.springblade.core.tool.utils.StringPool;'),
    # BeanPlusUtil - fix
    ('import org.springblade.core.utils.BeanPlusUtil;', 'import org.springblade.core.tool.utils.BeanUtil;'),
]

# Thinglinks-specific classes to remove
THINGLINKS_CLASSES = [
    'BizConstant', 'Builder', 'CacheHashKey', 'CacheKey', 'CacheKeyBuilder',
    'CachePlusUtil', 'CacheResult', 'DefTenantFacade', 'ExcelCheckResult',
    'FieldsVO', 'ProtocolMessageAdapter', 'SuperTableDTO', 'TdDataTypeEnum',
    'TreeUtil', 'ValueType',
]

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Apply import fixes
    for old, new in IMPORT_FIXES:
        content = content.replace(old, new)
    
    # Remove imports for thinglinks-specific classes
    for cls in THINGLINKS_CLASSES:
        content = re.sub(rf'^import\s+.*\.{cls};\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix class inheritance issues
    content = re.sub(r'extends\s+SuperServiceImpl\s*<', 'extends BaseServiceImpl<', content)
    content = re.sub(r'extends\s+SuperService\s*<', 'extends BaseService<', content)
    content = re.sub(r'extends\s+SuperMapper\s*<', 'extends BladeMapper<', content)
    
    # Remove broken imports
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import org\.springblade\.core\.base\.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Clean up
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    if content != original:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    count = 0
    for root, dirs, files in os.walk(DST_BIZ):
        for f in files:
            if not f.endswith('.java'):
                continue
            filepath = os.path.join(root, f)
            if fix_file(filepath):
                count += 1
    print(f"Fixed {count} files")

if __name__ == "__main__":
    main()
