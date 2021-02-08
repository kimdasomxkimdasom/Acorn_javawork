package test.main;

import java.io.IOException;
import java.io.InputStream;

public class MainClass01 {
	public static void main(String[] args) {
		System.out.println("메인 메소드가 시작 되었습니다.");
		//키보드로 부터 입력 받을 수 있는 객체의 참조값을 kbd 라는 이름의 지역변수에 담기
		//어디에 있는거를 갖다가 써야한다 : System.in (inputStream)
		InputStream kbd=System.in;
		try {
			System.out.print("문자1개 입력: ");
			int code=kbd.read();
			//입력받은 코드값 출력하기
			System.out.println("code : "+code);
			//코드에 대응되는 문자1개 얻어내는 방법
			char ch=(char)code;
			System.out.println("ch : "+ch);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
