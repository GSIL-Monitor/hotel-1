package com.fangcang.b2b.response;

import com.fangcang.b2b.dto.KeyWordDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class SearchHistoryResponseDTO implements Serializable{
    private static final long serialVersionUID = 3877370675250668608L;

    /**
     * 搜索历史
     */
    private List<KeyWordDTO> keyWords;
}






