package com.springstudy.springbootJsp.member.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springstudy.springbootJsp.member.dao.MemberDAOJavaSQL;
import com.springstudy.springbootJsp.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/member/sqlclass")
public class MemberJavaSQLController {
	static Logger logger = LoggerFactory.getLogger(MemberJavaSQLController.class);
    
	@Autowired
	MemberDAOJavaSQL memberDAOJavaSQL;
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<MemberVO> list = memberDAOJavaSQL.getMemberList();
		logger.info("=> member/sqlclass list: "+list);
		
		model.addAttribute("members", list);
		
		return "member/sqlclass/memberList";
	}
	
	@GetMapping("/view")
	public String getView(Model model, HttpServletRequest req) {
		String id =  req.getParameter("id");
		logger.info("member/view id: "+id);
		
		model.addAttribute("member",memberDAOJavaSQL.getMemberView(id));
		
		return "member/sqlclass/memberView";
	}
	
	// -------------------------------------------------------- //
	// SQL Class 맵핑:  SQL 어노테이션에 사용되는 SQL문 모듈화
	// -------------------------------------------------------- //	
	@GetMapping("/final_list")
	public String getFinalList(Model model) {
		List<MemberVO> list = memberDAOJavaSQL.getFinalMemberList();
		logger.info("=>  final_list: "+list);
		
		model.addAttribute("members", list);
		
		return "member/sqlclass/memberList";
	}
	@GetMapping("/list2")
	public String getMemberList2(Model model) {
		List<MemberVO> list = memberDAOJavaSQL.getMemberList2();
		logger.info("=> getMemberList2: "+list);
		
		model.addAttribute("members", list);
		
		return "member/sqlclass/memberList";
	}
	@GetMapping("/list3")
	public String getMemberList3(Model model) {
		List<MemberVO> list = memberDAOJavaSQL.getMemberList3();
		logger.info("=> getMemberList3: "+list);
		
		model.addAttribute("members", list);
		
		return "member/sqlclass/memberList";
	}
	// -------------------------------------------------- //	
	@GetMapping("/insert")
	public String insertMember(HttpServletRequest req) {
		
		MemberVO vo = MemberVO.builder()
				.id(req.getParameter("id"))
				.pwd(req.getParameter("pwd"))
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.build();
		
		logger.info("member/sqlclass/insert : "+vo);
		
		int isOK = memberDAOJavaSQL.insertMember(vo);

		return "redirect:/member/sqlclass/list";
	}
	@GetMapping("/modify")
	public String modifyMember(HttpServletRequest req) {
		MemberVO vo = MemberVO.builder()
				.id(req.getParameter("id"))
				.pwd(req.getParameter("pwd"))
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.build();
		
		logger.info("member/sqlclass/modify : "+vo);
		
		int isOk = memberDAOJavaSQL.updateMember(vo);
		
		return "redirect:/member/sqlclass/list";
	}
	@GetMapping("/delete")
	public String deleteMember(HttpServletRequest req) {
		String id = req.getParameter("id");
		
		int isOK = memberDAOJavaSQL.deleteMember(id);
		logger.info("=> sqlclass delete: "+ isOK );
		
		
		return "redirect:/member/sqlclass/list";
	}
	@GetMapping("/view2")
	public String view2Member(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		
		model.addAttribute("member", memberDAOJavaSQL.viewMember(id));
		
		return "member/sqlclass/memberView";
	}
	@GetMapping("/idcheck")
	public String idCheck(HttpServletRequest req) {
		String id = req.getParameter("id");
		
		String isOK =  memberDAOJavaSQL.idCheck(id);
		logger.info("=> idcheck result: "+isOK);
		
		return "redirect:/member/sqlclass/list";
	}	
	@GetMapping("/getMemberListIf")
	public String getMemberListIf(HttpServletRequest req) {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		
		List<MemberVO> members = memberDAOJavaSQL.getMemberListIf(name, email);
		
		return "redirect:/member/sqlclass/list";
	}	
	
}
