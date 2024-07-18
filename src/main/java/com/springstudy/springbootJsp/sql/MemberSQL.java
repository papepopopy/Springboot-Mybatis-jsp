package com.springstudy.springbootJsp.sql;

import org.apache.ibatis.jdbc.SQL;

// Mybatis SQL Class
public class MemberSQL {
	// 1. 회원 목록 
	// 1-1 : 상수 적용
	public static final String MEMBER_LIST = "select * from t_member";
	
	// 1-2. 함수 적용
	public String getMemberList2() {
		return new SQL(){{
			
			SELECT("*");
			FROM("t_member");
			
		}}.toString();
	}
	// 1-3
	public String getMemberList3() {
		return new SQL()
			.SELECT("*")
			.FROM("t_member")
			.toString();
	}
	
	// 2. 회원등록
	public String insertMember() {
//		String sql = new SQL()
//				.INSERT_INTO("t_member")
//				.VALUES("id, pwd, name, email", "#{id},#{pwd},#{name},#{email}")
//				.toString();
		
		String sql = new SQL()
				.INSERT_INTO("t_member")
				.VALUES("id", 	"#{id}")
				.VALUES("pwd", 	"#{pwd}")
				.VALUES("name", "#{name}")
				.VALUES("email","#{email}")
				.toString();
		return sql;
	}
	// 3. 회원수정
	public String updateMember() {
		
		return new SQL(){{
			UPDATE("t_member");
			SET("pwd = #{pwd}, name=#{name}, email=#{email}");
			WHERE("id=#{id}");
		}}.toString();
	}
	// 4. 회원삭제
	public String deleteMember() {
		return new SQL()
				.DELETE_FROM("t_member")
				.WHERE("id = #{id}")
				//.WHERE("id like concat(#{id}, '%')")
				.toString();
		// name like '%홍', name like '홍%', name like '%홍%'
	}
	// 5. 회원조회
	public String viewMember() {
		return new SQL()
				.SELECT("*").FROM("t_member").WHERE("id=#{id}")
				.toString();
	}
	// 6. 중복아이디체크
	public String idCheck() {
		return new SQL()
				.SELECT(" decode(count(*), 1, 'true', 'false') as isCheck")
				.FROM("t_member")
				.WHERE("id = #{id}")
				.toString();
	}
	
	// Mybatis 동적 SQL문
	public String getMemberListIf(final String name, final String email) {
		return new SQL() {{
			SELECT("*");
			FROM("t_member");
			if (name != null) {
				WHERE(" name = #{name}");
			}
			if (name != null && email != null) {
				WHERE("  email = #{email}");
			}
			if (name == null && email != null) {
				WHERE(" email = #{email}");
			}
		}}.toString();
	}
	
	
	
}