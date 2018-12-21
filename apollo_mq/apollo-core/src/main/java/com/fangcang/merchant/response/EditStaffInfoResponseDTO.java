package com.fangcang.merchant.response;

import com.fangcang.merchant.dto.RoleDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class EditStaffInfoResponseDTO implements Serializable {
    private static final long serialVersionUID = -8428433795545599706L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户ID
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 角色列表
     */
    private List<RoleDTO> roleList;
}
