package com.cdg.domain;

public enum ApiServiceIdType {

    KNOWLEDGE("knowledge"),
    BLOG("blog"),
    VCLIP("vclip"),
    IMAGE("image"),
    NEWS("news"),
    BOOK("book");

    private String apiServiceId;
    ApiServiceIdType(String apiServiceId) {
        this.apiServiceId = apiServiceId;
    }

    public String getApiServiceId() {
        return apiServiceId;
    }
}
