package com.travel.hotel.dlt.entity.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DltOrderCancelRulesPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DltOrderCancelRulesPOExample() {
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
            addCriterion("dlt_order_Id is null");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdIsNotNull() {
            addCriterion("dlt_order_Id is not null");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdEqualTo(String value) {
            addCriterion("dlt_order_Id =", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotEqualTo(String value) {
            addCriterion("dlt_order_Id <>", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdGreaterThan(String value) {
            addCriterion("dlt_order_Id >", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("dlt_order_Id >=", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdLessThan(String value) {
            addCriterion("dlt_order_Id <", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdLessThanOrEqualTo(String value) {
            addCriterion("dlt_order_Id <=", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdLike(String value) {
            addCriterion("dlt_order_Id like", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotLike(String value) {
            addCriterion("dlt_order_Id not like", value, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdIn(List<String> values) {
            addCriterion("dlt_order_Id in", values, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotIn(List<String> values) {
            addCriterion("dlt_order_Id not in", values, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdBetween(String value1, String value2) {
            addCriterion("dlt_order_Id between", value1, value2, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDltOrderIdNotBetween(String value1, String value2) {
            addCriterion("dlt_order_Id not between", value1, value2, "dltOrderId");
            return (Criteria) this;
        }

        public Criteria andDeductTypeIsNull() {
            addCriterion("deduct_type is null");
            return (Criteria) this;
        }

        public Criteria andDeductTypeIsNotNull() {
            addCriterion("deduct_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeductTypeEqualTo(Integer value) {
            addCriterion("deduct_type =", value, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeNotEqualTo(Integer value) {
            addCriterion("deduct_type <>", value, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeGreaterThan(Integer value) {
            addCriterion("deduct_type >", value, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("deduct_type >=", value, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeLessThan(Integer value) {
            addCriterion("deduct_type <", value, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeLessThanOrEqualTo(Integer value) {
            addCriterion("deduct_type <=", value, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeIn(List<Integer> values) {
            addCriterion("deduct_type in", values, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeNotIn(List<Integer> values) {
            addCriterion("deduct_type not in", values, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeBetween(Integer value1, Integer value2) {
            addCriterion("deduct_type between", value1, value2, "deductType");
            return (Criteria) this;
        }

        public Criteria andDeductTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("deduct_type not between", value1, value2, "deductType");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeIsNull() {
            addCriterion("last_cancel_time is null");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeIsNotNull() {
            addCriterion("last_cancel_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeEqualTo(Date value) {
            addCriterion("last_cancel_time =", value, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeNotEqualTo(Date value) {
            addCriterion("last_cancel_time <>", value, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeGreaterThan(Date value) {
            addCriterion("last_cancel_time >", value, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_cancel_time >=", value, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeLessThan(Date value) {
            addCriterion("last_cancel_time <", value, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_cancel_time <=", value, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeIn(List<Date> values) {
            addCriterion("last_cancel_time in", values, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeNotIn(List<Date> values) {
            addCriterion("last_cancel_time not in", values, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeBetween(Date value1, Date value2) {
            addCriterion("last_cancel_time between", value1, value2, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andLastCancelTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_cancel_time not between", value1, value2, "lastCancelTime");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(Long value) {
            addCriterion("value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(Long value) {
            addCriterion("value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(Long value) {
            addCriterion("value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(Long value) {
            addCriterion("value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(Long value) {
            addCriterion("value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(Long value) {
            addCriterion("value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<Long> values) {
            addCriterion("value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<Long> values) {
            addCriterion("value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(Long value1, Long value2) {
            addCriterion("value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(Long value1, Long value2) {
            addCriterion("value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
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

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNull() {
            addCriterion("modify_date is null");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("modify_date is not null");
            return (Criteria) this;
        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("modify_date =", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("modify_date <>", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("modify_date >", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_date >=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("modify_date <", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("modify_date <=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIn(List<Date> values) {
            addCriterion("modify_date in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotIn(List<Date> values) {
            addCriterion("modify_date not in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("modify_date between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("modify_date not between", value1, value2, "modifyDate");
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