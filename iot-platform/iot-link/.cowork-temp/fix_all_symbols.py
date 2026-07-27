#!/usr/bin/env python3
"""Fix ALL remaining symbol errors in one pass"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

# Symbols that need import fixing
IMPORT_FIX_MAP = {
    'BladeMapper': 'import org.springblade.core.mp.support.BladeMapper;',
    'Entity': 'import org.springblade.core.mp.base.BaseEntity;',
    'JsonUtil': 'import org.springblade.core.tool.utils.JsonUtil;',
    'StrPool': 'import org.springblade.core.tool.utils.StringPool;',
    'SuperServiceImpl': 'import org.springblade.core.mp.base.BaseServiceImpl;',
}

# Symbols to remove (thinglinks-specific)
REMOVE_SYMBOLS = [
    'Builder', 'CacheHashKey', 'CacheKey', 'CacheKeyBuilder',
    'CachePlusOps', 'CachePlusUtil', 'CacheResult', 'ContextAwareExecutor',
    'DefTenantFacade', 'DeviceLocationManager', 'DS', 'ExcelCheckResult',
    'FieldsVO', 'OtaUpgradesManager', 'OtaUpgradeTasksManager',
    'ProductVersionChangeLogManager', 'ProductVersionManager',
    'ProtocolMessageAdapter', 'SuperTableDTO', 'TdDataTypeEnum',
    'TreeUtil', 'ValueType',
]

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Fix class references in inheritance
    content = re.sub(r'extends\s+SuperServiceImpl\b', 'extends BaseServiceImpl', content)
    content = content.replace('import org.springblade.core.entity.Entity;', 'import org.springblade.core.mp.base.BaseEntity;')
    
    # Fix StrPool → StringPool in code
    content = content.replace('StrPool.', 'StringPool.')
    
    # Remove imports for thinglinks symbols
    for sym in REMOVE_SYMBOLS:
        content = re.sub(rf'^import\s+\S+\.{sym};\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove field declarations for thinglinks symbols
    for sym in REMOVE_SYMBOLS:
        content = re.sub(rf'^\s*(private|protected|public)\s+(final\s+)?{sym}\s+\w+\s*;\s*$', '', content, flags=re.MULTILINE)
        content = re.sub(rf'^\s*@\w+\s*\n\s*(private|protected|public)\s+(final\s+)?{sym}\s+\w+\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Remove @DS annotation usage
    content = re.sub(r'^\s*@DS\s*\([^)]*\)\s*$', '', content, flags=re.MULTILINE)
    
    # Remove DS import
    content = content.replace('import com.baomidou.dynamic.datasource.annotation.DS;\n', '')
    
    # Remove empty imports
    content = re.sub(r'^import\s+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import\s+\.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove remaining com.mqttsnet imports
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
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
    print(f"Fixed {count} files")

if __name__ == "__main__":
    main()
