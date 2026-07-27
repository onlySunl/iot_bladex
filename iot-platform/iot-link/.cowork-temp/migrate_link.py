#!/usr/bin/env python3
"""
Migration script: thinglinks-link → BladeX 4.9 iot-link
"""
import os, re, shutil, glob

SRC_BIZ = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\java\com\mqttsnet\thinglinks"
SRC_CTRL = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-controller\src\main\java\com\mqttsnet\thinglinks"
SRC_XML = r"D:\workspace\IOT\thinglinks\thinglinks-cloud\thinglinks-link\thinglinks-link-biz\src\main\resources"
DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"
DST_XML = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\resources\mapper"

# Modules in biz layer (not entity-related)
BIZ_MODULES = [
    "cacert", "cache", "dashboard", "device", "ota", "product",
    "productcommand", "productcommandrequest", "productcommandresponse",
    "productproperty", "productpublishrecord", "productservice", "producttopic",
    "productversion", "productversionchangelog", "utils"
]

# Controller modules (subdirs under controller source)
CTRL_MODULES = [
    "cacert", "dashboard", "device", "ota", "product",
    "productcommand", "productcommandrequest", "productcommandresponse",
    "productproperty", "productpublishrecord", "productservice", "producttopic",
    "productversion", "productversionchangelog",
]

# Files to skip (entity-related)
SKIP_PATTERNS = [
    r"\\entity\\", r"\\dto\\", r"\\vo\\", r"\\enumeration\\", r"\\enums\\",
    r"\\constant\\", r"\\converter\\", r"\\config\\", r"\\easyexcel\\(?!impl)",
    r"\\DeviceExportData\.java$", r"\\DeviceImportData\.java$",
]

# Manager files → will be merged, not copied as-is
MANAGER_PATTERN = re.compile(r"\\manager\\")

def should_skip(filepath):
    for p in SKIP_PATTERNS:
        if re.search(p, filepath):
            return True
    return False

def replace_package(content, module_name, is_controller=False):
    """Replace thinglinks packages with BladeX packages"""
    
    # Base package replacement
    content = content.replace("com.mqttsnet.thinglinks", "org.springblade.modules.iot")
    
    # Specific replacements
    content = content.replace("com.mqttsnet.basic.utils", "org.springblade.core.tool.utils")
    content = content.replace("com.mqttsnet.basic.exception", "org.springblade.core.log.exception")
    content = content.replace("com.mqttsnet.basic.context", "")  # delete context imports
    content = content.replace("com.mqttsnet.basic.cache", "org.springblade.common.cache")
    content = content.replace("com.mqttsnet.basic.base", "org.springblade.core")
    
    # Fix package declaration
    content = re.sub(
        r'^package com\.mqttsnet\.thinglinks\.(\w+)',
        r'package org.springblade.modules.iot.\1',
        content, flags=re.MULTILINE
    )
    
    # Fix sub-packages like com.mqttsnet.thinglinks.device.service → org.springblade.modules.iot.device.service
    content = re.sub(
        r'^package com\.mqttsnet\.thinglinks',
        r'package org.springblade.modules.iot',
        content, flags=re.MULTILINE
    )
    
    # Replace StrPool with StringPool
    content = content.replace("cn.hutool.core.text.StrPool", "org.springblade.core.tool.utils.StringPool")
    content = content.replace("import com.mqttsnet.basic.utils.StrPool;", "import org.springblade.core.tool.utils.StringPool;")
    
    # Replace SuperController with BladeController
    content = content.replace("extends SuperController", "extends BladeController")
    content = content.replace("import com.mqttsnet.basic.base.controller.SuperController;", 
                              "import org.springblade.core.boot.ctrl.BladeController;")
    
    # Replace SuperService with BaseService
    content = re.sub(r'extends SuperService<Long,\s*(\w+)>', r'extends BaseService<\1>', content)
    content = content.replace("import com.mqttsnet.basic.base.service.SuperService;",
                              "import org.springblade.core.mp.base.BaseService;")
    
    # Replace SuperServiceImpl with BaseServiceImpl
    content = re.sub(r'extends SuperServiceImpl<(\w+),\s*(\w+)>', r'extends BaseServiceImpl<\1, \2>', content)
    content = content.replace("import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;",
                              "import org.springblade.core.mp.base.BaseServiceImpl;")
    
    # Replace SuperMapper with BladeMapper
    content = re.sub(r'extends SuperMapper<(\w+)>', r'extends BladeMapper<\1>', content)
    content = content.replace("import com.mqttsnet.basic.base.mapper.SuperMapper;",
                              "import org.springblade.core.mp.support.BladeMapper;")
    
    # Replace SuperManager with BaseService (when merging, SuperManager methods go to Service)
    content = re.sub(r'extends SuperManager<(\w+)>', r'extends BaseService<\1>', content)
    content = content.replace("import com.mqttsnet.basic.base.manager.SuperManager;",
                              "import org.springblade.core.mp.base.BaseService;")
    
    # Replace R imports and usage
    content = content.replace("import com.mqttsnet.basic.base.R;", "import org.springblade.core.tool.api.R;")
    content = content.replace("import static com.mqttsnet.basic.base.R.ok;", "")
    
    # Replace ContextUtil with AuthUtil
    content = content.replace("import com.mqttsnet.basic.context.ContextUtil;", 
                              "import org.springblade.core.secure.utils.AuthUtil;")
    content = content.replace("ContextUtil.getUserId()", "AuthUtil.getUserId()")
    content = content.replace("ContextUtil.getTenantId()", "AuthUtil.getTenantId()")
    content = content.replace("ContextUtil.getUserName()", "AuthUtil.getUserName()")
    content = content.replace("ContextUtil.getUser()", "AuthUtil.getUser()")
    
    # Replace ArgumentAssert with Func
    content = content.replace("import com.mqttsnet.basic.utils.ArgumentAssert;", "")
    content = re.sub(r'ArgumentAssert\.\w+\([^)]+\)\s*;?\s*\n', '', content)
    
    # Remove @DS annotation
    content = re.sub(r'@DS\s*\(\s*"[^"]*"\s*\)\s*\n', '', content)
    content = re.sub(r'import com\.baomidou\.dynamic\.datasource\.annotation\.DS;\s*\n', '', content)
    
    # Replace @WebLog annotation
    content = content.replace("import com.mqttsnet.basic.annotation.log.WebLog;", "")
    content = re.sub(r'@WebLog\s*\([^)]*\)\s*\n', '', content)
    content = re.sub(r'@WebLog\s*\n', '', content)
    
    # Replace BeanPlusUtil with BeanUtil
    content = content.replace("import com.mqttsnet.basic.utils.BeanPlusUtil;", 
                              "import org.springblade.core.tool.utils.BeanUtil;")
    content = content.replace("BeanPlusUtil.", "BeanUtil.")
    
    # Replace SnowflakeIdUtil
    content = content.replace("import com.mqttsnet.basic.utils.SnowflakeIdUtil;", "")
    content = content.replace("SnowflakeIdUtil.", "org.springblade.core.tool.utils.IdUtil.")
    
    # Replace TenantUtil
    content = content.replace("import com.mqttsnet.basic.utils.TenantUtil;", "")
    
    # Replace Builder
    content = content.replace("import com.mqttsnet.basic.converter.Builder;", "")
    
    # Replace Wraps with Wrappers
    content = content.replace("import com.mqttsnet.basic.database.mybatis.conditions.Wraps;",
                              "import com.baomidou.mybatisplus.core.toolkit.Wrappers;")
    content = content.replace("Wraps.", "Wrappers.")
    
    # Replace QueryWrap
    content = content.replace("import com.mqttsnet.basic.database.mybatis.conditions.query.QueryWrap;", "")
    
    # Replace PageParams
    content = content.replace("import com.mqttsnet.basic.base.request.PageParams;",
                              "import org.springblade.core.mp.support.Query;")
    content = content.replace("PageParams<", "Query<")
    
    # Replace EchoService
    content = content.replace("import com.mqttsnet.basic.interfaces.echo.EchoService;", "")
    content = re.sub(r'private\s+(final\s+)?EchoService\s+\w+;\s*\n', '', content)
    
    # Replace EasyExcelListener / EasyExcelUtils / ExcelCheckManager / ExcelImportErrDto
    content = content.replace("import com.mqttsnet.basic.easyexcel.EasyExcelListener;", "")
    content = content.replace("import com.mqttsnet.basic.easyexcel.EasyExcelUtils;", "")
    content = content.replace("import com.mqttsnet.basic.easyexcel.ExcelCheckManager;", "")
    content = content.replace("import com.mqttsnet.basic.easyexcel.ExcelImportErrDto;", "")
    
    # Replace JsonUtil
    content = content.replace("import com.mqttsnet.basic.jackson.JsonUtil;",
                              "import org.springblade.core.tool.utils.JsonUtil;")
    
    # Replace CacheKey
    content = content.replace("import com.mqttsnet.basic.model.cache.CacheKey;", "")
    
    # Replace DistributedLock
    content = content.replace("import com.mqttsnet.basic.cache.lock.DistributedLock;", "")
    content = content.replace("import com.mqttsnet.basic.cache.lock.LockRunResult;", "")
    
    # Fix thinglinks common references
    content = content.replace("com.mqttsnet.thinglinks.common", "org.springblade.modules.iot.common")
    content = content.replace("com.mqttsnet.thinglinks.datascope", "org.springblade.modules.iot.datascope")
    
    # Remove unused imports that became empty or invalid
    content = re.sub(r'import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content)
    
    # Fix @RequestMapping paths
    if is_controller:
        # Fix @RequestMapping on class
        content = re.sub(
            r'@RequestMapping\("/device"\)',
            r'@RequestMapping("/iot/device")',
            content
        )
        # General pattern for other controllers
        for mod in CTRL_MODULES:
            old_path = f'@RequestMapping("/{mod}")'
            new_path = f'@RequestMapping("/iot/{mod}")'
            content = content.replace(old_path, new_path)
    
    # Replace @RequiredArgsConstructor with @AllArgsConstructor
    content = content.replace("import lombok.RequiredArgsConstructor;", "import lombok.AllArgsConstructor;")
    content = content.replace("@RequiredArgsConstructor", "@AllArgsConstructor")
    
    # Clean up empty lines (3+ consecutive newlines → 2)
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    return content


def copy_controller_files():
    """Copy controller Java files from source to target"""
    print("=== Copying Controller files ===")
    for mod in CTRL_MODULES:
        src_dir = os.path.join(SRC_CTRL, mod, "controller")
        if not os.path.isdir(src_dir):
            # Check subdirs like cacert/controller/license
            for root, dirs, files in os.walk(os.path.join(SRC_CTRL, mod)):
                for f in files:
                    if f.endswith(".java"):
                        src_path = os.path.join(root, f)
                        rel = os.path.relpath(src_path, SRC_CTRL)
                        # Determine target path
                        parts = rel.replace("\\", "/").split("/")
                        # e.g. cacert/controller/license/CaCertLicenseController.java
                        # → cacert/controller/CaCertLicenseController.java
                        if "controller" in parts:
                            # Keep subdirs after controller
                            dst_rel = "/".join(parts)
                        else:
                            dst_rel = f"{mod}/controller/{f}"
                        
                        dst_path = os.path.join(DST_CTRL, dst_rel)
                        os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                        
                        with open(src_path, 'r', encoding='utf-8') as fh:
                            content = fh.read()
                        
                        content = replace_package(content, mod, is_controller=True)
                        
                        with open(dst_path, 'w', encoding='utf-8') as fh:
                            fh.write(content)
                        print(f"  Controller: {dst_rel}")
            continue
        
        for root, dirs, files in os.walk(src_dir):
            for f in files:
                if f.endswith(".java"):
                    src_path = os.path.join(root, f)
                    rel = os.path.relpath(src_path, os.path.join(SRC_CTRL, mod))
                    dst_rel = f"{mod}/{rel}"
                    dst_path = os.path.join(DST_CTRL, dst_rel)
                    os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                    
                    with open(src_path, 'r', encoding='utf-8') as fh:
                        content = fh.read()
                    
                    content = replace_package(content, mod, is_controller=True)
                    
                    with open(dst_path, 'w', encoding='utf-8') as fh:
                        fh.write(content)
                    print(f"  Controller: {dst_rel}")


def copy_biz_files():
    """Copy biz Java files (service, mapper, etc.) from source to target"""
    print("\n=== Copying BIZ files ===")
    
    manager_methods = {}  # module -> { ManagerName -> [method_signatures] }
    manager_impl_methods = {}  # module -> { ManagerName -> [method_bodies] }
    
    for mod in BIZ_MODULES:
        src_dir = os.path.join(SRC_BIZ, mod)
        if not os.path.isdir(src_dir):
            continue
        
        for root, dirs, files in os.walk(src_dir):
            for f in files:
                if not f.endswith(".java"):
                    continue
                
                src_path = os.path.join(root, f)
                rel = os.path.relpath(src_path, SRC_BIZ)
                
                # Skip entity-related files
                if should_skip(rel):
                    continue
                
                # Handle Manager files separately (merge later)
                if MANAGER_PATTERN.search(rel):
                    # Read but don't copy yet
                    with open(src_path, 'r', encoding='utf-8') as fh:
                        content = fh.read()
                    
                    manager_name = f.replace(".java", "")
                    if mod not in manager_methods:
                        manager_methods[mod] = {}
                        manager_impl_methods[mod] = {}
                    
                    if "impl" in rel.lower():
                        manager_impl_methods[mod][manager_name] = content
                    else:
                        manager_methods[mod][manager_name] = content
                    print(f"  Manager (deferred): {rel}")
                    continue
                
                # Determine target path
                # e.g. device/service/DeviceService.java → device/service/DeviceService.java
                # e.g. device/mapper/DeviceMapper.java → device/mapper/DeviceMapper.java
                # e.g. device/event/publisher/DeviceEventPublisher.java → device/event/publisher/DeviceEventPublisher.java
                dst_rel = rel
                dst_path = os.path.join(DST_BIZ, dst_rel)
                os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                
                with open(src_path, 'r', encoding='utf-8') as fh:
                    content = fh.read()
                
                content = replace_package(content, mod)
                
                with open(dst_path, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                print(f"  BIZ: {dst_rel}")
    
    return manager_methods, manager_impl_methods


def merge_manager_to_service(manager_methods, manager_impl_methods):
    """Merge Manager interface methods into Service interface and ManagerImpl into ServiceImpl"""
    print("\n=== Merging Manager → Service ===")
    
    for mod, managers in manager_methods.items():
        for mgr_name, mgr_content in managers.items():
            # Find the corresponding Service: DeviceManager → DeviceService
            # Remove "Manager" suffix, add "Service"
            base_name = mgr_name.replace("Manager", "")
            service_name = base_name + "Service"
            service_impl_name = base_name + "ServiceImpl"
            
            # Find the Service interface file
            service_dir = os.path.join(DST_BIZ, mod, "service")
            service_file = os.path.join(service_dir, service_name + ".java")
            
            if not os.path.isfile(service_file):
                # Check subdirs
                for root, dirs, files in os.walk(service_dir):
                    if service_name + ".java" in files:
                        service_file = os.path.join(root, service_name + ".java")
                        break
            
            if os.path.isfile(service_file):
                # Extract method signatures from Manager (everything between first { and last })
                # Find all method declarations (public/private/protected + return type + method name + params)
                mgr_lines = mgr_content.split('\n')
                methods = extract_interface_methods(mgr_lines)
                
                if methods:
                    with open(service_file, 'r', encoding='utf-8') as fh:
                        svc_content = fh.read()
                    
                    # Insert methods before the last '}' of the interface
                    last_brace = svc_content.rfind('}')
                    methods_text = '\n'.join(methods)
                    svc_content = svc_content[:last_brace] + '\n' + methods_text + '\n' + svc_content[last_brace:]
                    
                    with open(service_file, 'w', encoding='utf-8') as fh:
                        fh.write(svc_content)
                    print(f"  Merged: {mgr_name} → {service_name}")
            
            # Merge ManagerImpl into ServiceImpl
            if mod in manager_impl_methods and mgr_name in manager_impl_methods[mod]:
                mgr_impl_content = manager_impl_methods[mod][mgr_name]
                
                service_impl_dir = os.path.join(DST_BIZ, mod, "service", "impl")
                service_impl_file = os.path.join(service_impl_dir, service_impl_name + ".java")
                
                if not os.path.isfile(service_impl_file):
                    for root, dirs, files in os.walk(os.path.join(DST_BIZ, mod, "service")):
                        if service_impl_name + ".java" in files:
                            service_impl_file = os.path.join(root, service_impl_name + ".java")
                            break
                
                if os.path.isfile(service_impl_file):
                    # Apply package replacements to manager impl content
                    mgr_impl_content = replace_package(mgr_impl_content, mod)
                    
                    # Extract method bodies from ManagerImpl
                    mgr_methods = extract_impl_methods(mgr_impl_content)
                    
                    if mgr_methods:
                        with open(service_impl_file, 'r', encoding='utf-8') as fh:
                            svc_impl = fh.read()
                        
                        # Insert before last '}'
                        last_brace = svc_impl.rfind('}')
                        methods_text = '\n\n'.join(mgr_methods)
                        svc_impl = svc_impl[:last_brace] + '\n\n' + methods_text + '\n' + svc_impl[last_brace:]
                        
                        with open(service_impl_file, 'w', encoding='utf-8') as fh:
                            fh.write(svc_impl)
                        print(f"  Merged impl: {mgr_name}Impl → {service_impl_name}")


def extract_interface_methods(lines):
    """Extract method signatures from interface content"""
    methods = []
    in_method = False
    method_lines = []
    brace_depth = 0
    
    for line in lines:
        stripped = line.strip()
        
        # Skip package, import, class declaration
        if stripped.startswith('package ') or stripped.startswith('import ') or stripped.startswith('public interface'):
            continue
        if stripped.startswith('}') and not in_method:
            continue
        if stripped.startswith('//') or stripped.startswith('/*') or stripped.startswith('*'):
            if not in_method:
                continue
        
        # Detect method start
        if not in_method and ('(' in stripped and ')' in stripped) and not stripped.startswith('@'):
            # Check if it's a method declaration
            if any(kw in stripped for kw in [' void ', ' boolean ', ' int ', ' long ', ' String ', ' List<', ' Map<', ' Set<', ' Optional<', ' IPage<', ' Device', ' Ota', ' Product', ' CaCert']):
                in_method = True
                method_lines = [line]
                if ';' in stripped:
                    methods.append('\n'.join(method_lines))
                    in_method = False
                    method_lines = []
                continue
        
        if in_method:
            method_lines.append(line)
            if ';' in stripped:
                methods.append('\n'.join(method_lines))
                in_method = False
                method_lines = []
    
    return methods


def extract_impl_methods(content):
    """Extract method implementations from a class body"""
    # Simple approach: split by method signature pattern, extract complete methods
    lines = content.split('\n')
    methods = []
    in_method = False
    method_lines = []
    brace_depth = 0
    in_class = False
    
    for line in lines:
        stripped = line.strip()
        
        if stripped.startswith('package ') or stripped.startswith('import '):
            continue
        if 'class ' in stripped:
            in_class = True
            continue
        if not in_class:
            continue
        
        # Skip annotations
        if stripped.startswith('@') and not in_method:
            continue
        
        # Detect method start (has ( and ), and is a method signature)
        if not in_method and '(' in stripped and ')' in stripped:
            is_method_sig = any(kw in stripped for kw in [
                'public ', 'private ', 'protected ', ' void ', ' boolean ', ' int ', ' long ',
                ' String ', ' List<', ' Map<', ' Set<', ' Optional<', ' IPage<',
                'Device', 'Ota', 'Product', 'CaCert', ' return '
            ])
            if is_method_sig and not stripped.startswith('import') and not stripped.startswith('package'):
                in_method = True
                method_lines = [line]
                brace_depth = stripped.count('{') - stripped.count('}')
                if brace_depth <= 0 and ';' in stripped:
                    methods.append('\n'.join(method_lines))
                    in_method = False
                    method_lines = []
                continue
        
        if in_method:
            method_lines.append(line)
            brace_depth += stripped.count('{') - stripped.count('}')
            if brace_depth <= 0 and len(method_lines) > 0:
                methods.append('\n'.join(method_lines))
                in_method = False
                method_lines = []
    
    return methods


def copy_xml_files():
    """Copy and fix MyBatis XML mapper files"""
    print("\n=== Copying XML Mapper files ===")
    
    xml_dirs = [
        "mapper_cacert", "mapper_device", "mapper_ota", "mapper_product",
        "mapper_productCommand", "mapper_productCommandRequest", "mapper_productCommandResponse",
        "mapper_productProperty", "mapper_productService", "mapper_productTopic"
    ]
    
    for xml_dir in xml_dirs:
        src_xml_dir = os.path.join(SRC_XML, xml_dir)
        if not os.path.isdir(src_xml_dir):
            continue
        
        for root, dirs, files in os.walk(src_xml_dir):
            for f in files:
                if f.endswith(".xml"):
                    src_path = os.path.join(root, f)
                    
                    with open(src_path, 'r', encoding='utf-8') as fh:
                        content = fh.read()
                    
                    # Fix namespace
                    content = content.replace(
                        "com.mqttsnet.thinglinks",
                        "org.springblade.modules.iot"
                    )
                    
                    # Determine target path
                    rel = os.path.relpath(src_path, src_xml_dir)
                    dst_path = os.path.join(DST_XML, rel)
                    os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                    
                    with open(dst_path, 'w', encoding='utf-8') as fh:
                        fh.write(content)
                    print(f"  XML: {rel}")


def copy_utils():
    """Copy utility classes"""
    print("\n=== Copying Utils ===")
    src_utils = os.path.join(SRC_BIZ, "utils")
    if os.path.isdir(src_utils):
        for root, dirs, files in os.walk(src_utils):
            for f in files:
                if f.endswith(".java"):
                    src_path = os.path.join(root, f)
                    rel = os.path.relpath(src_path, SRC_BIZ)
                    dst_path = os.path.join(DST_BIZ, rel)
                    os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                    
                    with open(src_path, 'r', encoding='utf-8') as fh:
                        content = fh.read()
                    
                    content = replace_package(content, "utils")
                    
                    with open(dst_path, 'w', encoding='utf-8') as fh:
                        fh.write(content)
                    print(f"  Utils: {rel}")


def copy_ws_controller():
    """Copy WebSocket controller"""
    print("\n=== Copying WebSocket Controller ===")
    src_ws = os.path.join(SRC_CTRL, "ws", "controller")
    if os.path.isdir(src_ws):
        for f in os.listdir(src_ws):
            if f.endswith(".java"):
                src_path = os.path.join(src_ws, f)
                dst_dir = os.path.join(DST_CTRL, "ws", "controller")
                os.makedirs(dst_dir, exist_ok=True)
                dst_path = os.path.join(dst_dir, f)
                
                with open(src_path, 'r', encoding='utf-8') as fh:
                    content = fh.read()
                
                content = replace_package(content, "ws", is_controller=True)
                
                with open(dst_path, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                print(f"  WS Controller: {f}")


def copy_inner_controller():
    """Copy inner controller files"""
    print("\n=== Copying Inner Controller ===")
    src_inner = os.path.join(SRC_CTRL, "inner", "controller")
    if os.path.isdir(src_inner):
        for f in os.listdir(src_inner):
            if f.endswith(".java"):
                src_path = os.path.join(src_inner, f)
                dst_dir = os.path.join(DST_CTRL, "inner", "controller")
                os.makedirs(dst_dir, exist_ok=True)
                dst_path = os.path.join(dst_dir, f)
                
                with open(src_path, 'r', encoding='utf-8') as fh:
                    content = fh.read()
                
                content = replace_package(content, "inner", is_controller=True)
                
                with open(dst_path, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                print(f"  Inner Controller: {f}")


def copy_anytenant_controller():
    """Copy anytenant controller files"""
    print("\n=== Copying AnyTenant Controller ===")
    src_at = os.path.join(SRC_CTRL, "anytenant", "controller")
    if os.path.isdir(src_at):
        for f in os.listdir(src_at):
            if f.endswith(".java"):
                src_path = os.path.join(src_at, f)
                dst_dir = os.path.join(DST_CTRL, "anytenant", "controller")
                os.makedirs(dst_dir, exist_ok=True)
                dst_path = os.path.join(dst_dir, f)
                
                with open(src_path, 'r', encoding='utf-8') as fh:
                    content = fh.read()
                
                content = replace_package(content, "anytenant", is_controller=True)
                
                with open(dst_path, 'w', encoding='utf-8') as fh:
                    fh.write(content)
                print(f"  AnyTenant Controller: {f}")


def main():
    print("Starting migration...")
    print(f"Source: {SRC_BIZ}")
    print(f"Target: {DST_BIZ}")
    
    # Step 1: Copy controllers
    copy_controller_files()
    copy_ws_controller()
    copy_inner_controller()
    copy_anytenant_controller()
    
    # Step 2: Copy biz files (excluding managers)
    manager_methods, manager_impl_methods = copy_biz_files()
    
    # Step 3: Copy utils
    copy_utils()
    
    # Step 4: Merge managers into services
    merge_manager_to_service(manager_methods, manager_impl_methods)
    
    # Step 5: Copy XML mappers
    copy_xml_files()
    
    print("\n=== Migration Complete ===")


if __name__ == "__main__":
    main()
