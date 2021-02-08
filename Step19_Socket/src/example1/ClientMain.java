package example1;

import java.net.Socket;

public class ClientMain {
	public static void main(String[] args) {
		Socket socket=null;
		try {
			//new Socket("접속할ip주소",포트번호); 
			socket=new Socket("127.0.0.1", 5000);
				
			
			socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
