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
	
	@GetMapping("/jdbcMembers")
	public String jdbcMemberList(Model model) {
		
		model.addAttribute("jdbcMembers", memberDAO.jdbcMemberList());
		
		System.out.println("list =>"+memberDAO.jdbcMemberList());
		return "jdbcView/jdbcMembers";
	}

}
