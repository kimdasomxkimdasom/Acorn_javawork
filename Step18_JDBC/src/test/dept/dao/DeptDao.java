package test.dept.dao;
/*
 * DeptDao 설계
 * Select Insert Update Delete
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dept.dto.DeptDto;
import test.member.dto.MemberDto;
import test.util.DBConnect;

public class DeptDao {
	
	//모든 정보를 SELECT해서 리턴하는 메소드
	public List<DeptDto> selectAll() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null; //Select에는 RseulstSet필요
		
		//DeptDto 객체를 누적시킬 ArrayList 객체 생성하기		
		List<DeptDto> list=new ArrayList<>();
		
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT deptno,dname,loc"
					+ " FROM dept"
					+ " ORDER BY num ASC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				//select된 row하나의 정보를 DeptDto객체를 생성해서 담고
				DeptDto dto=new DeptDto();
				dto.setDeptno(rs.getInt("depto"));
				dto.setDname(rs.getString("dame"));
				dto.setLoc(rs.getString("loc"));
				//새로 생성한 MemberDto객체의 참조값을 ArrayList객체에 누적시킨다.
				list.add(dto);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)rs.close();
				if(conn!=null)rs.close();
			}catch (Exception e) {
				}
			}
		
		return list; //오류를 잡기 위해 일단 return을 null로 
	}
	
	//하나의 정보를 SELECT해서 리턴하는 메소드
	public DeptDto select(int num) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT deptno,dname,loc"
					+ " FROM dept"
					+ " ORDER BY num ASC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				
			}
			
		}catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)rs.close();
				if(conn!=null)rs.close();
			}catch (Exception e){}
			
		}
		
		return null; //오류를 잡기 위해 일단 return을 null로 
	}
}
