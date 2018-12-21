package com.travel.hotel.dlt.entity.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DltOrderDayPricePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DltOrderDayPricePOExample() {
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

        public Criteria andEffectDateIsNull() {
            addCriterion("effect_date is null");
            return (Criteria) this;
        }

        public Criteria andEffectDateIsNotNull() {
            addCriterion("effect_date is not null");
            return (Criteria) this;
        }

        public Criteria andEffectDateEqualTo(Date value) {
            addCriterion("effect_date =", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotEqualTo(Date value) {
            addCriterion("effect_date <>", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThan(Date value) {
            addCriterion("effect_date >", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThanOrEqualTo(Date value) {
            addCriterion("effect_date >=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThan(Date value) {
            addCriterion("effect_date <", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThanOrEqualTo(Date value) {
            addCriterion("effect_date <=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateIn(List<Date> values) {
            addCriterion("effect_date in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotIn(List<Date> values) {
            addCriterion("effect_date not in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateBetween(Date value1, Date value2) {
            addCriterion("effect_date between", value1, value2, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotBetween(Date value1, Date value2) {
            addCriterion("effect_date not between", value1, value2, "effectDate");
            return (Criteria) this;
        }

        public Criteria andMealTypeIsNull() {
            addCriterion("meal_type is null");
            return (Criteria) this;
        }

        public Criteria andMealTypeIsNotNull() {
            addCriterion("meal_type is not null");
            return (Criteria) this;
        }

        public Criteria andMealTypeEqualTo(Integer value) {
            addCriterion("meal_type =", value, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeNotEqualTo(Integer value) {
            addCriterion("meal_type <>", value, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeGreaterThan(Integer value) {
            addCriterion("meal_type >", value, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("meal_type >=", value, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeLessThan(Integer value) {
            addCriterion("meal_type <", value, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeLessThanOrEqualTo(Integer value) {
            addCriterion("meal_type <=", value, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeIn(List<Integer> values) {
            addCriterion("meal_type in", values, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeNotIn(List<Integer> values) {
            addCriterion("meal_type not in", values, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeBetween(Integer value1, Integer value2) {
            addCriterion("meal_type between", value1, value2, "mealType");
            return (Criteria) this;
        }

        public Criteria andMealTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("meal_type not between", value1, value2, "mealType");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNull() {
            addCriterion("breakfast is null");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNotNull() {
            addCriterion("breakfast is not null");
            return (Criteria) this;
        }

        public Criteria andBreakfastEqualTo(String value) {
            addCriterion("breakfast =", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotEqualTo(String value) {
            addCriterion("breakfast <>", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThan(String value) {
            addCriterion("breakfast >", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThanOrEqualTo(String value) {
            addCriterion("breakfast >=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThan(String value) {
            addCriterion("breakfast <", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThanOrEqualTo(String value) {
            addCriterion("breakfast <=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLike(String value) {
            addCriterion("breakfast like", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotLike(String value) {
            addCriterion("breakfast not like", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastIn(List<String> values) {
            addCriterion("breakfast in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotIn(List<String> values) {
            addCriterion("breakfast not in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastBetween(String value1, String value2) {
            addCriterion("breakfast between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotBetween(String value1, String value2) {
            addCriterion("breakfast not between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumIsNull() {
            addCriterion("breakfast_num is null");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumIsNotNull() {
            addCriterion("breakfast_num is not null");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumEqualTo(Integer value) {
            addCriterion("breakfast_num =", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumNotEqualTo(Integer value) {
            addCriterion("breakfast_num <>", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumGreaterThan(Integer value) {
            addCriterion("breakfast_num >", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("breakfast_num >=", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumLessThan(Integer value) {
            addCriterion("breakfast_num <", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumLessThanOrEqualTo(Integer value) {
            addCriterion("breakfast_num <=", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumIn(List<Integer> values) {
            addCriterion("breakfast_num in", values, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumNotIn(List<Integer> values) {
            addCriterion("breakfast_num not in", values, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumBetween(Integer value1, Integer value2) {
            addCriterion("breakfast_num between", value1, value2, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumNotBetween(Integer value1, Integer value2) {
            addCriterion("breakfast_num not between", value1, value2, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("price not between", value1, value2, "price");
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