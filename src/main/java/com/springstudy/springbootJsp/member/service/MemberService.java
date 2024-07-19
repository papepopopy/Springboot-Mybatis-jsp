package com.springstudy.springbootJsp.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.springbootJsp.member.dto.MemberDTO;
import com.springstudy.springbootJsp.member.dto.PageRequestDTO;
import com.springstudy.springbootJsp.member.dto.PageResponseDTO;
import com.springstudy.springbootJsp.member.mapper.MemberXmlSQLMapperInterface;
import com.springstudy.springbootJsp.member.util.MapperUtil;
import com.springstudy.springbootJsp.member.vo.MemberVO;

import lombok.extern.log4j.Log4j2;

@Service@Log4j2
public class MemberService {
	
	// DAO(database 처리하는 인터페이스 구현)
	@Autowired
	private MemberXmlSQLMapperInterface memberDAO;
	
	//서비스 생성 시점에 ModelMapper 객체 생성
	private ModelMapper modelMapper;

	// 서비스 생성자
	public MemberService() {
		modelMapper = MapperUtil.INSTANCD.get();
	}
	// 회원 목록 (페이지 기능 추가)
	public PageResponseDTO<MemberDTO> getMemberList(PageRequestDTO pageRequestDTO){
		
		// 현재 페이지 => 해당페이지 레코드 시작 번호, 마지막 번호 계산 
		pageRequestDTO.pageStartToEndNumber();
		log.info("=> MemberService pageRequestDTO: "+pageRequestDTO);
		
		//vo <-> table 1:1 맵핑 
		List<MemberVO> list = memberDAO.getMemberList(pageRequestDTO); // 전체 목록 추출
		
		//vo => dto 전환
		//1. list값 읽어오기
		List<MemberDTO> dtoList = list.stream()
				.map(vo -> modelMapper.map(vo, MemberDTO.class))
				.collect(Collectors.toList());
		
		// 페이지 처리 기능을 추가
		int total = memberDAO.getCount(pageRequestDTO); // 레코드 전체 추출
		log.info("=> 전체 레코드수: "+total);
		
		// memberList view 페이지에 전달할 정보를 저장한 객체
		PageResponseDTO<MemberDTO> pageResponseDTO = PageResponseDTO.<MemberDTO>withAll()
				.memberList(dtoList)				// 조건에 대한 레코드 리스트
				.pageRequestDTO(pageRequestDTO) // 현재 페이지관련 정보
				.total(total)					// 조건에 대한 전체 레코드 수
				.build();
		
		return pageResponseDTO;
	}
}