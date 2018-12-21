package com.travel.hotel.dlt.entity.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DltOrderPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DltOrderPOExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdIsNull() {
            addCriterion("dlt_order_id is null");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdIsNotNull() {
            addCriterion("dlt_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdEqualTo(String value) {
            addCriterion("dlt_order_id =", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotEqualTo(String value) {
            addCriterion("dlt_order_id <>", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdGreaterThan(String value) {
            addCriterion("dlt_order_id >", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("dlt_order_id >=", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdLessThan(String value) {
            addCriterion("dlt_order_id <", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdLessThanOrEqualTo(String value) {
            addCriterion("dlt_order_id <=", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdLike(String value) {
            addCriterion("dlt_order_id like", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotLike(String value) {
            addCriterion("dlt_order_id not like", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdIn(List<String> values) {
            addCriterion("dlt_order_id in", values, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotIn(List<String> values) {
            addCriterion("dlt_order_id not in", values, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdBetween(String value1, String value2) {
            addCriterion("dlt_order_id between", value1, value2, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotBetween(String value1, String value2) {
            addCriterion("dlt_order_id not between", value1, value2, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andIsHandledIsNull() {
            addCriterion("is_handled is null");
            return (Criteria) this;
        }

        public Criteria andIsHandledIsNotNull() {
            addCriterion("is_handled is not null");
            return (Criteria) this;
        }

        public Criteria andIsHandledEqualTo(Integer value) {
            addCriterion("is_handled =", value, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledNotEqualTo(Integer value) {
            addCriterion("is_handled <>", value, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledGreaterThan(Integer value) {
            addCriterion("is_handled >", value, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_handled >=", value, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledLessThan(Integer value) {
            addCriterion("is_handled <", value, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledLessThanOrEqualTo(Integer value) {
            addCriterion("is_handled <=", value, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledIn(List<Integer> values) {
            addCriterion("is_handled in", values, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledNotIn(List<Integer> values) {
            addCriterion("is_handled not in", values, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledBetween(Integer value1, Integer value2) {
            addCriterion("is_handled between", value1, value2, "isHandled");
            return (Criteria) this;
        }

        public Criteria andIsHandledNotBetween(Integer value1, Integer value2) {
            addCriterion("is_handled not between", value1, value2, "isHandled");
            return (Criteria) this;
        }

        public Criteria andHandleDateIsNull() {
            addCriterion("handle_date is null");
            return (Criteria) this;
        }

        public Criteria andHandleDateIsNotNull() {
            addCriterion("handle_date is not null");
            return (Criteria) this;
        }

        public Criteria andHandleDateEqualTo(Date value) {
            addCriterion("handle_date =", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateNotEqualTo(Date value) {
            addCriterion("handle_date <>", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateGreaterThan(Date value) {
            addCriterion("handle_date >", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_date >=", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateLessThan(Date value) {
            addCriterion("handle_date <", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateLessThanOrEqualTo(Date value) {
            addCriterion("handle_date <=", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateIn(List<Date> values) {
            addCriterion("handle_date in", values, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateNotIn(List<Date> values) {
            addCriterion("handle_date not in", values, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateBetween(Date value1, Date value2) {
            addCriterion("handle_date between", value1, value2, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateNotBetween(Date value1, Date value2) {
            addCriterion("handle_date not between", value1, value2, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleResultIsNull() {
            addCriterion("handle_result is null");
            return (Criteria) this;
        }

        public Criteria andHandleResultIsNotNull() {
            addCriterion("handle_result is not null");
            return (Criteria) this;
        }

        public Criteria andHandleResultEqualTo(String value) {
            addCriterion("handle_result =", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultNotEqualTo(String value) {
            addCriterion("handle_result <>", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultGreaterThan(String value) {
            addCriterion("handle_result >", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultGreaterThanOrEqualTo(String value) {
            addCriterion("handle_result >=", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultLessThan(String value) {
            addCriterion("handle_result <", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultLessThanOrEqualTo(String value) {
            addCriterion("handle_result <=", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultLike(String value) {
            addCriterion("handle_result like", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultNotLike(String value) {
            addCriterion("handle_result not like", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultIn(List<String> values) {
            addCriterion("handle_result in", values, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultNotIn(List<String> values) {
            addCriterion("handle_result not in", values, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultBetween(String value1, String value2) {
            addCriterion("handle_result between", value1, value2, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultNotBetween(String value1, String value2) {
            addCriterion("handle_result not between", value1, value2, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkIsNull() {
            addCriterion("handle_remark is null");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkIsNotNull() {
            addCriterion("handle_remark is not null");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkEqualTo(String value) {
            addCriterion("handle_remark =", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkNotEqualTo(String value) {
            addCriterion("handle_remark <>", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkGreaterThan(String value) {
            addCriterion("handle_remark >", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("handle_remark >=", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkLessThan(String value) {
            addCriterion("handle_remark <", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkLessThanOrEqualTo(String value) {
            addCriterion("handle_remark <=", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkLike(String value) {
            addCriterion("handle_remark like", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkNotLike(String value) {
            addCriterion("handle_remark not like", value, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkIn(List<String> values) {
            addCriterion("handle_remark in", values, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkNotIn(List<String> values) {
            addCriterion("handle_remark not in", values, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkBetween(String value1, String value2) {
            addCriterion("handle_remark between", value1, value2, "handleRemark");
            return (Criteria) this;
        }

        public Criteria andHandleRemarkNotBetween(String value1, String value2) {
            addCriterion("handle_remark not between", value1, value2, "handleRemark");
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