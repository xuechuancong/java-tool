package org.jeff.javatool.tool.myactiviti.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TProcCustomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TProcCustomExample() {
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

        public Criteria andProcDefKeyIsNull() {
            addCriterion("PROC_DEF_KEY is null");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyIsNotNull() {
            addCriterion("PROC_DEF_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyEqualTo(String value) {
            addCriterion("PROC_DEF_KEY =", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyNotEqualTo(String value) {
            addCriterion("PROC_DEF_KEY <>", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyGreaterThan(String value) {
            addCriterion("PROC_DEF_KEY >", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_KEY >=", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyLessThan(String value) {
            addCriterion("PROC_DEF_KEY <", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyLessThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_KEY <=", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyLike(String value) {
            addCriterion("PROC_DEF_KEY like", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyNotLike(String value) {
            addCriterion("PROC_DEF_KEY not like", value, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyIn(List<String> values) {
            addCriterion("PROC_DEF_KEY in", values, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyNotIn(List<String> values) {
            addCriterion("PROC_DEF_KEY not in", values, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyBetween(String value1, String value2) {
            addCriterion("PROC_DEF_KEY between", value1, value2, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andProcDefKeyNotBetween(String value1, String value2) {
            addCriterion("PROC_DEF_KEY not between", value1, value2, "procDefKey");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("VERSION like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("VERSION not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andProcDefNameIsNull() {
            addCriterion("PROC_DEF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProcDefNameIsNotNull() {
            addCriterion("PROC_DEF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProcDefNameEqualTo(String value) {
            addCriterion("PROC_DEF_NAME =", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameNotEqualTo(String value) {
            addCriterion("PROC_DEF_NAME <>", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameGreaterThan(String value) {
            addCriterion("PROC_DEF_NAME >", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_NAME >=", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameLessThan(String value) {
            addCriterion("PROC_DEF_NAME <", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameLessThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_NAME <=", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameLike(String value) {
            addCriterion("PROC_DEF_NAME like", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameNotLike(String value) {
            addCriterion("PROC_DEF_NAME not like", value, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameIn(List<String> values) {
            addCriterion("PROC_DEF_NAME in", values, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameNotIn(List<String> values) {
            addCriterion("PROC_DEF_NAME not in", values, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameBetween(String value1, String value2) {
            addCriterion("PROC_DEF_NAME between", value1, value2, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefNameNotBetween(String value1, String value2) {
            addCriterion("PROC_DEF_NAME not between", value1, value2, "procDefName");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryIsNull() {
            addCriterion("PROC_DEF_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryIsNotNull() {
            addCriterion("PROC_DEF_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryEqualTo(String value) {
            addCriterion("PROC_DEF_CATEGORY =", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryNotEqualTo(String value) {
            addCriterion("PROC_DEF_CATEGORY <>", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryGreaterThan(String value) {
            addCriterion("PROC_DEF_CATEGORY >", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_CATEGORY >=", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryLessThan(String value) {
            addCriterion("PROC_DEF_CATEGORY <", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryLessThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_CATEGORY <=", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryLike(String value) {
            addCriterion("PROC_DEF_CATEGORY like", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryNotLike(String value) {
            addCriterion("PROC_DEF_CATEGORY not like", value, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryIn(List<String> values) {
            addCriterion("PROC_DEF_CATEGORY in", values, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryNotIn(List<String> values) {
            addCriterion("PROC_DEF_CATEGORY not in", values, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryBetween(String value1, String value2) {
            addCriterion("PROC_DEF_CATEGORY between", value1, value2, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andProcDefCategoryNotBetween(String value1, String value2) {
            addCriterion("PROC_DEF_CATEGORY not between", value1, value2, "procDefCategory");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNull() {
            addCriterion("FORM_ID is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("FORM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(String value) {
            addCriterion("FORM_ID =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(String value) {
            addCriterion("FORM_ID <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(String value) {
            addCriterion("FORM_ID >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_ID >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(String value) {
            addCriterion("FORM_ID <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(String value) {
            addCriterion("FORM_ID <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLike(String value) {
            addCriterion("FORM_ID like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotLike(String value) {
            addCriterion("FORM_ID not like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<String> values) {
            addCriterion("FORM_ID in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<String> values) {
            addCriterion("FORM_ID not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(String value1, String value2) {
            addCriterion("FORM_ID between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(String value1, String value2) {
            addCriterion("FORM_ID not between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andJsonIsNull() {
            addCriterion("JSON is null");
            return (Criteria) this;
        }

        public Criteria andJsonIsNotNull() {
            addCriterion("JSON is not null");
            return (Criteria) this;
        }

        public Criteria andJsonEqualTo(String value) {
            addCriterion("JSON =", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonNotEqualTo(String value) {
            addCriterion("JSON <>", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonGreaterThan(String value) {
            addCriterion("JSON >", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonGreaterThanOrEqualTo(String value) {
            addCriterion("JSON >=", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonLessThan(String value) {
            addCriterion("JSON <", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonLessThanOrEqualTo(String value) {
            addCriterion("JSON <=", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonLike(String value) {
            addCriterion("JSON like", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonNotLike(String value) {
            addCriterion("JSON not like", value, "json");
            return (Criteria) this;
        }

        public Criteria andJsonIn(List<String> values) {
            addCriterion("JSON in", values, "json");
            return (Criteria) this;
        }

        public Criteria andJsonNotIn(List<String> values) {
            addCriterion("JSON not in", values, "json");
            return (Criteria) this;
        }

        public Criteria andJsonBetween(String value1, String value2) {
            addCriterion("JSON between", value1, value2, "json");
            return (Criteria) this;
        }

        public Criteria andJsonNotBetween(String value1, String value2) {
            addCriterion("JSON not between", value1, value2, "json");
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

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
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

        public Criteria andCreatorIsNull() {
            addCriterion("CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("CREATOR =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("CREATOR <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("CREATOR >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("CREATOR <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("CREATOR <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("CREATOR like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("CREATOR not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("CREATOR in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("CREATOR not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("CREATOR between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("CREATOR not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andFormNameIsNull() {
            addCriterion("FORM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFormNameIsNotNull() {
            addCriterion("FORM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFormNameEqualTo(String value) {
            addCriterion("FORM_NAME =", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotEqualTo(String value) {
            addCriterion("FORM_NAME <>", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameGreaterThan(String value) {
            addCriterion("FORM_NAME >", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_NAME >=", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLessThan(String value) {
            addCriterion("FORM_NAME <", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLessThanOrEqualTo(String value) {
            addCriterion("FORM_NAME <=", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLike(String value) {
            addCriterion("FORM_NAME like", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotLike(String value) {
            addCriterion("FORM_NAME not like", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameIn(List<String> values) {
            addCriterion("FORM_NAME in", values, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotIn(List<String> values) {
            addCriterion("FORM_NAME not in", values, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameBetween(String value1, String value2) {
            addCriterion("FORM_NAME between", value1, value2, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotBetween(String value1, String value2) {
            addCriterion("FORM_NAME not between", value1, value2, "formName");
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