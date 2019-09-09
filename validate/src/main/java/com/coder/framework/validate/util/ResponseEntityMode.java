package com.coder.framework.validate.util;

public enum ResponseEntityMode {

    /**
     * 默认标识
     */
    DEFAULT("default"),
    /**
     * 响应实体类中，用作响应消息字段的标志
     */
    MESSAGE("message"),
    /**
     * 响应实体类中，用作标识码字段的标志
     */
    STATUS("status"),
    /**
     * 响应实体类中，用作数据字段的标志
     */
    DATA("data"),
    /**
     * 响应实体类中，用作判别条件字段的标志
     */
    CONDITION("condition");

    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    ResponseEntityMode(String fieldName) {
        this.fieldName = fieldName;
    }
}
