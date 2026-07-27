#!/usr/bin/env python3
"""Re-copy broken files from source and fix them properly"""
import os, re, shutil

SRC_BIZ = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"

# Files to re-copy (broken by Set-Content)
FILES_TO_FIX = [
    "device/event/source/DeviceDeletedEventSource.java",
    "device/event/source/DeviceInfoUpdatedEventSource.java",
    "ota/service/statemachine/event/source/OtaTaskExecutionEventSource.java",
    "ota/service/statemachine/config/OtaUpgradeStateMachineConfig.java",
    "productversion/service/ProductVersionService.java",
    "productpublishrecord/service/ProductPublishRecordService.java",
    "ota/service/OtaUpgradeRecordsService.java",
]

def fix_content(content):
    content = content.replace("com.mqttsnet.thinglinks", "org.springblade.modules.iot")
    content = content.replace("com.mqttsnet.basic", "org.springblade.core")
    content = content.replace("import org.springblade.core.entity.Entity;", "import org.springblade.core.mp.base.BaseEntity;")
    content = content.replace("import org.springblade.core.entity.BaseEntity;", "import org.springblade.core.mp.base.BaseEntity;")
    content = content.replace("import org.springblade.core.context.ContextUtil;", "import org.springblade.core.secure.utils.AuthUtil;")
    content = content.replace("ContextUtil.", "AuthUtil.")
    content = content.replace("import org.springblade.core.service.BaseService;", "import org.springblade.core.mp.base.BaseService;")
    content = content.replace("import org.springblade.core.service.SuperService;", "import org.springblade.core.mp.base.BaseService;")
    content = content.replace("import org.springblade.core.service.impl.SuperServiceImpl;", "import org.springblade.core.mp.base.BaseServiceImpl;")
    content = content.replace("import org.springblade.core.mapper.SuperMapper;", "import org.springblade.core.mp.support.BladeMapper;")
    content = content.replace("import org.springblade.core.request.PageParams;", "import org.springblade.core.mp.support.Query;")
    content = content.replace("import org.springblade.core.R;", "import org.springblade.core.tool.api.R;")
    
    # Fix class inheritance
    content = re.sub(r'extends SuperService<Long,\s*(\w+)>', r'extends BaseService<\1>', content)
    content = re.sub(r'extends SuperServiceImpl<(\w+),\s*(\w+)>', r'extends BaseServiceImpl<\1, \2>', content)
    content = re.sub(r'extends SuperMapper<(\w+)>', r'extends BladeMapper<\1>', content)
    
    # Fix Query<Xxx> → Query
    content = re.sub(r'Query<[^>]+>', 'Query', content)
    
    # Fix remaining broken imports
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import \.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Remove @DS
    content = re.sub(r'^\s*@DS\s*\([^)]*\)\s*$', '', content, flags=re.MULTILINE)
    
    return content

for rel in FILES_TO_FIX:
    src = os.path.join(SRC_BIZ, rel)
    dst = os.path.join(DST_BIZ, rel)
    
    if not os.path.exists(src):
        print(f"SKIP (not found): {rel}")
        continue
    
    with open(src, 'r', encoding='utf-8') as f:
        content = f.read()
    
    content = fix_content(content)
    
    with open(dst, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"OK: {rel}")

print("Done!")
