package test.main;

public class MainClass06 {
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
								//<-여기서 시작하면 처음부터
			System.out.println(i); 
			//for문 안에서 1초씩 delay 시키는 예제 
			try {				//<-여기서 시작하면 0 다음부터 시작 
				Thread.sleep(1000); //1초에 숫자가 하나씩 
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
