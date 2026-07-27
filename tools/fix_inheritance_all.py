#!/usr/bin/env python3
"""彻底修复 iot-link 所有继承关系，参照 nvr 规范"""
import os, re

DST = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link"

fixed = 0
for root, dirs, files in os.walk(DST):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            text = fh.read()
        
        original = text
        
        # === Controller: extends Xxx → extends BladeController ===
        if "Controller.java" in f and "Impl" not in f and "interface" not in text:
            # 替换任何 extends Xxx → extends BladeController
            text = re.sub(r'extends\s+\w+', 'extends BladeController', text)
            # 确保 import
            if "import org.springblade.core.boot.ctrl.BladeController;" not in text:
                text = text.replace(
                    "import org.springblade.core.boot.ctrl.",
                    "import org.springblade.core.boot.ctrl.BladeController;\nimport org.springblade.core.boot.ctrl.")
        
        # === Service 接口: extends Xxx → extends BladeService<Entity> ===
        if "Service.java" in f and "Impl" not in f and "interface" in text.split("{")[0]:
            # 找 extends 后面的内容，提取 Entity 泛型
            m = re.search(r'extends\s+(\w+)(<(\w+)>)?', text)
            if m:
                old = m.group(0)
                entity = m.group(3) if m.group(3) else "Object"
                text = text.replace(old, "extends BladeService<{}>".format(entity))
            # 确保 import
            if "import org.springblade.core.mp.service.BladeService;" not in text:
                # 在 package 行后加 import
                text = re.sub(
                    r'(package org\.springblade\.modules\.iot\.[^;]+;)',
                    r'\1\n\nimport org.springblade.core.mp.service.BladeService;',
                    text)
        
        # === ServiceImpl: extends Xxx → extends BladeServiceImpl<Mapper, Entity> ===
        if "ServiceImpl.java" in f:
            # 提取 Mapper 和 Entity
            m = re.search(r'extends\s+(\w+)(<([^>]+)>)?', text)
            if m:
                old = m.group(0)
                # 尝试从 implements 获取 Entity
                impl_m = re.search(r'implements\s+(\w+)', text)
                if impl_m:
                    svc_name = impl_m.group(1)
                    # 从 Service 名推断 Entity（去掉 Service 后缀）
                    entity = svc_name.replace("Service", "").replace("I", "")
                    # 找 Mapper 名
                    mapper_name = entity + "Mapper"
                    text = text.replace(old, "extends BladeServiceImpl<{}, {}>".format(mapper_name, entity))
            # 确保 import
            if "import org.springblade.core.mp.service.impl.BladeServiceImpl;" not in text:
                text = re.sub(
                    r'(package org\.springblade\.modules\.iot\.[^;]+;)',
                    r'\1\n\nimport org.springblade.core.mp.service.impl.BladeServiceImpl;',
                    text)
        
        # === Mapper: extends Xxx → extends BladeMapper<Entity> ===
        if "Mapper.java" in f and "interface" in text:
            m = re.search(r'extends\s+(\w+)(<(\w+)>)?', text)
            if m and "BladeMapper" not in m.group(0):
                old = m.group(0)
                entity = m.group(3) if m.group(3) else "Object"
                text = text.replace(old, "extends BladeMapper<{}>".format(entity))
        
        if text != original:
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(text)
            fixed += 1

print("Fixed: {} files".format(fixed))
