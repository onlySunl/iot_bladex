#!/usr/bin/env python3
"""修正 iot-link 中 Service/ServiceImpl 继承关系：BaseService→BladeService, BaseServiceImpl→BladeServiceImpl"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

files_fixed = 0

for root_dir in [DST_BIZ, DST_CTRL]:
    for root, dirs, files in os.walk(root_dir):
        for f in files:
            if not f.endswith(".java"):
                continue
            path = os.path.join(root, f)
            with open(path, "r", encoding="utf-8") as fh:
                text = fh.read()
            
            original = text
            
            # 1. Service 接口: extends BaseService<Entity> → extends BladeService<Entity>
            if "Service.java" in f and "Impl" not in f:
                text = text.replace(
                    "import org.springblade.core.mp.base.BaseService;",
                    "import org.springblade.core.mp.service.BladeService;")
                text = re.sub(r'extends BaseService<(\w+)>', r'extends BladeService<\1>', text)
            
            # 2. ServiceImpl: extends BaseServiceImpl<M, T> → extends BladeServiceImpl<M, T>
            if "ServiceImpl.java" in f:
                text = text.replace(
                    "import org.springblade.core.mp.base.BaseServiceImpl;",
                    "import org.springblade.core.mp.service.impl.BladeServiceImpl;")
                text = re.sub(r'extends BaseServiceImpl<(\w+),\s*(\w+)>', r'extends BladeServiceImpl<\1, \2>', text)
                # 也修复 implements
                text = text.replace(
                    "import org.springblade.core.mp.base.BaseService;",
                    "import org.springblade.core.mp.service.BladeService;")
                text = re.sub(r'implements BaseService<(\w+)>', r'implements BladeService<\1>', text)
            
            if text != original:
                with open(path, "w", encoding="utf-8") as fh:
                    fh.write(text)
                files_fixed += 1

print(f"Fixed: {files_fixed} files")
