package com.springstudy.springbootJsp.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springstudy.springbootJsp.member.dto.PageRequestDTO;
import com.springstudy.springbootJsp.member.vo.MemberVO;

@Mapper
public interface MemberXmlSQLMapperInterface {
	
	// 기능수행 함수 선언
	
	// 0. test 기능 : 
	// 메서스이름와 mapper xml의 select태그 id값이 동일행 연결됨
	public String getTime();
	
	// 1. 회원 목록조회
	public List<MemberVO> getMemberList(PageRequestDTO pageRequestDTO); //페이징 기능 추가
	public int getCount(PageRequestDTO pageRequestDTO);
	
	// 2. 등록기능
	public void insertMember(MemberVO vo);
	// 3. 수정기능
	public void updateMember(MemberVO vo);
	// 4. 삭제기능
	public void deleteMember(@Param("id") String id);
	// 5. 조회기능
	public MemberVO getMemberView(@Param("id") String id);
	// 6. 아이디 중복체크
	public String idCheck(@Param("id") String id);
	
	// 동적 SQL
	// 7. 검색기능
	// : where ~ if, where choose when
	public List<MemberVO> getMemberListIf(
			@Param("name") String name, 
			@Param("email") String email 
			);
	//값 정렬
	//foreach select
	public List<MemberVO> getForEachSelect(@Param("list") List<String> list);
	
	//foreach insert
	public List<MemberVO> getForEachInsert(@Param("list") List<MemberVO> list);
	
}
