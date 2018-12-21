package com.fangcang.finance.bill.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UploadBillOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单编码
     */
    private MultipartFile file;
}
