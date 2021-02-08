package test.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import test.member.dto.MemberDto;
import test.util.DBConnect;

/*
 * Scanner 객체를 이용해서 검색할 회원의 번호를 입력받아서
 * 입력받은 숫자를 이용해서 SELECT문을 수행하고
 * 결과값을 MemeberDto객체를 생성해서 담아보세요.
 * 
 * 결과가 없다면 MemberDto 객체를 생성하지 마세요 (null인 상태로 두기)
 */
public class MainClass07 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("검색할 번호 입력 : ");
		int num=scan.nextInt();
		
		//검색된 회원정보가 담길 MemberDto 객체의 참조값을 담을 지역변수
		MemberDto dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//SELECT문을 만들고, 검색할 회원의 번호 입력 받기 
			//DBConnect 객체를 이용해서 Connection객체의 참조값 얻어내기
			conn=new DBConnect().getConn();
			//실행한 sql문(SELECT)의 뼈대 구성하기(<-미완성)
			String sql="SELECT name,addr"
					+ " FROM member"
					+ " WHERE num=?";
			//sql문을 대신 실행 해 줄 객체의 참조값 얻어오기 
			pstmt=conn.prepareStatement(sql);
			//? 에 값 바인딩하기
			pstmt.setInt(1, num);
			//SELECT문 수행하고 결과를 ResultSet으로 받기 
			rs=pstmt.executeQuery();
			
			//row가 여러개 였다면 while문으로 해야하는데
			//row가 0또는 1이기 때문에 if문 으로 사용 
			if(rs.next()) {//SELECT된 결과가 있다면
				//MemberDto 객체를 생성해서 
				dto=new MemberDto();
				//setter 메소드를 이용해서 값을 담는다.
				dto.setNum(num);
				//ResultSet객체에 있는 값도 얻어와서 담아준다.
				String name=rs.getString("name");
				dto.setName(name);
				dto.setAddr(rs.getString("addr"));
			}//SLELECT된 결과가 없다면 여기로 건너뛴다.
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)rs.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		//위의 try~catch~finally절이 수행된후에 SELECT된 결과가 있는지 없는지를
		//여기에서 쉽게 판별할 수 있는 방법이 있나요?
		//=====>dto가 null인지 아닌지 
		if(dto == null) {
			System.out.println(num + "번 회원은 존재하지 않습니다.");
		}else {
			System.out.println("번호: "+dto.getNum()+
					", 이름: "+dto.getName()+", 주소: "+dto.getAddr());
		}
	}
}	
