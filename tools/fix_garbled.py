#!/usr/bin/env python3
"""修复 thinglinks 源码中文乱码 - 将 UTF-8 被错误解码为 Latin-1 的字符还原"""
import os, re

# 乱码映射表 (Latin-1 误读 UTF-8 中文的常见模式)
# 这些是通过分析实际乱码建立的映射
FIXES = {
    "RegionalUpgradeScopeStrategyImpl.java": {
        "鍖哄煙鍗囩骇绛栫暐瀹炵幇绫? * <p>": "区域升级策略实现类\n * <p>",
        "澶勭悊鍖哄煙鍗囩骇鑼冨洿鐨勮澶囩瓫閫夐€昏緫锛屼粠閫楀彿鍒嗛殧鐨勫尯鍩熺紪鐮佷腑鎻愬彇甯傜骇缂栫爜骞舵煡璇㈠搴旇澶? * </p>": "处理区域升级范围的设备筛选逻辑，从逗号分隔的区域编码中提取市级编码并查询对应设备\n * </p>",
        "妫€鏌ユ槸鍚︽敮鎸佹寚瀹氱殑鍗囩骇鑼冨洿绫诲瀷": "检查是否支持指定的升级范围类型",
        "鍗囩骇鑼冨洿绫诲瀷鍊?     * @return 濡傛灉鏀寔鍖哄煙鍗囩骇鑼冨洿鍒欒繑鍥瀟rue锛屽惁鍒欒繑鍥瀎alse": "升级范围类型值\n     * @return 如果支持区域升级范围则返回true，否则返回false",
        "鑾峰彇鏀寔鐨勫崌绾ц寖鍥存灇涓剧被鍨?     *": "获取支持的升级范围枚举类型\n     *",
        "鍖哄煙鍗囩骇鑼冨洿鏋氫妇": "区域升级范围枚举",
        "鑾峰彇鍖哄煙鍗囩骇鑼冨洿鍐呯殑璁惧鍒楄〃": "获取区域升级范围内的设备列表",
        "鍗囩骇浠诲姟鏁版嵁浼犺緭瀵硅薄": "升级任务数据传输对象",
        "鍖呭惈璁惧鍒楄〃鐨凮ptional瀵硅薄锛屽鏋滆幏鍙栧け璐ュ垯杩斿洖绌虹殑Optional": "包含设备列表的Optional对象，如果获取失败则返回空的Optional",
        "浠诲姟ID涓虹┖锛屾棤娉曡幏鍙栧尯鍩熷崌绾ц澶?);": "任务ID为空，无法获取区域升级设备\");",
        "鍖哄煙鍗囩骇浠诲姟鐨勫尯鍩熺紪鐮佸垪琛ㄤ负绌?- 浠诲姟ID: {}": "区域升级任务的区域编码列表为空 - 任务ID: {}",
        "鍗囩骇浠诲姟鐨勪骇鍝佹爣璇嗕负绌?- 浠诲姟ID: {}": "升级任务的产品标识为空 - 任务ID: {}",
        "瑙ｆ瀽閫楀彿鍒嗛殧鐨勫尯鍩熺紪鐮侊紝鎻愬彇甯傜骇缂栫爜": "解析逗号分隔的区域编码，提取市级编码",
        "鏃犳硶浠庡尯鍩熺紪鐮佷腑瑙ｆ瀽鍑烘湁鏁堢殑甯傜骇缂栫爜 - 浠诲姟ID: {}": "无法从区域编码中解析出有效的市级编码 - 任务ID: {}",
        "瑙ｆ瀽鍑哄競绾х紪鐮?- 浠诲姟ID: {}, 浜у搧鏍囪瘑: {}, 甯傜骇缂栫爜鏁伴噺: {}": "解析出市级编码 - 任务ID: {}, 产品标识: {}, 市级编码数量: {}",
        "鑾峰彇鍖哄煙鍗囩骇璁惧鍒楄〃寮傚父 - 浠诲姟ID: {}": "获取区域升级设备列表异常 - 任务ID: {}",
        "浠庨€楀彿鍒嗛殧鐨勫尯鍩熺紪鐮佸垪琛ㄤ腑瑙ｆ瀽鍑哄競绾х紪鐮?     *": "从逗号分隔的区域编码列表中解析出市级编码\n     *",
        "閫楀彿鍒嗛殧鐨勫尯鍩熺紪鐮佸垪琛?     * @return 鍘婚噸鍚庣殑甯傜骇缂栫爜鍒楄〃": "逗号分隔的区域编码列表\n     * @return 去重后的市级编码列表",
        "浠庡崟涓€楀彿鍒嗛殧鐨勫瓧绗︿覆涓彁鍙栧競绾х紪鐮?     *": "从单个逗号分隔的字符串中提取市级编码\n     *",
        "鍖哄煙缂栫爜瀛楃涓?     * @return 甯傜骇缂栫爜娴?     */": "区域编码字符串\n     * @return 市级编码流\n     */",
        "鏍规嵁甯傜骇缂栫爜鍒楄〃鑾峰彇璁惧鏍囪瘑鍒楄〃": "根据市级编码列表获取设备标识列表",
        "浜у搧鏍囪瘑": "产品标识",
        "甯傜骇缂栫爜鍒楄〃": "市级编码列表",
        "鍗囩骇浠诲姟": "升级任务",
        "璁惧缁撴灉瑙嗗浘瀵硅薄鍒楄〃": "设备结果视图对象列表",
        "甯傜骇缂栫爜鍒楄〃涓虹┖锛屾棤娉曟煡璇㈣澶?);": "市级编码列表为空，无法查询设备\");",
        "鍦ㄦ寚瀹氱殑甯傜骇缂栫爜涓嬫湭鎵惧埌璁惧浣嶇疆淇℃伅 - 甯傜骇缂栫爜: {}": "在指定的市级编码下未找到设备位置信息 - 市级编码: {}",
        "浠庤澶囦綅缃俊鎭腑鎻愬彇鍒拌澶囨爣璇嗘暟閲? {}": "从设备位置信息中提取到设备标识数量: {}",
        "娣诲姞鐗堟湰杩囨护鏉′欢": "添加版本过滤条件",
        "鏍规嵁甯傜骇缂栫爜鑾峰彇璁惧鍒楄〃寮傚父 - 甯傜骇缂栫爜: {}": "根据市级编码获取设备列表异常 - 市级编码: {}",
    }
}

base = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link\iot-link-biz\src\main\java\org\springblade\modules\iot"

file_paths = {
    "RegionalUpgradeScopeStrategyImpl.java": os.path.join(base, "ota/service/statemachine/strategy/scope/impl/RegionalUpgradeScopeStrategyImpl.java"),
}

for fname, repairs in FIXES.items():
    path = file_paths.get(fname)
    if not path or not os.path.exists(path):
        print(f"SKIP: {fname} not found at {path}")
        continue
    
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()
    
    for old, new in repairs.items():
        if old in content:
            content = content.replace(old, new)
        else:
            print(f"  NOT FOUND: {old[:30]}...")
    
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Fixed: {fname}")

print("Done")
