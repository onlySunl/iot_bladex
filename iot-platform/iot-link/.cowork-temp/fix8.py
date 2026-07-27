#!/usr/bin/env python3
"""Fix Query<Xxx> → Query and handle duplicate methods"""
import os, re

DST_BIZ = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"
DST_CTRL = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-controller\src\main\java\org\springblade\modules\iot"

def remove_duplicate_methods(content):
    """Remove duplicate method signatures"""
    lines = content.split('\n')
    seen = {}
    result = []
    skip_until_semicolon = False
    
    for i, line in enumerate(lines):
        stripped = line.strip()
        
        # If we're skipping a duplicate, continue until we find the semicolon
        if skip_until_semicolon:
            if ';' in stripped:
                skip_until_semicolon = False
            continue
        
        # Check if this is a method signature line
        if stripped.endswith(';') and '(' in stripped and ')' in stripped:
            # Normalize: remove all whitespace differences
            sig = re.sub(r'\s+', ' ', stripped)
            # Extract method name and params
            match = re.match(r'(?:default\s+)?(?:public\s+|private\s+|protected\s+)?(?:static\s+)?(?:<[^>]+>\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\(([^)]*)\)', sig)
            if match:
                method_key = f"{match.group(2)}({match.group(3)})"
                if method_key in seen:
                    # This is a duplicate - skip it
                    skip_until_semicolon = True
                    # Also remove preceding javadoc lines
                    while result and (result[-1].strip().startswith('*') or result[-1].strip() == '/**' or result[-1].strip().startswith('//')):
                        result.pop()
                    continue
                seen[method_key] = True
        
        result.append(line)
    
    return '\n'.join(result)

def fix_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    original = content
    
    # Fix: Query<Xxx> → Query
    content = re.sub(r'Query<[^>]+>', 'Query', content)
    
    # Fix: remove duplicate methods in service interfaces
    if '/service/' in filepath.replace('\\', '/') and 'Service.java' in filepath and '/impl/' not in filepath.replace('\\', '/'):
        content = remove_duplicate_methods(content)
    
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
