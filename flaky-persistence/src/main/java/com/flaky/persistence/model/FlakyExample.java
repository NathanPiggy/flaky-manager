package com.flaky.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlakyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlakyExample() {
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

        public Criteria andFlakyIdIsNull() {
            addCriterion("flaky_id is null");
            return (Criteria) this;
        }

        public Criteria andFlakyIdIsNotNull() {
            addCriterion("flaky_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlakyIdEqualTo(Integer value) {
            addCriterion("flaky_id =", value, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdNotEqualTo(Integer value) {
            addCriterion("flaky_id <>", value, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdGreaterThan(Integer value) {
            addCriterion("flaky_id >", value, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("flaky_id >=", value, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdLessThan(Integer value) {
            addCriterion("flaky_id <", value, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdLessThanOrEqualTo(Integer value) {
            addCriterion("flaky_id <=", value, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdIn(List<Integer> values) {
            addCriterion("flaky_id in", values, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdNotIn(List<Integer> values) {
            addCriterion("flaky_id not in", values, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdBetween(Integer value1, Integer value2) {
            addCriterion("flaky_id between", value1, value2, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("flaky_id not between", value1, value2, "flakyId");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusIsNull() {
            addCriterion("flaky_status is null");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusIsNotNull() {
            addCriterion("flaky_status is not null");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusEqualTo(Integer value) {
            addCriterion("flaky_status =", value, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusNotEqualTo(Integer value) {
            addCriterion("flaky_status <>", value, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusGreaterThan(Integer value) {
            addCriterion("flaky_status >", value, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("flaky_status >=", value, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusLessThan(Integer value) {
            addCriterion("flaky_status <", value, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("flaky_status <=", value, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusIn(List<Integer> values) {
            addCriterion("flaky_status in", values, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusNotIn(List<Integer> values) {
            addCriterion("flaky_status not in", values, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusBetween(Integer value1, Integer value2) {
            addCriterion("flaky_status between", value1, value2, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andFlakyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("flaky_status not between", value1, value2, "flakyStatus");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeIsNull() {
            addCriterion("last_detect_time is null");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeIsNotNull() {
            addCriterion("last_detect_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeEqualTo(Date value) {
            addCriterion("last_detect_time =", value, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeNotEqualTo(Date value) {
            addCriterion("last_detect_time <>", value, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeGreaterThan(Date value) {
            addCriterion("last_detect_time >", value, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_detect_time >=", value, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeLessThan(Date value) {
            addCriterion("last_detect_time <", value, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_detect_time <=", value, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeIn(List<Date> values) {
            addCriterion("last_detect_time in", values, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeNotIn(List<Date> values) {
            addCriterion("last_detect_time not in", values, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeBetween(Date value1, Date value2) {
            addCriterion("last_detect_time between", value1, value2, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastDetectTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_detect_time not between", value1, value2, "lastDetectTime");
            return (Criteria) this;
        }

        public Criteria andLastSha1IsNull() {
            addCriterion("last_sha_1 is null");
            return (Criteria) this;
        }

        public Criteria andLastSha1IsNotNull() {
            addCriterion("last_sha_1 is not null");
            return (Criteria) this;
        }

        public Criteria andLastSha1EqualTo(String value) {
            addCriterion("last_sha_1 =", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1NotEqualTo(String value) {
            addCriterion("last_sha_1 <>", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1GreaterThan(String value) {
            addCriterion("last_sha_1 >", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1GreaterThanOrEqualTo(String value) {
            addCriterion("last_sha_1 >=", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1LessThan(String value) {
            addCriterion("last_sha_1 <", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1LessThanOrEqualTo(String value) {
            addCriterion("last_sha_1 <=", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1Like(String value) {
            addCriterion("last_sha_1 like", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1NotLike(String value) {
            addCriterion("last_sha_1 not like", value, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1In(List<String> values) {
            addCriterion("last_sha_1 in", values, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1NotIn(List<String> values) {
            addCriterion("last_sha_1 not in", values, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1Between(String value1, String value2) {
            addCriterion("last_sha_1 between", value1, value2, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andLastSha1NotBetween(String value1, String value2) {
            addCriterion("last_sha_1 not between", value1, value2, "lastSha1");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameIsNull() {
            addCriterion("unit_test_name is null");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameIsNotNull() {
            addCriterion("unit_test_name is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameEqualTo(String value) {
            addCriterion("unit_test_name =", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameNotEqualTo(String value) {
            addCriterion("unit_test_name <>", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameGreaterThan(String value) {
            addCriterion("unit_test_name >", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameGreaterThanOrEqualTo(String value) {
            addCriterion("unit_test_name >=", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameLessThan(String value) {
            addCriterion("unit_test_name <", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameLessThanOrEqualTo(String value) {
            addCriterion("unit_test_name <=", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameLike(String value) {
            addCriterion("unit_test_name like", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameNotLike(String value) {
            addCriterion("unit_test_name not like", value, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameIn(List<String> values) {
            addCriterion("unit_test_name in", values, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameNotIn(List<String> values) {
            addCriterion("unit_test_name not in", values, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameBetween(String value1, String value2) {
            addCriterion("unit_test_name between", value1, value2, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andUnitTestNameNotBetween(String value1, String value2) {
            addCriterion("unit_test_name not between", value1, value2, "unitTestName");
            return (Criteria) this;
        }

        public Criteria andDetectCountIsNull() {
            addCriterion("detect_count is null");
            return (Criteria) this;
        }

        public Criteria andDetectCountIsNotNull() {
            addCriterion("detect_count is not null");
            return (Criteria) this;
        }

        public Criteria andDetectCountEqualTo(Integer value) {
            addCriterion("detect_count =", value, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountNotEqualTo(Integer value) {
            addCriterion("detect_count <>", value, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountGreaterThan(Integer value) {
            addCriterion("detect_count >", value, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("detect_count >=", value, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountLessThan(Integer value) {
            addCriterion("detect_count <", value, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountLessThanOrEqualTo(Integer value) {
            addCriterion("detect_count <=", value, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountIn(List<Integer> values) {
            addCriterion("detect_count in", values, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountNotIn(List<Integer> values) {
            addCriterion("detect_count not in", values, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountBetween(Integer value1, Integer value2) {
            addCriterion("detect_count between", value1, value2, "detectCount");
            return (Criteria) this;
        }

        public Criteria andDetectCountNotBetween(Integer value1, Integer value2) {
            addCriterion("detect_count not between", value1, value2, "detectCount");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
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