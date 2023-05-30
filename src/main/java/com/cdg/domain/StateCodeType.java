package com.cdg.domain;

public enum StateCodeType {
    INVAILD_PARAMETER("10"),
    SUCCESS("200"),
    NOTFOUND("404");

    private String stateCode;

    StateCodeType(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateCode() {
        return stateCode;
    }
}
