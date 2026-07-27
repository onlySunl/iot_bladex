#!/usr/bin/env python3
"""
Fix substantive issues:
1. Query<Xxx> → Query (BladeX Query is not generic)
2. Remove duplicate methods from Manager merge
3. Fix remaining missing class references
"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

def remove_duplicate_methods(content):
    """Remove duplicate method signatures from Manager merge"""
    lines = content.split('\n')
    seen_methods = set()
    new_lines = []
    i = 0
    while i < len(lines):
        line = lines[i]
        # Check if this is a method signature (ends with ; and has ())
        stripped = line.strip()
        if stripped.endswith(';') and '(' in stripped and ')' in stripped:
            # Normalize whitespace for comparison
            normalized = ' '.join(stripped.split())
            if normalized in seen_methods:
                # Skip this duplicate method
                i += 1
                # Also skip preceding javadoc if any
                while new_lines and new_lines[-1].strip().startswith('*'):
                    new_lines.pop()
                if new_lines and new_lines[-1].strip() == '/**':
                    new_lines.pop()
                if new_lines and new_lines[-1].strip().startswith('//'):
                    new_lines.pop()
                continue
            seen_methods.add(normalized)
        new_lines.append(line)
        i += 1
    return '\n'.join(new_lines)

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Fix 1: Query<Xxx> → Query
    content = re.sub(r'Query<\w+>', 'Query', content)
    
    # Fix 2: Remove duplicate methods
    if '已在接口' not in original:  # Only fix files that had duplicate method errors
        # Apply to all service interfaces
        if '/service/' in filepath and not '/impl/' in filepath and filepath.endswith('Service.java'):
            content = remove_duplicate_methods(content)
    
    # Fix 3: org.springblade.modules.iot.ota.service.statemachine.config
    # This should be org.springblade.modules.iot.ota.service.statemachine.config
    # Check if the file exists
    config_file = os.path.join(DST_BIZ, "ota", "service", "statemachine", "config", "OtaUpgradeStateMachineConfig.java")
    
    # Fix 4: Remove thinglinks-specific cache infrastructure references
    content = re.sub(r'^import org\.springblade\.common\.cache\.[^;]+;\s*\n', '', content, flags=re.MULTILINE)
    
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
