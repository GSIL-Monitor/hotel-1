package com.travel.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class GenericValidate {

    private static final Log log = LogFactory.getLog(GenericValidate.class);

    private static Validator validator;

    static {
        getValidator();
    }

    private static void getValidator() {
        if (validator == null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }
    }

    public static String validate(Object obj, Class[] classes, String... paraNames) {
        if (obj == null) {
            log.error("the validate object is null!");
            return "待校验的对象为空";
        }

        StringBuilder validateResult = new StringBuilder();
        if (paraNames != null && paraNames.length > 0) {
            for (String paraName : paraNames) {
                validateResult.append(validateProperty(obj, classes, paraName));
            }
        } else {
            validateResult.append(validateObj(obj, classes));
        }
        return validateResult.toString();
    }

    private static String validateProperty(Object obj, Class[] classes, String paraName) {
        if (obj == null) {
            log.error("the validate object is null!");
            return "待校验的对象为空";
        }

        StringBuilder validateResult = new StringBuilder();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(obj, paraName, classes);
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            validateResult.append(constraintViolation.getRootBeanClass().getSimpleName()
                    + "." + constraintViolation.getPropertyPath()
                    + " " + constraintViolation.getMessage() + " ");
        }
        return validateResult.toString();
    }

    private static String validateObj(Object obj, Class[] classes) {
        if (obj == null) {
            log.error("the validate object is null!");
            return "待校验的对象为空";
        }

        StringBuilder validateResult = new StringBuilder();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, classes);
        if (constraintViolations != null && constraintViolations.size() > 0) {
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                validateResult.append(constraintViolation.getRootBeanClass().getSimpleName()
                        + "." + constraintViolation.getPropertyPath()
                        + " " + constraintViolation.getMessage() + " ");
            }
        }
        return validateResult.toString();
    }
}