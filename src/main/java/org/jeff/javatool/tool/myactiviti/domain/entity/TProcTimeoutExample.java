package org.jeff.javatool.tool.myactiviti.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class TProcTimeoutExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TProcTimeoutExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andProcDefIdIsNull() {
            addCriterion("PROC_DEF_ID is null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIsNotNull() {
            addCriterion("PROC_DEF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdEqualTo(String value) {
            addCriterion("PROC_DEF_ID =", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotEqualTo(String value) {
            addCriterion("PROC_DEF_ID <>", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThan(String value) {
            addCriterion("PROC_DEF_ID >", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_ID >=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThan(String value) {
            addCriterion("PROC_DEF_ID <", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_ID <=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLike(String value) {
            addCriterion("PROC_DEF_ID like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotLike(String value) {
            addCriterion("PROC_DEF_ID not like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIn(List<String> values) {
            addCriterion("PROC_DEF_ID in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotIn(List<String> values) {
            addCriterion("PROC_DEF_ID not in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdBetween(String value1, String value2) {
            addCriterion("PROC_DEF_ID between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotBetween(String value1, String value2) {
            addCriterion("PROC_DEF_ID not between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIsNull() {
            addCriterion("TASK_DEF_KEY is null");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIsNotNull() {
            addCriterion("TASK_DEF_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyEqualTo(String value) {
            addCriterion("TASK_DEF_KEY =", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotEqualTo(String value) {
            addCriterion("TASK_DEF_KEY <>", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyGreaterThan(String value) {
            addCriterion("TASK_DEF_KEY >", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_DEF_KEY >=", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLessThan(String value) {
            addCriterion("TASK_DEF_KEY <", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLessThanOrEqualTo(String value) {
            addCriterion("TASK_DEF_KEY <=", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLike(String value) {
            addCriterion("TASK_DEF_KEY like", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotLike(String value) {
            addCriterion("TASK_DEF_KEY not like", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIn(List<String> values) {
            addCriterion("TASK_DEF_KEY in", values, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotIn(List<String> values) {
            addCriterion("TASK_DEF_KEY not in", values, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyBetween(String value1, String value2) {
            addCriterion("TASK_DEF_KEY between", value1, value2, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotBetween(String value1, String value2) {
            addCriterion("TASK_DEF_KEY not between", value1, value2, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("TIME is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Integer value) {
            addCriterion("TIME =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Integer value) {
            addCriterion("TIME <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Integer value) {
            addCriterion("TIME >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Integer value) {
            addCriterion("TIME <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Integer value) {
            addCriterion("TIME <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Integer> values) {
            addCriterion("TIME in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Integer> values) {
            addCriterion("TIME not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Integer value1, Integer value2) {
            addCriterion("TIME between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("TIME not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Short value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Short value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Short value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Short value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Short value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Short value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Short> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Short> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Short value1, Short value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Short value1, Short value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondIsNull() {
            addCriterion("TIME_BEYOND is null");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondIsNotNull() {
            addCriterion("TIME_BEYOND is not null");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondEqualTo(Integer value) {
            addCriterion("TIME_BEYOND =", value, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondNotEqualTo(Integer value) {
            addCriterion("TIME_BEYOND <>", value, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondGreaterThan(Integer value) {
            addCriterion("TIME_BEYOND >", value, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIME_BEYOND >=", value, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondLessThan(Integer value) {
            addCriterion("TIME_BEYOND <", value, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondLessThanOrEqualTo(Integer value) {
            addCriterion("TIME_BEYOND <=", value, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondIn(List<Integer> values) {
            addCriterion("TIME_BEYOND in", values, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondNotIn(List<Integer> values) {
            addCriterion("TIME_BEYOND not in", values, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondBetween(Integer value1, Integer value2) {
            addCriterion("TIME_BEYOND between", value1, value2, "timeBeyond");
            return (Criteria) this;
        }

        public Criteria andTimeBeyondNotBetween(Integer value1, Integer value2) {
            addCriterion("TIME_BEYOND not between", value1, value2, "timeBeyond");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}