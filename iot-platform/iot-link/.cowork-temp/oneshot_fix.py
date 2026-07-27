#!/usr/bin/env python3
"""ONE-SHOT: Apply ALL post-migration fixes"""
import os, re, shutil

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

# Step 1: Delete problematic common directories
for d in ['common/cache', 'common/lock']:
    path = os.path.join(DST_BIZ, d)
    if os.path.exists(path):
        shutil.rmtree(path)

# Step 2: Fix all files
REMOVE_IMPORT_PATTERNS = [
    r'^import org\.springblade\.modules\.iot\.\w+\.manager\.[^;]+;\s*\n',
    r'^import org\.springblade\.modules\.iot\.system\.[^;]+;\s*\n',
    r'^import org\.springblade\.modules\.iot\.context\.[^;]+;\s*\n',
    r'^import org\.springblade\.modules\.iot\.common\.cache\.[^;]+;\s*\n',
    r'^import org\.springblade\.modules\.iot\.common\.lock\.[^;]+;\s*\n',
    r'^import com\.baomidou\.dynamic\.datasource\.annotation\.DS;\s*\n',
]

THINGLINKS_SYMBOLS = [
    'AppendixType', 'BizConstant', 'CaCertLicenseManager',
    'CachePlusOps', 'CachePlusUtil', 'CacheResult', 'ContextAwareExecutor',
    'DefTenantFacade', 'DeviceAclRuleManager', 'DeviceActionManager',
    'DeviceCommandManager', 'DeviceGroupManager', 'DeviceGroupRelManager',
    'DeviceLocationManager', 'DeviceManager', 'DistributedLock',
    'ExcelCheckResult', 'FieldsVO', 'FileFacade', 'FileResultVO',
    'OtaUpgradeRecordsManager', 'OtaUpgradesManager', 'OtaUpgradeTargetsManager',
    'OtaUpgradeTasksManager', 'ProductCommandManager', 'ProductCommandRequestManager',
    'ProductCommandResponseManager', 'ProductManager', 'ProductPropertyManager',
    'ProductPublishRecordManager', 'ProductServiceManager', 'ProductTopicManager',
    'ProductVersionChangeLogManager', 'ProductVersionManager',
    'ProtocolMessageAdapter', 'SuperTableDTO', 'TdDataTypeEnum', 'TreeUtil',
]

count = 0
for root_dir in [DST_BIZ, DST_CTRL]:
    for root, dirs, files in os.walk(root_dir):
        for f in files:
            if not f.endswith('.java'):
                continue
            filepath = os.path.join(root, f)
            with open(filepath, 'r', encoding='utf-8') as fh:
                content = fh.read()
            original = content
            
            # Remove bad imports
            for pat in REMOVE_IMPORT_PATTERNS:
                content = re.sub(pat, '', content, flags=re.MULTILINE)
            
            # Fix Builder.of → new Xxx()
            content = re.sub(r'Builder\.of\((\w+)::new\)', r'new \1()', content)
            
            # Remove thinglinks-specific imports (but NOT lombok)
            for sym in THINGLINKS_SYMBOLS:
                content = re.sub(rf'^import\s+(?!lombok\.)\S+\.{sym};\s*\n', '', content, flags=re.MULTILINE)
            
            # Add lombok.Builder if @Builder is used
            if '@Builder' in content and 'import lombok.Builder;' not in content:
                content = re.sub(r'(package\s+[^;]+;)', r'\1\nimport lombok.Builder;', content)
            
            # Remove empty imports
            content = re.sub(r'^import\s+;\s*\n', '', content, flags=re.MULTILINE)
            content = re.sub(r'^import\s+\.\w+;\s*\n', '', content, flags=re.MULTILINE)
            
            # Clean up
            content = re.sub(r'\n{3,}', '\n\n', content)
            
            if content != original:
                with open(filepath, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                count += 1

print(f"Fixed {count} files")
