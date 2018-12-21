package com.travel.channel.entity.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DltMapHotelPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DltMapHotelPOExample() {
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

        public Criteria andZhHotelIdIsNull() {
            addCriterion("zh_hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdIsNotNull() {
            addCriterion("zh_hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdEqualTo(Long value) {
            addCriterion("zh_hotel_id =", value, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdNotEqualTo(Long value) {
            addCriterion("zh_hotel_id <>", value, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdGreaterThan(Long value) {
            addCriterion("zh_hotel_id >", value, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("zh_hotel_id >=", value, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdLessThan(Long value) {
            addCriterion("zh_hotel_id <", value, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdLessThanOrEqualTo(Long value) {
            addCriterion("zh_hotel_id <=", value, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdIn(List<Long> values) {
            addCriterion("zh_hotel_id in", values, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdNotIn(List<Long> values) {
            addCriterion("zh_hotel_id not in", values, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdBetween(Long value1, Long value2) {
            addCriterion("zh_hotel_id between", value1, value2, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelIdNotBetween(Long value1, Long value2) {
            addCriterion("zh_hotel_id not between", value1, value2, "zhHotelId");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameIsNull() {
            addCriterion("zh_hotel_name is null");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameIsNotNull() {
            addCriterion("zh_hotel_name is not null");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameEqualTo(String value) {
            addCriterion("zh_hotel_name =", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameNotEqualTo(String value) {
            addCriterion("zh_hotel_name <>", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameGreaterThan(String value) {
            addCriterion("zh_hotel_name >", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameGreaterThanOrEqualTo(String value) {
            addCriterion("zh_hotel_name >=", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameLessThan(String value) {
            addCriterion("zh_hotel_name <", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameLessThanOrEqualTo(String value) {
            addCriterion("zh_hotel_name <=", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameLike(String value) {
            addCriterion("zh_hotel_name like", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameNotLike(String value) {
            addCriterion("zh_hotel_name not like", value, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameIn(List<String> values) {
            addCriterion("zh_hotel_name in", values, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameNotIn(List<String> values) {
            addCriterion("zh_hotel_name not in", values, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameBetween(String value1, String value2) {
            addCriterion("zh_hotel_name between", value1, value2, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andZhHotelNameNotBetween(String value1, String value2) {
            addCriterion("zh_hotel_name not between", value1, value2, "zhHotelName");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdIsNull() {
            addCriterion("dlt_master_hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdIsNotNull() {
            addCriterion("dlt_master_hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdEqualTo(Long value) {
            addCriterion("dlt_master_hotel_id =", value, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdNotEqualTo(Long value) {
            addCriterion("dlt_master_hotel_id <>", value, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdGreaterThan(Long value) {
            addCriterion("dlt_master_hotel_id >", value, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dlt_master_hotel_id >=", value, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdLessThan(Long value) {
            addCriterion("dlt_master_hotel_id <", value, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdLessThanOrEqualTo(Long value) {
            addCriterion("dlt_master_hotel_id <=", value, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdIn(List<Long> values) {
            addCriterion("dlt_master_hotel_id in", values, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdNotIn(List<Long> values) {
            addCriterion("dlt_master_hotel_id not in", values, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdBetween(Long value1, Long value2) {
            addCriterion("dlt_master_hotel_id between", value1, value2, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltMasterHotelIdNotBetween(Long value1, Long value2) {
            addCriterion("dlt_master_hotel_id not between", value1, value2, "dltMasterHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdIsNull() {
            addCriterion("dlt_hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdIsNotNull() {
            addCriterion("dlt_hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdEqualTo(Long value) {
            addCriterion("dlt_hotel_id =", value, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdNotEqualTo(Long value) {
            addCriterion("dlt_hotel_id <>", value, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdGreaterThan(Long value) {
            addCriterion("dlt_hotel_id >", value, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dlt_hotel_id >=", value, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdLessThan(Long value) {
            addCriterion("dlt_hotel_id <", value, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdLessThanOrEqualTo(Long value) {
            addCriterion("dlt_hotel_id <=", value, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdIn(List<Long> values) {
            addCriterion("dlt_hotel_id in", values, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdNotIn(List<Long> values) {
            addCriterion("dlt_hotel_id not in", values, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdBetween(Long value1, Long value2) {
            addCriterion("dlt_hotel_id between", value1, value2, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelIdNotBetween(Long value1, Long value2) {
            addCriterion("dlt_hotel_id not between", value1, value2, "dltHotelId");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameIsNull() {
            addCriterion("dlt_hotel_name is null");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameIsNotNull() {
            addCriterion("dlt_hotel_name is not null");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameEqualTo(String value) {
            addCriterion("dlt_hotel_name =", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameNotEqualTo(String value) {
            addCriterion("dlt_hotel_name <>", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameGreaterThan(String value) {
            addCriterion("dlt_hotel_name >", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameGreaterThanOrEqualTo(String value) {
            addCriterion("dlt_hotel_name >=", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameLessThan(String value) {
            addCriterion("dlt_hotel_name <", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameLessThanOrEqualTo(String value) {
            addCriterion("dlt_hotel_name <=", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameLike(String value) {
            addCriterion("dlt_hotel_name like", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameNotLike(String value) {
            addCriterion("dlt_hotel_name not like", value, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameIn(List<String> values) {
            addCriterion("dlt_hotel_name in", values, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameNotIn(List<String> values) {
            addCriterion("dlt_hotel_name not in", values, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameBetween(String value1, String value2) {
            addCriterion("dlt_hotel_name between", value1, value2, "dltHotelName");
            return (Criteria) this;
        }

        public Criteria andDltHotelNameNotBetween(String value1, String value2) {
            addCriterion("dlt_hotel_name not between", value1, value2, "dltHotelName");
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