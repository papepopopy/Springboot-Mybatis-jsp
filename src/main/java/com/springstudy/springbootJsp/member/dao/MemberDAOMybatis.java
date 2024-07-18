package com.springstudy.springbootJsp.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.springbootJsp.member.dto.PageRequestDTO;
import com.springstudy.springbootJsp.member.mapper.MemberXmlSQLMapperInterface;
import com.springstudy.springbootJsp.member.vo.MemberVO;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Repository
public class MemberDAOMybatis {
	
	@Autowired
	private SqlSession session;
	
	@Autowired
	private MemberXmlSQLMapperInterface memberXmlSqlDao;
	
	// mybatis xml와 java mapper interface 연결 테스트
	public String getTime() {
		return  memberXmlSqlDao.getTime(); 
	}
	public String getTime2() {
		return  session.selectOne("getTime");
	}
	
	//----------------------------------------------- //
	
	
	
	// 회원 목록 조회
	public List<MemberVO> getMemberList(PageRequestDTO pageRequestDTO){
		return memberXmlSqlDao.getMemberList(pageRequestDTO);
		
		// Mybatis의 SqlSession객체를 이용
		//return session.selectList("getMemberList");
	}
	
	//전체 레코드 개수 
	public int getCount(PageRequestDTO pageRequestDTO) {
		return memberXmlSqlDao.getCount(pageRequestDTO);
	}
	// 회원 정보 조회
	public MemberVO getMemberView(String id) {
		//return memberXmlSqlDao.getMemberView(id);
		return session.selectOne("getMemberView", id);
	}
	// 회원 등록
	public void insertMember(MemberVO vo) {
		//int isOK = 0;
		memberXmlSqlDao.insertMember(vo);
		
		//isOK = session.update("insertMember", vo);
		//session.commit();
		
		return ;
	}
	
	// 회원 삭제
	public void deleteMember(String id) {
				
		 memberXmlSqlDao.deleteMember(id);
		 //session.delete("deleteMember", id);
		 //session.commit();
		return;
	}
	
	// 회원 수정
	public void updateMember(MemberVO vo) {
		memberXmlSqlDao.updateMember(vo);
		
		//session.update("updateMember", vo);
		//session.commit();
		return;
	}
	
	// 중복 아이디 체크
	public String idCheck(String id) {
		return memberXmlSqlDao.idCheck(id); // 'true' or 'false' 반환
	}
	
	// ---------------------------------------------
	//동적 SQL
	// ---------------------------------------------
	//조건 검색
	public List<MemberVO> getMemberListIf(String name, String email){
		return memberXmlSqlDao.getMemberListIf(name, email);
	}
	public List<MemberVO> getForEachSelect(List<String> list) {
		return memberXmlSqlDao.getForEachSelect(list);
	}
	public List<MemberVO> getForEachInsert(List<MemberVO> list) {
		return memberXmlSqlDao.getForEachInsert(list);
	}
}