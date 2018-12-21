package com.travel.common.utils;

import com.github.pagehelper.PageInfo;
import com.travel.common.dto.PaginationDTO;

/**
 *  Administrator on 2017/5/19.
 */
public class PageConvert
{

    /**
     * 转换PageInfo内容进入PaginationSupport
     * @param <T>
     *
     * @param pageInfo
     * @return
     */
    public static <T> PaginationDTO<T> getPaginationSupport(PageInfo<T> pageInfo)
    {

    	PaginationDTO<T> support = new PaginationDTO<T>();
        if (pageInfo != null)
        {
            support.setRecordList(pageInfo.getList());
            support.setPageSize(pageInfo.getPageSize());
            support.setCurrentPage(pageInfo.getPageNum());
            support.setTotalCount(Long.valueOf(pageInfo.getTotal()).intValue());
            support.setTotalPages(pageInfo.getPages());
        }

        return support;
    }
}
