package BikeRunning;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *기상청 url에 접속하여 최고기온, 최저기온, 날씨를 파싱하여 저장한다.
 * 
 * Final Version
 * @author Dayoung
 */
public class UrlSplit {

    private URL url;
    private HttpURLConnection conn;
    private String code;

    public UrlSplit(String url, String code) throws MalformedURLException, IOException {
        this.url = new URL(url);
        this.conn = (HttpURLConnection) this.url.openConnection();
        this.code = code;
    }

    public String[] split() throws UnsupportedEncodingException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "euc-kr"));
        LinkedList<String> lines = new LinkedList();
        String readLine = "";
        String[] setWeatherData = new String[4]; //최고기온과 최저기온과 날씨를 전달하기 위해 저장하는 배열
        String[] getHighLow = new String[2]; //최고기온과 최저기온을 파싱하여 저장하는 배열
        String[] getIcon = new String[2]; //날씨와 아이콘 이름를 파싱하여 저장하는 배열

        while ((readLine = in.readLine()) != null) {
            lines.add(readLine);
        }

        for (String line : lines) { //최저, 최고 기온
            if (line.contains(code)) {
                getHighLow = parHighLowData(line);
                setWeatherData[0] = getHighLow[0]; //최고기온 저장
                setWeatherData[1] = getHighLow[1]; //최저기온 저장
                break;
            }
        }
        
        for (String line : lines) { //날씨
            if (line.contains("<dd><a href=\"/weather/main.jsp#2611053000\"><img src=\"/images/icon/NW/")) {
                getIcon = parIcon(line);
                setWeatherData[2] = getIcon[0]; //날씨 저장
                setWeatherData[3] = getIcon[1]; //날씨 아이콘 저장
                break;
            }
        }
        
        return setWeatherData;
    }

    public String[] parHighLowData(String urlSplitlLine) { //문자열 분리하는 함수
        
        String[] resultDegree = new String[3];
        String[] resultArray = new String[2];

        if (urlSplitlLine.contains("#" + code + "\"><span class=")) { //최저기온, 최고기온
            resultDegree = urlSplitlLine.split("<span class=\"");

        }
        String resultHigh = resultDegree[1].substring(5, 9);
        String resultLow = resultDegree[2].substring(6,10);
        
        resultArray[0] = resultHigh;        
        resultArray[1] = resultLow;
        
       return resultArray;
    }
    
    public String[] parIcon(String urlSplitLine){
        
        String[] splitWeather = new String[2];
        String[] icon = new String[2]; //[0] = 날씨 결과 [1] = 날씨 아이콘
        
        if(urlSplitLine.contains("#" + code + "\"><img src=\"/images/icon/NW/NB")){ //날씨(비, 구름 등)
            splitWeather = urlSplitLine.split("NB");
        }
        
        String weather = splitWeather[1].substring(0,2);
        
        if(weather.compareTo("01")==0){
            icon[0] = "맑음";
            icon[1] = "sunny";
        }
        else if(weather.compareTo("02")==0){
            icon[0] = "구름조금";
            icon[1] = "cloud";
        }
        else if(weather.compareTo("03")==0){
            icon[0] = "구름많음";
            icon[1] = "cloud";
        }
        else if(weather.compareTo("04")==0){
            icon[0] = "흐림";
            icon[1] = "cloud";
        }
        else if(weather.compareTo("07")==0){
            icon[0] = "소나기";
            icon[1] = "rain";
        }
        else if(weather.compareTo("08")==0){
            icon[0] = "비";
            icon[1] = "rain";
        }
        else if(weather.compareTo("11")==0){
            icon[0] = "눈";
            icon[1] = "cloud";
        }
        else if(weather.compareTo("13")==0){
            icon[0] = "비 또는 눈";
            icon[1] = "cloud";
        }
        else if(weather.compareTo("17")==0){
            icon[0] = "박무";
            icon[1] = "cloud";
        }
        else{
            icon[0] = "기타";
            icon[1] = "기타";
        }
       
        return icon;
    }    
}
