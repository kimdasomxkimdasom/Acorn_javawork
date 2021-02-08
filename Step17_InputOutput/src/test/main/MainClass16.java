package test.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainClass16 {
	public static void main(String[] args) {
		/*
		 *  c:/myFolder/1.jpg 에서 byte 데이터를 읽어서
		 *  --> FileInputStream 객체를 이용
		 *  
		 *  c:/myFolder/copied.jpg 파일에 출력하기 
		 *  --> FileOutputStream 객체를 이용 
		 */
		
		//FileInputStream type의 참조값을 담을 지역변수를 미리 만들기
		FileInputStream fis=null;
		//FileOutputStream type의 참조값을 담을 지역변수를 미리 만들기
		FileOutputStream fos=null;
		
		try {
			//미리 만든 지역변수에 넣기 (객체 생성)
			fis=new FileInputStream(new File("c:/myFolder/1.jpg")); //<-()에 File객체의 참조값
			//미리 만든 지역변수에 넣기 (객체 생성)
			fos=new FileOutputStream(new File("c:/myFolder/copied.jpg"));
			
			// 한번에 byte 알갱이 1024개를 저장할 수 있는 배열 객체 생성
			// 1024 byte는 1 kilo byte라고도 한다.
			byte[] buffer=new byte[1024]; // 0이 들어있는 방이1024개 만들어진다 
			
			while(true) {
				//byte배열의 참조값을 read()메소드에 전달해서 byte 데이터를
				//배열에 담아오고, 읽은 갯수를 리턴받는다.
				int readedCount=fis.read(buffer); //한번에 1024개씩의 바이트 알갱이를 퍼오겠다
				if(readedCount==-1) {//더이상 읽을게 없으면
					break;//반복문 탈출
				}
				//FileOutputStream 객체를 이용해서
				//byte배열에 담긴 데이터를 0번 인덱스로 부터 읽은 갯수만큼 출력한다.
				fos.write(buffer, 0 , readedCount);
				
			}
			System.out.println("파일을 성공적으로 복사했습니다.");
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally { //예외가 발생하던 안하던 실행이 보장되는 블럭 
			//마무리작업 (새로 open한 stream은 닫아 줘야 한다.)
			try {
				fis.close(); //중괄호에 선언된 변수는 중괄호 안에서만 사용이 가능하다.
				fos.close(); //그러므로 위에서 만든 지역변수를 분리를 시켜준 후 try~catch로 다시 감싸준다.
			}catch(Exception e) {}
			
		}
		
	}
}
