package org.springblade.modules.iot.mybatis.core.enums;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DbTypeEnum {

    MYSQL("mysql", "com.mysql.cj.jdbc.Driver", DbType.MYSQL),
    MARIADB("mariadb", "org.mariadb.jdbc.Driver", DbType.MARIADB),
    ORACLE("oracle", "oracle.jdbc.driver.OracleDriver", DbType.ORACLE),
    SQLSERVER("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver", DbType.SQL_SERVER),
    POSTGRE_SQL("postgresql", "org.postgresql.Driver", DbType.POSTGRE_SQL),
    ;

    private final String name;
    private final String driverClass;
    private final DbType dbType;

    public static DbTypeEnum find(String name) {
        for (DbTypeEnum type : values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
