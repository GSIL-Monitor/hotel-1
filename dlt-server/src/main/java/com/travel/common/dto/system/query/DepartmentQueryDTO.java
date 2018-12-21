package com.travel.common.dto.system.query;

import com.travel.common.dto.GenericQueryDTO;
import lombok.Data;

/**
 *   2018/1/8.
 */
@Data
public class DepartmentQueryDTO extends GenericQueryDTO {

    private static final long serialVersionUID = 1L;

    public DepartmentQueryDTO() {}

    public DepartmentQueryDTO(String deptName) {
        this.deptName = deptName;
    }

    private String deptName;

}
