package test.main;

import test.member.dao.MemberDao;

public class MainClass09 {
	public static void main(String[] args) {
		//850번 회원의 정보를 삭제하고자 한다.
		
		//이미 만들어진 클래스로 객체를 생성해서
		MemberDao dao=new MemberDao();
		//메소드를 사용하는 사용자 입장이다.
		boolean isSuccess=dao.delete(907);
		//MemberDao에 void로 해놓았기 때문에 위 작업의 성공 여부를 알 수 가 없다.
		//return타입을 boolean으로 수정해주고, 담아준다
		
		if(isSuccess) {
			System.out.println("삭제 성공 !");
		}else {
			System.out.println("삭제 실패 !");
		}
		
		
		System.out.println("main 메소드가 종료됩니다.");
		
	}
}
