package com.springstudy.springbootJsp.testcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	
	// 1. jsp view 연결하기
	@RequestMapping("/jsptest")
	public String test1() {
							// =>  '/WEB-INF/views/' + return "문자열"+".jsp"  
		return "indexjsp"; 	// =>  '/WEB-INF/views/indexjsp.jsp' */
	}
	@RequestMapping("/jsptest2")
	public String test2() {
		return "testsub/test";
	}
	
	// 2. 정적 페이지(html)
	@RequestMapping("/html")
	public String hello() {
		return "redirect:/html/hello.html";
	}
	@RequestMapping("/images")
	public String image() {
		return "redirect:images/life.jpg";
	}
	
	// 3.RESTful API 적용: xml이나 json데이를 반환
	@RequestMapping("/")
	public @ResponseBody  String root() {
		return "Model and View";
	}
	
	// 4. Model and View
	@RequestMapping("/model")
	public String dataModel(Model model) {
		
		// Model객체를 이용해사 view로 Data전달
		model.addAttribute("name","홍길동");
		model.addAttribute("age","10");
		model.addAttribute("address","부산시");
		
		return "testsub/data";
	}
	@RequestMapping("/model2")
	public ModelAndView dataViewModel() {
		// 데이터와 뷰를 동시에 설정
		ModelAndView mv = new ModelAndView();
		
		List<String> names = new ArrayList<String>();
		
		names.add("홍길동");
		names.add("동순이");
		names.add("길순이");
		
		mv.addObject("names",names);
		mv.addObject("address","부산시");
		mv.setViewName("testsub/modelAndView");
		
		return mv;
	}
	

}
