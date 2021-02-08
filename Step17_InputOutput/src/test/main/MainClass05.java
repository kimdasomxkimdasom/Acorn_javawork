package test.main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class MainClass05 {
	public static void main(String[] args) {
		//콘솔창에 출력할 수 있는 객체의 참조값을 ps라는 지역변수에 담기
		PrintStream ps=System.out;
		//학습을 위해서 PrintStream 객체를 부모type OutputStream 변수에 담기
		OutputStream os=ps;
		//2byte 처리
		OutputStreamWriter osw=new OutputStreamWriter(os);
		try {
			osw.write("안녕하세요");
			osw.write("\r\n"); //개행기호
			osw.write("궁시렁..");
			osw.write("\r\n"); //개행기호
			osw.write("잘가세요");
			osw.write("\r\n"); //개행기호
			osw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
