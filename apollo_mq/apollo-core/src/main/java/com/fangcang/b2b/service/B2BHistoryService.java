package com.fangcang.b2b.service;

import com.fangcang.b2b.request.SaveSearchHistoryDTO;
import com.fangcang.b2b.request.SearchHistoryQueryDTO;
import com.fangcang.b2b.response.ContactUsResponseDTO;
import com.fangcang.b2b.response.SearchHistoryResponseDTO;
import com.fangcang.common.ResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/3 10:31
 * @Description: B2B分销商用户搜索历史Service
 */
public interface B2BHistoryService {

    /**
     * 保存搜索历史
     *
     * @param saveSearchHistoryDTO
     * @return
     */
    public ResponseDTO saveSearchHistory(SaveSearchHistoryDTO saveSearchHistoryDTO);


    /**
     * 查询搜索历史
     *
     * @param searchHistoryQueryDTO
     * @return
     */
    public ResponseDTO<SearchHistoryResponseDTO> querySearchHistory(SearchHistoryQueryDTO searchHistoryQueryDTO);

    /**
     * 联系我们
     *
     * @return
     */
    public ResponseDTO<ContactUsResponseDTO> contactUs(Long merchantId, String agentCode);

    /**
     * 删除搜索历史
     * @param searchHistoryQueryDTO
     * @return
     */
    public ResponseDTO deleteSearchHistory(SearchHistoryQueryDTO searchHistoryQueryDTO);

}
