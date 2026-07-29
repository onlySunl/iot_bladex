package org.springblade.basic.utils;

import cn.hutool.core.util.ReUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * -----------------------------------------------------------------------------
 * File Name: VersionValidator
 * -----------------------------------------------------------------------------
 * Description:
 * 版本校验
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/9       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/9 18:38
 */
public class VersionValidator {
    // 通用版本号正则表达式
    private static final String VERSION_REGEX = "^\\d+(\\.\\d+){2}(-[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?(\\+[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?$";

    /**
     * 校验字符串是否为正确的版本号
     *
     * @param version 要校验的版本号字符串
     * @return 如果版本号格式正确，则返回true；否则返回false
     */
    public static boolean isValidVersion(String version) {
        return ReUtil.isMatch(VERSION_REGEX, version);
    }

    /**
     * 从版本集合中获取最新的版本号
     *
     * @param versions 版本号集合
     * @return 最新的版本号（如果集合为空，则返回Optional.empty()）
     */
    public static Optional<String> getLatestVersion(List<String> versions) {
        return versions.stream()
                .filter(VersionValidator::isValidVersion) // 过滤掉不合法的版本号
                .max(Comparator.comparing(VersionValidator::parseVersion));
    }

    /**
     * 解析版本号字符串，将其转换为Comparable数组
     *
     * @param version 版本号字符串
     * @return 可比较的版本号数组
     */
    private static Version parseVersion(String version) {
        return new Version(version);
    }

    public static void main(String[] args) {
        List<String> versions = List.of(
                "1.0.0",
                "1.0",
                "1.0.0-alpha",
                "1.0.0-alpha.1",
                "1.0.0+build",
                "1.0.0-alpha+build",
                "1.0.0-alpha.1+build.1",
                "1.0.0-alpha-1+build-1",
                "2.0.0"
        );

        System.out.println("Valid versions:");
        versions.forEach(version -> System.out.println(version + " is valid: " + isValidVersion(version)));

        Optional<String> latestVersion = getLatestVersion(versions);
        latestVersion.ifPresent(version -> System.out.println("Latest version: " + version));
    }

    // 内部类，表示可比较的版本号
    private static class Version implements Comparable<Version> {
        private final int[] parts;

        public Version(String version) {
            String[] split = version.replaceAll("[^\\d.]", "").split("\\.");
            parts = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                parts[i] = Integer.parseInt(split[i]);
            }
        }

        @Override
        public int compareTo(Version other) {
            for (int i = 0; i < Math.max(this.parts.length, other.parts.length); i++) {
                int thisPart = i < this.parts.length ? this.parts[i] : 0;
                int otherPart = i < other.parts.length ? other.parts[i] : 0;
                if (thisPart != otherPart) {
                    return thisPart - otherPart;
                }
            }
            return 0;
        }
    }
}
