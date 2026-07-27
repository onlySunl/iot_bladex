#!/usr/bin/env python3
"""Add missing imports for BladeX base classes"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

# Map of class usage patterns to required imports
NEEDED_IMPORTS = [
    (r'extends\s+BaseService\b', 'import org.springblade.core.mp.base.BaseService;'),
    (r'extends\s+BaseServiceImpl\b', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    (r'extends\s+BladeMapper\b', 'import org.springblade.core.mp.support.BladeMapper;'),
    (r'extends\s+BladeController\b', 'import org.springblade.core.boot.ctrl.BladeController;'),
    (r'extends\s+BaseEntity\b', 'import org.springblade.core.mp.base.BaseEntity;'),
    (r'\bQuery\s*<', 'import org.springblade.core.mp.support.Query;'),
    (r'\bQuery\s+\w+\s*[=;(]', 'import org.springblade.core.mp.support.Query;'),
    (r'\bR\s*<', 'import org.springblade.core.tool.api.R;'),
    (r'\bR\.', 'import org.springblade.core.tool.api.R;'),
    (r'\bJsonUtil\.', 'import org.springblade.core.tool.utils.JsonUtil;'),
    (r'\bStringPool\.', 'import org.springblade.core.tool.utils.StringPool;'),
    (r'\bBeanUtil\.', 'import org.springblade.core.tool.utils.BeanUtil;'),
    (r'\bAuthUtil\.', 'import org.springblade.core.secure.utils.AuthUtil;'),
    (r'\bDateUtil\.', 'import cn.hutool.core.date.DateUtil;'),
    (r'\bWrappers\.', 'import com.baomidou.mybatisplus.core.toolkit.Wrappers;'),
    (r'@AllArgsConstructor', 'import lombok.AllArgsConstructor;'),
]

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Find the package line
    pkg_match = re.search(r'^package\s+[^;]+;\s*$', content, re.MULTILINE)
    if not pkg_match:
        return False
    
    insert_pos = pkg_match.end()
    
    for pattern, import_line in NEEDED_IMPORTS:
        # Check if the class is used
        if re.search(pattern, content):
            # Check if the import already exists
            if import_line not in content:
                # Insert after package
                content = content[:insert_pos] + '\n' + import_line + content[insert_pos:]
                insert_pos += len(import_line) + 1
    
    # Also remove any remaining broken org.springblade.core.base.* imports
    content = re.sub(r'^import org\.springblade\.core\.base\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
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
    print(f"Fixed {count} files")

if __name__ == "__main__":
    main()
