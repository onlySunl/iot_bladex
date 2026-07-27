#!/usr/bin/env python3
"""修复没有 extends 的 Controller"""
import os, re

DST = r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link"

controllers = [
    "DeviceOpenAnyTenantController", "DashboardStatsController", "DeviceGeneratorController",
    "DeviceQrcodeController", "DeviceShadowController", "CacheInnerController",
    "DeviceOpenInnerController", "DeviceSyncInnerController", "OtaOpenInnerController",
    "ProductOpenInnerController", "ProductTopicOpenInnerController",
]

for root, dirs, files in os.walk(DST):
    for f in files:
        if not f.endswith(".java"):
            continue
        name = f.replace(".java", "")
        if name not in controllers:
            continue
        
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            text = fh.read()
        
        # public class XxxController → public class XxxController extends BladeController
        text = re.sub(
            r'(public class \w+Controller)',
            r'\1 extends BladeController',
            text
        )
        
        # 加 import
        if "import org.springblade.core.boot.ctrl.BladeController;" not in text:
            text = re.sub(
                r'(package org\.springblade\.modules\.iot\.[^;]+;)',
                r'\1\n\nimport org.springblade.core.boot.ctrl.BladeController;',
                text
            )
        
        with open(path, "w", encoding="utf-8") as fh:
            fh.write(text)
        print("Fixed: {}".format(f))

print("Done")
