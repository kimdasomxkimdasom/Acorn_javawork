package test.mypac;

public class MyUtil {

	//그림을 그리는 메소드
	public static void draw() {
		System.out.println("5초동안 그림을 그려요");
		try { //1번방법 try~catch
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("그림이 완성 되었습니다");
	}
	
	//전송을 하는 메소드
	public static void send() throws InterruptedException { //2번방법 throw
		System.out.println("5초동안 전송을 해요");
		Thread.sleep(5000);
		System.out.println("전송을 완료 했습니다");
	}
}
