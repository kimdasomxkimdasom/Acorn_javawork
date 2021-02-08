package example5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SeverMain {
	//접속한 클라이언트를 응대하는 스레드 객체의 목록을 담을 필드
	static List<ServerThread> threadList;
	
	public static void main(String[] args) {
		//ArrayList객체 생성해서 필드에 저장하기
		threadList=new ArrayList<>();
		
		ServerSocket serverSocket=null;
		try {
			//5000번 통신port를 열고 클라이언트의 접속을 기다린다.
			serverSocket=new ServerSocket(5000);
			/*
			 *  .accept()메소드는 클라이언트가 접속을 해야하지 리턴하는 메소드이다.
			 *  클라이언트가 접속을 해오면 해당 클라이언트와 연결된 Socket객체의
			 *  참조값을 리턴한다.
			 */
			while(true) {
				System.out.println("클라이언트의 Socket 접속을 기다립니다...");
				//1. 클라이언트가 접속을 해오면 
				Socket socket=serverSocket.accept();
				//2. 새로운 스레드를 시작 시킨다. 
				ServerThread t=new ServerThread(socket);
				t.start();
				//3.시작된 스레드의 참조값을 목록에 누적시킨다.
				threadList.add(t);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("ServerMain main 메소드가 종료 됩니다.");
	}
	
	//스레드 클래스 설계
	static class ServerThread extends Thread{
		//필드 (클라이언트와 연결된 Socket객체의 참조값을 저장할 필드)
		private Socket socket;
		//클라이언트에게 문자열을 출력할 객체의 참조값을 저장할 필드
		private BufferedWriter bw;
		
		//생성자
		public ServerThread(Socket socket) {
			//생성자의 인자로 전달받은
			this.socket=socket;
		}
		
		//인자로 전달되는 문자열을 socket 객체를 통해서 출력하는 메소드
		public void broadcast(String msg) throws IOException {
			//인자로 전달된 문자열을 필드에서 저장된 BufferedWriter 객체의 참조값을 이영해서
			//클라이언트에게 출력하기
			bw.write(msg);
			bw.newLine(); //개행기호
			bw.flush(); //방출
			// ==> 아래에서 try~chtch가 되어있기때문에 throws를 사용 
		}
	
		@Override
		public void run() {
			try {
				//InputStream is=socket.getInputStream();
				//InputStreamReader isr=new InputStreamReader(is);
				//BufferedReader br=new BufferedReader(isr);
				//위의3줄 코딩을 아래의 1줄코딩으로 만든다면
				
				//클라이언트가 전송하는 문자열을 읽어드릴 객체
				BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//클라이언트에게 문자열을 출력할 객체
				bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				//반복문 돌면서 클라이언트가 전송하는 문자열이 잇는지 읽어와본다.
				while(true) {
					//문자열 한줄이 전송될때 가지 블록킹 되는 메소드
					String line=br.readLine(); //클라이언트가 전송한 문자열을 읽어와서
					for(ServerThread tmp:threadList) {
						tmp.broadcast(line);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//오류나거나 접속종료된 스레드는 목록에서 제거해야한다
			threadList.remove(this);
			
		}
	}
}
