package com.fangcang.merchant.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.util.MD5Util;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.merchant.dto.ResourceDTO;
import com.fangcang.merchant.dto.RoleDTO;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.merchant.service.RoleService;
import com.fangcang.merchant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vinney on 2018/6/4.
 */
@Controller
@Slf4j
@CrossOrigin
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;

//    @RequestMapping(value = "/apolloLogin")
//    @ResponseBody
//    public ResponseDTO apolloLogin(@AuthenticationPrincipal UserDTO loginedUser, @RequestParam(name = "logout", required = false) String logout, HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        if (null != loginedUser && StringUtil.isValidString(loginedUser.getMerchantCode()) && StringUtil.isValidString(loginedUser.getUsername())) {
//            //登录成功
//            ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
//            responseDTO.setModel(loginedUser);
//            return responseDTO;
//        } else {
//            ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.LOGIN_NOT_LOGIN);
//            return responseDTO;
//        }
//    }


    @RequestMapping(value = "/login" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO login(@RequestBody UserDTO requestDTO){
        ResponseDTO responseDTO=null;
        String domain = super.getRequest().getServerName();
        try{
            UserDO user = userService.queryUserByDomainAndUsername(domain,requestDTO.getUsername());

            if (null == user){
                log.error("登录失败，用户不存在："+JSON.toJSONString(requestDTO));
                responseDTO = new ResponseDTO(ErrorCodeEnum.LOGIN_ERROR_USERNAME_NOT_EXIST);
                return responseDTO;
            }

            if (!checkPassword(user.getPassword(),requestDTO.getPassword())){
                log.error("登录失败，用户或密码错误："+JSON.toJSONString(requestDTO));
                responseDTO = new ResponseDTO(ErrorCodeEnum.LOGIN_ERROR_USERNAME_OR_PASSWORD_ERROR);
                return responseDTO;
            }

            List<Long> roleIdList = new ArrayList<>();
            List<String> roleCodedList = new ArrayList<>();
            List<RoleDTO> roleDTOList = roleService.getRoleListByUserId(user.getUserId());
            for (RoleDTO roleDTO : roleDTOList) {
                if (roleDTO != null && roleDTO.getRoleCode()!=null) {
                    roleIdList.add(roleDTO.getRoleId());
                    roleCodedList.add(roleDTO.getRoleCode());
                }
            }

            List<ResourceDTO> resourceDTOList = roleService.queryMenuByRoleId(roleIdList);
            Map<String,List<ResourceDTO>> menuMap =  roleService.queryMenuMap(resourceDTOList);

            UserDTO userDTO = PropertyCopyUtil.transfer(user,UserDTO.class);
            userDTO.setRoleCodedList(roleCodedList);
            userDTO.setMenuMap(menuMap);
            //后面判断权限时会用到
            userDTO.setResourceList(roleService.getResourceUrl(resourceDTOList));

            responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
            responseDTO.setModel(userDTO);

            super.getSession().setAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER,userDTO);
            super.getSession().setAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_MERCHANTCODE,userDTO.getMerchantCode());

        }catch (Exception e){
            log.error("domain="+domain+",requestDTO="+ JSON.toJSONString(requestDTO),e);
            responseDTO=new ResponseDTO(ErrorCodeEnum.LOGIN_FAILED);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/logout" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO logout(){
        super.getSession().setAttribute(Constant.SESSION_ATTRIBUTE_CURRENT_USER,null);
        return new ResponseDTO(ErrorCodeEnum.SUCCESS);
    }

    private boolean checkPassword(String encodePassword,String requestPassword){
        return encodePassword.equals(MD5Util.encode(requestPassword));
    }


}
