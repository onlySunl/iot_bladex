#!/usr/bin/env python3
"""
thinglinks-link → iot-link 完整迁移脚本
规则：Controller→BladeController, Service→BaseService, ServiceImpl→BaseServiceImpl
      Manager→合并到Service/ServiceImpl, Mapper→BladeMapper
"""
import os, re, shutil

SRC_BIZ = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
SRC_CTRL = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-controller\src\main\java\com\mqttsnet\thinglinks"
SRC_RES = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\resources"

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"
DST_RES = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\resources"

# 包名替换规则
PKG_REPLACEMENTS = [
    ("com.mqttsnet.thinglinks", "org.springblade.modules.iot"),
    ("com.mqttsnet.basic.utils.StrPool", "org.springblade.common.utils.StrPool"),
    ("com.mqttsnet.basic.utils.DateUtils", "org.springblade.common.utils.DateUtils"),
    ("com.mqttsnet.basic.utils.ArgumentAssert", "org.springblade.core.tool.utils.Func"),
    ("com.mqttsnet.basic.utils.BeanPlusUtil", "org.springblade.core.tool.utils.BeanUtil"),
    ("com.mqttsnet.basic.context.ContextUtil", "org.springblade.core.secure.utils.AuthUtil"),
    ("com.mqttsnet.basic.exception.BizException", "org.springblade.core.log.exception.ServiceException"),
    ("com.mqttsnet.basic.exception.ServiceException", "org.springblade.core.log.exception.ServiceException"),
    ("com.mqttsnet.basic.cache.redis2.CacheResult", "org.springblade.core.tool.api.R"),
    ("com.mqttsnet.basic.cache.repository.CachePlusOps", "org.springframework.data.redis.core.RedisTemplate"),
    ("com.mqttsnet.basic.model.cache.CacheHashKey", "java.lang.String"),
    ("cn.hutool.core.text.StrPool", "org.springblade.common.utils.StrPool"),
    ("com.mqttsnet.basic.base.R", "org.springblade.core.tool.api.R"),
    ("com.mqttsnet.basic.base.manager.SuperManager", "org.springblade.core.mp.base.BaseService"),
    ("com.mqttsnet.basic.base.manager.impl.SuperManagerImpl", "org.springblade.core.mp.base.BaseServiceImpl"),
    ("com.mqttsnet.basic.base.service.SuperService", "org.springblade.core.mp.base.BaseService"),
    ("com.mqttsnet.basic.base.service.impl.SuperServiceImpl", "org.springblade.core.mp.base.BaseServiceImpl"),
    ("com.mqttsnet.basic.base.mapper.SuperMapper", "org.springblade.core.mp.support.BladeMapper"),
    ("com.mqttsnet.basic.base.entity.SuperEntity", "org.springblade.core.mp.base.BaseEntity"),
    ("com.mqttsnet.basic.base.entity.TreeEntity", "org.springblade.core.mp.base.BaseEntity"),
    ("com.mqttsnet.basic.base.entity.Entity", "org.springblade.core.mp.base.BaseEntity"),
    ("com.mqttsnet.basic.annotation.echo.Echo", "org.springblade.modules.iot.common.annotation.Echo"),
    ("com.mqttsnet.basic.interfaces.echo.EchoVO", "org.springblade.modules.iot.common.interfaces.echo.EchoVO"),
    ("com.mqttsnet.basic.interfaces.echo.LoadService", "org.springblade.modules.iot.common.interfaces.echo.LoadService"),
]

def fix_content(text, is_controller=False, is_service=False, is_service_impl=False, is_mapper=False):
    """修复文件内容"""
    # 1. 包名替换
    for old, new in PKG_REPLACEMENTS:
        text = text.replace(old, new)
    
    # 2. 移除 @DS 注解
    text = re.sub(r'\s*@DS\([^)]+\)\s*\n', '\n', text)
    text = re.sub(r'import com\.baomidou\.dynamic\.datasource\.annotation\.DS;\s*\n', '', text)
    
    # 3. Controller 特殊处理
    if is_controller:
        # extends SuperController → extends BladeController
        text = text.replace("extends SuperController", "extends BladeController")
        text = text.replace("import org.springblade.modules.iot.common.controller.SuperController;", "import org.springblade.core.boot.ctrl.BladeController;")
        # R< → 保持不变（BladeX 也用 R）
        # @Api → @Tag
        text = text.replace("import io.swagger.annotations.Api;", "import io.swagger.v3.oas.annotations.tags.Tag;")
        text = re.sub(r'@Api\([^)]+\)', '@Tag(name = "IoT")', text)
    
    # 4. Service 特殊处理
    if is_service:
        # extends SuperService<Long, Entity> → extends BaseService<Entity>
        text = re.sub(r'extends SuperService<Long,\s*(\w+)>', r'extends BaseService<\1>', text)
        # implements SuperManager<Entity> → (合并到 BaseService)
        text = re.sub(r',\s*SuperManager<\w+>', '', text)
        text = re.sub(r'implements SuperManager<\w+>', '', text)
    
    # 5. ServiceImpl 特殊处理
    if is_service_impl:
        # extends SuperServiceImpl<Mapper, Entity> → extends BaseServiceImpl<Mapper, Entity>
        text = re.sub(r'extends SuperServiceImpl<(\w+),\s*(\w+)>', r'extends BaseServiceImpl<\1, \2>', text)
        # implements SuperManager<Entity> → 移除
        text = re.sub(r',\s*SuperManager<\w+>', '', text)
        text = re.sub(r'implements SuperManager<\w+>', '', text)
        # superManager.xxx → this.xxx / baseMapper.xxx
        text = text.replace("superManager.count(", "this.count(")
        text = text.replace("superManager.getById(", "this.getById(")
        text = text.replace("superManager.save(", "this.save(")
        text = text.replace("superManager.updateById(", "this.updateById(")
        text = text.replace("superManager.removeByIds(", "this.removeByIds(")
        text = text.replace("superManager.list(", "this.list(")
        text = text.replace("superManager.page(", "this.page(")
        text = text.replace("superManager.getOne(", "this.getOne(")
        text = text.replace("superManager.saveBatch(", "this.saveBatch(")
        text = text.replace("superManager.updateBatchById(", "this.updateBatchById(")
        text = re.sub(r'superManager\.\w+\(', 'this.baseMapper.selectList(', text)  # fallback
        # Wraps.lbQ → Wrappers.lambdaQuery
        text = text.replace("Wraps.lbQ()", "Wrappers.lambdaQuery()")
        text = text.replace("Wraps.<", "Wrappers.<")
        # ArgumentAssert → Func
        text = re.sub(r'ArgumentAssert\.notNull\(([^,]+),\s*"([^"]*)"\)', r'Func.hasText(\1, "\2")', text)
        text = re.sub(r'ArgumentAssert\.notBlank\(([^,]+),\s*"([^"]*)"\)', r'Func.hasText(\1, "\2")', text)
        text = re.sub(r'ArgumentAssert\.notEmpty\(([^,]+),\s*"([^"]*)"\)', r'Func.hasText(\1, "\2")', text)
        text = re.sub(r'ArgumentAssert\.isTrue\(([^,]+),\s*"([^"]*)"\)', r'if (!(\1)) throw new ServiceException("\2")', text)
        text = re.sub(r'ArgumentAssert\.isNull\(([^,]+),\s*"([^"]*)"\)', r'if (\1 != null) throw new ServiceException("\2")', text)
        text = re.sub(r'ArgumentAssert\.\w+\([^)]+\)', '// ArgumentAssert removed', text)
        # BizException.wrap → new ServiceException
        text = re.sub(r'BizException\.wrap\("([^"]*)"\)', r'new ServiceException("\1")', text)
        text = re.sub(r'throw BizException\.wrap\(([^)]+)\)', r'throw new ServiceException(\1)', text)
        # ContextUtil → AuthUtil
        text = text.replace("ContextUtil.getCurrentDeptId()", "AuthUtil.getDeptId()")
        text = text.replace("ContextUtil.getUserId()", "AuthUtil.getUserId()")
        text = text.replace("ContextUtil.getTenantId()", "AuthUtil.getTenantId()")
    
    # 6. Mapper 特殊处理
    if is_mapper:
        # extends SuperMapper<Entity> → extends BladeMapper<Entity>
        text = re.sub(r'extends SuperMapper<(\w+)>', r'extends BladeMapper<\1>', text)
    
    # 7. 通用：移除 @TableLogic
    text = re.sub(r'\s*@TableLogic\s*\n', '\n', text)
    
    # 8. LIKE → EQUAL
    text = text.replace("condition = LIKE", "condition = EQUAL")
    
    return text


def copy_controllers():
    """复制 Controller 文件"""
    count = 0
    for root, dirs, files in os.walk(SRC_CTRL):
        for f in files:
            if not f.endswith(".java"):
                continue
            if "Test" in f or f.endswith("Test.java"):
                continue
            
            src = os.path.join(root, f)
            rel = os.path.relpath(src, SRC_CTRL)
            dst = os.path.join(DST_CTRL, rel)
            
            with open(src, "rb") as fh:
                raw = fh.read()
            if raw.startswith(b"\xef\xbb\xbf"):
                raw = raw[3:]
            
            text = raw.decode("utf-8", errors="replace")
            text = fix_content(text, is_controller=True)
            
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            with open(dst, "w", encoding="utf-8") as fh:
                fh.write(text)
            count += 1
    return count


def copy_services():
    """复制 Service/ServiceImpl/Mapper 文件，跳过 manager 目录"""
    svc_count = 0
    impl_count = 0
    mapper_count = 0
    
    for root, dirs, files in os.walk(SRC_BIZ):
        # 跳过 manager 目录
        if "manager" in dirs:
            dirs.remove("manager")
        # 跳过 event 目录（后续单独处理）
        if "event" in dirs:
            dirs.remove("event")
        # 跳过 statemachine 目录
        if "statemachine" in dirs:
            dirs.remove("statemachine")
        if "config" in dirs:
            dirs.remove("config")
        
        for f in files:
            if not f.endswith(".java"):
                continue
            if "Test" in f:
                continue
            
            src = os.path.join(root, f)
            rel = os.path.relpath(src, SRC_BIZ)
            dst = os.path.join(DST_BIZ, rel)
            
            with open(src, "rb") as fh:
                raw = fh.read()
            if raw.startswith(b"\xef\xbb\xbf"):
                raw = raw[3:]
            
            text = raw.decode("utf-8", errors="replace")
            
            # 判断文件类型
            is_service = "Service.java" in f and "Impl" not in f
            is_service_impl = "ServiceImpl.java" in f
            is_mapper = "Mapper.java" in f
            
            text = fix_content(text, is_service=is_service, is_service_impl=is_service_impl, is_mapper=is_mapper)
            
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            with open(dst, "w", encoding="utf-8") as fh:
                fh.write(text)
            
            if is_service:
                svc_count += 1
            elif is_service_impl:
                impl_count += 1
            elif is_mapper:
                mapper_count += 1
    
    return svc_count, impl_count, mapper_count


def copy_mapper_xml():
    """复制 Mapper XML 文件"""
    count = 0
    mapper_dir = os.path.join(SRC_RES, "mapper")
    if not os.path.exists(mapper_dir):
        # 搜索 resources 下的 mapper 目录
        for root, dirs, files in os.walk(SRC_RES):
            if "mapper" in root:
                mapper_dir = root
                break
    
    for root, dirs, files in os.walk(SRC_RES):
        for f in files:
            if not f.endswith(".xml"):
                continue
            src = os.path.join(root, f)
            rel = os.path.relpath(src, SRC_RES)
            dst = os.path.join(DST_RES, rel)
            
            with open(src, "rb") as fh:
                raw = fh.read()
            if raw.startswith(b"\xef\xbb\xbf"):
                raw = raw[3:]
            
            text = raw.decode("utf-8", errors="replace")
            # 修复 namespace
            text = text.replace("com.mqttsnet.thinglinks", "org.springblade.modules.iot")
            
            os.makedirs(os.path.dirname(dst), exist_ok=True)
            with open(dst, "w", encoding="utf-8") as fh:
                fh.write(text)
            count += 1
    return count


# 执行迁移
print("=== 1. Copy Controllers ===")
ctrl_count = copy_controllers()
print(f"Controllers: {ctrl_count}")

print("\n=== 2. Copy Services/Mappers ===")
svc, impl, mapper = copy_services()
print(f"Services: {svc}, ServiceImpls: {impl}, Mappers: {mapper}")

print("\n=== 3. Copy Mapper XML ===")
xml_count = copy_mapper_xml()
print(f"XML files: {xml_count}")

print(f"\n=== Total: {ctrl_count + svc + impl + mapper + xml_count} files ===")
