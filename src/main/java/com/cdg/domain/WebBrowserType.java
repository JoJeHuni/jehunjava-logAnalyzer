package com.cdg.domain;

public enum WebBrowserType {
    IE("IE"),
    FIREFOX("Firefox"),
    SAFARI("Safari"),
    CHROME("Chrome"),
    OPERA("Opera");

    private String webBrowser;

    WebBrowserType(String webBrowser) {
        this.webBrowser = webBrowser;
    }

    public String getWebBrowser() {
        return webBrowser;
    }
}
