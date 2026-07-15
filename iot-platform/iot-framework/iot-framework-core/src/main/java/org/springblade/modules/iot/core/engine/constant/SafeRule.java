package org.springblade.modules.iot.core.engine.constant;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SafeRule {

  public static Set<String> packages =
      Stream.of(
              "cn.hutool.json.JSONUtil",
              "cn.hutool.service.util.HexUtil",
              "cn.hutool.service.util.RandomUtil",
              "cn.hutool.service.util.StrUtil")
          .collect(Collectors.toSet());

  public static boolean filter(String packageName) {
    return packageName != null
        && !"".equalsIgnoreCase(packageName)
        && !packages.contains(packageName);
  }
}
