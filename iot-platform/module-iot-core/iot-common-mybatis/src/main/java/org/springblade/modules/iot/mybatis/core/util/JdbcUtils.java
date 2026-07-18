package org.springblade.modules.iot.mybatis.core.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class JdbcUtils {

    public static DbType getDbType() {
        try {
            DataSource dataSource = org.springblade.core.tool.utils.SpringUtil.getBean(DataSource.class);
            if (dataSource == null) {
                return DbType.MYSQL;
            }
            return getDbTypeFromDataSource(dataSource);
        } catch (Exception e) {
            return DbType.MYSQL;
        }
    }

    @SneakyThrows
    public static DbType getDbTypeFromDataSource(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            String url = metaData.getURL();
            return JdbcUtils.getDbType(url, null);
        }
    }

    public static boolean isMySQL(DbType dbType) {
        return DbType.MYSQL == dbType || DbType.MARIADB == dbType;
    }

    public static boolean isSQLServer(DbType dbType) {
        return DbType.SQL_SERVER == dbType;
    }

    public static boolean isPostgreSQL(DbType dbType) {
        return DbType.POSTGRE_SQL == dbType;
    }
}
