package test.main;

import java.util.Random;
/*
 * 프로그래머가 직접 예외 클래스를 정의하고
 * 필요한 시점에서 예외를 발생 시킬수도 있을까?
 */

//예외를 발생시키기
public class MainClass07 {
	public static void main(String[] args) {
		System.out.println("main 메소드가 시작 되었습니다.");
		
		Random ran=new Random();
		int ranNum=ran.nextInt(3);
		if(ranNum==0) { //우연히 0이 나오면
			//throw 예약어를 이용해서 HeadacheException 발생 시키기
			throw new HeadacheException("으악 머리 아파! ! !!");
		}
		
		System.out.println("main 메소드가 종료 되었습니다.");
	}
	//머리 아픈 Exception
	static class HeadacheException extends RuntimeException{
		
		//public HeadacheException() { }
			//디폴트 생성자가 자동으로 정의가 되어있기 때문에 
			//throw new HeadacheException();							
			//로 입력해도 오류가 안난다. 
			//하지만 다른 타입으로 받으려면 명시적으로 생성자를 설정해야한다.
		public HeadacheException(String msg) {
			//부모생성자에 전달해야한다.
			super(msg);
		}
	}
}
