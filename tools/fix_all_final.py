#!/usr/bin/env python3
"""
一次性修复 iot-link 所有继承关系 + Mapper注解 + DateUtil引用
参照 nvr-platform 规范，修复后编译验证
"""
import os, re

DST = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link"

fixed_count = 0

for root, dirs, files in os.walk(DST):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            text = fh.read()
        
        original = text
        is_controller = "Controller.java" in f and "Impl" not in f
        is_service = "Service.java" in f and "Impl" not in f and "interface" in text.split("{")[0]
        is_service_impl = "ServiceImpl.java" in f
        is_mapper = "Mapper.java" in f
        
        # === 1. Controller: extends BladeController ===
        if is_controller:
            # 先移除任何已有的 extends
            text = re.sub(r'\bextends\s+\w+\b', '', text)
            # 添加 extends BladeController
            text = re.sub(
                r'(public class \w+Controller)\s*\{',
                r'\1 extends BladeController {',
                text
            )
            # 确保 import
            if "import org.springblade.core.boot.ctrl.BladeController;" not in text:
                text = text.replace(
                    "package org.springblade.modules.iot.",
                    "package org.springblade.modules.iot.",
                    1
                )
                text = re.sub(
                    r'(package org\.springblade\.modules\.iot\.[^;]+;)',
                    r'\1\n\nimport org.springblade.core.boot.ctrl.BladeController;',
                    text
                )
        
        # === 2. Service 接口: extends BladeService<Entity> ===
        if is_service:
            # 提取 Entity 名
            entity = f.replace("Service.java", "")
            # 处理 I 前缀
            if entity.startswith("I"):
                entity = entity[1:]
            # 移除旧 extends
            text = re.sub(r'\bextends\s+\w+(<[^>]+>)?\s*', '', text)
            # 添加 BladeService
            text = re.sub(
                r'(public interface \w+)\s*\{',
                r'\1 extends BladeService<{}> {{'.format(entity),
                text
            )
            # 确保 import
            if "import org.springblade.core.mp.service.BladeService;" not in text:
                text = re.sub(
                    r'(package org\.springblade\.modules\.iot\.[^;]+;)',
                    r'\1\n\nimport org.springblade.core.mp.service.BladeService;',
                    text
                )
        
        # === 3. ServiceImpl: extends BladeServiceImpl<Mapper, Entity> ===
        if is_service_impl:
            # 提取 Entity 名
            entity = f.replace("ServiceImpl.java", "")
            mapper = entity + "Mapper"
            # 移除旧 extends
            text = re.sub(r'\bextends\s+\w+(<[^>]+>)?\s*', '', text)
            # 移除旧 implements
            text = re.sub(r'\bimplements\s+\w+(<[^>]+>)?\s*', '', text)
            svc_name = entity + "Service"
            # 添加 BladeServiceImpl
            text = re.sub(
                r'(public class \w+)\s*\{',
                r'\1 extends BladeServiceImpl<{}, {}> implements {} {{'.format(mapper, entity, svc_name),
                text
            )
            # 确保 import
            if "import org.springblade.core.mp.service.impl.BladeServiceImpl;" not in text:
                text = re.sub(
                    r'(package org\.springblade\.modules\.iot\.[^;]+;)',
                    r'\1\n\nimport org.springblade.core.mp.service.impl.BladeServiceImpl;',
                    text
                )
        
        # === 4. Mapper: @Repository → @Mapper, extends BladeMapper<Entity> ===
        if is_mapper:
            # @Repository → @Mapper
            text = text.replace("@Repository", "@Mapper")
            text = text.replace("import org.springframework.stereotype.Repository;", "import org.apache.ibatis.annotations.Mapper;")
            # extends
            entity = f.replace("Mapper.java", "")
            text = re.sub(r'\bextends\s+\w+(<[^>]+>)?\s*', '', text)
            text = re.sub(
                r'(public interface \w+)\s*\{',
                r'\1 extends BladeMapper<{}> {{'.format(entity),
                text
            )
            if "import org.springblade.core.mp.support.BladeMapper;" not in text:
                text = re.sub(
                    r'(package org\.springblade\.modules\.iot\.[^;]+;)',
                    r'\1\n\nimport org.springblade.core.mp.support.BladeMapper;',
                    text
                )
        
        # === 5. DateUtil 引用 ===
        text = text.replace("import cn.hutool.core.date.DateUtil;", "import org.springblade.common.utils.DateUtil;")
        text = text.replace("import cn.hutool.core.date.DateUtils;", "import org.springblade.common.utils.DateUtil;")
        text = text.replace("import com.mqttsnet.basic.utils.DateUtils;", "import org.springblade.common.utils.DateUtil;")
        
        # === 6. 清理错误 import ===
        # 移除 thinglinks 残留 import
        for bad_import in [
            "import com.mqttsnet.basic.",
            "import com.mqttsnet.thinglinks.",
            "import com.baomidou.dynamic.datasource.annotation.DS;",
        ]:
            for line in text.split("\n"):
                if bad_import in line:
                    text = text.replace(line + "\n", "")
        
        if text != original:
            with open(path, "w", encoding="utf-8") as fh:
                fh.write(text)
            fixed_count += 1

print("Fixed: {} files".format(fixed_count))
