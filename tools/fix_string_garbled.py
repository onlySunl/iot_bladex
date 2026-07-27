#!/usr/bin/env python3
"""直接修复乱码导致的语法错误——替换包含乱码的字符串和注释"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java"

# 需要修复的文件及具体行修复
FIXES = {
    "ota/service/impl/OtaUpgradesServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "ota/service/impl/OtaUpgradeTaskExecutionServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "product/service/impl/ProductServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "productcommand/service/impl/ProductCommandServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "productcommandresponse/service/impl/ProductCommandResponseServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "productproperty/service/impl/ProductPropertyServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "productservice/service/impl/ProductServiceServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "producttopic/service/impl/ProductTopicServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "cacert/service/license/impl/CaCertLicenseServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
    "device/service/impl/DeviceAclRuleServiceImpl.java": [
        (r'throw new ServiceException\("[^"]*[\x80-\xff]+[^"]*"\)', 'throw new ServiceException("Invalid parameter")'),
    ],
}

count = 0
for rel_path, patterns in FIXES.items():
    path = os.path.join(BASE, rel_path)
    if not os.path.exists(path):
        print(f"NOT FOUND: {path}")
        continue
    
    with open(path, "r", encoding="utf-8", errors="replace") as f:
        content = f.read()
    
    original = content
    for pattern, replacement in patterns:
        content = re.sub(pattern, replacement, content)
    
    if content != original:
        with open(path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"Fixed: {os.path.basename(path)}")
        count += 1

print(f"\nTotal fixed: {count}")
