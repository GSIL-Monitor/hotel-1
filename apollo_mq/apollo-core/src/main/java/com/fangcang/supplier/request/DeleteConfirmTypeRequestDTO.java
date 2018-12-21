package com.fangcang.supplier.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Vinney on 2018/9/30.
 */
@Data
public class DeleteConfirmTypeRequestDTO implements Serializable {

    private static final long serialVersionUID = -5176640479532047377L;

    @NotNull
    private Integer confirmTypeId;
}
