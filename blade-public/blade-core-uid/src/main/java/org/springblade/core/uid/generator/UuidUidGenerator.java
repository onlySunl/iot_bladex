package org.springblade.core.uid.generator;

import org.springblade.core.uid.UidGenerator;

import java.util.UUID;

/**
 * UUID UID 生成器
 *
 * @author Chill
 */
public class UuidUidGenerator implements UidGenerator {

    @Override
    public long getUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits();
    }

    @Override
    public String parseUid(long uid) {
        return "UUID-based UID: " + uid;
    }

    /**
     * 生成字符串格式的 UUID
     *
     * @return UUID 字符串
     */
    public String getUuidString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
