package com.springstudy.springbootJsp.testcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springstudy.springbootJsp.member.dao.MemberJDBCTemplate;
import com.springstudy.springbootJsp.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MemberJDBCTemplateController {
	
	@Autowired
	private MemberJDBCTemplate memberDAO;
	
	// 회원 List
	@GetMapping("/jdbcMembers")
	public String jdbcMemberList(Model model) {
		
		model.addAttribute("jdbcMembers", memberDAO.jdbcMemberList());
		
		System.out.println("list =>"+memberDAO.jdbcMemberList());
		return "jdbcView/jdbcMembers";
	}
	// 회원조회
	@GetMapping("/jdbcMemberView")
	public String jdbcMemberView(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		
		MemberVO member = memberDAO.jdbcMemberView(id);
		model.addAttribute("member",member);
		
		return "jdbcView/jdbcMemberView";
	}
	// 회원등록
	@GetMapping("/jdbcMemberRegister")
	public String jdbcMemberRegister(HttpServletRequest req) {
		MemberVO memberVO = MemberVO.builder()
				.id(req.getParameter("id"))
				.pwd(req.getParameter("pwd"))
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.build();
		
		int isOK = memberDAO.jdbcMemberInsert(memberVO);
		return "redirect:/jdbcMembers";
	}
	// 회원수정
	@GetMapping("/jdbcMemberModify")
	public String jdbcMemberModify(HttpServletRequest req) {
		MemberVO memberVO = MemberVO.builder()
				.id(req.getParameter("id"))
				.pwd(req.getParameter("pwd"))
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.build();
		
		int isOK = memberDAO.jdbcMemberUpdate(memberVO);
		return "redirect:/jdbcMembers";
	}
	// 회원삭제
	@GetMapping("/jdbcMemberDelete")
	public String dbcMemberDelete(HttpServletRequest req) {
		String id = req.getParameter("id");
		
		int isOK = memberDAO.jdbcMemberDelete(id);
		
		return "redirect:/jdbcMembers";
	}

}
