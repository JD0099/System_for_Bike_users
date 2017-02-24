/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import java.sql.*;  //SQL에 관한 패키지를 사용

/**
 *
 * @author user
 */
public class Server {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException{
        String[][] list = new String[351][7];
        String url = "jdbc:mysql://127.0.0.1:3306";    //IP,포트번호
        String user = "root";   //DB 아이디
        String pass = "Rlatjdfo79!";   //DB 패스워드
        String driver = "org.gjt.mm.mysql.Driver";  //JDBC 드라이버
        String query = "SELECT *FROM project.bike";    //DB 테이블
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        Connection con = null;
        ObjectOutputStream oos = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            serverSocket = new ServerSocket(1003);  //1003번 포트 생성
        }catch(IOException e){
            System.out.println("다음의 포트 번호에 연결할 수 없습니다.");
            System.exit(1);
        }
        
        while(true){
       
        try{
            clientSocket = serverSocket.accept();   //접속을 받아들인다.
        }catch(IOException e){
            System.out.println(" accept() 실패");
            System.exit(1);
        }
        
        
        
       
        try{
           
             Class.forName(driver); //JDBC 드라이버 로드
             con = DriverManager.getConnection(url,user,pass);  //DB 접속
             
             
             stmt = con.createStatement();  //정적쿼리를 보내기 위해
             rs = stmt.executeQuery(query); //DB 테이블 불러오기
           
           
             
             for(int i=0; i<351; i++){
              if(rs.next()){    //다음줄로 넘어가기 위해
                 for(int j=0; j<7; j++){
                 list[i][j] = rs.getString(j+1);  //정보 저장
                 }
             }
         }
          
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        //객체를 보내기 위해 ObjectOutputStream를 사용
        oos.writeObject(list);  //list를 보낸다.
        oos.reset();    //리셋
        
       
        oos.close();    //ObjectOutputStream 닫기
        clientSocket.close();   //클라이언트소켓 닫기
        }catch (SQLException e){ 
     }  
        
        }
    }
}