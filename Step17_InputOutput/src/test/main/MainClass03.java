package test.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainClass03 {
	public static void main(String[] args) {
		System.out.println("메인 메소드가 시작 되었습니다.");
		//키보드로 부터 입력 받을 수 있는 객체의 참조값을 kbd 라는 이름의 지역변수에 담기
		//어디에 있는거를 갖다가 써야한다 : System.in (inputStream)
		InputStream kbd=System.in;
		InputStreamReader isr=new InputStreamReader(kbd);
		BufferedReader br=new BufferedReader(isr);
		try {
			System.out.println("문자열 입력(아무거나 다 가능) : ");
			String line=br.readLine();
			System.out.println("line : "+line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
