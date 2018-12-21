package com.travel.hotel.dlt.entity.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DltOrderDetailPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DltOrderDetailPOExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChildChannelIsNull() {
            addCriterion("child_channel is null");
            return (Criteria) this;
        }

        public Criteria andChildChannelIsNotNull() {
            addCriterion("child_channel is not null");
            return (Criteria) this;
        }

        public Criteria andChildChannelEqualTo(String value) {
            addCriterion("child_channel =", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelNotEqualTo(String value) {
            addCriterion("child_channel <>", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelGreaterThan(String value) {
            addCriterion("child_channel >", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelGreaterThanOrEqualTo(String value) {
            addCriterion("child_channel >=", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelLessThan(String value) {
            addCriterion("child_channel <", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelLessThanOrEqualTo(String value) {
            addCriterion("child_channel <=", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelLike(String value) {
            addCriterion("child_channel like", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelNotLike(String value) {
            addCriterion("child_channel not like", value, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelIn(List<String> values) {
            addCriterion("child_channel in", values, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelNotIn(List<String> values) {
            addCriterion("child_channel not in", values, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelBetween(String value1, String value2) {
            addCriterion("child_channel between", value1, value2, "childChannel");
            return (Criteria) this;
        }

        public Criteria andChildChannelNotBetween(String value1, String value2) {
            addCriterion("child_channel not between", value1, value2, "childChannel");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNull() {
            addCriterion("order_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNotNull() {
            addCriterion("order_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDateEqualTo(Date value) {
            addCriterion("order_date =", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotEqualTo(Date value) {
            addCriterion("order_date <>", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThan(Date value) {
            addCriterion("order_date >", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThanOrEqualTo(Date value) {
            addCriterion("order_date >=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThan(Date value) {
            addCriterion("order_date <", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThanOrEqualTo(Date value) {
            addCriterion("order_date <=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIn(List<Date> values) {
            addCriterion("order_date in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotIn(List<Date> values) {
            addCriterion("order_date not in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateBetween(Date value1, Date value2) {
            addCriterion("order_date between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotBetween(Date value1, Date value2) {
            addCriterion("order_date not between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyIsNull() {
            addCriterion("order_currency is null");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyIsNotNull() {
            addCriterion("order_currency is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyEqualTo(String value) {
            addCriterion("order_currency =", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyNotEqualTo(String value) {
            addCriterion("order_currency <>", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyGreaterThan(String value) {
            addCriterion("order_currency >", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("order_currency >=", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyLessThan(String value) {
            addCriterion("order_currency <", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyLessThanOrEqualTo(String value) {
            addCriterion("order_currency <=", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyLike(String value) {
            addCriterion("order_currency like", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyNotLike(String value) {
            addCriterion("order_currency not like", value, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyIn(List<String> values) {
            addCriterion("order_currency in", values, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyNotIn(List<String> values) {
            addCriterion("order_currency not in", values, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyBetween(String value1, String value2) {
            addCriterion("order_currency between", value1, value2, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyNotBetween(String value1, String value2) {
            addCriterion("order_currency not between", value1, value2, "orderCurrency");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNull() {
            addCriterion("order_price is null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNotNull() {
            addCriterion("order_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceEqualTo(BigDecimal value) {
            addCriterion("order_price =", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotEqualTo(BigDecimal value) {
            addCriterion("order_price <>", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThan(BigDecimal value) {
            addCriterion("order_price >", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_price >=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThan(BigDecimal value) {
            addCriterion("order_price <", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_price <=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIn(List<BigDecimal> values) {
            addCriterion("order_price in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotIn(List<BigDecimal> values) {
            addCriterion("order_price not in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_price between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_price not between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNull() {
            addCriterion("form_type is null");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNotNull() {
            addCriterion("form_type is not null");
            return (Criteria) this;
        }

        public Criteria andFormTypeEqualTo(String value) {
            addCriterion("form_type =", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotEqualTo(String value) {
            addCriterion("form_type <>", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThan(String value) {
            addCriterion("form_type >", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThanOrEqualTo(String value) {
            addCriterion("form_type >=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThan(String value) {
            addCriterion("form_type <", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThanOrEqualTo(String value) {
            addCriterion("form_type <=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLike(String value) {
            addCriterion("form_type like", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotLike(String value) {
            addCriterion("form_type not like", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeIn(List<String> values) {
            addCriterion("form_type in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotIn(List<String> values) {
            addCriterion("form_type not in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeBetween(String value1, String value2) {
            addCriterion("form_type between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotBetween(String value1, String value2) {
            addCriterion("form_type not between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(String value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(String value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(String value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(String value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(String value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(String value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLike(String value) {
            addCriterion("order_status like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotLike(String value) {
            addCriterion("order_status not like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<String> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<String> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(String value1, String value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(String value1, String value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNull() {
            addCriterion("payment_type is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("payment_type is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeEqualTo(String value) {
            addCriterion("payment_type =", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotEqualTo(String value) {
            addCriterion("payment_type <>", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThan(String value) {
            addCriterion("payment_type >", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("payment_type >=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThan(String value) {
            addCriterion("payment_type <", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(String value) {
            addCriterion("payment_type <=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLike(String value) {
            addCriterion("payment_type like", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotLike(String value) {
            addCriterion("payment_type not like", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIn(List<String> values) {
            addCriterion("payment_type in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotIn(List<String> values) {
            addCriterion("payment_type not in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeBetween(String value1, String value2) {
            addCriterion("payment_type between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotBetween(String value1, String value2) {
            addCriterion("payment_type not between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andConfirmNoIsNull() {
            addCriterion("confirm_no is null");
            return (Criteria) this;
        }

        public Criteria andConfirmNoIsNotNull() {
            addCriterion("confirm_no is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmNoEqualTo(String value) {
            addCriterion("confirm_no =", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoNotEqualTo(String value) {
            addCriterion("confirm_no <>", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoGreaterThan(String value) {
            addCriterion("confirm_no >", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoGreaterThanOrEqualTo(String value) {
            addCriterion("confirm_no >=", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoLessThan(String value) {
            addCriterion("confirm_no <", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoLessThanOrEqualTo(String value) {
            addCriterion("confirm_no <=", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoLike(String value) {
            addCriterion("confirm_no like", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoNotLike(String value) {
            addCriterion("confirm_no not like", value, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoIn(List<String> values) {
            addCriterion("confirm_no in", values, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoNotIn(List<String> values) {
            addCriterion("confirm_no not in", values, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoBetween(String value1, String value2) {
            addCriterion("confirm_no between", value1, value2, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andConfirmNoNotBetween(String value1, String value2) {
            addCriterion("confirm_no not between", value1, value2, "confirmNo");
            return (Criteria) this;
        }

        public Criteria andCheckInDateIsNull() {
            addCriterion("check_in_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckInDateIsNotNull() {
            addCriterion("check_in_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckInDateEqualTo(Date value) {
            addCriterion("check_in_date =", value, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateNotEqualTo(Date value) {
            addCriterion("check_in_date <>", value, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateGreaterThan(Date value) {
            addCriterion("check_in_date >", value, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateGreaterThanOrEqualTo(Date value) {
            addCriterion("check_in_date >=", value, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateLessThan(Date value) {
            addCriterion("check_in_date <", value, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateLessThanOrEqualTo(Date value) {
            addCriterion("check_in_date <=", value, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateIn(List<Date> values) {
            addCriterion("check_in_date in", values, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateNotIn(List<Date> values) {
            addCriterion("check_in_date not in", values, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateBetween(Date value1, Date value2) {
            addCriterion("check_in_date between", value1, value2, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckInDateNotBetween(Date value1, Date value2) {
            addCriterion("check_in_date not between", value1, value2, "checkInDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateIsNull() {
            addCriterion("check_out_Date is null");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateIsNotNull() {
            addCriterion("check_out_Date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateEqualTo(Date value) {
            addCriterion("check_out_Date =", value, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateNotEqualTo(Date value) {
            addCriterion("check_out_Date <>", value, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateGreaterThan(Date value) {
            addCriterion("check_out_Date >", value, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateGreaterThanOrEqualTo(Date value) {
            addCriterion("check_out_Date >=", value, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateLessThan(Date value) {
            addCriterion("check_out_Date <", value, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateLessThanOrEqualTo(Date value) {
            addCriterion("check_out_Date <=", value, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateIn(List<Date> values) {
            addCriterion("check_out_Date in", values, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateNotIn(List<Date> values) {
            addCriterion("check_out_Date not in", values, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateBetween(Date value1, Date value2) {
            addCriterion("check_out_Date between", value1, value2, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCheckOutDateNotBetween(Date value1, Date value2) {
            addCriterion("check_out_Date not between", value1, value2, "checkOutDate");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Long value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Long value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Long value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Long value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Long value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Long> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Long> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Long value1, Long value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Long value1, Long value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityEnameIsNull() {
            addCriterion("city_ename is null");
            return (Criteria) this;
        }

        public Criteria andCityEnameIsNotNull() {
            addCriterion("city_ename is not null");
            return (Criteria) this;
        }

        public Criteria andCityEnameEqualTo(String value) {
            addCriterion("city_ename =", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotEqualTo(String value) {
            addCriterion("city_ename <>", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameGreaterThan(String value) {
            addCriterion("city_ename >", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameGreaterThanOrEqualTo(String value) {
            addCriterion("city_ename >=", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameLessThan(String value) {
            addCriterion("city_ename <", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameLessThanOrEqualTo(String value) {
            addCriterion("city_ename <=", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameLike(String value) {
            addCriterion("city_ename like", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotLike(String value) {
            addCriterion("city_ename not like", value, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameIn(List<String> values) {
            addCriterion("city_ename in", values, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotIn(List<String> values) {
            addCriterion("city_ename not in", values, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameBetween(String value1, String value2) {
            addCriterion("city_ename between", value1, value2, "cityEname");
            return (Criteria) this;
        }

        public Criteria andCityEnameNotBetween(String value1, String value2) {
            addCriterion("city_ename not between", value1, value2, "cityEname");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNull() {
            addCriterion("hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNotNull() {
            addCriterion("hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andHotelIdEqualTo(String value) {
            addCriterion("hotel_id =", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotEqualTo(String value) {
            addCriterion("hotel_id <>", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThan(String value) {
            addCriterion("hotel_id >", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThanOrEqualTo(String value) {
            addCriterion("hotel_id >=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThan(String value) {
            addCriterion("hotel_id <", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThanOrEqualTo(String value) {
            addCriterion("hotel_id <=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLike(String value) {
            addCriterion("hotel_id like", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotLike(String value) {
            addCriterion("hotel_id not like", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdIn(List<String> values) {
            addCriterion("hotel_id in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotIn(List<String> values) {
            addCriterion("hotel_id not in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdBetween(String value1, String value2) {
            addCriterion("hotel_id between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotBetween(String value1, String value2) {
            addCriterion("hotel_id not between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelNameIsNull() {
            addCriterion("hotel_name is null");
            return (Criteria) this;
        }

        public Criteria andHotelNameIsNotNull() {
            addCriterion("hotel_name is not null");
            return (Criteria) this;
        }

        public Criteria andHotelNameEqualTo(String value) {
            addCriterion("hotel_name =", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameNotEqualTo(String value) {
            addCriterion("hotel_name <>", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameGreaterThan(String value) {
            addCriterion("hotel_name >", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameGreaterThanOrEqualTo(String value) {
            addCriterion("hotel_name >=", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameLessThan(String value) {
            addCriterion("hotel_name <", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameLessThanOrEqualTo(String value) {
            addCriterion("hotel_name <=", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameLike(String value) {
            addCriterion("hotel_name like", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameNotLike(String value) {
            addCriterion("hotel_name not like", value, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameIn(List<String> values) {
            addCriterion("hotel_name in", values, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameNotIn(List<String> values) {
            addCriterion("hotel_name not in", values, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameBetween(String value1, String value2) {
            addCriterion("hotel_name between", value1, value2, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelNameNotBetween(String value1, String value2) {
            addCriterion("hotel_name not between", value1, value2, "hotelName");
            return (Criteria) this;
        }

        public Criteria andHotelEnameIsNull() {
            addCriterion("hotel_ename is null");
            return (Criteria) this;
        }

        public Criteria andHotelEnameIsNotNull() {
            addCriterion("hotel_ename is not null");
            return (Criteria) this;
        }

        public Criteria andHotelEnameEqualTo(String value) {
            addCriterion("hotel_ename =", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameNotEqualTo(String value) {
            addCriterion("hotel_ename <>", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameGreaterThan(String value) {
            addCriterion("hotel_ename >", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameGreaterThanOrEqualTo(String value) {
            addCriterion("hotel_ename >=", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameLessThan(String value) {
            addCriterion("hotel_ename <", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameLessThanOrEqualTo(String value) {
            addCriterion("hotel_ename <=", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameLike(String value) {
            addCriterion("hotel_ename like", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameNotLike(String value) {
            addCriterion("hotel_ename not like", value, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameIn(List<String> values) {
            addCriterion("hotel_ename in", values, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameNotIn(List<String> values) {
            addCriterion("hotel_ename not in", values, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameBetween(String value1, String value2) {
            addCriterion("hotel_ename between", value1, value2, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andHotelEnameNotBetween(String value1, String value2) {
            addCriterion("hotel_ename not between", value1, value2, "hotelEname");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(String value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(String value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(String value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(String value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(String value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(String value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLike(String value) {
            addCriterion("room_id like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotLike(String value) {
            addCriterion("room_id not like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<String> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<String> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(String value1, String value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(String value1, String value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNull() {
            addCriterion("room_name is null");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNotNull() {
            addCriterion("room_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNameEqualTo(String value) {
            addCriterion("room_name =", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotEqualTo(String value) {
            addCriterion("room_name <>", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThan(String value) {
            addCriterion("room_name >", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThanOrEqualTo(String value) {
            addCriterion("room_name >=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThan(String value) {
            addCriterion("room_name <", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThanOrEqualTo(String value) {
            addCriterion("room_name <=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLike(String value) {
            addCriterion("room_name like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotLike(String value) {
            addCriterion("room_name not like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameIn(List<String> values) {
            addCriterion("room_name in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotIn(List<String> values) {
            addCriterion("room_name not in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameBetween(String value1, String value2) {
            addCriterion("room_name between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotBetween(String value1, String value2) {
            addCriterion("room_name not between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomEnameIsNull() {
            addCriterion("room_ename is null");
            return (Criteria) this;
        }

        public Criteria andRoomEnameIsNotNull() {
            addCriterion("room_ename is not null");
            return (Criteria) this;
        }

        public Criteria andRoomEnameEqualTo(String value) {
            addCriterion("room_ename =", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameNotEqualTo(String value) {
            addCriterion("room_ename <>", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameGreaterThan(String value) {
            addCriterion("room_ename >", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameGreaterThanOrEqualTo(String value) {
            addCriterion("room_ename >=", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameLessThan(String value) {
            addCriterion("room_ename <", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameLessThanOrEqualTo(String value) {
            addCriterion("room_ename <=", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameLike(String value) {
            addCriterion("room_ename like", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameNotLike(String value) {
            addCriterion("room_ename not like", value, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameIn(List<String> values) {
            addCriterion("room_ename in", values, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameNotIn(List<String> values) {
            addCriterion("room_ename not in", values, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameBetween(String value1, String value2) {
            addCriterion("room_ename between", value1, value2, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomEnameNotBetween(String value1, String value2) {
            addCriterion("room_ename not between", value1, value2, "roomEname");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNull() {
            addCriterion("room_num is null");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNotNull() {
            addCriterion("room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNumEqualTo(Integer value) {
            addCriterion("room_num =", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotEqualTo(Integer value) {
            addCriterion("room_num <>", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThan(Integer value) {
            addCriterion("room_num >", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_num >=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThan(Integer value) {
            addCriterion("room_num <", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("room_num <=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumIn(List<Integer> values) {
            addCriterion("room_num in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotIn(List<Integer> values) {
            addCriterion("room_num not in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("room_num between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("room_num not between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andBedTypeIsNull() {
            addCriterion("bed_type is null");
            return (Criteria) this;
        }

        public Criteria andBedTypeIsNotNull() {
            addCriterion("bed_type is not null");
            return (Criteria) this;
        }

        public Criteria andBedTypeEqualTo(String value) {
            addCriterion("bed_type =", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeNotEqualTo(String value) {
            addCriterion("bed_type <>", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeGreaterThan(String value) {
            addCriterion("bed_type >", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bed_type >=", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeLessThan(String value) {
            addCriterion("bed_type <", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeLessThanOrEqualTo(String value) {
            addCriterion("bed_type <=", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeLike(String value) {
            addCriterion("bed_type like", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeNotLike(String value) {
            addCriterion("bed_type not like", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeIn(List<String> values) {
            addCriterion("bed_type in", values, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeNotIn(List<String> values) {
            addCriterion("bed_type not in", values, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeBetween(String value1, String value2) {
            addCriterion("bed_type between", value1, value2, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeNotBetween(String value1, String value2) {
            addCriterion("bed_type not between", value1, value2, "bedType");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerDidIsNull() {
            addCriterion("customer_did is null");
            return (Criteria) this;
        }

        public Criteria andCustomerDidIsNotNull() {
            addCriterion("customer_did is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerDidEqualTo(String value) {
            addCriterion("customer_did =", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidNotEqualTo(String value) {
            addCriterion("customer_did <>", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidGreaterThan(String value) {
            addCriterion("customer_did >", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidGreaterThanOrEqualTo(String value) {
            addCriterion("customer_did >=", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidLessThan(String value) {
            addCriterion("customer_did <", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidLessThanOrEqualTo(String value) {
            addCriterion("customer_did <=", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidLike(String value) {
            addCriterion("customer_did like", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidNotLike(String value) {
            addCriterion("customer_did not like", value, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidIn(List<String> values) {
            addCriterion("customer_did in", values, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidNotIn(List<String> values) {
            addCriterion("customer_did not in", values, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidBetween(String value1, String value2) {
            addCriterion("customer_did between", value1, value2, "customerDid");
            return (Criteria) this;
        }

        public Criteria andCustomerDidNotBetween(String value1, String value2) {
            addCriterion("customer_did not between", value1, value2, "customerDid");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoIsNull() {
            addCriterion("special_memo is null");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoIsNotNull() {
            addCriterion("special_memo is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoEqualTo(String value) {
            addCriterion("special_memo =", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoNotEqualTo(String value) {
            addCriterion("special_memo <>", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoGreaterThan(String value) {
            addCriterion("special_memo >", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoGreaterThanOrEqualTo(String value) {
            addCriterion("special_memo >=", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoLessThan(String value) {
            addCriterion("special_memo <", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoLessThanOrEqualTo(String value) {
            addCriterion("special_memo <=", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoLike(String value) {
            addCriterion("special_memo like", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoNotLike(String value) {
            addCriterion("special_memo not like", value, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoIn(List<String> values) {
            addCriterion("special_memo in", values, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoNotIn(List<String> values) {
            addCriterion("special_memo not in", values, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoBetween(String value1, String value2) {
            addCriterion("special_memo between", value1, value2, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andSpecialMemoNotBetween(String value1, String value2) {
            addCriterion("special_memo not between", value1, value2, "specialMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoIsNull() {
            addCriterion("order_memo is null");
            return (Criteria) this;
        }

        public Criteria andOrderMemoIsNotNull() {
            addCriterion("order_memo is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMemoEqualTo(String value) {
            addCriterion("order_memo =", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoNotEqualTo(String value) {
            addCriterion("order_memo <>", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoGreaterThan(String value) {
            addCriterion("order_memo >", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoGreaterThanOrEqualTo(String value) {
            addCriterion("order_memo >=", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoLessThan(String value) {
            addCriterion("order_memo <", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoLessThanOrEqualTo(String value) {
            addCriterion("order_memo <=", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoLike(String value) {
            addCriterion("order_memo like", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoNotLike(String value) {
            addCriterion("order_memo not like", value, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoIn(List<String> values) {
            addCriterion("order_memo in", values, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoNotIn(List<String> values) {
            addCriterion("order_memo not in", values, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoBetween(String value1, String value2) {
            addCriterion("order_memo between", value1, value2, "orderMemo");
            return (Criteria) this;
        }

        public Criteria andOrderMemoNotBetween(String value1, String value2) {
            addCriterion("order_memo not between", value1, value2, "orderMemo");
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

        public Criteria andZhOrderCodeIsNull() {
            addCriterion("zh_order_code is null");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeIsNotNull() {
            addCriterion("zh_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeEqualTo(String value) {
            addCriterion("zh_order_code =", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeNotEqualTo(String value) {
            addCriterion("zh_order_code <>", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeGreaterThan(String value) {
            addCriterion("zh_order_code >", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("zh_order_code >=", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeLessThan(String value) {
            addCriterion("zh_order_code <", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("zh_order_code <=", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeLike(String value) {
            addCriterion("zh_order_code like", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeNotLike(String value) {
            addCriterion("zh_order_code not like", value, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeIn(List<String> values) {
            addCriterion("zh_order_code in", values, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeNotIn(List<String> values) {
            addCriterion("zh_order_code not in", values, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeBetween(String value1, String value2) {
            addCriterion("zh_order_code between", value1, value2, "zhOrderCode");
            return (Criteria) this;
        }

        public Criteria andZhOrderCodeNotBetween(String value1, String value2) {
            addCriterion("zh_order_code not between", value1, value2, "zhOrderCode");
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