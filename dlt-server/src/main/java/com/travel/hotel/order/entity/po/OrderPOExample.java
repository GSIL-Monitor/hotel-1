package com.travel.hotel.order.entity.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderPOExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("ORDER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("ORDER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("ORDER_CODE =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("ORDER_CODE <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("ORDER_CODE >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_CODE >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("ORDER_CODE <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("ORDER_CODE <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("ORDER_CODE like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("ORDER_CODE not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("ORDER_CODE in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("ORDER_CODE not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("ORDER_CODE between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("ORDER_CODE not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeIsNull() {
            addCriterion("supply_code is null");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeIsNotNull() {
            addCriterion("supply_code is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeEqualTo(String value) {
            addCriterion("supply_code =", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeNotEqualTo(String value) {
            addCriterion("supply_code <>", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeGreaterThan(String value) {
            addCriterion("supply_code >", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("supply_code >=", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeLessThan(String value) {
            addCriterion("supply_code <", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeLessThanOrEqualTo(String value) {
            addCriterion("supply_code <=", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeLike(String value) {
            addCriterion("supply_code like", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeNotLike(String value) {
            addCriterion("supply_code not like", value, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeIn(List<String> values) {
            addCriterion("supply_code in", values, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeNotIn(List<String> values) {
            addCriterion("supply_code not in", values, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeBetween(String value1, String value2) {
            addCriterion("supply_code between", value1, value2, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyCodeNotBetween(String value1, String value2) {
            addCriterion("supply_code not between", value1, value2, "supplyCode");
            return (Criteria) this;
        }

        public Criteria andSupplyNameIsNull() {
            addCriterion("supply_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplyNameIsNotNull() {
            addCriterion("supply_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyNameEqualTo(String value) {
            addCriterion("supply_name =", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameNotEqualTo(String value) {
            addCriterion("supply_name <>", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameGreaterThan(String value) {
            addCriterion("supply_name >", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameGreaterThanOrEqualTo(String value) {
            addCriterion("supply_name >=", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameLessThan(String value) {
            addCriterion("supply_name <", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameLessThanOrEqualTo(String value) {
            addCriterion("supply_name <=", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameLike(String value) {
            addCriterion("supply_name like", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameNotLike(String value) {
            addCriterion("supply_name not like", value, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameIn(List<String> values) {
            addCriterion("supply_name in", values, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameNotIn(List<String> values) {
            addCriterion("supply_name not in", values, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameBetween(String value1, String value2) {
            addCriterion("supply_name between", value1, value2, "supplyName");
            return (Criteria) this;
        }

        public Criteria andSupplyNameNotBetween(String value1, String value2) {
            addCriterion("supply_name not between", value1, value2, "supplyName");
            return (Criteria) this;
        }

        public Criteria andAgentCodeIsNull() {
            addCriterion("agent_code is null");
            return (Criteria) this;
        }

        public Criteria andAgentCodeIsNotNull() {
            addCriterion("agent_code is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCodeEqualTo(String value) {
            addCriterion("agent_code =", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotEqualTo(String value) {
            addCriterion("agent_code <>", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeGreaterThan(String value) {
            addCriterion("agent_code >", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("agent_code >=", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeLessThan(String value) {
            addCriterion("agent_code <", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeLessThanOrEqualTo(String value) {
            addCriterion("agent_code <=", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeLike(String value) {
            addCriterion("agent_code like", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotLike(String value) {
            addCriterion("agent_code not like", value, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeIn(List<String> values) {
            addCriterion("agent_code in", values, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotIn(List<String> values) {
            addCriterion("agent_code not in", values, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeBetween(String value1, String value2) {
            addCriterion("agent_code between", value1, value2, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentCodeNotBetween(String value1, String value2) {
            addCriterion("agent_code not between", value1, value2, "agentCode");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNull() {
            addCriterion("agent_name is null");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNotNull() {
            addCriterion("agent_name is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNameEqualTo(String value) {
            addCriterion("agent_name =", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotEqualTo(String value) {
            addCriterion("agent_name <>", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThan(String value) {
            addCriterion("agent_name >", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThanOrEqualTo(String value) {
            addCriterion("agent_name >=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThan(String value) {
            addCriterion("agent_name <", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThanOrEqualTo(String value) {
            addCriterion("agent_name <=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLike(String value) {
            addCriterion("agent_name like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotLike(String value) {
            addCriterion("agent_name not like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameIn(List<String> values) {
            addCriterion("agent_name in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotIn(List<String> values) {
            addCriterion("agent_name not in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameBetween(String value1, String value2) {
            addCriterion("agent_name between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotBetween(String value1, String value2) {
            addCriterion("agent_name not between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andSettlementIsNull() {
            addCriterion("settlement is null");
            return (Criteria) this;
        }

        public Criteria andSettlementIsNotNull() {
            addCriterion("settlement is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementEqualTo(String value) {
            addCriterion("settlement =", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementNotEqualTo(String value) {
            addCriterion("settlement <>", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementGreaterThan(String value) {
            addCriterion("settlement >", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementGreaterThanOrEqualTo(String value) {
            addCriterion("settlement >=", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementLessThan(String value) {
            addCriterion("settlement <", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementLessThanOrEqualTo(String value) {
            addCriterion("settlement <=", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementLike(String value) {
            addCriterion("settlement like", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementNotLike(String value) {
            addCriterion("settlement not like", value, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementIn(List<String> values) {
            addCriterion("settlement in", values, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementNotIn(List<String> values) {
            addCriterion("settlement not in", values, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementBetween(String value1, String value2) {
            addCriterion("settlement between", value1, value2, "settlement");
            return (Criteria) this;
        }

        public Criteria andSettlementNotBetween(String value1, String value2) {
            addCriterion("settlement not between", value1, value2, "settlement");
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

        public Criteria andCustomerOrderCodeIsNull() {
            addCriterion("customer_order_code is null");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeIsNotNull() {
            addCriterion("customer_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeEqualTo(String value) {
            addCriterion("customer_order_code =", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeNotEqualTo(String value) {
            addCriterion("customer_order_code <>", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeGreaterThan(String value) {
            addCriterion("customer_order_code >", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("customer_order_code >=", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeLessThan(String value) {
            addCriterion("customer_order_code <", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("customer_order_code <=", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeLike(String value) {
            addCriterion("customer_order_code like", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeNotLike(String value) {
            addCriterion("customer_order_code not like", value, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeIn(List<String> values) {
            addCriterion("customer_order_code in", values, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeNotIn(List<String> values) {
            addCriterion("customer_order_code not in", values, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeBetween(String value1, String value2) {
            addCriterion("customer_order_code between", value1, value2, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustomerOrderCodeNotBetween(String value1, String value2) {
            addCriterion("customer_order_code not between", value1, value2, "customerOrderCode");
            return (Criteria) this;
        }

        public Criteria andOrderStateIsNull() {
            addCriterion("order_state is null");
            return (Criteria) this;
        }

        public Criteria andOrderStateIsNotNull() {
            addCriterion("order_state is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStateEqualTo(String value) {
            addCriterion("order_state =", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateNotEqualTo(String value) {
            addCriterion("order_state <>", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateGreaterThan(String value) {
            addCriterion("order_state >", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateGreaterThanOrEqualTo(String value) {
            addCriterion("order_state >=", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateLessThan(String value) {
            addCriterion("order_state <", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateLessThanOrEqualTo(String value) {
            addCriterion("order_state <=", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateLike(String value) {
            addCriterion("order_state like", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateNotLike(String value) {
            addCriterion("order_state not like", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateIn(List<String> values) {
            addCriterion("order_state in", values, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateNotIn(List<String> values) {
            addCriterion("order_state not in", values, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateBetween(String value1, String value2) {
            addCriterion("order_state between", value1, value2, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateNotBetween(String value1, String value2) {
            addCriterion("order_state not between", value1, value2, "orderState");
            return (Criteria) this;
        }

        public Criteria andPayMethodIsNull() {
            addCriterion("pay_method is null");
            return (Criteria) this;
        }

        public Criteria andPayMethodIsNotNull() {
            addCriterion("pay_method is not null");
            return (Criteria) this;
        }

        public Criteria andPayMethodEqualTo(String value) {
            addCriterion("pay_method =", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotEqualTo(String value) {
            addCriterion("pay_method <>", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodGreaterThan(String value) {
            addCriterion("pay_method >", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodGreaterThanOrEqualTo(String value) {
            addCriterion("pay_method >=", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLessThan(String value) {
            addCriterion("pay_method <", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLessThanOrEqualTo(String value) {
            addCriterion("pay_method <=", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLike(String value) {
            addCriterion("pay_method like", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotLike(String value) {
            addCriterion("pay_method not like", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodIn(List<String> values) {
            addCriterion("pay_method in", values, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotIn(List<String> values) {
            addCriterion("pay_method not in", values, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodBetween(String value1, String value2) {
            addCriterion("pay_method between", value1, value2, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotBetween(String value1, String value2) {
            addCriterion("pay_method not between", value1, value2, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayStateIsNull() {
            addCriterion("pay_state is null");
            return (Criteria) this;
        }

        public Criteria andPayStateIsNotNull() {
            addCriterion("pay_state is not null");
            return (Criteria) this;
        }

        public Criteria andPayStateEqualTo(Integer value) {
            addCriterion("pay_state =", value, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateNotEqualTo(Integer value) {
            addCriterion("pay_state <>", value, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateGreaterThan(Integer value) {
            addCriterion("pay_state >", value, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_state >=", value, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateLessThan(Integer value) {
            addCriterion("pay_state <", value, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateLessThanOrEqualTo(Integer value) {
            addCriterion("pay_state <=", value, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateIn(List<Integer> values) {
            addCriterion("pay_state in", values, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateNotIn(List<Integer> values) {
            addCriterion("pay_state not in", values, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateBetween(Integer value1, Integer value2) {
            addCriterion("pay_state between", value1, value2, "payState");
            return (Criteria) this;
        }

        public Criteria andPayStateNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_state not between", value1, value2, "payState");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyIsNull() {
            addCriterion("base_currency is null");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyIsNotNull() {
            addCriterion("base_currency is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyEqualTo(String value) {
            addCriterion("base_currency =", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotEqualTo(String value) {
            addCriterion("base_currency <>", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyGreaterThan(String value) {
            addCriterion("base_currency >", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("base_currency >=", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyLessThan(String value) {
            addCriterion("base_currency <", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyLessThanOrEqualTo(String value) {
            addCriterion("base_currency <=", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyLike(String value) {
            addCriterion("base_currency like", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotLike(String value) {
            addCriterion("base_currency not like", value, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyIn(List<String> values) {
            addCriterion("base_currency in", values, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotIn(List<String> values) {
            addCriterion("base_currency not in", values, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyBetween(String value1, String value2) {
            addCriterion("base_currency between", value1, value2, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBaseCurrencyNotBetween(String value1, String value2) {
            addCriterion("base_currency not between", value1, value2, "baseCurrency");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNull() {
            addCriterion("base_price is null");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNotNull() {
            addCriterion("base_price is not null");
            return (Criteria) this;
        }

        public Criteria andBasePriceEqualTo(BigDecimal value) {
            addCriterion("base_price =", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotEqualTo(BigDecimal value) {
            addCriterion("base_price <>", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThan(BigDecimal value) {
            addCriterion("base_price >", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("base_price >=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThan(BigDecimal value) {
            addCriterion("base_price <", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("base_price <=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceIn(List<BigDecimal> values) {
            addCriterion("base_price in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotIn(List<BigDecimal> values) {
            addCriterion("base_price not in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_price between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_price not between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyIsNull() {
            addCriterion("SALE_CURRENCY is null");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyIsNotNull() {
            addCriterion("SALE_CURRENCY is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyEqualTo(String value) {
            addCriterion("SALE_CURRENCY =", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyNotEqualTo(String value) {
            addCriterion("SALE_CURRENCY <>", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyGreaterThan(String value) {
            addCriterion("SALE_CURRENCY >", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("SALE_CURRENCY >=", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyLessThan(String value) {
            addCriterion("SALE_CURRENCY <", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyLessThanOrEqualTo(String value) {
            addCriterion("SALE_CURRENCY <=", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyLike(String value) {
            addCriterion("SALE_CURRENCY like", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyNotLike(String value) {
            addCriterion("SALE_CURRENCY not like", value, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyIn(List<String> values) {
            addCriterion("SALE_CURRENCY in", values, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyNotIn(List<String> values) {
            addCriterion("SALE_CURRENCY not in", values, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyBetween(String value1, String value2) {
            addCriterion("SALE_CURRENCY between", value1, value2, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSaleCurrencyNotBetween(String value1, String value2) {
            addCriterion("SALE_CURRENCY not between", value1, value2, "saleCurrency");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("SALE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("SALE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(BigDecimal value) {
            addCriterion("SALE_PRICE =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("SALE_PRICE <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(BigDecimal value) {
            addCriterion("SALE_PRICE >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SALE_PRICE >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(BigDecimal value) {
            addCriterion("SALE_PRICE <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SALE_PRICE <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<BigDecimal> values) {
            addCriterion("SALE_PRICE in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("SALE_PRICE not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SALE_PRICE between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SALE_PRICE not between", value1, value2, "salePrice");
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

        public Criteria andChannelCodeIsNull() {
            addCriterion("channel_code is null");
            return (Criteria) this;
        }

        public Criteria andChannelCodeIsNotNull() {
            addCriterion("channel_code is not null");
            return (Criteria) this;
        }

        public Criteria andChannelCodeEqualTo(String value) {
            addCriterion("channel_code =", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeNotEqualTo(String value) {
            addCriterion("channel_code <>", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeGreaterThan(String value) {
            addCriterion("channel_code >", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_code >=", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeLessThan(String value) {
            addCriterion("channel_code <", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeLessThanOrEqualTo(String value) {
            addCriterion("channel_code <=", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeLike(String value) {
            addCriterion("channel_code like", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeNotLike(String value) {
            addCriterion("channel_code not like", value, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeIn(List<String> values) {
            addCriterion("channel_code in", values, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeNotIn(List<String> values) {
            addCriterion("channel_code not in", values, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeBetween(String value1, String value2) {
            addCriterion("channel_code between", value1, value2, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChannelCodeNotBetween(String value1, String value2) {
            addCriterion("channel_code not between", value1, value2, "channelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeIsNull() {
            addCriterion("child_channel_code is null");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeIsNotNull() {
            addCriterion("child_channel_code is not null");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeEqualTo(String value) {
            addCriterion("child_channel_code =", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeNotEqualTo(String value) {
            addCriterion("child_channel_code <>", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeGreaterThan(String value) {
            addCriterion("child_channel_code >", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("child_channel_code >=", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeLessThan(String value) {
            addCriterion("child_channel_code <", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeLessThanOrEqualTo(String value) {
            addCriterion("child_channel_code <=", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeLike(String value) {
            addCriterion("child_channel_code like", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeNotLike(String value) {
            addCriterion("child_channel_code not like", value, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeIn(List<String> values) {
            addCriterion("child_channel_code in", values, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeNotIn(List<String> values) {
            addCriterion("child_channel_code not in", values, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeBetween(String value1, String value2) {
            addCriterion("child_channel_code between", value1, value2, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChildChannelCodeNotBetween(String value1, String value2) {
            addCriterion("child_channel_code not between", value1, value2, "childChannelCode");
            return (Criteria) this;
        }

        public Criteria andChannelStateIsNull() {
            addCriterion("channel_state is null");
            return (Criteria) this;
        }

        public Criteria andChannelStateIsNotNull() {
            addCriterion("channel_state is not null");
            return (Criteria) this;
        }

        public Criteria andChannelStateEqualTo(String value) {
            addCriterion("channel_state =", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotEqualTo(String value) {
            addCriterion("channel_state <>", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateGreaterThan(String value) {
            addCriterion("channel_state >", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateGreaterThanOrEqualTo(String value) {
            addCriterion("channel_state >=", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateLessThan(String value) {
            addCriterion("channel_state <", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateLessThanOrEqualTo(String value) {
            addCriterion("channel_state <=", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateLike(String value) {
            addCriterion("channel_state like", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotLike(String value) {
            addCriterion("channel_state not like", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateIn(List<String> values) {
            addCriterion("channel_state in", values, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotIn(List<String> values) {
            addCriterion("channel_state not in", values, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateBetween(String value1, String value2) {
            addCriterion("channel_state between", value1, value2, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotBetween(String value1, String value2) {
            addCriterion("channel_state not between", value1, value2, "channelState");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusIsNull() {
            addCriterion("finance_lock_status is null");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusIsNotNull() {
            addCriterion("finance_lock_status is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusEqualTo(Integer value) {
            addCriterion("finance_lock_status =", value, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusNotEqualTo(Integer value) {
            addCriterion("finance_lock_status <>", value, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusGreaterThan(Integer value) {
            addCriterion("finance_lock_status >", value, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("finance_lock_status >=", value, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusLessThan(Integer value) {
            addCriterion("finance_lock_status <", value, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusLessThanOrEqualTo(Integer value) {
            addCriterion("finance_lock_status <=", value, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusIn(List<Integer> values) {
            addCriterion("finance_lock_status in", values, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusNotIn(List<Integer> values) {
            addCriterion("finance_lock_status not in", values, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusBetween(Integer value1, Integer value2) {
            addCriterion("finance_lock_status between", value1, value2, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("finance_lock_status not between", value1, value2, "financeLockStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerIsNull() {
            addCriterion("finance_locker is null");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerIsNotNull() {
            addCriterion("finance_locker is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerEqualTo(String value) {
            addCriterion("finance_locker =", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerNotEqualTo(String value) {
            addCriterion("finance_locker <>", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerGreaterThan(String value) {
            addCriterion("finance_locker >", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerGreaterThanOrEqualTo(String value) {
            addCriterion("finance_locker >=", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerLessThan(String value) {
            addCriterion("finance_locker <", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerLessThanOrEqualTo(String value) {
            addCriterion("finance_locker <=", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerLike(String value) {
            addCriterion("finance_locker like", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerNotLike(String value) {
            addCriterion("finance_locker not like", value, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerIn(List<String> values) {
            addCriterion("finance_locker in", values, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerNotIn(List<String> values) {
            addCriterion("finance_locker not in", values, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerBetween(String value1, String value2) {
            addCriterion("finance_locker between", value1, value2, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andFinanceLockerNotBetween(String value1, String value2) {
            addCriterion("finance_locker not between", value1, value2, "financeLocker");
            return (Criteria) this;
        }

        public Criteria andGuestNameIsNull() {
            addCriterion("guest_name is null");
            return (Criteria) this;
        }

        public Criteria andGuestNameIsNotNull() {
            addCriterion("guest_name is not null");
            return (Criteria) this;
        }

        public Criteria andGuestNameEqualTo(String value) {
            addCriterion("guest_name =", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameNotEqualTo(String value) {
            addCriterion("guest_name <>", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameGreaterThan(String value) {
            addCriterion("guest_name >", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameGreaterThanOrEqualTo(String value) {
            addCriterion("guest_name >=", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameLessThan(String value) {
            addCriterion("guest_name <", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameLessThanOrEqualTo(String value) {
            addCriterion("guest_name <=", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameLike(String value) {
            addCriterion("guest_name like", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameNotLike(String value) {
            addCriterion("guest_name not like", value, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameIn(List<String> values) {
            addCriterion("guest_name in", values, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameNotIn(List<String> values) {
            addCriterion("guest_name not in", values, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameBetween(String value1, String value2) {
            addCriterion("guest_name between", value1, value2, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestNameNotBetween(String value1, String value2) {
            addCriterion("guest_name not between", value1, value2, "guestName");
            return (Criteria) this;
        }

        public Criteria andGuestFileIsNull() {
            addCriterion("guest_file is null");
            return (Criteria) this;
        }

        public Criteria andGuestFileIsNotNull() {
            addCriterion("guest_file is not null");
            return (Criteria) this;
        }

        public Criteria andGuestFileEqualTo(String value) {
            addCriterion("guest_file =", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileNotEqualTo(String value) {
            addCriterion("guest_file <>", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileGreaterThan(String value) {
            addCriterion("guest_file >", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileGreaterThanOrEqualTo(String value) {
            addCriterion("guest_file >=", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileLessThan(String value) {
            addCriterion("guest_file <", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileLessThanOrEqualTo(String value) {
            addCriterion("guest_file <=", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileLike(String value) {
            addCriterion("guest_file like", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileNotLike(String value) {
            addCriterion("guest_file not like", value, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileIn(List<String> values) {
            addCriterion("guest_file in", values, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileNotIn(List<String> values) {
            addCriterion("guest_file not in", values, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileBetween(String value1, String value2) {
            addCriterion("guest_file between", value1, value2, "guestFile");
            return (Criteria) this;
        }

        public Criteria andGuestFileNotBetween(String value1, String value2) {
            addCriterion("guest_file not between", value1, value2, "guestFile");
            return (Criteria) this;
        }

        public Criteria andReceivableIsNull() {
            addCriterion("receivable is null");
            return (Criteria) this;
        }

        public Criteria andReceivableIsNotNull() {
            addCriterion("receivable is not null");
            return (Criteria) this;
        }

        public Criteria andReceivableEqualTo(BigDecimal value) {
            addCriterion("receivable =", value, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableNotEqualTo(BigDecimal value) {
            addCriterion("receivable <>", value, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableGreaterThan(BigDecimal value) {
            addCriterion("receivable >", value, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable >=", value, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableLessThan(BigDecimal value) {
            addCriterion("receivable <", value, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable <=", value, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableIn(List<BigDecimal> values) {
            addCriterion("receivable in", values, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableNotIn(List<BigDecimal> values) {
            addCriterion("receivable not in", values, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable between", value1, value2, "receivable");
            return (Criteria) this;
        }

        public Criteria andReceivableNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable not between", value1, value2, "receivable");
            return (Criteria) this;
        }

        public Criteria andPayableIsNull() {
            addCriterion("payable is null");
            return (Criteria) this;
        }

        public Criteria andPayableIsNotNull() {
            addCriterion("payable is not null");
            return (Criteria) this;
        }

        public Criteria andPayableEqualTo(BigDecimal value) {
            addCriterion("payable =", value, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableNotEqualTo(BigDecimal value) {
            addCriterion("payable <>", value, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableGreaterThan(BigDecimal value) {
            addCriterion("payable >", value, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payable >=", value, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableLessThan(BigDecimal value) {
            addCriterion("payable <", value, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payable <=", value, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableIn(List<BigDecimal> values) {
            addCriterion("payable in", values, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableNotIn(List<BigDecimal> values) {
            addCriterion("payable not in", values, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payable between", value1, value2, "payable");
            return (Criteria) this;
        }

        public Criteria andPayableNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payable not between", value1, value2, "payable");
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

        public Criteria andCheckinDateIsNull() {
            addCriterion("checkin_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckinDateIsNotNull() {
            addCriterion("checkin_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinDateEqualTo(Date value) {
            addCriterion("checkin_date =", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateNotEqualTo(Date value) {
            addCriterion("checkin_date <>", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateGreaterThan(Date value) {
            addCriterion("checkin_date >", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateGreaterThanOrEqualTo(Date value) {
            addCriterion("checkin_date >=", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateLessThan(Date value) {
            addCriterion("checkin_date <", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateLessThanOrEqualTo(Date value) {
            addCriterion("checkin_date <=", value, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateIn(List<Date> values) {
            addCriterion("checkin_date in", values, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateNotIn(List<Date> values) {
            addCriterion("checkin_date not in", values, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateBetween(Date value1, Date value2) {
            addCriterion("checkin_date between", value1, value2, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckinDateNotBetween(Date value1, Date value2) {
            addCriterion("checkin_date not between", value1, value2, "checkinDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateIsNull() {
            addCriterion("checkout_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateIsNotNull() {
            addCriterion("checkout_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateEqualTo(Date value) {
            addCriterion("checkout_date =", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateNotEqualTo(Date value) {
            addCriterion("checkout_date <>", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateGreaterThan(Date value) {
            addCriterion("checkout_date >", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateGreaterThanOrEqualTo(Date value) {
            addCriterion("checkout_date >=", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateLessThan(Date value) {
            addCriterion("checkout_date <", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateLessThanOrEqualTo(Date value) {
            addCriterion("checkout_date <=", value, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateIn(List<Date> values) {
            addCriterion("checkout_date in", values, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateNotIn(List<Date> values) {
            addCriterion("checkout_date not in", values, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateBetween(Date value1, Date value2) {
            addCriterion("checkout_date between", value1, value2, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andCheckoutDateNotBetween(Date value1, Date value2) {
            addCriterion("checkout_date not between", value1, value2, "checkoutDate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDeptNoIsNull() {
            addCriterion("dept_no is null");
            return (Criteria) this;
        }

        public Criteria andDeptNoIsNotNull() {
            addCriterion("dept_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNoEqualTo(String value) {
            addCriterion("dept_no =", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoNotEqualTo(String value) {
            addCriterion("dept_no <>", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoGreaterThan(String value) {
            addCriterion("dept_no >", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoGreaterThanOrEqualTo(String value) {
            addCriterion("dept_no >=", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoLessThan(String value) {
            addCriterion("dept_no <", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoLessThanOrEqualTo(String value) {
            addCriterion("dept_no <=", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoLike(String value) {
            addCriterion("dept_no like", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoNotLike(String value) {
            addCriterion("dept_no not like", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoIn(List<String> values) {
            addCriterion("dept_no in", values, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoNotIn(List<String> values) {
            addCriterion("dept_no not in", values, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoBetween(String value1, String value2) {
            addCriterion("dept_no between", value1, value2, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoNotBetween(String value1, String value2) {
            addCriterion("dept_no not between", value1, value2, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "deptName");
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

        public Criteria andRoomNoIsNull() {
            addCriterion("room_no is null");
            return (Criteria) this;
        }

        public Criteria andRoomNoIsNotNull() {
            addCriterion("room_no is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNoEqualTo(String value) {
            addCriterion("room_no =", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotEqualTo(String value) {
            addCriterion("room_no <>", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoGreaterThan(String value) {
            addCriterion("room_no >", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoGreaterThanOrEqualTo(String value) {
            addCriterion("room_no >=", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoLessThan(String value) {
            addCriterion("room_no <", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoLessThanOrEqualTo(String value) {
            addCriterion("room_no <=", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoLike(String value) {
            addCriterion("room_no like", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotLike(String value) {
            addCriterion("room_no not like", value, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoIn(List<String> values) {
            addCriterion("room_no in", values, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotIn(List<String> values) {
            addCriterion("room_no not in", values, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoBetween(String value1, String value2) {
            addCriterion("room_no between", value1, value2, "roomNo");
            return (Criteria) this;
        }

        public Criteria andRoomNoNotBetween(String value1, String value2) {
            addCriterion("room_no not between", value1, value2, "roomNo");
            return (Criteria) this;
        }

        public Criteria andMakerIsNull() {
            addCriterion("maker is null");
            return (Criteria) this;
        }

        public Criteria andMakerIsNotNull() {
            addCriterion("maker is not null");
            return (Criteria) this;
        }

        public Criteria andMakerEqualTo(String value) {
            addCriterion("maker =", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotEqualTo(String value) {
            addCriterion("maker <>", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerGreaterThan(String value) {
            addCriterion("maker >", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerGreaterThanOrEqualTo(String value) {
            addCriterion("maker >=", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLessThan(String value) {
            addCriterion("maker <", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLessThanOrEqualTo(String value) {
            addCriterion("maker <=", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLike(String value) {
            addCriterion("maker like", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotLike(String value) {
            addCriterion("maker not like", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerIn(List<String> values) {
            addCriterion("maker in", values, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotIn(List<String> values) {
            addCriterion("maker not in", values, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerBetween(String value1, String value2) {
            addCriterion("maker between", value1, value2, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotBetween(String value1, String value2) {
            addCriterion("maker not between", value1, value2, "maker");
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