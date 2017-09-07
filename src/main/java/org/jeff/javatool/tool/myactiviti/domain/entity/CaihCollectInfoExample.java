package org.jeff.javatool.tool.myactiviti.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaihCollectInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaihCollectInfoExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIsNull() {
            addCriterion("COLLECT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIsNotNull() {
            addCriterion("COLLECT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCollectTypeEqualTo(String value) {
            addCriterion("COLLECT_TYPE =", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotEqualTo(String value) {
            addCriterion("COLLECT_TYPE <>", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeGreaterThan(String value) {
            addCriterion("COLLECT_TYPE >", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COLLECT_TYPE >=", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLessThan(String value) {
            addCriterion("COLLECT_TYPE <", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLessThanOrEqualTo(String value) {
            addCriterion("COLLECT_TYPE <=", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLike(String value) {
            addCriterion("COLLECT_TYPE like", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotLike(String value) {
            addCriterion("COLLECT_TYPE not like", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIn(List<String> values) {
            addCriterion("COLLECT_TYPE in", values, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotIn(List<String> values) {
            addCriterion("COLLECT_TYPE not in", values, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeBetween(String value1, String value2) {
            addCriterion("COLLECT_TYPE between", value1, value2, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotBetween(String value1, String value2) {
            addCriterion("COLLECT_TYPE not between", value1, value2, "collectType");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andCollectIdIsNull() {
            addCriterion("COLLECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andCollectIdIsNotNull() {
            addCriterion("COLLECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCollectIdEqualTo(String value) {
            addCriterion("COLLECT_ID =", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotEqualTo(String value) {
            addCriterion("COLLECT_ID <>", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdGreaterThan(String value) {
            addCriterion("COLLECT_ID >", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdGreaterThanOrEqualTo(String value) {
            addCriterion("COLLECT_ID >=", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLessThan(String value) {
            addCriterion("COLLECT_ID <", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLessThanOrEqualTo(String value) {
            addCriterion("COLLECT_ID <=", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdLike(String value) {
            addCriterion("COLLECT_ID like", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotLike(String value) {
            addCriterion("COLLECT_ID not like", value, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdIn(List<String> values) {
            addCriterion("COLLECT_ID in", values, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotIn(List<String> values) {
            addCriterion("COLLECT_ID not in", values, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdBetween(String value1, String value2) {
            addCriterion("COLLECT_ID between", value1, value2, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectIdNotBetween(String value1, String value2) {
            addCriterion("COLLECT_ID not between", value1, value2, "collectId");
            return (Criteria) this;
        }

        public Criteria andCollectTimeIsNull() {
            addCriterion("COLLECT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCollectTimeIsNotNull() {
            addCriterion("COLLECT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCollectTimeEqualTo(Date value) {
            addCriterion("COLLECT_TIME =", value, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeNotEqualTo(Date value) {
            addCriterion("COLLECT_TIME <>", value, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeGreaterThan(Date value) {
            addCriterion("COLLECT_TIME >", value, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("COLLECT_TIME >=", value, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeLessThan(Date value) {
            addCriterion("COLLECT_TIME <", value, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeLessThanOrEqualTo(Date value) {
            addCriterion("COLLECT_TIME <=", value, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeIn(List<Date> values) {
            addCriterion("COLLECT_TIME in", values, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeNotIn(List<Date> values) {
            addCriterion("COLLECT_TIME not in", values, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeBetween(Date value1, Date value2) {
            addCriterion("COLLECT_TIME between", value1, value2, "collectTime");
            return (Criteria) this;
        }

        public Criteria andCollectTimeNotBetween(Date value1, Date value2) {
            addCriterion("COLLECT_TIME not between", value1, value2, "collectTime");
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

        public Criteria andRequestStaffIsNull() {
            addCriterion("REQUEST_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andRequestStaffIsNotNull() {
            addCriterion("REQUEST_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andRequestStaffEqualTo(String value) {
            addCriterion("REQUEST_STAFF =", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffNotEqualTo(String value) {
            addCriterion("REQUEST_STAFF <>", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffGreaterThan(String value) {
            addCriterion("REQUEST_STAFF >", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_STAFF >=", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffLessThan(String value) {
            addCriterion("REQUEST_STAFF <", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_STAFF <=", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffLike(String value) {
            addCriterion("REQUEST_STAFF like", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffNotLike(String value) {
            addCriterion("REQUEST_STAFF not like", value, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffIn(List<String> values) {
            addCriterion("REQUEST_STAFF in", values, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffNotIn(List<String> values) {
            addCriterion("REQUEST_STAFF not in", values, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffBetween(String value1, String value2) {
            addCriterion("REQUEST_STAFF between", value1, value2, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestStaffNotBetween(String value1, String value2) {
            addCriterion("REQUEST_STAFF not between", value1, value2, "requestStaff");
            return (Criteria) this;
        }

        public Criteria andRequestOrgIsNull() {
            addCriterion("REQUEST_ORG is null");
            return (Criteria) this;
        }

        public Criteria andRequestOrgIsNotNull() {
            addCriterion("REQUEST_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andRequestOrgEqualTo(String value) {
            addCriterion("REQUEST_ORG =", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgNotEqualTo(String value) {
            addCriterion("REQUEST_ORG <>", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgGreaterThan(String value) {
            addCriterion("REQUEST_ORG >", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_ORG >=", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgLessThan(String value) {
            addCriterion("REQUEST_ORG <", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_ORG <=", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgLike(String value) {
            addCriterion("REQUEST_ORG like", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgNotLike(String value) {
            addCriterion("REQUEST_ORG not like", value, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgIn(List<String> values) {
            addCriterion("REQUEST_ORG in", values, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgNotIn(List<String> values) {
            addCriterion("REQUEST_ORG not in", values, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgBetween(String value1, String value2) {
            addCriterion("REQUEST_ORG between", value1, value2, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestOrgNotBetween(String value1, String value2) {
            addCriterion("REQUEST_ORG not between", value1, value2, "requestOrg");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNull() {
            addCriterion("REQUEST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("REQUEST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("REQUEST_TIME =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("REQUEST_TIME <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("REQUEST_TIME >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REQUEST_TIME >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("REQUEST_TIME <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("REQUEST_TIME <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("REQUEST_TIME in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("REQUEST_TIME not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("REQUEST_TIME between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("REQUEST_TIME not between", value1, value2, "requestTime");
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