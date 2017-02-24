/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BikeRunning;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * GoogleMaps를 불러오는 클래스
 * 
 * Final Version
 * @author Sungrae
 */
public class GoogleMaps {
    public String latitude = ""; //위도에 관한 인스턴스 변수.
    public String longitude = ""; //경도에 관한 인스턴스 변수.
    
    void getGoogleMaps(){
          try {
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
                    + latitude
                    + ","
                    + longitude
                    + "&zoom=16&size=700x700&scale=1&maptype=roadmap";
            String destinationFile = "image.jpg";
 
            // Google로부터 지도 이미지를 읽어드린다.
            // 그때 이미지를 Local File(image.jpg)로 저장한다.
            //
            URL url = new URL(imageUrl); //Url주소가 가리키는 String 형식의 inmageUrl
            InputStream is = url.openStream(); //imageUrl을 읽는다.
            OutputStream os = new FileOutputStream(destinationFile); //파일을 image.jpg라는 이륾으로 출력.
 
            byte[] b = new byte[2048]; //Stream을 처리할 버퍼 b를 생성한다.
            int length; //한번에 읽어드린 버퍼의 크기를 기록할 변수.
 
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length); //버퍼의 크기만큼 OutputStream에 기록.
            }
 
            is.close(); //inputStream 객체 반환.
            os.close(); //OutputStream 객체 반환.
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
   public void setGoogleMaps(String latitude,String longitude){
        this.latitude = latitude; //입력된 위도로 위도 인스턴스 변수 초기화.
        this.longitude = longitude; //입력된 경도로 경도 인스턴스 변수 초기화
    }
}