package test.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainClass15 {
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
			while(true) {
				int data=fis.read(); //1byte 읽어들이기
				System.out.println(data);
				if(data == -1) { //더이상 읽을 데이터가 없으면
					break; //while 반복문 탈출 
				}
				fos.write(data); //출력할 준비
				fos.flush(); //방출
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
