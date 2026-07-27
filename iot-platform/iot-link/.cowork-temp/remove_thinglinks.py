#!/usr/bin/env python3
"""Remove ALL thinglinks-specific class references"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

# All thinglinks-specific symbols to remove
THINGLINKS_SYMBOLS = [
    'AppendixType', 'BizConstant', 'Builder', 'CaCertLicenseManager',
    'CachePlusOps', 'CachePlusUtil', 'CacheResult', 'ContextAwareExecutor',
    'DefTenantFacade', 'DeviceAclRuleManager', 'DeviceActionManager',
    'DeviceCommandManager', 'DeviceGroupManager', 'DeviceGroupRelManager',
    'DeviceLocationManager', 'DeviceManager', 'DistributedLock',
    'Entity', 'ExcelCheckResult', 'FieldsVO', 'FileFacade', 'FileResultVO',
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
            
            for sym in THINGLINKS_SYMBOLS:
                # Remove import lines
                content = re.sub(rf'^import\s+\S+\.{sym};\s*\n', '', content, flags=re.MULTILINE)
                # Remove field declarations
                content = re.sub(rf'^\s*(private|protected|public)\s+(final\s+)?{sym}\s+\w+\s*;\s*$', '', content, flags=re.MULTILINE)
                # Remove constructor params: "Sym sym," or ", Sym sym"
                content = re.sub(rf',\s*{sym}\s+\w+', '', content)
                content = re.sub(rf'{sym}\s+\w+\s*,', '', content)
            
            # Clean up
            content = re.sub(r'\n{3,}', '\n\n', content)
            
            if content != original:
                with open(filepath, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                count += 1

print(f"Fixed {count} files")
