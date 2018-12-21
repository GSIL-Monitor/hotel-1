package com.fangcang.common.controller;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.UploadResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.FileUploadUtil;
import com.fangcang.common.util.UploadFileConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 公共服务
 *
 * @author : zhanwang
 * @date : 2018/7/11
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Resource
    private UploadFileConfig uploadFileConfig;

    /**
     * 上传附件到服务器
     *
     * @param file
     * @param attachType
     * @return
     */
    @RequestMapping(value = "/uploadAttach", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<UploadResponseDTO> uploadAttach(@RequestParam("file") MultipartFile file, Integer attachType) {
        ResponseDTO<UploadResponseDTO> responseDTO = new ResponseDTO<>();
        try {
            if (file == null || attachType == null) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("参数不能为空");
                return responseDTO;
            }
            // 附件类型，1订单附件，2财务附件
            String uploadDir = null;
            String[] allowTypes = null;
            if (attachType == 1) {
                uploadDir = Constant.FILE_UPLOAD_ORDER_DIR;
                allowTypes = Constant.ORDER_ALLOW_TYPES;
            } else if (attachType == 2) {
                uploadDir = Constant.FILE_UPLOAD_FINANCE_DIR;
                allowTypes = Constant.ORDER_FINANCE_ALLOW_TYPES;
            }
            // 获取文件后缀名
            if (allowTypes != null && allowTypes.length > 0) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                List<String> typeList = Arrays.asList(allowTypes);
                if (!typeList.contains(suffix)) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailReason("上传文件的格式不正确");
                    return responseDTO;
                }
            }
            responseDTO = FileUploadUtil.addFile(file, uploadDir, null, uploadFileConfig);
        } catch (Exception e) {
            log.error("uploadAttach error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 从服务器删除附件
     *
     * @param attachType
     * @param realFileName
     * @return
     */
    @RequestMapping(value = "/deleteAttach", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteAttach(Integer attachType, String realFileName) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (attachType == null || StringUtils.isEmpty(realFileName)) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("参数不能为空");
                return responseDTO;
            }
            // 附件类型，1订单附件，2财务附件
            String uploadDir = null;
            if (attachType == 1) {
                uploadDir = Constant.FILE_UPLOAD_ORDER_DIR;
            } else if (attachType == 2) {
                uploadDir = Constant.FILE_UPLOAD_FINANCE_DIR;
            }
            responseDTO = FileUploadUtil.deleteFile(uploadDir, realFileName, uploadFileConfig);
        } catch (Exception e) {
            log.error("deleteAttach error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

}
