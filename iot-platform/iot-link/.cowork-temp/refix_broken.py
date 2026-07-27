#!/usr/bin/env python3
"""Re-copy specific broken files from source"""
import os, re

SRC_BIZ = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"

BROKEN_FILES = [
    "device/service/impl/DeviceSyncInnerServiceImpl.java",
    "device/service/impl/DeviceQrcodeServiceImpl.java",
    "ota/service/impl/OtaUpgradesServiceImpl.java",
    "device/easyexcel/impl/DeviceEasyExcelServiceImpl.java",
    "ota/service/statemachine/event/handler/OtaTaskExecutionHandler.java",
    "dashboard/service/impl/DashboardStatsServiceImpl.java",
]

def transform(content):
    content = content.replace('com.mqttsnet.thinglinks', 'org.springblade.modules.iot')
    content = content.replace('com.mqttsnet.basic.base.service.SuperService', 'org.springblade.core.mp.base.BaseService')
    content = content.replace('com.mqttsnet.basic.base.service.impl.SuperServiceImpl', 'org.springblade.core.mp.base.BaseServiceImpl')
    content = content.replace('com.mqttsnet.basic.base.mapper.SuperMapper', 'org.springblade.core.mp.support.BladeMapper')
    content = content.replace('com.mqttsnet.basic.base.controller.SuperController', 'org.springblade.core.boot.ctrl.BladeController')
    content = content.replace('com.mqttsnet.basic.base.manager.SuperManager', 'org.springblade.core.mp.base.BaseService')
    content = content.replace('com.mqttsnet.basic.base.request.PageParams', 'org.springblade.core.mp.support.Query')
    content = content.replace('com.mqttsnet.basic.base.R', 'org.springblade.core.tool.api.R')
    content = content.replace('com.mqttsnet.basic.context.ContextUtil', 'org.springblade.core.secure.utils.AuthUtil')
    content = content.replace('com.mqttsnet.basic.utils.BeanPlusUtil', 'org.springblade.core.tool.utils.BeanUtil')
    content = content.replace('com.mqttsnet.basic.jackson.JsonUtil', 'org.springblade.core.tool.utils.JsonUtil')
    content = content.replace('com.mqttsnet.basic.database.mybatis.conditions.Wraps', 'com.baomidou.mybatisplus.core.toolkit.Wrappers')
    content = content.replace('com.mqttsnet.basic.utils.StrPool', 'org.springblade.core.tool.utils.StringPool')
    content = re.sub(r'^import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import com\.mqttsnet\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'extends\s+SuperController\b', 'extends BladeController', content)
    content = re.sub(r'extends\s+SuperService\s*<\s*Long\s*,\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    content = re.sub(r'extends\s+SuperService\s*<\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    content = re.sub(r'extends\s+SuperServiceImpl\s*<\s*(\w+)\s*,\s*(\w+)\s*>', r'extends BaseServiceImpl<\1, \2>', content)
    content = re.sub(r'extends\s+SuperMapper\s*<\s*(\w+)\s*>', r'extends BladeMapper<\1>', content)
    content = re.sub(r'extends\s+SuperManager\s*<\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    content = content.replace('ContextUtil.', 'AuthUtil.')
    content = content.replace('BeanPlusUtil.', 'BeanUtil.')
    content = content.replace('Wraps.', 'Wrappers.')
    content = content.replace('StrPool.', 'StringPool.')
    content = content.replace('PageParams<', 'Query<')
    content = re.sub(r'Query<[^>]+>', 'Query', content)
    content = re.sub(r'^\s*@DS\s*\(\s*"[^"]*"\s*\)\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*@WebLog\s*\([^)]*\)\s*\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*@WebLog\s*\n', '', content, flags=re.MULTILINE)
    content = content.replace('import lombok.RequiredArgsConstructor;', 'import lombok.AllArgsConstructor;')
    content = content.replace('@RequiredArgsConstructor', '@AllArgsConstructor')
    content = re.sub(r'\n{3,}', '\n\n', content)
    return content

for rel in BROKEN_FILES:
    src = os.path.join(SRC_BIZ, rel)
    dst = os.path.join(DST_BIZ, rel)
    with open(src, 'r', encoding='utf-8') as f:
        content = f.read()
    content = transform(content)
    with open(dst, 'w', encoding='utf-8') as f:
        f.write(content)
    print(f"Fixed: {rel}")

print("Done!")
