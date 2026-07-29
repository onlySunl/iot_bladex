package org.springblade.core.uid;

/**
 * UID 生成器接口
 *
 * @author Chill
 */
public interface UidGenerator {

    /**
     * 生成唯一 ID
     *
     * @return 唯一 ID
     */
    long getUid();

    /**
     * 解析 UID
     *
     * @param uid UID
     * @return 解析结果
     */
    String parseUid(long uid);
}
