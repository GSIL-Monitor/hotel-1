package com.fangcang.supplier.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Vinney on 2018/10/1.
 */
@Data
public class SetCooperationRequestDTO implements Serializable {

    private static final long serialVersionUID = -5215414988590373996L;

    /**
     * 供应商ID
     */
    @NotNull
    private Long supplyId;

    /**
     * 1-预警
     * 0-不预警
     */
    private Integer cooperationStatus;
}
