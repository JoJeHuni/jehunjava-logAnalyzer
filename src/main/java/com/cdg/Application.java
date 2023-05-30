package com.cdg;

import com.cdg.io.LogAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {
    // 1. 로그 파일을 받는다
    static final String logFilePath = "input.log";

    public static void main(String[] args) throws IOException {
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(logFilePath));
        // 버퍼에 들어오는게 없을 때까지 반복
        while(bufferedReader.readLine() != null) {
            logAnalyzer.divString(bufferedReader);
            logAnalyzer.addStateCode();
            logAnalyzer.addApiKey();
            logAnalyzer.addApiServiceId();
            logAnalyzer.addWebBrowser();
            logAnalyzer.addCallTime();
        }
        logAnalyzer.printResult();
    }

}
