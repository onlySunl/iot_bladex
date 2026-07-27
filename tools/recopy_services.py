#!/usr/bin/env python3
"""重新复制所有有乱码的 ServiceImpl 文件"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java"
SRC_BASE = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"

# 报错的文件列表
FILES = [
    "ota/service/impl/OtaUpgradesServiceImpl.java",
    "ota/service/impl/OtaUpgradeTaskExecutionServiceImpl.java",
    "product/service/impl/ProductServiceImpl.java",
    "product/service/impl/ProductQueryServiceImpl.java",
    "productcommand/service/impl/ProductCommandServiceImpl.java",
    "productcommandresponse/service/impl/ProductCommandResponseServiceImpl.java",
    "productproperty/service/impl/ProductPropertyServiceImpl.java",
    "productservice/service/impl/ProductServiceServiceImpl.java",
    "producttopic/service/impl/ProductTopicServiceImpl.java",
    "cacert/service/license/impl/CaCertLicenseServiceImpl.java",
    "device/service/impl/DeviceAclRuleServiceImpl.java",
    "device/service/impl/DeviceServiceImpl.java",
    "device/service/impl/DeviceCommandServiceImpl.java",
]

REPLACEMENTS = [
    ("com.mqttsnet.thinglinks", "org.springblade.modules.iot"),
    ("com.mqttsnet.basic.utils.StrPool", "org.springblade.common.utils.StrPool"),
    ("com.mqttsnet.basic.utils.DateUtils", "org.springblade.common.utils.DateUtils"),
    ("com.mqttsnet.basic.utils.ArgumentAssert", "org.springblade.modules.iot.common.utils.ArgumentAssert"),
    ("com.mqttsnet.basic.utils.BeanPlusUtil", "org.springblade.modules.iot.common.utils.BeanPlusUtil"),
    ("com.mqttsnet.basic.context.ContextUtil", "org.springblade.modules.iot.common.context.ContextUtil"),
    ("com.mqttsnet.basic.exception.BizException", "org.springblade.core.log.exception.ServiceException"),
    ("com.mqttsnet.basic.exception.ServiceException", "org.springblade.core.log.exception.ServiceException"),
    ("com.mqttsnet.basic.exception.code.ExceptionCode", "org.springblade.core.tool.api.ResultCode"),
    ("com.mqttsnet.basic.base.R", "org.springblade.core.tool.api.R"),
    ("com.mqttsnet.basic.cache.redis2.CacheResult", "org.springblade.core.tool.api.R"),
    ("com.mqttsnet.basic.cache.repository.CachePlusOps", "org.springframework.data.redis.core.RedisTemplate"),
    ("com.mqttsnet.basic.model.cache.CacheHashKey", "org.springframework.cache.annotation.Cacheable"),
    ("cn.hutool.core.text.StrPool", "org.springblade.common.utils.StrPool"),
    ("import com.baomidou.dynamic.datasource.annotation.DS;", ""),
    ("BizException.wrap", "new ServiceException"),
    ("BizException", "ServiceException"),
]

for rel_path in FILES:
    src = os.path.join(SRC_BASE, rel_path)
    dst = os.path.join(BASE, rel_path)
    
    if not os.path.exists(src):
        print(f"NOT FOUND: {src}")
        continue
    
    with open(src, "rb") as f:
        raw = f.read()
    if raw.startswith(b"\xef\xbb\xbf"):
        raw = raw[3:]
    
    text = raw.decode("utf-8", errors="replace")
    
    for old, new in REPLACEMENTS:
        text = text.replace(old, new)
    
    # 移除 @DS 注解
    text = re.sub(r'\s*@DS\([^)]+\)\s*\n', '\n', text)
    # 移除 import DS
    text = re.sub(r'import com\.baomidou\.dynamic\.datasource\.annotation\.DS;\s*\n', '', text)
    
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    with open(dst, "w", encoding="utf-8") as f:
        f.write(text)
    
    # 检查中文
    chinese = sum(1 for c in text if '\u4e00' <= c <= '\u9fff')
    print(f"OK ({chinese} CN): {os.path.basename(dst)}")

print("Done")
