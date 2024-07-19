package com.springstudy.springbootJsp.member.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springstudy.springbootJsp.member.dao.MemberDAOMybatis;
import com.springstudy.springbootJsp.member.dto.PageRequestDTO;
import com.springstudy.springbootJsp.member.dto.PageResponseDTO;
import com.springstudy.springbootJsp.member.service.MemberService;
import com.springstudy.springbootJsp.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {
	static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memverService;
	
	@Autowired
	private MemberDAOMybatis memberDAO;

	// 서비스 객체 선언
	
	@GetMapping("/now")
	public String getDbGetTime(Model model) {
		
		String getTime = memberDAO.getTime();
		logger.info("==> XML로작성한 sql과 java선언 함수 getTime() mapping결과: "+getTime);

		model.addAttribute("now", getTime);
		model.addAttribute("now2", memberDAO.getTime2());
		
		return "member/mybatisViewTest";
	}

	//-------------------------------------------- //
	
	// 회원목록
	@GetMapping("/list")
	public String getList(
				Model model,
				PageRequestDTO pageRequestDTO
				) {
		
		log.info("=> 클라이언트로 부터 받은 페이지 정보 객체 <= ");
		log.info("=> member list => "+pageRequestDTO);
		if (pageRequestDTO.getTypes() != null) {
			log.info("=> list pageRequestDTO.getTypes not null => "+pageRequestDTO.getTypes().length);
		} else {
			log.info("=> list pageRequestDTO.getTypes is null => "+pageRequestDTO.getTypes());
		}
		
		// 페이지 기능 설정
		PageResponseDTO<MemberVO> pageResponseDTO = memverService.getMemberList(pageRequestDTO);
		model.addAttribute("pageResponseDTO", pageResponseDTO);
		
		// 
//		List<MemberVO> list = memberDAO.getMemberList();
//		logger.info("=> member list: "+list);
//		model.addAttribute("members", list);
		
		
		return "member/memberList";

	}
	
	
	// 회원 조회
	@GetMapping("/view")
	public String getView(
						//Model model, HttpServletRequest req) {
						Model model,
						PageRequestDTO pageRequestDTO, String id
						) {
		
//		String id =  req.getParameter("id");
//		logger.info("member/view id: "+id);
//		log.info("member/view id: "+id);
		
		model.addAttribute("member",memberDAO.getMemberView(id));

		
		return "member/memberView";
	}
	
	// 회원 등록 입력폼 요청
	@GetMapping("/registerMember")
	public String registerMember() {
		return "member/registerMember";
	}
	// 회원 정보 데이터 처리 요청
	@PostMapping("/insert")
	public String  memberRegister(
			HttpServletRequest req,
			// MemberVO 속성이름과 입력폼에서 전달해준 매개변수가 동일한 이름일 경우
			MemberVO vo) {
		
//		MemberVO vo = MemberVO.builder()
//				.id(req.getParameter("id"))
//				.pwd(req.getParameter("pwd"))
//				.name(req.getParameter("name"))
//				.email(req.getParameter("email"))
//				.build();
		
		logger.info("member/insert : "+vo);
		
		memberDAO.insertMember(vo);
		//logger.info("=> member/insert isOK: "+isOK);

		return  "redirect:/member/list";
	}	
	
	@GetMapping("/remove")
	public String removeMember(
						//HttpServletRequest req) {
			
						//@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
						PageRequestDTO pageRequestDTO,
						String id
						) {
//		String id = req.getParameter("id");
//		logger.info("=> /remove id: "+id);
		
		memberDAO.deleteMember(id);

		//return "redirect:/member/list"
		// 현재 페이지 정보 유지 하여 list 요청
		return "redirect:/member/list?"+pageRequestDTO.getLink();
	}
	
	@PostMapping("/modify")
	public String modifyMember(
			//HttpServletRequest req,
			// MemberVO 속성이름과 입력폼에서 전달해준 매개변수가 동일한 이름일 경우
			//MemberVO vo) {
			
			// redirect방식에서 데이터 전달 객체
			RedirectAttributes redirectModel,
			PageRequestDTO pageRequestDTO,
			MemberVO vo) {
		
//		MemberVO vo = MemberVO.builder()
//				.id(req.getParameter("id"))
//				.pwd(req.getParameter("pwd"))
//				.name(req.getParameter("name"))
//				.email(req.getParameter("email"))
//				.build();
		
		logger.info("member/modify : "+vo);
		
		memberDAO.updateMember(vo);
	
		// 1. 방법 : 데이터 전달 없음
		//return "redirect:/member/list";
		
		// 2. 방법: 
		//return "redirect:/member/list?"+pageRequestDTO.getLink();//현재 페이지 정보유지
		
		// 3. 방법
		// redirect: 정보 전달 객체 : GET방식 전달
		redirectModel.addAttribute("page", pageRequestDTO.getPage());
		redirectModel.addAttribute("size", pageRequestDTO.getSize());
		return "redirect:/member/list";
		
		// 3. 방법
		// redirect: 정보 전달 객체 : POST방식 전달, session: 1회용
//		redirectModel.addFlashAttribute("page",pageRequestDTO.getPage() );
//		redirectModel.addFlashAttribute("size",pageRequestDTO.getSize() );
//		return "redirect:/member/list";
	}

	
	@PostMapping("/idcheck")
	public ResponseEntity<String> idCheck(HttpServletRequest req) {
		String id = req.getParameter("id");
		
		String isOK = memberDAO.idCheck(id);
//		logger.info("=> /idcheck id: "+id);
//		logger.info("=> id check result: "+isOK);
//		logger.info("=> "+( isOK.equals("true") ? "이미 사용중인 아이디입니다.":"사용가능한 아이디입니다."));
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		
		return new ResponseEntity<String>(isOK, responseHeaders, HttpStatus.CREATED);
	}
	
	
	// ------------------------------------------ //
	// 동적 SQL
	// ------------------------------------------ //
	// 조건 검색 하는 DAO기능 요청
	@GetMapping("/searchMember")
	public String searchMember(Model model, HttpServletRequest req) {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		model.addAttribute("members",memberDAO.getMemberListIf(name, email));
		
		return "member/memberList";
	}
	
	@GetMapping("/foreachMemberSelect")
	public String foreachMember(Model model) {
		
		List<String> nameList = new ArrayList<>();
		nameList.add("홍길동");
		nameList.add("이순신");
		nameList.add("강감찬");
		
		model.addAttribute("members",memberDAO.getForEachSelect(nameList));
		
		return "member/memberList";
	}
	@GetMapping("/foreachMemberInsert")
	public String foreachMemberInsert(Model model) {
		
		List<MemberVO> memberList = new ArrayList<>();
		
		for (int i=100; i<104; i++) {
			
			MemberVO vo = MemberVO.builder()
					.id("m"+i).pwd("1234").name("김길순"+i).email("m"+i+"@test.com")
					.build();
			
			memberList.add(vo);
		}
		logger.info("=> foreach Insert: "+memberList);
		// 일단보류
		//model.addAttribute("members",memberDAO.setForEachInsert(memberList));

		return "redirect:/member/list";

	}
	

}