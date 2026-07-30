package org.springblade.fastdfs.domain.proto;

import org.springblade.fastdfs.domain.conn.Connection;

/**
 * Fdfs交易命令抽象
 *
 * @author tobato
 */
public interface FdfsCommand<T> {

    /**
     * 执行交易
     */
    public T execute(Connection conn);

}
