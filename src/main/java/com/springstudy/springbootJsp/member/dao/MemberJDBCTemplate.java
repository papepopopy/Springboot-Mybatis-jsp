package com.springstudy.springbootJsp.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springstudy.springbootJsp.member.vo.MemberVO;

@Repository
public class MemberJDBCTemplate {
	
	@Autowired
	private JdbcTemplate jdbc ;
	
	// JdbcTemplate 테스트 
	
	// 1. 회원 목록
	public List<MemberVO> jdbcMemberList(){
			
		String sql = "select * from t_member";
		
		// jdbc객체.query(쿼리문, new BeanPropertyRowMapper<객체>(클래스타입.class));
		List<MemberVO> list  = jdbc.query(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		return list;
	}
	
	// 2. 회원 조회
	public MemberVO jdbcMemberView(String id) {
		String sql = "select * from t_member where id=?";
		
		MemberVO member = 
				jdbc.queryForObject(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class), id);
		
		return member;
	}
	
	// 3. 회원 삭제
	public int jdbcMemberDelete(String id) {
		int isOK = 0;
		
		String sql = "delete from t_member where id = ?" ;
		isOK = jdbc.update(sql, id);
		
		return isOK;
	}
	
	
	// 4. 회원 수정
	public int jdbcMemberUpdate(MemberVO vo) {
		int isOK = 0;
		
		String sql = "update t_member set pwd=?, name=?, email=? where id = ?";
		isOK = jdbc.update(sql, vo.getPwd(), vo.getName(), vo.getEmail(), vo.getId());
		
		return isOK;
	}
	
	// 5. 회원 등록
	public int jdbcMemberInsert(MemberVO vo) {
		int isOK = 0;
		
		String sql = "insert into t_member (id, pwd, name, email) values(?,?,?,?) ";
		isOK = jdbc.update(sql, vo.getId(), vo.getPwd(), vo.getName(), vo.getEmail());
		
		return isOK;
	}
	
}
