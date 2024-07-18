package com.springstudy.springbootJsp.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.springbootJsp.member.dto.PageRequestDTO;
import com.springstudy.springbootJsp.member.dto.PageResponseDTO;
import com.springstudy.springbootJsp.member.mapper.MemberXmlSQLMapperInterface;
import com.springstudy.springbootJsp.member.vo.MemberVO;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service //만드시 붙여줘야함
public class MemberService {
	
	//DAO(database 처리하는 인터페이스 구현)
	@Autowired
	private MemberXmlSQLMapperInterface memberDAO;
	
	//회원 목록 (페이지 기능 추가)
	public PageResponseDTO<MemberVO> getMemberList(PageRequestDTO pageRequestDTO) {
		List<MemberVO> list = memberDAO.getMemberList(pageRequestDTO); //전체 리스트 들고오기
		
		//페이지 처리 기능을 추가
		int total = memberDAO.getCount(pageRequestDTO);//레코드 전체 추출
		log.info("=> 전체 레코드 수 : " + total);
		
		
		PageResponseDTO<MemberVO> pageResponseDTO = PageResponseDTO.<MemberVO>withAll()
				.memberList(list) //조건에 대한 레코드 리스트
				.pageRequestDTO(pageRequestDTO) // 현재 페이지 정보
				.total(total) // 전체 
				.build();
		return pageResponseDTO;
	}
}
