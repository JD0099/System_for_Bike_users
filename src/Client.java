/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
/**
 *
 * @author user
 */
public class Client {
    public static void main(String[] args)throws IOException, ClassNotFoundException{
        Socket socket = null;
        
        try{
            socket = new Socket("127.0.0.1",1003);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String[][] list = (String[][]) ois.readObject();
            for(int i=0; i<351; i++){
                    
                    System.out.print(list[i][0]+"\t");  //동
                    System.out.print(list[i][1]+"\t"); //역
                    System.out.print(list[i][2]+"\t");   //위도
                    System.out.print(list[i][3]+"\t");   //경도
                    System.out.print(list[i][4]+"\t");  //보관대위치
                    System.out.print(list[i][5]+"\t");   //갯수
                    System.out.print(list[i][6]+"\t");
                    System.out.println();//경사로유무
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
    }
}
