package BikeRunning;


import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *UrlSplit클래스의 객체를 생성하여 
 * UrlSplit클래스가 기상청 페이지 소스를 받아 
 * 파싱한 날씨 결과를 리턴받는다.
 * 
 * Final Version
 * @author Dayoung
 */
public class Weather {
    private UrlSplit urlSplit;
    private String weatherData[];
    
    public void start() throws IOException{ //시작
        weatherData = getWeatherData();
    }
    
    public String[] getWeatherData() throws IOException{ //url주고 짜르는일
        String[] weatherData = new String[4];
        urlSplit = new UrlSplit("http://www.kma.go.kr/index.jsp", "2611053000");
        weatherData = urlSplit.split();
        
        return weatherData;
    }
    
}
