package org.springblade.modules.iot.framework.datapermission.core.rule.dept;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * DeptDataPermissionRuleCustomizer adapter.
 */
public class DeptDataPermissionRuleCustomizer {
    private final Set<String> deptColumns = new HashSet<>();
    private final Set<String> userColumns = new HashSet<>();
    private final Set<Class<?>> tableClasses = new HashSet<>();

    public DeptDataPermissionRuleCustomizer addDeptColumn(String column) {
        deptColumns.add(column);
        return this;
    }

    public DeptDataPermissionRuleCustomizer addUserColumn(String column) {
        userColumns.add(column);
        return this;
    }

    public DeptDataPermissionRuleCustomizer addTable(Class<?> entityClass) {
        tableClasses.add(entityClass);
        return this;
    }
}
