package test.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * member 테이블에서
 * num이 804번 인 회원의
 * addr 을 노량진으로 수정하는 코드를 작성해보세요.
 */
public class MainClass04 {
	public static void main(String[] args) {
		int num=804;
		String addr="노량진";
		
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			String url="jdbc:oracle:thin:@14.63.164.99:1521:xe";
			conn=DriverManager.getConnection(url,"acorn01","tiger01");
			
			System.out.println("오라클 데이터베이스 접속 완료");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//member테이블 콘솔창 출력
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			// 미완성의 update문
			String sql="UPDATE member" 
					+ " SET addr=?"
					+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			// ?에 순서대로 값을 바인딩 하기
			// 바인딩 => binding => 연결하기 => 붙이기 => ???
			pstmt.setString(1, addr);
			pstmt.setInt(2, num);
			// 완성된 sql문을 수행하고 변화된 row의 갯수를 리턴을 받는다.
			flag=pstmt.executeUpdate();
			
			System.out.println("회원정보 수정");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) {
			System.out.println("수정(UPDATE) 성공");
		}else {
			System.out.println("수정(UPDATE) 실패");
		}
		
	}
}
