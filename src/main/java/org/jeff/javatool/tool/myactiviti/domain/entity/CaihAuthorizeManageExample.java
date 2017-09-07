package org.jeff.javatool.tool.myactiviti.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaihAuthorizeManageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaihAuthorizeManageExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdIsNull() {
            addCriterion("AUTH_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdIsNotNull() {
            addCriterion("AUTH_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdEqualTo(String value) {
            addCriterion("AUTH_STAFF_ID =", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdNotEqualTo(String value) {
            addCriterion("AUTH_STAFF_ID <>", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdGreaterThan(String value) {
            addCriterion("AUTH_STAFF_ID >", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_STAFF_ID >=", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdLessThan(String value) {
            addCriterion("AUTH_STAFF_ID <", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdLessThanOrEqualTo(String value) {
            addCriterion("AUTH_STAFF_ID <=", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdLike(String value) {
            addCriterion("AUTH_STAFF_ID like", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdNotLike(String value) {
            addCriterion("AUTH_STAFF_ID not like", value, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdIn(List<String> values) {
            addCriterion("AUTH_STAFF_ID in", values, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdNotIn(List<String> values) {
            addCriterion("AUTH_STAFF_ID not in", values, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdBetween(String value1, String value2) {
            addCriterion("AUTH_STAFF_ID between", value1, value2, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffIdNotBetween(String value1, String value2) {
            addCriterion("AUTH_STAFF_ID not between", value1, value2, "authStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameIsNull() {
            addCriterion("AUTH_STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameIsNotNull() {
            addCriterion("AUTH_STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameEqualTo(String value) {
            addCriterion("AUTH_STAFF_NAME =", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameNotEqualTo(String value) {
            addCriterion("AUTH_STAFF_NAME <>", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameGreaterThan(String value) {
            addCriterion("AUTH_STAFF_NAME >", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_STAFF_NAME >=", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameLessThan(String value) {
            addCriterion("AUTH_STAFF_NAME <", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameLessThanOrEqualTo(String value) {
            addCriterion("AUTH_STAFF_NAME <=", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameLike(String value) {
            addCriterion("AUTH_STAFF_NAME like", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameNotLike(String value) {
            addCriterion("AUTH_STAFF_NAME not like", value, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameIn(List<String> values) {
            addCriterion("AUTH_STAFF_NAME in", values, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameNotIn(List<String> values) {
            addCriterion("AUTH_STAFF_NAME not in", values, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameBetween(String value1, String value2) {
            addCriterion("AUTH_STAFF_NAME between", value1, value2, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthStaffNameNotBetween(String value1, String value2) {
            addCriterion("AUTH_STAFF_NAME not between", value1, value2, "authStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdIsNull() {
            addCriterion("AUTHED_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdIsNotNull() {
            addCriterion("AUTHED_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdEqualTo(String value) {
            addCriterion("AUTHED_STAFF_ID =", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdNotEqualTo(String value) {
            addCriterion("AUTHED_STAFF_ID <>", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdGreaterThan(String value) {
            addCriterion("AUTHED_STAFF_ID >", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHED_STAFF_ID >=", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdLessThan(String value) {
            addCriterion("AUTHED_STAFF_ID <", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdLessThanOrEqualTo(String value) {
            addCriterion("AUTHED_STAFF_ID <=", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdLike(String value) {
            addCriterion("AUTHED_STAFF_ID like", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdNotLike(String value) {
            addCriterion("AUTHED_STAFF_ID not like", value, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdIn(List<String> values) {
            addCriterion("AUTHED_STAFF_ID in", values, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdNotIn(List<String> values) {
            addCriterion("AUTHED_STAFF_ID not in", values, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdBetween(String value1, String value2) {
            addCriterion("AUTHED_STAFF_ID between", value1, value2, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffIdNotBetween(String value1, String value2) {
            addCriterion("AUTHED_STAFF_ID not between", value1, value2, "authedStaffId");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameIsNull() {
            addCriterion("AUTHED_STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameIsNotNull() {
            addCriterion("AUTHED_STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameEqualTo(String value) {
            addCriterion("AUTHED_STAFF_NAME =", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameNotEqualTo(String value) {
            addCriterion("AUTHED_STAFF_NAME <>", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameGreaterThan(String value) {
            addCriterion("AUTHED_STAFF_NAME >", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHED_STAFF_NAME >=", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameLessThan(String value) {
            addCriterion("AUTHED_STAFF_NAME <", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameLessThanOrEqualTo(String value) {
            addCriterion("AUTHED_STAFF_NAME <=", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameLike(String value) {
            addCriterion("AUTHED_STAFF_NAME like", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameNotLike(String value) {
            addCriterion("AUTHED_STAFF_NAME not like", value, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameIn(List<String> values) {
            addCriterion("AUTHED_STAFF_NAME in", values, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameNotIn(List<String> values) {
            addCriterion("AUTHED_STAFF_NAME not in", values, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameBetween(String value1, String value2) {
            addCriterion("AUTHED_STAFF_NAME between", value1, value2, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andAuthedStaffNameNotBetween(String value1, String value2) {
            addCriterion("AUTHED_STAFF_NAME not between", value1, value2, "authedStaffName");
            return (Criteria) this;
        }

        public Criteria andProcessTypeIsNull() {
            addCriterion("PROCESS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andProcessTypeIsNotNull() {
            addCriterion("PROCESS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTypeEqualTo(String value) {
            addCriterion("PROCESS_TYPE =", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeNotEqualTo(String value) {
            addCriterion("PROCESS_TYPE <>", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeGreaterThan(String value) {
            addCriterion("PROCESS_TYPE >", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_TYPE >=", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeLessThan(String value) {
            addCriterion("PROCESS_TYPE <", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_TYPE <=", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeLike(String value) {
            addCriterion("PROCESS_TYPE like", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeNotLike(String value) {
            addCriterion("PROCESS_TYPE not like", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeIn(List<String> values) {
            addCriterion("PROCESS_TYPE in", values, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeNotIn(List<String> values) {
            addCriterion("PROCESS_TYPE not in", values, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeBetween(String value1, String value2) {
            addCriterion("PROCESS_TYPE between", value1, value2, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeNotBetween(String value1, String value2) {
            addCriterion("PROCESS_TYPE not between", value1, value2, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyIsNull() {
            addCriterion("PROCESS_DEF_KEY is null");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyIsNotNull() {
            addCriterion("PROCESS_DEF_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyEqualTo(String value) {
            addCriterion("PROCESS_DEF_KEY =", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyNotEqualTo(String value) {
            addCriterion("PROCESS_DEF_KEY <>", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyGreaterThan(String value) {
            addCriterion("PROCESS_DEF_KEY >", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_DEF_KEY >=", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyLessThan(String value) {
            addCriterion("PROCESS_DEF_KEY <", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_DEF_KEY <=", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyLike(String value) {
            addCriterion("PROCESS_DEF_KEY like", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyNotLike(String value) {
            addCriterion("PROCESS_DEF_KEY not like", value, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyIn(List<String> values) {
            addCriterion("PROCESS_DEF_KEY in", values, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyNotIn(List<String> values) {
            addCriterion("PROCESS_DEF_KEY not in", values, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyBetween(String value1, String value2) {
            addCriterion("PROCESS_DEF_KEY between", value1, value2, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessDefKeyNotBetween(String value1, String value2) {
            addCriterion("PROCESS_DEF_KEY not between", value1, value2, "processDefKey");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNull() {
            addCriterion("PROCESS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNotNull() {
            addCriterion("PROCESS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProcessNameEqualTo(String value) {
            addCriterion("PROCESS_NAME =", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotEqualTo(String value) {
            addCriterion("PROCESS_NAME <>", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThan(String value) {
            addCriterion("PROCESS_NAME >", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_NAME >=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThan(String value) {
            addCriterion("PROCESS_NAME <", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_NAME <=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLike(String value) {
            addCriterion("PROCESS_NAME like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotLike(String value) {
            addCriterion("PROCESS_NAME not like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameIn(List<String> values) {
            addCriterion("PROCESS_NAME in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotIn(List<String> values) {
            addCriterion("PROCESS_NAME not in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameBetween(String value1, String value2) {
            addCriterion("PROCESS_NAME between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotBetween(String value1, String value2) {
            addCriterion("PROCESS_NAME not between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdIsNull() {
            addCriterion("TASK_DEF_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdIsNotNull() {
            addCriterion("TASK_DEF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdEqualTo(String value) {
            addCriterion("TASK_DEF_ID =", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdNotEqualTo(String value) {
            addCriterion("TASK_DEF_ID <>", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdGreaterThan(String value) {
            addCriterion("TASK_DEF_ID >", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_DEF_ID >=", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdLessThan(String value) {
            addCriterion("TASK_DEF_ID <", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdLessThanOrEqualTo(String value) {
            addCriterion("TASK_DEF_ID <=", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdLike(String value) {
            addCriterion("TASK_DEF_ID like", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdNotLike(String value) {
            addCriterion("TASK_DEF_ID not like", value, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdIn(List<String> values) {
            addCriterion("TASK_DEF_ID in", values, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdNotIn(List<String> values) {
            addCriterion("TASK_DEF_ID not in", values, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdBetween(String value1, String value2) {
            addCriterion("TASK_DEF_ID between", value1, value2, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskDefIdNotBetween(String value1, String value2) {
            addCriterion("TASK_DEF_ID not between", value1, value2, "taskDefId");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("TASK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("TASK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("TASK_NAME =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("TASK_NAME <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("TASK_NAME >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_NAME >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("TASK_NAME <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_NAME <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("TASK_NAME like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("TASK_NAME not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("TASK_NAME in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("TASK_NAME not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("TASK_NAME between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("TASK_NAME not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeIsNull() {
            addCriterion("AUTH_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeIsNotNull() {
            addCriterion("AUTH_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeEqualTo(Date value) {
            addCriterion("AUTH_START_TIME =", value, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeNotEqualTo(Date value) {
            addCriterion("AUTH_START_TIME <>", value, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeGreaterThan(Date value) {
            addCriterion("AUTH_START_TIME >", value, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AUTH_START_TIME >=", value, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeLessThan(Date value) {
            addCriterion("AUTH_START_TIME <", value, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("AUTH_START_TIME <=", value, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeIn(List<Date> values) {
            addCriterion("AUTH_START_TIME in", values, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeNotIn(List<Date> values) {
            addCriterion("AUTH_START_TIME not in", values, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeBetween(Date value1, Date value2) {
            addCriterion("AUTH_START_TIME between", value1, value2, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("AUTH_START_TIME not between", value1, value2, "authStartTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeIsNull() {
            addCriterion("AUTH_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeIsNotNull() {
            addCriterion("AUTH_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeEqualTo(Date value) {
            addCriterion("AUTH_END_TIME =", value, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeNotEqualTo(Date value) {
            addCriterion("AUTH_END_TIME <>", value, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeGreaterThan(Date value) {
            addCriterion("AUTH_END_TIME >", value, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AUTH_END_TIME >=", value, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeLessThan(Date value) {
            addCriterion("AUTH_END_TIME <", value, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("AUTH_END_TIME <=", value, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeIn(List<Date> values) {
            addCriterion("AUTH_END_TIME in", values, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeNotIn(List<Date> values) {
            addCriterion("AUTH_END_TIME not in", values, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeBetween(Date value1, Date value2) {
            addCriterion("AUTH_END_TIME between", value1, value2, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andAuthEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("AUTH_END_TIME not between", value1, value2, "authEndTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("MODIFY_TIME =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("MODIFY_TIME <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("MODIFY_TIME >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("MODIFY_TIME <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("MODIFY_TIME in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("MODIFY_TIME not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andSpareField1IsNull() {
            addCriterion("SPARE_FIELD1 is null");
            return (Criteria) this;
        }

        public Criteria andSpareField1IsNotNull() {
            addCriterion("SPARE_FIELD1 is not null");
            return (Criteria) this;
        }

        public Criteria andSpareField1EqualTo(String value) {
            addCriterion("SPARE_FIELD1 =", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1NotEqualTo(String value) {
            addCriterion("SPARE_FIELD1 <>", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1GreaterThan(String value) {
            addCriterion("SPARE_FIELD1 >", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1GreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_FIELD1 >=", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1LessThan(String value) {
            addCriterion("SPARE_FIELD1 <", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1LessThanOrEqualTo(String value) {
            addCriterion("SPARE_FIELD1 <=", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1Like(String value) {
            addCriterion("SPARE_FIELD1 like", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1NotLike(String value) {
            addCriterion("SPARE_FIELD1 not like", value, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1In(List<String> values) {
            addCriterion("SPARE_FIELD1 in", values, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1NotIn(List<String> values) {
            addCriterion("SPARE_FIELD1 not in", values, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1Between(String value1, String value2) {
            addCriterion("SPARE_FIELD1 between", value1, value2, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField1NotBetween(String value1, String value2) {
            addCriterion("SPARE_FIELD1 not between", value1, value2, "spareField1");
            return (Criteria) this;
        }

        public Criteria andSpareField2IsNull() {
            addCriterion("SPARE_FIELD2 is null");
            return (Criteria) this;
        }

        public Criteria andSpareField2IsNotNull() {
            addCriterion("SPARE_FIELD2 is not null");
            return (Criteria) this;
        }

        public Criteria andSpareField2EqualTo(String value) {
            addCriterion("SPARE_FIELD2 =", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2NotEqualTo(String value) {
            addCriterion("SPARE_FIELD2 <>", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2GreaterThan(String value) {
            addCriterion("SPARE_FIELD2 >", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2GreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_FIELD2 >=", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2LessThan(String value) {
            addCriterion("SPARE_FIELD2 <", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2LessThanOrEqualTo(String value) {
            addCriterion("SPARE_FIELD2 <=", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2Like(String value) {
            addCriterion("SPARE_FIELD2 like", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2NotLike(String value) {
            addCriterion("SPARE_FIELD2 not like", value, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2In(List<String> values) {
            addCriterion("SPARE_FIELD2 in", values, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2NotIn(List<String> values) {
            addCriterion("SPARE_FIELD2 not in", values, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2Between(String value1, String value2) {
            addCriterion("SPARE_FIELD2 between", value1, value2, "spareField2");
            return (Criteria) this;
        }

        public Criteria andSpareField2NotBetween(String value1, String value2) {
            addCriterion("SPARE_FIELD2 not between", value1, value2, "spareField2");
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