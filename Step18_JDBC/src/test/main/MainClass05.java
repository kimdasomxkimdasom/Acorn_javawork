package test.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import test.util.DBConnect;

/*
 * member 테이블에서
 * 회원번호가 801번 회원의 정보를 삭제하는 기능을 완성해보기
 * hint ) new DBConnect().getConn();
 */
public class MainClass05 {
	public static void main(String[] args) {
		//삭제할 회원의 번호
		int num=909;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		int flag=0;
		try {
			//connection 객체의 참조값 얻어오기 
			conn=new DBConnect().getConn();
			//실행할 sql문의 뼈대 준비하기
			String sql="DELETE FROM member WHERE num=?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt=conn.prepareStatement(sql);
			//?에 순서대로 값을 바인딩 하기
			pstmt.setInt(1, num);
			//sql문을 수행하고 변화된 row의 갯수를 리턴을 받기
			flag=pstmt.executeUpdate();
			System.out.println("회원정보 삭제");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 작업
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) {
			System.out.println("삭제(DELETE) 성공");
		}else {
			System.out.println("삭제(DELETE) 실패");
		}
	}
}
