package com.travel.common.autocomplete;

import com.alibaba.fastjson.JSON;
import com.travel.common.dto.AutoCompleteDTO;

import java.util.List;

/**
 *   2018/1/15.
 */
public abstract class AbstractAutoComplete {

    protected abstract List<? extends AutoCompleteDTO> getData();

    public String getJsonData(){
        return JSON.toJSONString(getData());
    }
}
