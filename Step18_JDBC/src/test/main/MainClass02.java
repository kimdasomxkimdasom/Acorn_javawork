package test.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * 접속 ip주소 : 14.63.164.99
 * 아이디 : acorn01
 * 비밀번호 : tiger01
 * 
 * 위의 DB에 접속해서
 * emp 테이블의 내용중에서 사원번호(empno), 사원이름(ename), 부서번호(deptno)
 * 를 select해서 콘솔창에 출력해보세요.
 * 단, 사원번호에 대해서 오름차순으로 정렬하세요
 */
public class MainClass02 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			String url="jdbc:oracle:thin:@14.63.164.99:1521:xe";
			conn=DriverManager.getConnection(url,"acorn01","tiger01");
			
			System.out.println("오라클 데이터베이스 접속 완료");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//emp테이블의 내용을 select해서 console에 출력해보기
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String sql="SELECT empno,ename,deptno"
					+ " FROM emp" //여러줄 쓸땐 첫번째 칸 띄어쓰기 해줘야함 
					+ " ORDER BY empno ASC";
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				int deptno=rs.getInt("deptno");
				
				System.out.println(empno+"|"+ename+"|"+deptno);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		System.out.println("main 메소드 종료");
	}

}
