package org.springblade.core.database.mybatis.tkmybatis;
import tk.mybatis.mapper.entity.Example;

public class SeeyonExample extends Example {
    public SeeyonExample(Class<?> entityClass) {
        super(entityClass);
    }

    public SeeyonExample(Class<?> entityClass, boolean exists) {
        super(entityClass, exists);
    }

    public SeeyonExample(Class<?> entityClass, boolean exists, boolean notNull) {
        super(entityClass, exists, notNull);
    }

    public SeeyonCriteria createCriteriaAddOn() {
        SeeyonCriteria seeyonCriteria = new SeeyonCriteria(this.propertyMap, this.exists, this.notNull);
        return seeyonCriteria;
    }

    @Override
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        criteria.setAndOr("or");
        oredCriteria.add(criteria);
        return criteria;
    }

    @Override
    public Criteria and() {
        Criteria criteria = createCriteriaInternal();
        criteria.setAndOr("and");
        oredCriteria.add(criteria);
        return criteria;
    }

    @Override
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            criteria.setAndOr("and");
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    @Override
    protected Criteria createCriteriaInternal() {
        return this.createCriteriaAddOn();
    }
}
