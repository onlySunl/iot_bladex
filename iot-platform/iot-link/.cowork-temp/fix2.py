#!/usr/bin/env python3
"""
Comprehensive fix script for remaining migration issues
"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

FIXES = [
    # Fix 1: org.springblade.core.service.BaseService → org.springblade.core.mp.base.BaseService
    (r'import org\.springblade\.core\.service\.BaseService;', 'import org.springblade.core.mp.base.BaseService;'),
    (r'import org\.springblade\.core\.service\.impl\.SuperServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    (r'import org\.springblade\.core\.service\.impl\.BaseServiceImpl;', 'import org.springblade.core.mp.base.BaseServiceImpl;'),
    
    # Fix 2: org.springblade.core.request.PageParams → org.springblade.core.mp.support.Query
    (r'import org\.springblade\.core\.request\.PageParams;', 'import org.springblade.core.mp.support.Query;'),
    
    # Fix 3: org.springblade.core.R → org.springblade.core.tool.api.R
    (r'import org\.springblade\.core\.R;', 'import org.springblade.core.tool.api.R;'),
    
    # Fix 4: org.springblade.core.tool.utils.DateUtils → cn.hutool.core.date.DateUtil
    (r'import org\.springblade\.core\.tool\.utils\.DateUtils;', 'import cn.hutool.core.date.DateUtil;'),
    
    # Fix 5: Remove @DS annotation (remaining)
    (r'^\s*@DS\s*\(\s*"[^"]*"\s*\)\s*$', ''),
    
    # Fix 6: Remove remaining com.mqttsnet imports
    (r'^import com\.mqttsnet\.[^;]+;\s*\n', ''),
    
    # Fix 7: Fix broken "import .Xxx;" patterns
    (r'^import \.\w+;\s*\n', ''),
    
    # Fix 8: Fix org.springblade.modules.iot.common.constant → need to find correct path
    (r'import org\.springblade\.modules\.iot\.common\.constant\.(\w+);', r'import org.springblade.modules.iot.common.constant.\1;'),
    
    # Fix 9: Fix org.springblade.modules.iot.common.enums → org.springblade.modules.iot.device.enumeration
    (r'import org\.springblade\.modules\.iot\.common\.enums\.DeviceActionTypeEnum;', 'import org.springblade.modules.iot.device.enumeration.DeviceActionTypeEnum;'),
    
    # Fix 10: Fix org.springblade.modules.iot.context imports
    (r'^import org\.springblade\.modules\.iot\.context\.[^;]+;\s*\n', ''),
    
    # Fix 11: Fix CachePlusOps → remove (thinglinks specific)
    (r'^import com\.mqttsnet\.basic\.cache\.CachePlusOps;\s*\n', ''),
    
    # Fix 12: Fix ContextAwareExecutor → remove (thinglinks specific)
    (r'^import com\.mqttsnet\.basic\.cache\.ContextAwareExecutor;\s*\n', ''),
    
    # Fix 13: Fix Builder references
    (r'^import com\.mqttsnet\.basic\.converter\.Builder;\s*\n', ''),
    
    # Fix 14: Fix SuperServiceImpl class reference
    (r'extends SuperServiceImpl<(\w+),\s*(\w+)>', r'extends BaseServiceImpl<\1, \2>'),
    
    # Fix 15: Fix SuperService references
    (r'extends SuperService<Long,\s*(\w+)>', r'extends BaseService<\1>'),
    
    # Fix 16: Fix PageParams usage
    (r'PageParams<', 'Query<'),
    
    # Fix 17: Fix DateUtils method calls
    (r'DateUtils\.', 'DateUtil.'),
    
    # Fix 18: Fix Wraps → Wrappers
    (r'Wraps\.', 'Wrappers.'),
    
    # Fix 19: Remove remaining manager imports
    (r'^import org\.springblade\.modules\.iot\.\w+\.manager\.[^;]+;\s*\n', ''),
    
    # Fix 20: Fix org.springblade.modules.iot.datascope
    (r'import org\.springblade\.modules\.iot\.datascope\.(\w+);', r'import org.springblade.modules.iot.datascope.\1;'),
    
    # Fix 21: Fix com.mqttsnet.thinglinks.cache references
    (r'^import com\.mqttsnet\.thinglinks\.cache\.[^;]+;\s*\n', ''),
    
    # Fix 22: Fix com.mqttsnet.thinglinks.common.lock references
    (r'^import com\.mqttsnet\.thinglinks\.common\.lock\.[^;]+;\s*\n', ''),
    
    # Fix 23: Fix com.mqttsnet.thinglinks.datascope references
    (r'^import com\.mqttsnet\.thinglinks\.datascope\.[^;]+;\s*\n', ''),
]

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    original = content
    
    for pattern, replacement in FIXES:
        content = re.sub(pattern, replacement, content, flags=re.MULTILINE)
    
    # Remove lines that are just broken annotations (e.g., "@DS" without import)
    # These would be caught by the @DS pattern above
    
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
    
    print(f"Total files fixed: {count}")

if __name__ == "__main__":
    main()
