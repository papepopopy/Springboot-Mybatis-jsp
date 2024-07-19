package com.springstudy.springbootJsp.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.springbootJsp.member.dto.PageRequestDTO;
import com.springstudy.springbootJsp.member.dto.PageResponseDTO;
import com.springstudy.springbootJsp.member.mapper.MemberXmlSQLMapperInterface;
import com.springstudy.springbootJsp.member.vo.MemberVO;

import lombok.extern.log4j.Log4j2;

@Service@Log4j2
public class MemberService {
	
	// DAO(database 처리하는 인터페이스 구현)
	@Autowired
	private MemberXmlSQLMapperInterface memberDAO;

	// 회원 목록 (페이지 기능 추가)
	public PageResponseDTO<MemberVO> getMemberList(PageRequestDTO pageRequestDTO){
		
		// 현재 페이지 => 해당페이지 레코드 시작 번호, 마지막 번호 계산 
		pageRequestDTO.pageStartToEndNumber();
		log.info("=> MemberService pageRequestDTO: "+pageRequestDTO);
		
		List<MemberVO> list = memberDAO.getMemberList(pageRequestDTO); // 전체 목록 추출
		
		// 페이지 처리 기능을 추가
		int total = memberDAO.getCount(pageRequestDTO); // 레코드 전체 추출
		log.info("=> 전체 레코드수: "+total);
		
		// memberList view 페이지에 전달할 정보를 저장한 객체
		PageResponseDTO<MemberVO> pageResponseDTO = PageResponseDTO.<MemberVO>withAll()
				.memberList(list)				// 조건에 대한 레코드 리스트
				.pageRequestDTO(pageRequestDTO) // 현재 페이지관련 정보
				.total(total)					// 조건에 대한 전체 레코드 수
				.build();
		
		return pageResponseDTO;
	}
}