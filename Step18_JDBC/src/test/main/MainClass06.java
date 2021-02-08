package test.main;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import test.util.DBConnect;

/*
 *  Scanner 객체를 이용해서 검색할 회원의 번호를 입력받아서
 *  DB에서 SELECT하고
 *  결과 값이 있으면 해당 회원의 정보를 콘솔창에 출력하고
 *  결과 값이 없으면 해당 회원은 존재 하지 않습니다.
 *  라고 출력되는 프로그래밍을 해보세요
 *  
 *  예)
 *  검색할 회원번호 입력 :
 *  801
 *  801번 회원은 존재하지 않습니다.
 *  
 *  예2)
 *  검색할 회원번호 입력
 *  850
 *  번호 : 850, 이름 : 톰캣, 주소 : 건물 옥상 
 */
public class MainClass06 {
	public static void main(String[] args) {
		//콘솔창으로 부터 입력 받을 객체 생성
		Scanner scan=new Scanner(System.in);
		//콘솔창에 입력한 값을 정수로 얻어낸다.
		//int num에 담아준다 
		System.out.println("검색할 회원 번호 입력 : ");
		int num=scan.nextInt(); //숫자타입이니깐 nextLine말고 nextInt
		
		//필요한 객체를 담을 지역변수 미리 만들기 
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null; // <-- SELECT문
		try {
			//Connection 객체의 참조값을 얻어오기
			conn=new DBConnect().getConn();
			//실행 할 SELECT문 의 뼈대 준비하기
			String sql="SELECT name,addr FROM member WHERE num=?";
			//PreparedStatement 객체
			pstmt=conn.prepareStatement(sql);
			//?에 값 바인딩해서 SELECT문 완성하기
			pstmt.setInt(1, num);
			//쿼리문(SELECT) 수행하고 결과를 ResultSet으로 받아오기
			rs=pstmt.executeQuery();
			/*
			 * member table에서 num은 PRIMARY KEY 값 이다.
			 * 따라서 SELECT된 결과 row의 갯수는
			 * 0이거나 1이 된다.
			 * 
			 * 커서를 한번만 내리는거기 때문에 반복문을 돌 필요가 없다 
			 */
			boolean isExist=rs.next();
			if(isExist) {
				//true면 : SELECT된 row가 존재 한다면 커서가 위치한곳의 데이터 얻어오기
				String name=rs.getString("name");
				String addr=rs.getString("addr");
				System.out.println("번호: "+num+" , 이름: "+name+" , 주소: "+addr);
			}else {
				//false면
				System.out.println(num+" 번 회원은 존재하지 않습니다.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)rs.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
	}
}	
