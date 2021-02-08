package study;

public class Study {

	public void call() {
		System.out.println("부릉부릉");
	}
	
	public void call2(String name) {
		System.out.println(name+"입니다.");
	}
	
	public static void main(String[] args) {
		Study s=new Study();
		s.call();
		s.call2("김구라");
	}
}
