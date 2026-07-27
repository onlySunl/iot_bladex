#!/usr/bin/env python3
"""
处理 iot-entity 下 VO 继承实体类的问题：
1. extends AuditableResultVO 的 VO → 保留继承，移除与实体重复的字段
2. extends CustomBaseEntity 的 VO → 改为不继承实体，只保留 VO 独有字段
3. extends 具体实体类的 VO → 改为不继承实体，只保留 VO 独有字段
"""
import os, re

BASE = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-entity"

# CustomBaseEntity 及其父类包含的所有字段
BASE_ENTITY_FIELDS = {
    # TenantEntity
    "tenantId", "tenant_id",
    # BaseEntity
    "id", "createTime", "create_time", "updateTime", "update_time",
    "createUser", "create_user", "updateUser", "update_user", "isDeleted", "is_deleted",
    # CustomBaseEntity
    "revision", "remark", "attr1", "attr2", "attr3", "attr4", "attr5",
    "createdOrgId", "created_org_id",
    # AuditableResultVO
    "echoMap", "echo_map", "createdBy", "created_by", "updatedBy", "updated_by",
}

def get_fields_from_class(content):
    """提取类中声明的所有字段名"""
    # 匹配 private XXX fieldName;
    fields = set()
    for m in re.finditer(r'private\s+\w+(?:<[^>]+>)?\s+(\w+)\s*[;=]', content):
        fields.add(m.group(1))
    return fields

def remove_field(content, field_name):
    """移除一个字段声明及其注释和注解"""
    # 匹配字段声明行及其前面的 Javadoc 注释和注解
    # 模式：可选 Javadoc + 可选注解 + private Type fieldName;
    pattern = re.compile(
        r'(?:/\*\*.*?\*/\s*)?'  # Javadoc
        r'(?:@\w+(?:\([^)]*\))?\s*)*'  # 注解
        r'private\s+\w+(?:<[^>]+>)?\s+' + re.escape(field_name) + r'\s*[;=][^\n]*\n',
        re.DOTALL
    )
    return pattern.sub('', content)

def fix_vo_file(path):
    with open(path, "r", encoding="utf-8", errors="replace") as f:
        content = f.read()
    
    original = content
    
    # 检查继承关系
    extends_auditable = "extends AuditableResultVO" in content
    extends_custom_base = "extends CustomBaseEntity" in content
    extends_entity = bool(re.search(r'extends\s+(\w+)\s*(implements|\{)', content))
    
    if not (extends_auditable or extends_custom_base or extends_entity):
        return None
    
    # 获取 VO 自己的字段
    vo_fields = get_fields_from_class(content)
    
    # 找出与基类重复的字段
    duplicate = vo_fields & BASE_ENTITY_FIELDS
    
    if extends_auditable:
        # 保留继承 AuditableResultVO，只移除重复字段
        for field in duplicate:
            content = remove_field(content, field)
        if duplicate:
            return f"AuditableResultVO - removed {duplicate}"
        else:
            return "AuditableResultVO - no duplicates"
    
    elif extends_custom_base:
        # 改为不继承 CustomBaseEntity，实现 Serializable
        content = content.replace("extends CustomBaseEntity implements Serializable", "implements Serializable")
        content = content.replace("extends CustomBaseEntity", "implements Serializable")
        # 移除 @EqualsAndHashCode(callSuper = true) → callSuper = false
        content = content.replace("@EqualsAndHashCode(callSuper = true)", "@EqualsAndHashCode(callSuper = false)")
        # 移除 @ToString(callSuper = true) → 不带 callSuper
        content = content.replace("@ToString(callSuper = true)", "@ToString")
        # 移除 CustomBaseEntity import
        content = re.sub(r'import org\.springblade\.common\.entity\.CustomBaseEntity;\s*\n', '', content)
        # 移除重复字段
        for field in duplicate:
            content = remove_field(content, field)
        return f"CustomBaseEntity → Serializable (removed {duplicate})" if duplicate else "CustomBaseEntity → Serializable"
    
    elif extends_entity:
        # 改为不继承实体类
        match = re.search(r'extends\s+(\w+)\s+(implements\s+Serializable)', content)
        if match:
            entity_name = match.group(1)
            content = content.replace(f"extends {entity_name} implements Serializable", "implements Serializable")
            content = content.replace(f"extends {entity_name}", "implements Serializable")
            # 移除实体类 import
            content = re.sub(rf'import org\.springblade\.modules\.iot\.\w+\.entity\.{entity_name};\s*\n', '', content)
            # 移除重复字段
            for field in duplicate:
                content = remove_field(content, field)
            return f"{entity_name} → Serializable (removed {duplicate})" if duplicate else f"{entity_name} → Serializable"
    
    if content != original:
        with open(path, "w", encoding="utf-8") as f:
            f.write(content)
        return "fixed"
    return None

# 处理所有 VO 文件
count = 0
results = {}
for root, dirs, files in os.walk(BASE):
    dirs[:] = [d for d in dirs if d != "target"]
    for f in files:
        if not f.endswith("VO.java"):
            continue
        path = os.path.join(root, f)
        result = fix_vo_file(path)
        if result:
            results[os.path.relpath(path, BASE)] = result
            count += 1

print(f"Processed {count} VO files:")
for path, result in sorted(results.items()):
    print(f"  {path}: {result}")
