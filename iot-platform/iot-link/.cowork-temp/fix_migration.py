#!/usr/bin/env python3
"""
Fix script: apply corrections to migrated files
"""
import os, re, glob

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    original = content
    
    # Fix broken import .ContextUtil;
    content = re.sub(r'^import \.ContextUtil;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix broken import .ArgumentAssert;
    content = re.sub(r'^import \.ArgumentAssert;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix wrong SuperServiceImpl path
    content = content.replace(
        "import org.springblade.core.service.impl.SuperServiceImpl;",
        "import org.springblade.core.mp.base.BaseServiceImpl;"
    )
    
    # Fix wrong ArgumentAssert path
    content = content.replace(
        "import org.springblade.core.tool.utils.ArgumentAssert;",
        ""
    )
    
    # Fix wrong BeanPlusUtil path → BeanUtil
    content = content.replace(
        "import org.springblade.core.tool.utils.BeanPlusUtil;",
        "import org.springblade.core.tool.utils.BeanUtil;"
    )
    
    # Fix wrong BaseService path
    content = content.replace(
        "import org.springblade.core.service.BaseService;",
        "import org.springblade.core.mp.base.BaseService;"
    )
    
    # Fix wrong BaseServiceImpl path
    content = content.replace(
        "import org.springblade.core.service.impl.BaseServiceImpl;",
        "import org.springblade.core.mp.base.BaseServiceImpl;"
    )
    
    # Fix wrong BladeController path
    content = content.replace(
        "import org.springblade.core.boot.ctrl.BladeController;",
        "import org.springblade.core.boot.ctrl.BladeController;"
    )
    
    # Fix wrong BladeMapper path
    content = content.replace(
        "import org.springblade.core.mp.support.BladeMapper;",
        "import org.springblade.core.mp.support.BladeMapper;"
    )
    
    # Fix wrong R path
    content = content.replace(
        "import org.springblade.core.tool.api.R;",
        "import org.springblade.core.tool.api.R;"
    )
    
    # Fix broken ArgumentAssert method calls (lines like "ArgumentAssert.notNull(...);")
    content = re.sub(r'^\s*ArgumentAssert\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken ContextUtil method calls 
    content = re.sub(r'^\s*ContextUtil\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken BeanPlusUtil → BeanUtil
    content = content.replace("BeanPlusUtil.", "BeanUtil.")
    
    # Fix broken TenantUtil references
    content = re.sub(r'^\s*TenantUtil\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken SnowflakeIdUtil references
    content = re.sub(r'^\s*SnowflakeIdUtil\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken Builder references  
    content = re.sub(r'^\s*Builder\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken CacheKey references
    content = re.sub(r'^\s*CacheKey\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken DistributedLock references
    content = re.sub(r'^\s*DistributedLock\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken LockRunResult references
    content = re.sub(r'^\s*LockRunResult\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken EasyExcelListener/EasyExcelUtils/ExcelCheckManager/ExcelImportErrDto references
    content = re.sub(r'^\s*EasyExcelListener\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*EasyExcelUtils\.\w+\([^)]*\)\s*;\s*$', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*ExcelCheckManager\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    content = re.sub(r'^\s*ExcelImportErrDto\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken EchoService references
    content = re.sub(r'^\s*EchoService\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken QueryWrap references
    content = re.sub(r'^\s*QueryWrap\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken DataScopeHelper references
    content = re.sub(r'^\s*DataScopeHelper\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken LinkLockKeyBuilder references
    content = re.sub(r'^\s*LinkLockKeyBuilder\.\w+[^;]*;\s*$', '', content, flags=re.MULTILINE)
    
    # Fix broken com.mqttsnet.basic references in string literals or remaining imports
    content = re.sub(r'^import com\.mqttsnet\.basic\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix remaining com.mqttsnet.thinglinks references
    content = re.sub(r'^import com\.mqttsnet\.thinglinks\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Fix empty import lines
    content = re.sub(r'^import\s+\.\w+;\s*\n', '', content, flags=re.MULTILINE)
    
    # Clean up triple+ newlines
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
                        rel = os.path.relpath(filepath, root_dir)
                        print(f"Fixed: {rel}")
    
    print(f"\nTotal files fixed: {count}")

if __name__ == "__main__":
    main()
