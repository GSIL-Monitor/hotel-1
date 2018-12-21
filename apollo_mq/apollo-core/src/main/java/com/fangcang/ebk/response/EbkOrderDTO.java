package com.fangcang.ebk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbkOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Long id;

    private String supplyOrderCode;

    private Integer orderStatus;

    private Integer isGroupon;

    private Long hotelId;

    private String hotelName;

    private Long roomtypeId;

    private String roomtypeName;

    private Long rateplanId;

    private String rateplanName;

    private String checkInDate;

    private String checkOutDate;

    private Integer nightNum;

    private Integer roomNum;

    private String guest;

    private String guestAttachUrl;

    private BigDecimal orderSum;

    private BigDecimal originalOrderSum;

    private String currency;

    private String specialRequirement;

    private String supplyCode;

    private String supplyName;

    private String merchantCode;

    private String merchantName;

    private String merchantRemark;

    private Integer balanceMethod;

    private String guide;

    private String guidePhone;

    private Long requestId;

    private String confirmNo;

    private String belongUser;

    private String belongName;

    private String lockUser;

    private String lockName;

    private String lockTime;

    private String creator;

    private String createTime;

    /**
     * 1编辑，0被别人锁定，只能查看
     */
    private Integer isEdit;

    /**
     * 产品
     */
    private List<EbkOrderProductDTO> productList;

    /**
     * 附加项
     */
    private List<EbkOrderProductDTO> additionList;

    /**
     * 减免
     */
    private List<EbkOrderProductDTO> derateList;

    private List<EbkOrderLogDTO> logList;

    /**
     * 未处理请求
     */
    private EbkRequestDTO untreatedRequest;
}
