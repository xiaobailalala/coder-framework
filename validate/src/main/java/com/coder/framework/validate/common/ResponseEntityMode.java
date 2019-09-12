package com.coder.framework.validate.common;

/**
 * @author zpx
 */
public enum ResponseEntityMode {

    /**
     * 默认标识
     */
    DEFAULT("default"),
    /**
     * 响应实体类中，用作响应消息字段的标志
     */
    MESSAGE("messageField", String.class),
    /**
     * 响应实体类中，用作标识码字段的标志
     */
    STATUS("statusField", Integer.class, int.class, String.class),
    /**
     * 响应实体类中，用作数据字段的标志
     */
    DATA("dataField", Object.class),
    /**
     * 响应实体类中，用作判别条件字段的标志
     */
    CONDITION("conditionField", Boolean.class, boolean.class);

    private String fieldName;
    private Class<?>[] fieldType;

    public String getFieldName() {
        return fieldName;
    }

    public Class<?>[] getFieldType() {
        return fieldType;
    }

    ResponseEntityMode(String fieldName, Class<?> ...fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public static ResponseEntityMode findByFieldName(String fieldName) {
        for (ResponseEntityMode value : ResponseEntityMode.values()) {
            if (fieldName.equals(value.getFieldName())) {
                return value;
            }
        }
        return ResponseEntityMode.DEFAULT;
    }

}
