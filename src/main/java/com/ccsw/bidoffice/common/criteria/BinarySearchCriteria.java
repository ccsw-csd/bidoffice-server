package com.ccsw.bidoffice.common.criteria;

public class BinarySearchCriteria {

    private String key;

    private String operation;

    private Object value;

    private Object value2;

    public BinarySearchCriteria(String key, String operation, Object value, Object value2) {
        this(key, operation, value);
        this.value2 = value2;
    }

    public BinarySearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }
}
