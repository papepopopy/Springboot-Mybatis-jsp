package com.springstudy.springbootJsp.testcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springstudy.springbootJsp.member.dao.MemberJDBCTemplate;

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
	public String jdbcMemberView() {
		return "jdbcView/jdbcMemberView";
	}
	// 회원등록
	@GetMapping("/jdbcMemberRegister")
	public String jdbcMemberRegister() {
		return "jdbcView/jdbcMemberRegister";
	}
	// 회원수정
	@GetMapping("/jdbcMemberModify")
	public String jdbcMemberModify() {
		return "jdbcView/jdbcMemberModify";
	}
	// 회원삭제
	@GetMapping("/jdbcMemberDelete")
	public String dbcMemberDelete() {
		return "jdbcView/jdbcMemberDelete";
	}

}
