package com.travel.hotel.order.entity.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderDayPricePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDayPricePOExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andDayPriceIdIsNull() {
            addCriterion("day_price_id is null");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdIsNotNull() {
            addCriterion("day_price_id is not null");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdEqualTo(Long value) {
            addCriterion("day_price_id =", value, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdNotEqualTo(Long value) {
            addCriterion("day_price_id <>", value, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdGreaterThan(Long value) {
            addCriterion("day_price_id >", value, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("day_price_id >=", value, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdLessThan(Long value) {
            addCriterion("day_price_id <", value, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdLessThanOrEqualTo(Long value) {
            addCriterion("day_price_id <=", value, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdIn(List<Long> values) {
            addCriterion("day_price_id in", values, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdNotIn(List<Long> values) {
            addCriterion("day_price_id not in", values, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdBetween(Long value1, Long value2) {
            addCriterion("day_price_id between", value1, value2, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andDayPriceIdNotBetween(Long value1, Long value2) {
            addCriterion("day_price_id not between", value1, value2, "dayPriceId");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNull() {
            addCriterion("op_id is null");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNotNull() {
            addCriterion("op_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpIdEqualTo(Long value) {
            addCriterion("op_id =", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotEqualTo(Long value) {
            addCriterion("op_id <>", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThan(Long value) {
            addCriterion("op_id >", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("op_id >=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThan(Long value) {
            addCriterion("op_id <", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThanOrEqualTo(Long value) {
            addCriterion("op_id <=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdIn(List<Long> values) {
            addCriterion("op_id in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotIn(List<Long> values) {
            addCriterion("op_id not in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdBetween(Long value1, Long value2) {
            addCriterion("op_id between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotBetween(Long value1, Long value2) {
            addCriterion("op_id not between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdIsNull() {
            addCriterion("priceplan_id is null");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdIsNotNull() {
            addCriterion("priceplan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdEqualTo(Long value) {
            addCriterion("priceplan_id =", value, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdNotEqualTo(Long value) {
            addCriterion("priceplan_id <>", value, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdGreaterThan(Long value) {
            addCriterion("priceplan_id >", value, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("priceplan_id >=", value, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdLessThan(Long value) {
            addCriterion("priceplan_id <", value, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdLessThanOrEqualTo(Long value) {
            addCriterion("priceplan_id <=", value, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdIn(List<Long> values) {
            addCriterion("priceplan_id in", values, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdNotIn(List<Long> values) {
            addCriterion("priceplan_id not in", values, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdBetween(Long value1, Long value2) {
            addCriterion("priceplan_id between", value1, value2, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanIdNotBetween(Long value1, Long value2) {
            addCriterion("priceplan_id not between", value1, value2, "priceplanId");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameIsNull() {
            addCriterion("PRICEPLAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameIsNotNull() {
            addCriterion("PRICEPLAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameEqualTo(String value) {
            addCriterion("PRICEPLAN_NAME =", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameNotEqualTo(String value) {
            addCriterion("PRICEPLAN_NAME <>", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameGreaterThan(String value) {
            addCriterion("PRICEPLAN_NAME >", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRICEPLAN_NAME >=", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameLessThan(String value) {
            addCriterion("PRICEPLAN_NAME <", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameLessThanOrEqualTo(String value) {
            addCriterion("PRICEPLAN_NAME <=", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameLike(String value) {
            addCriterion("PRICEPLAN_NAME like", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameNotLike(String value) {
            addCriterion("PRICEPLAN_NAME not like", value, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameIn(List<String> values) {
            addCriterion("PRICEPLAN_NAME in", values, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameNotIn(List<String> values) {
            addCriterion("PRICEPLAN_NAME not in", values, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameBetween(String value1, String value2) {
            addCriterion("PRICEPLAN_NAME between", value1, value2, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andPriceplanNameNotBetween(String value1, String value2) {
            addCriterion("PRICEPLAN_NAME not between", value1, value2, "priceplanName");
            return (Criteria) this;
        }

        public Criteria andSaleDateIsNull() {
            addCriterion("SALE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSaleDateIsNotNull() {
            addCriterion("SALE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDateEqualTo(Date value) {
            addCriterionForJDBCDate("SALE_DATE =", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("SALE_DATE <>", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("SALE_DATE >", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SALE_DATE >=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThan(Date value) {
            addCriterionForJDBCDate("SALE_DATE <", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SALE_DATE <=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateIn(List<Date> values) {
            addCriterionForJDBCDate("SALE_DATE in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("SALE_DATE not in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SALE_DATE between", value1, value2, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SALE_DATE not between", value1, value2, "saleDate");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyIsNull() {
            addCriterion("BASE_CURRENCY is null");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyIsNotNull() {
            addCriterion("BASE_CURRENCY is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyEqualTo(String value) {
            addCriterion("BASE_CURRENCY =", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotEqualTo(String value) {
            addCriterion("BASE_CURRENCY <>", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyGreaterThan(String value) {
            addCriterion("BASE_CURRENCY >", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_CURRENCY >=", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyLessThan(String value) {
            addCriterion("BASE_CURRENCY <", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyLessThanOrEqualTo(String value) {
            addCriterion("BASE_CURRENCY <=", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyLike(String value) {
            addCriterion("BASE_CURRENCY like", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotLike(String value) {
            addCriterion("BASE_CURRENCY not like", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyIn(List<String> values) {
            addCriterion("BASE_CURRENCY in", values, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotIn(List<String> values) {
            addCriterion("BASE_CURRENCY not in", values, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyBetween(String value1, String value2) {
            addCriterion("BASE_CURRENCY between", value1, value2, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotBetween(String value1, String value2) {
            addCriterion("BASE_CURRENCY not between", value1, value2, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNull() {
            addCriterion("BASE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNotNull() {
            addCriterion("BASE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andBasePriceEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE =", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE <>", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThan(BigDecimal value) {
            addCriterion("BASE_PRICE >", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE >=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThan(BigDecimal value) {
            addCriterion("BASE_PRICE <", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BASE_PRICE <=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceIn(List<BigDecimal> values) {
            addCriterion("BASE_PRICE in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotIn(List<BigDecimal> values) {
            addCriterion("BASE_PRICE not in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BASE_PRICE between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BASE_PRICE not between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyIsNull() {
            addCriterion("sale_b_currency is null");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyIsNotNull() {
            addCriterion("sale_b_currency is not null");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyEqualTo(String value) {
            addCriterion("sale_b_currency =", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyNotEqualTo(String value) {
            addCriterion("sale_b_currency <>", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyGreaterThan(String value) {
            addCriterion("sale_b_currency >", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("sale_b_currency >=", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyLessThan(String value) {
            addCriterion("sale_b_currency <", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyLessThanOrEqualTo(String value) {
            addCriterion("sale_b_currency <=", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyLike(String value) {
            addCriterion("sale_b_currency like", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyNotLike(String value) {
            addCriterion("sale_b_currency not like", value, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyIn(List<String> values) {
            addCriterion("sale_b_currency in", values, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyNotIn(List<String> values) {
            addCriterion("sale_b_currency not in", values, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyBetween(String value1, String value2) {
            addCriterion("sale_b_currency between", value1, value2, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBCurrencyNotBetween(String value1, String value2) {
            addCriterion("sale_b_currency not between", value1, value2, "saleBCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceIsNull() {
            addCriterion("sale_b_price is null");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceIsNotNull() {
            addCriterion("sale_b_price is not null");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceEqualTo(BigDecimal value) {
            addCriterion("sale_b_price =", value, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceNotEqualTo(BigDecimal value) {
            addCriterion("sale_b_price <>", value, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceGreaterThan(BigDecimal value) {
            addCriterion("sale_b_price >", value, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_b_price >=", value, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceLessThan(BigDecimal value) {
            addCriterion("sale_b_price <", value, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_b_price <=", value, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceIn(List<BigDecimal> values) {
            addCriterion("sale_b_price in", values, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceNotIn(List<BigDecimal> values) {
            addCriterion("sale_b_price not in", values, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_b_price between", value1, value2, "saleBPrice");
            return (Criteria) this;
        }

        public Criteria andSaleBPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_b_price not between", value1, value2, "saleBPrice");
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

        public Criteria andOrderBillStatusIsNull() {
            addCriterion("order_bill_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusIsNotNull() {
            addCriterion("order_bill_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusEqualTo(Integer value) {
            addCriterion("order_bill_status =", value, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusNotEqualTo(Integer value) {
            addCriterion("order_bill_status <>", value, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusGreaterThan(Integer value) {
            addCriterion("order_bill_status >", value, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_bill_status >=", value, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusLessThan(Integer value) {
            addCriterion("order_bill_status <", value, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_bill_status <=", value, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusIn(List<Integer> values) {
            addCriterion("order_bill_status in", values, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusNotIn(List<Integer> values) {
            addCriterion("order_bill_status not in", values, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_bill_status between", value1, value2, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_bill_status not between", value1, value2, "orderBillStatus");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserIsNull() {
            addCriterion("order_bill_user is null");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserIsNotNull() {
            addCriterion("order_bill_user is not null");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserEqualTo(String value) {
            addCriterion("order_bill_user =", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserNotEqualTo(String value) {
            addCriterion("order_bill_user <>", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserGreaterThan(String value) {
            addCriterion("order_bill_user >", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserGreaterThanOrEqualTo(String value) {
            addCriterion("order_bill_user >=", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserLessThan(String value) {
            addCriterion("order_bill_user <", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserLessThanOrEqualTo(String value) {
            addCriterion("order_bill_user <=", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserLike(String value) {
            addCriterion("order_bill_user like", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserNotLike(String value) {
            addCriterion("order_bill_user not like", value, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserIn(List<String> values) {
            addCriterion("order_bill_user in", values, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserNotIn(List<String> values) {
            addCriterion("order_bill_user not in", values, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserBetween(String value1, String value2) {
            addCriterion("order_bill_user between", value1, value2, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andOrderBillUserNotBetween(String value1, String value2) {
            addCriterion("order_bill_user not between", value1, value2, "orderBillUser");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyIsNull() {
            addCriterion("sale_c_currency is null");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyIsNotNull() {
            addCriterion("sale_c_currency is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyEqualTo(String value) {
            addCriterion("sale_c_currency =", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyNotEqualTo(String value) {
            addCriterion("sale_c_currency <>", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyGreaterThan(String value) {
            addCriterion("sale_c_currency >", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("sale_c_currency >=", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyLessThan(String value) {
            addCriterion("sale_c_currency <", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyLessThanOrEqualTo(String value) {
            addCriterion("sale_c_currency <=", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyLike(String value) {
            addCriterion("sale_c_currency like", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyNotLike(String value) {
            addCriterion("sale_c_currency not like", value, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyIn(List<String> values) {
            addCriterion("sale_c_currency in", values, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyNotIn(List<String> values) {
            addCriterion("sale_c_currency not in", values, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyBetween(String value1, String value2) {
            addCriterion("sale_c_currency between", value1, value2, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCCurrencyNotBetween(String value1, String value2) {
            addCriterion("sale_c_currency not between", value1, value2, "saleCCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceIsNull() {
            addCriterion("sale_c_price is null");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceIsNotNull() {
            addCriterion("sale_c_price is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceEqualTo(BigDecimal value) {
            addCriterion("sale_c_price =", value, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceNotEqualTo(BigDecimal value) {
            addCriterion("sale_c_price <>", value, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceGreaterThan(BigDecimal value) {
            addCriterion("sale_c_price >", value, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_c_price >=", value, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceLessThan(BigDecimal value) {
            addCriterion("sale_c_price <", value, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_c_price <=", value, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceIn(List<BigDecimal> values) {
            addCriterion("sale_c_price in", values, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceNotIn(List<BigDecimal> values) {
            addCriterion("sale_c_price not in", values, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_c_price between", value1, value2, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andSaleCPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_c_price not between", value1, value2, "saleCPrice");
            return (Criteria) this;
        }

        public Criteria andBaseRateIsNull() {
            addCriterion("base_rate is null");
            return (Criteria) this;
        }

        public Criteria andBaseRateIsNotNull() {
            addCriterion("base_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBaseRateEqualTo(BigDecimal value) {
            addCriterion("base_rate =", value, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateNotEqualTo(BigDecimal value) {
            addCriterion("base_rate <>", value, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateGreaterThan(BigDecimal value) {
            addCriterion("base_rate >", value, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("base_rate >=", value, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateLessThan(BigDecimal value) {
            addCriterion("base_rate <", value, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("base_rate <=", value, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateIn(List<BigDecimal> values) {
            addCriterion("base_rate in", values, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateNotIn(List<BigDecimal> values) {
            addCriterion("base_rate not in", values, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_rate between", value1, value2, "baseRate");
            return (Criteria) this;
        }

        public Criteria andBaseRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_rate not between", value1, value2, "baseRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateIsNull() {
            addCriterion("sale_channel_rate is null");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateIsNotNull() {
            addCriterion("sale_channel_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateEqualTo(BigDecimal value) {
            addCriterion("sale_channel_rate =", value, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateNotEqualTo(BigDecimal value) {
            addCriterion("sale_channel_rate <>", value, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateGreaterThan(BigDecimal value) {
            addCriterion("sale_channel_rate >", value, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_channel_rate >=", value, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateLessThan(BigDecimal value) {
            addCriterion("sale_channel_rate <", value, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_channel_rate <=", value, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateIn(List<BigDecimal> values) {
            addCriterion("sale_channel_rate in", values, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateNotIn(List<BigDecimal> values) {
            addCriterion("sale_channel_rate not in", values, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_channel_rate between", value1, value2, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleChannelRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_channel_rate not between", value1, value2, "saleChannelRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateIsNull() {
            addCriterion("sale_c_rate is null");
            return (Criteria) this;
        }

        public Criteria andSaleCRateIsNotNull() {
            addCriterion("sale_c_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCRateEqualTo(BigDecimal value) {
            addCriterion("sale_c_rate =", value, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateNotEqualTo(BigDecimal value) {
            addCriterion("sale_c_rate <>", value, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateGreaterThan(BigDecimal value) {
            addCriterion("sale_c_rate >", value, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_c_rate >=", value, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateLessThan(BigDecimal value) {
            addCriterion("sale_c_rate <", value, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_c_rate <=", value, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateIn(List<BigDecimal> values) {
            addCriterion("sale_c_rate in", values, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateNotIn(List<BigDecimal> values) {
            addCriterion("sale_c_rate not in", values, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_c_rate between", value1, value2, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andSaleCRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_c_rate not between", value1, value2, "saleCRate");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeIsNull() {
            addCriterion("voucher_code is null");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeIsNotNull() {
            addCriterion("voucher_code is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeEqualTo(String value) {
            addCriterion("voucher_code =", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotEqualTo(String value) {
            addCriterion("voucher_code <>", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeGreaterThan(String value) {
            addCriterion("voucher_code >", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeGreaterThanOrEqualTo(String value) {
            addCriterion("voucher_code >=", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeLessThan(String value) {
            addCriterion("voucher_code <", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeLessThanOrEqualTo(String value) {
            addCriterion("voucher_code <=", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeLike(String value) {
            addCriterion("voucher_code like", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotLike(String value) {
            addCriterion("voucher_code not like", value, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeIn(List<String> values) {
            addCriterion("voucher_code in", values, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotIn(List<String> values) {
            addCriterion("voucher_code not in", values, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeBetween(String value1, String value2) {
            addCriterion("voucher_code between", value1, value2, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andVoucherCodeNotBetween(String value1, String value2) {
            addCriterion("voucher_code not between", value1, value2, "voucherCode");
            return (Criteria) this;
        }

        public Criteria andRoomsIsNull() {
            addCriterion("rooms is null");
            return (Criteria) this;
        }

        public Criteria andRoomsIsNotNull() {
            addCriterion("rooms is not null");
            return (Criteria) this;
        }

        public Criteria andRoomsEqualTo(Integer value) {
            addCriterion("rooms =", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsNotEqualTo(Integer value) {
            addCriterion("rooms <>", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsGreaterThan(Integer value) {
            addCriterion("rooms >", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsGreaterThanOrEqualTo(Integer value) {
            addCriterion("rooms >=", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsLessThan(Integer value) {
            addCriterion("rooms <", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsLessThanOrEqualTo(Integer value) {
            addCriterion("rooms <=", value, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsIn(List<Integer> values) {
            addCriterion("rooms in", values, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsNotIn(List<Integer> values) {
            addCriterion("rooms not in", values, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsBetween(Integer value1, Integer value2) {
            addCriterion("rooms between", value1, value2, "rooms");
            return (Criteria) this;
        }

        public Criteria andRoomsNotBetween(Integer value1, Integer value2) {
            addCriterion("rooms not between", value1, value2, "rooms");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusIsNull() {
            addCriterion("supply_bill_status is null");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusIsNotNull() {
            addCriterion("supply_bill_status is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusEqualTo(Integer value) {
            addCriterion("supply_bill_status =", value, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusNotEqualTo(Integer value) {
            addCriterion("supply_bill_status <>", value, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusGreaterThan(Integer value) {
            addCriterion("supply_bill_status >", value, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_bill_status >=", value, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusLessThan(Integer value) {
            addCriterion("supply_bill_status <", value, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusLessThanOrEqualTo(Integer value) {
            addCriterion("supply_bill_status <=", value, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusIn(List<Integer> values) {
            addCriterion("supply_bill_status in", values, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusNotIn(List<Integer> values) {
            addCriterion("supply_bill_status not in", values, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusBetween(Integer value1, Integer value2) {
            addCriterion("supply_bill_status between", value1, value2, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_bill_status not between", value1, value2, "supplyBillStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserIsNull() {
            addCriterion("supply_bill_user is null");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserIsNotNull() {
            addCriterion("supply_bill_user is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserEqualTo(String value) {
            addCriterion("supply_bill_user =", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserNotEqualTo(String value) {
            addCriterion("supply_bill_user <>", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserGreaterThan(String value) {
            addCriterion("supply_bill_user >", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserGreaterThanOrEqualTo(String value) {
            addCriterion("supply_bill_user >=", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserLessThan(String value) {
            addCriterion("supply_bill_user <", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserLessThanOrEqualTo(String value) {
            addCriterion("supply_bill_user <=", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserLike(String value) {
            addCriterion("supply_bill_user like", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserNotLike(String value) {
            addCriterion("supply_bill_user not like", value, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserIn(List<String> values) {
            addCriterion("supply_bill_user in", values, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserNotIn(List<String> values) {
            addCriterion("supply_bill_user not in", values, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserBetween(String value1, String value2) {
            addCriterion("supply_bill_user between", value1, value2, "supplyBillUser");
            return (Criteria) this;
        }

        public Criteria andSupplyBillUserNotBetween(String value1, String value2) {
            addCriterion("supply_bill_user not between", value1, value2, "supplyBillUser");
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

        public Criteria andBreakfastNumEqualTo(String value) {
            addCriterion("breakfast_num =", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumNotEqualTo(String value) {
            addCriterion("breakfast_num <>", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumGreaterThan(String value) {
            addCriterion("breakfast_num >", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumGreaterThanOrEqualTo(String value) {
            addCriterion("breakfast_num >=", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumLessThan(String value) {
            addCriterion("breakfast_num <", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumLessThanOrEqualTo(String value) {
            addCriterion("breakfast_num <=", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumLike(String value) {
            addCriterion("breakfast_num like", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumNotLike(String value) {
            addCriterion("breakfast_num not like", value, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumIn(List<String> values) {
            addCriterion("breakfast_num in", values, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumNotIn(List<String> values) {
            addCriterion("breakfast_num not in", values, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumBetween(String value1, String value2) {
            addCriterion("breakfast_num between", value1, value2, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andBreakfastNumNotBetween(String value1, String value2) {
            addCriterion("breakfast_num not between", value1, value2, "breakfastNum");
            return (Criteria) this;
        }

        public Criteria andActualReceivedIsNull() {
            addCriterion("actual_received is null");
            return (Criteria) this;
        }

        public Criteria andActualReceivedIsNotNull() {
            addCriterion("actual_received is not null");
            return (Criteria) this;
        }

        public Criteria andActualReceivedEqualTo(BigDecimal value) {
            addCriterion("actual_received =", value, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedNotEqualTo(BigDecimal value) {
            addCriterion("actual_received <>", value, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedGreaterThan(BigDecimal value) {
            addCriterion("actual_received >", value, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_received >=", value, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedLessThan(BigDecimal value) {
            addCriterion("actual_received <", value, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_received <=", value, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedIn(List<BigDecimal> values) {
            addCriterion("actual_received in", values, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedNotIn(List<BigDecimal> values) {
            addCriterion("actual_received not in", values, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_received between", value1, value2, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualReceivedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_received not between", value1, value2, "actualReceived");
            return (Criteria) this;
        }

        public Criteria andActualPaiedIsNull() {
            addCriterion("actual_paied is null");
            return (Criteria) this;
        }

        public Criteria andActualPaiedIsNotNull() {
            addCriterion("actual_paied is not null");
            return (Criteria) this;
        }

        public Criteria andActualPaiedEqualTo(BigDecimal value) {
            addCriterion("actual_paied =", value, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedNotEqualTo(BigDecimal value) {
            addCriterion("actual_paied <>", value, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedGreaterThan(BigDecimal value) {
            addCriterion("actual_paied >", value, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_paied >=", value, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedLessThan(BigDecimal value) {
            addCriterion("actual_paied <", value, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_paied <=", value, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedIn(List<BigDecimal> values) {
            addCriterion("actual_paied in", values, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedNotIn(List<BigDecimal> values) {
            addCriterion("actual_paied not in", values, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_paied between", value1, value2, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andActualPaiedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_paied not between", value1, value2, "actualPaied");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionIsNull() {
            addCriterion("agent_commission is null");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionIsNotNull() {
            addCriterion("agent_commission is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionEqualTo(BigDecimal value) {
            addCriterion("agent_commission =", value, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionNotEqualTo(BigDecimal value) {
            addCriterion("agent_commission <>", value, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionGreaterThan(BigDecimal value) {
            addCriterion("agent_commission >", value, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_commission >=", value, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionLessThan(BigDecimal value) {
            addCriterion("agent_commission <", value, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_commission <=", value, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionIn(List<BigDecimal> values) {
            addCriterion("agent_commission in", values, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionNotIn(List<BigDecimal> values) {
            addCriterion("agent_commission not in", values, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_commission between", value1, value2, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andAgentCommissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_commission not between", value1, value2, "agentCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionIsNull() {
            addCriterion("supply_commission is null");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionIsNotNull() {
            addCriterion("supply_commission is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionEqualTo(BigDecimal value) {
            addCriterion("supply_commission =", value, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionNotEqualTo(BigDecimal value) {
            addCriterion("supply_commission <>", value, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionGreaterThan(BigDecimal value) {
            addCriterion("supply_commission >", value, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("supply_commission >=", value, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionLessThan(BigDecimal value) {
            addCriterion("supply_commission <", value, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("supply_commission <=", value, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionIn(List<BigDecimal> values) {
            addCriterion("supply_commission in", values, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionNotIn(List<BigDecimal> values) {
            addCriterion("supply_commission not in", values, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("supply_commission between", value1, value2, "supplyCommission");
            return (Criteria) this;
        }

        public Criteria andSupplyCommissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("supply_commission not between", value1, value2, "supplyCommission");
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