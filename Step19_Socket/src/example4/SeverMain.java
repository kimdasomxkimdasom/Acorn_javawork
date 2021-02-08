package example4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverMain {

	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		try {
			//5000번 통신port를 열고 클라이언트의 접속을 기다린다.
			serverSocket=new ServerSocket(5000);
			/*
			 *  .accept()메소드는 클라이언트가 접속을 해야하지 리턴하는 메소드이다.
			 *  클라이언트가 접속을 해오면 해당 클라이언트와 연결된 Socket객체의
			 *  참조값을 리턴한다.
			 */
			while(true) { //while문으로 감싸주면서 무한루프
				System.out.println("클라이언트의 Socket접속을 기다립니다...");
				Socket socket=serverSocket.accept();
				System.out.println("클라이언트가 접속을 했습니다.");
				String ip=socket.getInetAddress().getHostAddress();
				System.out.println("접속한 클라이언트의 ip주소:"+ip);
				//방금 접속을 한 클라이언트로 부터 입력받을 객체의 참조값
				InputStream is=socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is);
				BufferedReader br=new BufferedReader(isr);
				String msg=br.readLine();
				System.out.println("클라이언트가 전송한 문자열 : "+msg);
				
				//2.Socket을 통해서 출력하기
				//클라이언트에게 출력 할 수 있는 객체의 참조값
				OutputStream os=socket.getOutputStream();
				OutputStreamWriter osw=new OutputStreamWriter(os);
				BufferedWriter bw=new BufferedWriter(osw);
				bw.write("안녕 클라이언트야 난 서버야"); //문자열을 출력해주는
				bw.newLine();//개행기호를 메소드를 이용해서 출력할 수 있다
				bw.flush();
				bw.close();
				socket.close();
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("ServerMain main 메소드가 종료 됩니다.");
	}
}
