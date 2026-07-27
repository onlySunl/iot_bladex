#!/usr/bin/env python3
"""Fix remaining org.springblade.core.service.* imports"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Fix all org.springblade.core.service.* imports
    content = re.sub(r'import org\.springblade\.core\.service\.\w+;', '', content)
    # Add back the correct import if needed
    if 'BaseService' in original and 'import org.springblade.core.mp.base.BaseService;' not in content:
        if 'extends BaseService' in content or 'implements BaseService' in content:
            content = content.replace('package ', 'import org.springblade.core.mp.base.BaseService;\npackage ', 1)
    if 'BaseServiceImpl' in original and 'import org.springblade.core.mp.base.BaseServiceImpl;' not in content:
        if 'extends BaseServiceImpl' in content:
            content = content.replace('package ', 'import org.springblade.core.mp.base.BaseServiceImpl;\npackage ', 1)
    if 'BladeMapper' in original and 'import org.springblade.core.mp.support.BladeMapper;' not in content:
        if 'extends BladeMapper' in content:
            content = content.replace('package ', 'import org.springblade.core.mp.support.BladeMapper;\npackage ', 1)
    if 'Query' in original and 'import org.springblade.core.mp.support.Query;' not in content:
        if 'Query<' in content:
            content = content.replace('package ', 'import org.springblade.core.mp.support.Query;\npackage ', 1)
    
    # Fix org.springblade.core.entity.BaseEntity
    content = content.replace('import org.springblade.core.entity.BaseEntity;', 'import org.springblade.core.mp.base.BaseEntity;')
    
    # Fix remaining SuperService<Long, Xxx> → BaseService<Xxx>
    content = re.sub(r'extends\s+SuperService\s*<\s*Long\s*,\s*(\w+)\s*>', r'extends BaseService<\1>', content)
    
    # Fix remaining SuperServiceImpl<Mapper, Entity> → BaseServiceImpl<Mapper, Entity>
    content = re.sub(r'extends\s+SuperServiceImpl\s*<\s*(\w+)\s*,\s*(\w+)\s*>', r'extends BaseServiceImpl<\1, \2>', content)
    
    # Fix remaining SuperMapper<Entity> → BladeMapper<Entity>
    content = re.sub(r'extends\s+SuperMapper\s*<\s*(\w+)\s*>', r'extends BladeMapper<\1>', content)
    
    # Clean up
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    if content != original:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    count = 0
    for root_dir in [DST_BIZ, DST_CTRL]:
        for root, dirs, files in os.walk(root_dir):
            for f in files:
                if f.endswith(".java"):
                    filepath = os.path.join(root, f)
                    if fix_file(filepath):
                        count += 1
    print(f"Fixed: {count}")

if __name__ == "__main__":
    main()
