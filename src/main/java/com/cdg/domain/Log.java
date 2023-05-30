package com.cdg.domain;

public class Log {
    // 2. 로그 파일 입력 받아 저장 -> 3. 입력 받은 로그파일 읽기
    // log 내부에서 사용하는 상태코드, url, 웹브라우저, 호출시간
    // 4가지를 받아오는 log는 다른 곳에서 사용해야하므로 public?
    private String stateCode;

    private String url;

    private String webBrowser;

    private String callTime;

    public Log(String stateCode, String url, String webBrowser, String callTime) {
        stateCode = getStateCode();
        url = getUrl();
        webBrowser = getWebBrowser();
        callTime = getCallTime();
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebBrowser() {
        return webBrowser;
    }

    public void setWebBrowser(String webBrowser) {
        this.webBrowser = webBrowser;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }
}
