package com.travel.hotel.order.entity.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderRestrictPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderRestrictPOExample() {
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

        public Criteria andRestrictIdIsNull() {
            addCriterion("restrict_id is null");
            return (Criteria) this;
        }

        public Criteria andRestrictIdIsNotNull() {
            addCriterion("restrict_id is not null");
            return (Criteria) this;
        }

        public Criteria andRestrictIdEqualTo(Long value) {
            addCriterion("restrict_id =", value, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdNotEqualTo(Long value) {
            addCriterion("restrict_id <>", value, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdGreaterThan(Long value) {
            addCriterion("restrict_id >", value, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdGreaterThanOrEqualTo(Long value) {
            addCriterion("restrict_id >=", value, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdLessThan(Long value) {
            addCriterion("restrict_id <", value, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdLessThanOrEqualTo(Long value) {
            addCriterion("restrict_id <=", value, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdIn(List<Long> values) {
            addCriterion("restrict_id in", values, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdNotIn(List<Long> values) {
            addCriterion("restrict_id not in", values, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdBetween(Long value1, Long value2) {
            addCriterion("restrict_id between", value1, value2, "restrictId");
            return (Criteria) this;
        }

        public Criteria andRestrictIdNotBetween(Long value1, Long value2) {
            addCriterion("restrict_id not between", value1, value2, "restrictId");
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

        public Criteria andSaleDateIsNull() {
            addCriterion("sale_date is null");
            return (Criteria) this;
        }

        public Criteria andSaleDateIsNotNull() {
            addCriterion("sale_date is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDateEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date =", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date <>", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("sale_date >", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date >=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThan(Date value) {
            addCriterionForJDBCDate("sale_date <", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date <=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateIn(List<Date> values) {
            addCriterionForJDBCDate("sale_date in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sale_date not in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sale_date between", value1, value2, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sale_date not between", value1, value2, "saleDate");
            return (Criteria) this;
        }

        public Criteria andBookDaysIsNull() {
            addCriterion("book_days is null");
            return (Criteria) this;
        }

        public Criteria andBookDaysIsNotNull() {
            addCriterion("book_days is not null");
            return (Criteria) this;
        }

        public Criteria andBookDaysEqualTo(Integer value) {
            addCriterion("book_days =", value, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysNotEqualTo(Integer value) {
            addCriterion("book_days <>", value, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysGreaterThan(Integer value) {
            addCriterion("book_days >", value, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_days >=", value, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysLessThan(Integer value) {
            addCriterion("book_days <", value, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysLessThanOrEqualTo(Integer value) {
            addCriterion("book_days <=", value, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysIn(List<Integer> values) {
            addCriterion("book_days in", values, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysNotIn(List<Integer> values) {
            addCriterion("book_days not in", values, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysBetween(Integer value1, Integer value2) {
            addCriterion("book_days between", value1, value2, "bookDays");
            return (Criteria) this;
        }

        public Criteria andBookDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("book_days not between", value1, value2, "bookDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeIsNull() {
            addCriterion("OCCUPANCY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeIsNotNull() {
            addCriterion("OCCUPANCY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeEqualTo(Integer value) {
            addCriterion("OCCUPANCY_TYPE =", value, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeNotEqualTo(Integer value) {
            addCriterion("OCCUPANCY_TYPE <>", value, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeGreaterThan(Integer value) {
            addCriterion("OCCUPANCY_TYPE >", value, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("OCCUPANCY_TYPE >=", value, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeLessThan(Integer value) {
            addCriterion("OCCUPANCY_TYPE <", value, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("OCCUPANCY_TYPE <=", value, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeIn(List<Integer> values) {
            addCriterion("OCCUPANCY_TYPE in", values, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeNotIn(List<Integer> values) {
            addCriterion("OCCUPANCY_TYPE not in", values, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeBetween(Integer value1, Integer value2) {
            addCriterion("OCCUPANCY_TYPE between", value1, value2, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("OCCUPANCY_TYPE not between", value1, value2, "occupancyType");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysIsNull() {
            addCriterion("OCCUPANCY_DAYS is null");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysIsNotNull() {
            addCriterion("OCCUPANCY_DAYS is not null");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysEqualTo(Integer value) {
            addCriterion("OCCUPANCY_DAYS =", value, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysNotEqualTo(Integer value) {
            addCriterion("OCCUPANCY_DAYS <>", value, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysGreaterThan(Integer value) {
            addCriterion("OCCUPANCY_DAYS >", value, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("OCCUPANCY_DAYS >=", value, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysLessThan(Integer value) {
            addCriterion("OCCUPANCY_DAYS <", value, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysLessThanOrEqualTo(Integer value) {
            addCriterion("OCCUPANCY_DAYS <=", value, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysIn(List<Integer> values) {
            addCriterion("OCCUPANCY_DAYS in", values, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysNotIn(List<Integer> values) {
            addCriterion("OCCUPANCY_DAYS not in", values, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysBetween(Integer value1, Integer value2) {
            addCriterion("OCCUPANCY_DAYS between", value1, value2, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andOccupancyDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("OCCUPANCY_DAYS not between", value1, value2, "occupancyDays");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIsNull() {
            addCriterion("CANCEL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIsNotNull() {
            addCriterion("CANCEL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTypeEqualTo(Integer value) {
            addCriterion("CANCEL_TYPE =", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotEqualTo(Integer value) {
            addCriterion("CANCEL_TYPE <>", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeGreaterThan(Integer value) {
            addCriterion("CANCEL_TYPE >", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("CANCEL_TYPE >=", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeLessThan(Integer value) {
            addCriterion("CANCEL_TYPE <", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("CANCEL_TYPE <=", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIn(List<Integer> values) {
            addCriterion("CANCEL_TYPE in", values, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotIn(List<Integer> values) {
            addCriterion("CANCEL_TYPE not in", values, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeBetween(Integer value1, Integer value2) {
            addCriterion("CANCEL_TYPE between", value1, value2, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("CANCEL_TYPE not between", value1, value2, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelDaysIsNull() {
            addCriterion("CANCEL_DAYS is null");
            return (Criteria) this;
        }

        public Criteria andCancelDaysIsNotNull() {
            addCriterion("CANCEL_DAYS is not null");
            return (Criteria) this;
        }

        public Criteria andCancelDaysEqualTo(Integer value) {
            addCriterion("CANCEL_DAYS =", value, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysNotEqualTo(Integer value) {
            addCriterion("CANCEL_DAYS <>", value, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysGreaterThan(Integer value) {
            addCriterion("CANCEL_DAYS >", value, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("CANCEL_DAYS >=", value, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysLessThan(Integer value) {
            addCriterion("CANCEL_DAYS <", value, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysLessThanOrEqualTo(Integer value) {
            addCriterion("CANCEL_DAYS <=", value, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysIn(List<Integer> values) {
            addCriterion("CANCEL_DAYS in", values, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysNotIn(List<Integer> values) {
            addCriterion("CANCEL_DAYS not in", values, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysBetween(Integer value1, Integer value2) {
            addCriterion("CANCEL_DAYS between", value1, value2, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("CANCEL_DAYS not between", value1, value2, "cancelDays");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNull() {
            addCriterion("CANCEL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNotNull() {
            addCriterion("CANCEL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeEqualTo(String value) {
            addCriterion("CANCEL_TIME =", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotEqualTo(String value) {
            addCriterion("CANCEL_TIME <>", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThan(String value) {
            addCriterion("CANCEL_TIME >", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CANCEL_TIME >=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThan(String value) {
            addCriterion("CANCEL_TIME <", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThanOrEqualTo(String value) {
            addCriterion("CANCEL_TIME <=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLike(String value) {
            addCriterion("CANCEL_TIME like", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotLike(String value) {
            addCriterion("CANCEL_TIME not like", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIn(List<String> values) {
            addCriterion("CANCEL_TIME in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotIn(List<String> values) {
            addCriterion("CANCEL_TIME not in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeBetween(String value1, String value2) {
            addCriterion("CANCEL_TIME between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotBetween(String value1, String value2) {
            addCriterion("CANCEL_TIME not between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkIsNull() {
            addCriterion("CANCEL_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkIsNotNull() {
            addCriterion("CANCEL_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkEqualTo(String value) {
            addCriterion("CANCEL_REMARK =", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotEqualTo(String value) {
            addCriterion("CANCEL_REMARK <>", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkGreaterThan(String value) {
            addCriterion("CANCEL_REMARK >", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("CANCEL_REMARK >=", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkLessThan(String value) {
            addCriterion("CANCEL_REMARK <", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkLessThanOrEqualTo(String value) {
            addCriterion("CANCEL_REMARK <=", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkLike(String value) {
            addCriterion("CANCEL_REMARK like", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotLike(String value) {
            addCriterion("CANCEL_REMARK not like", value, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkIn(List<String> values) {
            addCriterion("CANCEL_REMARK in", values, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotIn(List<String> values) {
            addCriterion("CANCEL_REMARK not in", values, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkBetween(String value1, String value2) {
            addCriterion("CANCEL_REMARK between", value1, value2, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkNotBetween(String value1, String value2) {
            addCriterion("CANCEL_REMARK not between", value1, value2, "cancelRemark");
            return (Criteria) this;
        }

        public Criteria andBookRoomsIsNull() {
            addCriterion("BOOK_ROOMS is null");
            return (Criteria) this;
        }

        public Criteria andBookRoomsIsNotNull() {
            addCriterion("BOOK_ROOMS is not null");
            return (Criteria) this;
        }

        public Criteria andBookRoomsEqualTo(Integer value) {
            addCriterion("BOOK_ROOMS =", value, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsNotEqualTo(Integer value) {
            addCriterion("BOOK_ROOMS <>", value, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsGreaterThan(Integer value) {
            addCriterion("BOOK_ROOMS >", value, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsGreaterThanOrEqualTo(Integer value) {
            addCriterion("BOOK_ROOMS >=", value, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsLessThan(Integer value) {
            addCriterion("BOOK_ROOMS <", value, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsLessThanOrEqualTo(Integer value) {
            addCriterion("BOOK_ROOMS <=", value, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsIn(List<Integer> values) {
            addCriterion("BOOK_ROOMS in", values, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsNotIn(List<Integer> values) {
            addCriterion("BOOK_ROOMS not in", values, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsBetween(Integer value1, Integer value2) {
            addCriterion("BOOK_ROOMS between", value1, value2, "bookRooms");
            return (Criteria) this;
        }

        public Criteria andBookRoomsNotBetween(Integer value1, Integer value2) {
            addCriterion("BOOK_ROOMS not between", value1, value2, "bookRooms");
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

        public Criteria andBookTimeIsNull() {
            addCriterion("book_time is null");
            return (Criteria) this;
        }

        public Criteria andBookTimeIsNotNull() {
            addCriterion("book_time is not null");
            return (Criteria) this;
        }

        public Criteria andBookTimeEqualTo(String value) {
            addCriterion("book_time =", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeNotEqualTo(String value) {
            addCriterion("book_time <>", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeGreaterThan(String value) {
            addCriterion("book_time >", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeGreaterThanOrEqualTo(String value) {
            addCriterion("book_time >=", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeLessThan(String value) {
            addCriterion("book_time <", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeLessThanOrEqualTo(String value) {
            addCriterion("book_time <=", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeLike(String value) {
            addCriterion("book_time like", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeNotLike(String value) {
            addCriterion("book_time not like", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeIn(List<String> values) {
            addCriterion("book_time in", values, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeNotIn(List<String> values) {
            addCriterion("book_time not in", values, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeBetween(String value1, String value2) {
            addCriterion("book_time between", value1, value2, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeNotBetween(String value1, String value2) {
            addCriterion("book_time not between", value1, value2, "bookTime");
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