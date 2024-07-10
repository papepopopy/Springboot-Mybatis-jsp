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
}
