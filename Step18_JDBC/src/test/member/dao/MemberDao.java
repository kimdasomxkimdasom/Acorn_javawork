package test.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DBConnect;
/*
 * 회원정보 (member테이블)의 내용을
 * select
 * insert
 * update
 * delete
 * 작업을 할 객체를 생성할 클래스 설계하기
 * 
 * - 필요한 객체를 담을 지역변수를 선언하는 위치도 중요하다.
 * - 필요한 객체를 생성하는 위치도 중요하다.
 * 
 * Data Access Object (DAO)
 * -DB에 INSERT, UPDATE, DELETE, SELECT 작업을 수행하는 객체
 * -Table 마다 하나의 DAO혹은 주제(카테고리) 마다 하나의 DAO를 작성하게 된다.
 * -DAO를 만들기 위해서는 DTO 클래스를 미리 설계하고 만들어야 한다.
 * -주제(카테고리)에 관련된 DAO는 여러개의 Table의 join이 일어 날 수도 있다.
 *  따라서 하나의 Table당 하나의 DAO는 아닌것이다.
 *  예를 들어 사원정보를 출력한다고 가정을 해보면
 *  emp, dept, salgrade 3개의 테이블의 join이 일어날 수 있다.
 */
public class MemberDao {
	//모든 회원의 정보를 SELECT해서 리턴하는 메소드
	public List<MemberDto> selectAll(){

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//MemberDto 객체를 누적시킬 ArrayList 객체 생성하기		
		List<MemberDto> list=new ArrayList<>(); //only one
		
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT num,name,addr"
					+ " FROM member"
					+ " ORDER BY num DESC";
			pstmt=conn.prepareStatement(sql);
			//?에 바인딩 할게 있음 하고 아님 말고
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				//select된 row하나의 정보를 MemberDto객체를 생성해서 담고
				MemberDto dto=new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//새로 생성한 MemberDto객체의 참조값을 ArrayList객체에 누적시킨다.
				list.add(dto);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e){
				
			}
		}
		return list; //오류나는 이유: try{}안에서 만든 지역변수는 저기 안에서만 사용가능 
					//오류 안나게 하려면 : try{} 밖으로 꺼낸다 
	}
	
	//회원 한명의 정보를 SELECT해서 리턴하는 메소드
	public MemberDto select(int num) { //PrimaryKey num을 이용해서 select
									//select*from member where num=?
		
		//회원한명의 정보를 담고있는 MemberDto 객체를 담을 지역변수 만들기
		MemberDto dto=null;
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT name,addr FROM member WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			// ?에 값 바인딩하기
			pstmt.setInt(1, num);
			//SELECT문을 수행하고 결과를 ResultSet으로 받기
			rs=pstmt.executeQuery();
			if(rs.next()) {//만일 select된 row가 있다면 
				//결과를 MemberDto 객체를 생성해서 담는다.
				dto=new MemberDto();
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		
		//return null; //return타입이 MemberDto니까 null로 리턴 해주는게 좋다.
		//코드를 완성하였으니 형식에 맞게 return 수정
		return dto;
	}
	
	//회원 한명의 정보를 저장하는 메소드
	//메소드명 : insert
	//메소드에 전달하는 인자의 type : MemberDto
	public boolean insert(MemberDto dto) {//<-- 괄호안에는 메소드에 전달하는 인자 입력
		//왜 오류가 났을까? boolean타입으로 명시하고 return타입을 명시하지 않아서
		//일단 오류를 잡기 위해서 아래와 같이 적어주고. 코드를 완성 시킨 후 마지막에 수정해준다.
		//return false; 
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			//connection 객체의 참조값 얻어오기 
			conn=new DBConnect().getConn();
			
			String sql="INSERT INTO member"
					+ " (num,name,addr)"
					+ " VALUES(member_seq.NEXTVAL, ?, ?)";
								//num은 시퀀스로 관리
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			
			//INSERT문 수행하기 (1개의 row가 추가 되었으므로 1이 리턴된다)
			flag=pstmt.executeUpdate();		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	}
		
	//회원 한명의 정보를 수정하는 메소드
	//메소드명 : update
	//메소드에 전달하는 인자의 type : MemberDto
	public boolean update(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			//connection 객체의 참조값 얻어오기 
			conn=new DBConnect().getConn();
			
			// 실행할 sql문 작성 
			String sql="UPDATE member" 
					+ " SET name=?, addr=?"
					+ " WHERE num=?";
			
			pstmt=conn.prepareStatement(sql);
			
			// ?에 값을 바인딩 할 게 있으면 여기서 한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			
			flag=pstmt.executeUpdate();	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//회원 한명의 정보를 삭제하는 메소드
	public boolean delete(int num) {
		//return타입을 boolean으로 바꿔준다 
	
		//필요한 참조값을 담을 지역변수 미리 만들고 초기화 하기 
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0; //flag의 초기값은 0으로 설정 
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
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 작업
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}
		if(flag>0) { //flag가 0보다 크면 성공했다는 의미니깐 
			return true; //boolean return타입을 적어준다 
		}else { //flag가 변함이 없으면 실패했다는 의미
			return false;
		}
	
	}
}
