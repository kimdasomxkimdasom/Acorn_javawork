package test.main;

import java.util.Scanner;

public class MainClass03 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("나눌 수 입력 : ");
		String inputNum1=scan.nextLine();
		System.out.println("나누어 지는 수 입력 : ");
		String inputNum2=scan.nextLine();
		
		//어떤 예외가 발생 할지 모를때 
		//cathc( )괄호안에 Exception을 넣어주면 
		//종류를 지정하지않고 모든 예외의 경우(Exception)을 다 처리 할수있다
		
		try {
			//입력한 숫자형식의 문자열을 실제 정수로 바꾸기
			int num1=Integer.parseInt(inputNum1);
			int num2=Integer.parseInt(inputNum2);
			//정수를 정수로 나누면 소수점은 짤리고 정수만 남는다 (나눈 몫이 구해진다)
			int result1=num2/num1;
			int result2=num2%num1; //나머지 연산자를 이용해서 나눈 나머지값을 구한다.
			
			System.out.println(num2+" 를 "+num1+" 으로 나눈 몫은 "+result1);
			System.out.println(num2+" 를 "+num1+" 으로 나눈 나머지값은 "+result2);
		}catch(Exception e) { //종류를 지정하지않고 모든 예외의 경우(Exception)을 다 처리 할수있다
			e.printStackTrace();
		}

		
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
