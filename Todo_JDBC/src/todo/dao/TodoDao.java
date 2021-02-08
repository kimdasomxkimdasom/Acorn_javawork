package todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import todo.dto.TodoDto;
import todo.util.DBConnect;

/*
 * TodoDao클래스 만들기 
 * 
 * select / insert / update / delete
 * 작업을 할 객체를 생성할 클래스 설계하기
 * 
 * DAO를 만들기 위해서는 DTO 클래스를 미리 설계하고 만들어야 한다.
 */
public class TodoDao {
	//모든 회원의 정보를 SELECT해서 리턴하는 메소드
	public List<TodoDto> selectAll(){

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//TodoDto 객체를 누적시킬 ArrayList 객체 생성하기		
		List<TodoDto> list=new ArrayList<>(); //only one
		
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT num,content,regdate"
					+ " FROM todo"
					+ " ORDER BY num DESC";
			pstmt=conn.prepareStatement(sql);
			//?에 바인딩 할게 있음 하고 아님 말고
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				//select된 row하나의 정보를 MemberDto객체를 생성해서 담고
				TodoDto dto=new TodoDto();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
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
		return list; 
	}
	
	//하나의 정보를 SELECT해서 리턴하는 메소드
	public TodoDto select(int num) {
		
		//하나의 정보를 담고있는 TodoDto 객체를 담을 지역변수 만들기
		TodoDto dto=null;
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT content,regdate FROM todo WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			// ?에 값 바인딩하기
			pstmt.setInt(1, num);
			//SELECT문을 수행하고 결과를 ResultSet으로 받기
			rs=pstmt.executeQuery();
			if(rs.next()) {//만일 select된 row가 있다면 
				//결과를 MemberDto 객체를 생성해서 담는다.
				dto=new TodoDto();
				dto.setNum(num);
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
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
		return dto;
	}
	
	//INSERT
	public boolean insert(TodoDto dto) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			//connection 객체의 참조값 얻어오기 
			conn=new DBConnect().getConn();
			
			String sql="INSERT INTO todo"
					+ " (num,content,regdate)"
					+ " VALUES(member_seq.NEXTVAL, ?, SYSDATE)";
			
			pstmt=conn.prepareStatement(sql);
			// ?에 값 바인딩을 순서대로 한다.
			pstmt.setString(1, dto.getContent());
			
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
	
	//UPDATE
	public boolean update(TodoDto dto) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DBConnect().getConn();
			
			String sql="UPDATE todo"
					+ " SET content=?, regdate=?"
					+ " WHERE num=?";
			
			pstmt=conn.prepareStatement(sql);
			// ?에 값 바인딩을 순서대로 한다.
			pstmt.setString(1, dto.getContent());
			pstmt.setString(2, dto.getRegdate());
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
	
	//DELETE
	public boolean delete(int num) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DBConnect().getConn();
	
			String sql="DELETE FROM todo WHERE num=?";
	
			pstmt=conn.prepareStatement(sql);
			// ?값에 바인딩 
			pstmt.setInt(1, num);
			flag=pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {}
		}if(flag>0) {
			return true;
		}else {
			return false;
		}
	}

	
}
