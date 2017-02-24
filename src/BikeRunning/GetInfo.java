/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BikeRunning;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * 서버의 정보를 저장하여 
 * 각각에 맞는 자료 구조로 표현.
 * 
 *Final Version
 * @author Dayoung
 */
public class GetInfo {
     
    ArrayList<String> setStationData = new ArrayList<String>(); //역 정보 저장

       public String[] getInfo(String station) throws IOException, ClassNotFoundException{
        Socket socket = null;
        setStationData.removeAll(setStationData);
        
          String[] setLatHar = new String[2]; //위도 경도 설정
          
        try{
            socket = new Socket("127.0.0.1",1003);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String[][] list = (String[][]) ois.readObject();
            
            for(int i = 0; i<351; i++){
                if(list[i][1].contains(station)){
                    setLatHar[0] = list[i][2];
                    setLatHar[1] = list[i][3];
                  
                   setStationData.add(list[i][4]);
                   setStationData.add(list[i][5]);
                   setStationData.add(list[i][6]);
                }
                
            }
            
            ois.close();
        }catch(UnknownHostException e){
            System.err.println("localhost에 접근 x");
            System.exit(1);
        }catch(IOException e){
            System.err.println("입출력 오류");
            System.exit(1);
        }
        
        socket.close();
           return setLatHar;
    }

}
