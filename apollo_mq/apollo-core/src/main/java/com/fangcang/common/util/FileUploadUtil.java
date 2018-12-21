package com.fangcang.common.util;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.UploadResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Owen on 2018/5/29.
 */
@Slf4j
public class FileUploadUtil {
    /**
     * @param multipartFile 文件
     * @param fileDir       服务器上文件存放的文件夹，如：product
     * @param realFileName  文件真实名字，如：123.txt，必须带文件类型。如果存在同名文件，则之前文件会被此文件覆盖.
     *                      文件真实名字可以为空，系统自动生成
     * @return
     */
    public static ResponseDTO<UploadResponseDTO> addFile(MultipartFile multipartFile, String fileDir, String realFileName, UploadFileConfig uploadFileConfig) {
        ResponseDTO result = validateFile(multipartFile);
        if (result.getResult() == ResultCodeEnum.SUCCESS.code) {
            FTPClientForFangcang ftpClient = null;
            try {
                int pos = multipartFile.getOriginalFilename().lastIndexOf(".");
                if (!StringUtil.isValidString(realFileName)) {
                    realFileName = System.currentTimeMillis() + multipartFile.getOriginalFilename().substring(pos);
                }

                String filePath;
                String fileUrl;

                ftpClient = FTPClientForFangcang.getInstance();
                //建立链接
                String addr = uploadFileConfig.getAddr();
                String port = uploadFileConfig.getPort();
                String username = uploadFileConfig.getUserName();
                String password = uploadFileConfig.getPassWord();
                ftpClient.connect(addr, Integer.parseInt(port), username, password);

                if (StringUtil.isValidString(fileDir)) {
                    filePath = uploadFileConfig.getFileDirpRefix().concat("/").concat(fileDir).concat("/").concat(realFileName);
                    fileUrl = uploadFileConfig.getFileUrlRefix().concat("/").concat(fileDir).concat("/").concat(realFileName);
                    ftpClient.changeWorkingDirectory(uploadFileConfig.getFileDirpRefix().concat("/").concat(fileDir));
                } else {
                    filePath = uploadFileConfig.getFileDirpRefix().concat("/").concat(realFileName);
                    fileUrl = uploadFileConfig.getFileUrlRefix().concat("/").concat(realFileName);
                    ftpClient.changeWorkingDirectory(uploadFileConfig.getFileDirpRefix());
                }

                boolean rs = ftpClient.upload(multipartFile.getInputStream(), realFileName);
                log.info("上传文件结果:" + rs);

                UploadResponseDTO uploadResponseDTO = new UploadResponseDTO(realFileName, fileUrl, filePath, multipartFile.getOriginalFilename());
                result.setModel(uploadResponseDTO);

            } catch (Exception e) {
                log.error("上传附件失败！" + JSON.toJSONString(uploadFileConfig), e);
                result = new ResponseDTO(ResultCodeEnum.FAILURE.code, "", "上传附件失败！");
            } finally {
                if (null != ftpClient) {
                    try {
                        ftpClient.closeConnection();
                    } catch (IOException e) {
                        log.error("ftp close has error", e);
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param fileDir      服务器上文件存放的文件夹，如：product
     * @param realFileName 文件真实名字，如：123.txt，必须带文件类型。
     * @return
     */
    public static ResponseDTO deleteFile(String fileDir, String realFileName, UploadFileConfig uploadFileConfig) {
        ResponseDTO result = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        FTPClientForFangcang ftpClient = null;
        try {
            if (!StringUtil.isValidString(realFileName)) {
                return new ResponseDTO(ResultCodeEnum.FAILURE.code, "", "文件名不能为空！");
            }

            String localPath;

            if (StringUtil.isValidString(fileDir)) {
                localPath = Constant.FILEDIRP_REFIX.concat("/").concat(fileDir).concat("/").concat(realFileName);
            } else {
                localPath = Constant.FILEDIRP_REFIX.concat("/").concat(realFileName);
            }

            ftpClient = FTPClientForFangcang.getInstance();
            //建立链接
            String addr = uploadFileConfig.getAddr();
            String port = uploadFileConfig.getPort();
            String username = uploadFileConfig.getUserName();
            String password = uploadFileConfig.getPassWord();
            ftpClient.connect(addr, Integer.parseInt(port), username, password);

            ftpClient.delete(localPath);

        } catch (Exception e) {
            log.error("删除附件失败！" + JSON.toJSONString(uploadFileConfig), e);
            return new ResponseDTO(ResultCodeEnum.FAILURE.code, "", "删除附件失败！");
        }
        return result;
    }

    private static ResponseDTO validateFile(MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(ResultCodeEnum.FAILURE.code);

        if (file == null || file.isEmpty()) {
            responseDTO.setFailReason("文件为空");
            return responseDTO;
        }

        String filename = file.getOriginalFilename();
        if (!StringUtil.isValidString(filename)) {
            filename = file.getOriginalFilename();
        }

        // 判断文件名称
        if (filename != null && filename.indexOf("\0") != -1) {
            responseDTO.setFailReason("文件名字不正确");
            return responseDTO;
        }

        // 判断文件大小
        int size = (int) (file.getSize() / 10240);
        if (size > 1024) {
            responseDTO.setFailReason("文件大小超过10M");
            return responseDTO;
        }

        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }
}
