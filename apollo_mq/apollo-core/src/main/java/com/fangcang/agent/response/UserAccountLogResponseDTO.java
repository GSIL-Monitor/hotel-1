package com.fangcang.agent.response;

import com.fangcang.agent.dto.UserAccountLogDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class UserAccountLogResponseDTO implements Serializable {
    private static final long serialVersionUID = -9001323193120748236L;

    /**
     * 日志列表
     */
    private List<UserAccountLogDTO> userAccountLogList;
}
