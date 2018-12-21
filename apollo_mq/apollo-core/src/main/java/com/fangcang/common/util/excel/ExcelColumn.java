package com.fangcang.common.util.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    String name();

    String orderNum();

    String[] replace() default {};
}
