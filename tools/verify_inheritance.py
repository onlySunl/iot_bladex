#!/usr/bin/env python3
"""验证 iot-link 中 Controller/Service/ServiceImpl/Mapper 的继承关系"""
import os, re

ctrl_ok = svc_ok = impl_ok = mapper_ok = 0
ctrl_err = []
svc_err = []
impl_err = []
mapper_err = []

for root, dirs, files in os.walk(r"D:\workspace\IOT\iot_bladex_v1.0\iot-platform\iot-link"):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, "r", encoding="utf-8", errors="replace") as fh:
            text = fh.read()
        
        # Controller
        if "Controller.java" in f and "Impl" not in f:
            if "extends BladeController" in text:
                ctrl_ok += 1
            else:
                m = re.search(r"public class \w+ extends (\w+)", text)
                ctrl_err.append("{}: extends {}".format(f, m.group(1) if m else "nothing"))
        
        # Service 接口
        if "Service.java" in f and "Impl" not in f and "interface" in text.split("{")[0]:
            if "extends BladeService<" in text:
                svc_ok += 1
            else:
                m = re.search(r"public interface \w+ extends (\w+)", text)
                svc_err.append("{}: extends {}".format(f, m.group(1) if m else "nothing"))
        
        # ServiceImpl
        if "ServiceImpl.java" in f:
            if "extends BladeServiceImpl<" in text:
                impl_ok += 1
            else:
                m = re.search(r"public class \w+ extends (\w+)", text)
                impl_err.append("{}: extends {}".format(f, m.group(1) if m else "nothing"))
        
        # Mapper
        if "Mapper.java" in f:
            if "extends BladeMapper<" in text:
                mapper_ok += 1
            else:
                m = re.search(r"public interface \w+ extends (\w+)", text)
                mapper_err.append("{}: extends {}".format(f, m.group(1) if m else "nothing"))

print("Controller: {} OK".format(ctrl_ok))
for e in ctrl_err:
    print("  ERR: {}".format(e))
print("Service: {} OK".format(svc_ok))
for e in svc_err:
    print("  ERR: {}".format(e))
print("ServiceImpl: {} OK".format(impl_ok))
for e in impl_err:
    print("  ERR: {}".format(e))
print("Mapper: {} OK".format(mapper_ok))
for e in mapper_err:
    print("  ERR: {}".format(e))
