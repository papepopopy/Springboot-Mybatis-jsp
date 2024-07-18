package com.springstudy.springbootJsp.member.dao;

import java.util.List;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springstudy.springbootJsp.member.mapper.MemberJavaSQLMapperInterface;
import com.springstudy.springbootJsp.member.vo.MemberVO;

//Repository => 적용시 error
@Component
public final class MemberDAOJavaSQL {
	
	@Autowired
	MemberJavaSQLMapperInterface member;
	
	@Autowired
	private SqlSession session;


	// 1. 회원 목록
	// 1-1. 어노테이션 적용
	public List<MemberVO> getMemberList(){
//		MemberJavaSQLMapperInterface member = 
//				session.getMapper(MemberJavaSQLMapperInterface.class);

		return member.getMemberList();

	}
	
	public MemberVO getMemberView(String id) {
//		MemberJavaSQLMapperInterface member = 
//				session.getMapper(MemberJavaSQLMapperInterface.class);
		return member.getfindIdMemberList(id);
	}

	// -------------------------------------------------------- //
	// SQL Class 맵핑:  SQL 어노테이션에 사용되는 SQL문 모듈화
	// -------------------------------------------------------- //
	public List<MemberVO> getFinalMemberList(){
//		MemberJavaSQLMapperInterface member = 
//				session.getMapper(MemberJavaSQLMapperInterface.class);
		
		return member.getFinalMemberList() ;
	}	
	public List<MemberVO> getMemberList2(){
//		MemberJavaSQLMapperInterface member = 
//				session.getMapper(MemberJavaSQLMapperInterface.class);
		
		return member.getMemberList2() ;
	}	
	public List<MemberVO> getMemberList3(){
//		MemberJavaSQLMapperInterface member = 
//				session.getMapper(MemberJavaSQLMapperInterface.class);
		
		return member.getMemberList3() ;
	}		
	// -------------------------------------------------- //
	// 회원 등록
	public int insertMember(MemberVO vo){
//		MemberJavaSQLMapperInterface member = 
//				session.getMapper(MemberJavaSQLMapperInterface.class);
		
		return member.insertMember(vo) ;
	}
	// 회원 수정
	public int updateMember(MemberVO vo){
//		MemberJavaSQLMapperInterface member = 
//				session.getMapper(MemberJavaSQLMapperInterface.class);
		return member.updateMember(vo);
	}
	// 회원 삭제
	public int deleteMember(String id) {
		return member.deleteMember(id);
	}
	// 회원 조회
	public MemberVO viewMember(String id) {
		return member.viewMember(id);
	}
	// 중복아이디체크
	public String idCheck(String id) {
		return member.idCheck(id);
	}
	
	//동적 SQL
	//조건 검색
	public List<MemberVO> getMemberListIf(String name, String email) {
		return member.getMemberListIf(name, email);
	}
}