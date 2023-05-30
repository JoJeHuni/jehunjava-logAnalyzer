package com.cdg.io;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.substringBetween;
// substringBetween을 쓰기 위해 http://www.java2s.com/Code/Jar/c/Downloadcommonlang3jar.htm 참고

public class LogAnalyzer {
    //3. 입력받은 로그파일 읽어서 저장하기

    // key, value가 String, Integer 형태인 HashMap
    protected Map<String, Integer> apiKey = new HashMap<>();
    protected Map<String, Integer> stateCode = new HashMap<>();
    protected Map<String, Integer> apiServiceId = new HashMap<>();
    protected Map<String, Integer> webBrowser = new HashMap<>();
    protected Map<String, Integer> callTime = new HashMap<>();

    // 특정 문자열인 [,]을 기준으로 사이에 있는 문자열 나눠서 String 배열에 넣기
    private String[] string;


    public void divString(BufferedReader log) throws IOException {
        string = StringUtils.substringsBetween(log.readLine(), "[", "]");
    }

    // String 배열에 있는 log 요소들을 arrayList로 넣어주기
    // getOrDefault로 동일 키 값이 없으면 0부터 시작해서 있을 때마다 1 더해주기
    public void addStateCode() {
        stateCode.put(string[0], stateCode.getOrDefault(string[0], 0) + 1);
    }

    public void addApiKey() {
        String findApikey = substringBetween(string[1], "apikey=", "&");
        apiKey.put(findApikey, apiKey.getOrDefault(findApikey, 0) + 1);
    }

    public void addApiServiceId() {
        String findServiceId = substringBetween(string[1], "search", "apikey=");
        apiServiceId.put(findServiceId, apiServiceId.getOrDefault(findServiceId, 0) + 1);
    }
    public void addWebBrowser() {
        webBrowser.put(string[2], webBrowser.getOrDefault(string[2], 0) +1);
    }

    public void addCallTime() {
        // 시간은 "-" 문자열, 공백 문자열 때문에 길이에서 -3 해주기
        callTime.put(string[3].substring(0,string[3].length()-3),
                callTime.getOrDefault(string[3].substring(0, string[3].length()-3), 0) +1);
    }

    // -> 4. 넣어줬으니 출력하기
    private final String outputFileName = "output.log";

    // 코드 작성하던 중 상위 3개의 API ServiceID 추가 작성
    private static final int apiServiceIdTop3 = 3;

    public void printResult() throws IOException {

        File outputFile = new File(outputFileName);
        if(!outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(outputFile);
        List<String> apiKeyList = new ArrayList<>(apiKey.keySet());
        apiKeyList.sort((o1, o2) -> apiKey.get(o2).compareTo(apiKey.get(o1)));
        fileWriter.write("최다호출 APIKEY\n\n");
        fileWriter.write(apiKeyList.get(0) + "\n\n");
        // 버퍼를 비워주는 flush, close 중 flush 사용
        fileWriter.flush();

        fileWriter.write("상태 코드 별 횟수\n\n");
        fileWriter.write(stateCode + "\n\n");
        fileWriter.flush();

        fileWriter.write("상위 3개의 API Service ID와 각각의 요청 수\n\n");
        List<String> listServiceId = new ArrayList<>(apiServiceId.keySet());
        listServiceId.sort((o1, o2) -> apiServiceId.get(o2).compareTo(apiServiceId.get(o1)));
        for(int i = 0; i < apiServiceIdTop3; i++) {
            fileWriter.write(listServiceId.get(i) + " " +  apiServiceId.get(listServiceId.get(i)) + "\n");
            fileWriter.flush();
        }

        fileWriter.write("\n피크 시간대\n\n");
        List<String> listTime = new ArrayList<>(callTime.keySet());
        listTime.sort((o1, o2) -> callTime.get(o2).compareTo(callTime.get(o1)));
        fileWriter.write(listTime.get(0) + " " +  callTime.get(listTime.get(0)) + "\n");

        fileWriter.write("\n웹 브라우저 별 사용 비율\n\n");
        List<String> listBrowser = new ArrayList<>(webBrowser.keySet());
        listBrowser.sort((o1, o2) -> webBrowser.get(o2).compareTo(webBrowser.get(o1)));
        int total = 0;
        for(int i = 0; i < listBrowser.size(); i++) {
            total += webBrowser.get(listBrowser.get(i));
        }

        for(int i = 0; i < listBrowser.size(); i++) {
            fileWriter.write(listBrowser.get(i) + " " + ((double)webBrowser.get(listBrowser.get(i))/total*100 + "%\n"));
            fileWriter.flush();
        }
        fileWriter.flush();


    }
}
