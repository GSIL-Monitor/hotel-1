package com.fangcang.finance.bill.request;

import lombok.Data;

import java.util.List;

@Data
public class QueryCheckOutCurrencyDTO extends QueryCheckOutDTO{

    private List<String> orgCodelist;
}
