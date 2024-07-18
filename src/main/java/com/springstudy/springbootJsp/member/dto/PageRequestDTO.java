package com.springstudy.springbootJsp.member.dto;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.springstudy.springbootJsp.member.controller.MemberJavaSQLController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//페이징 처리
@NoArgsConstructor
@AllArgsConstructor
@Data
@Log4j2
@Builder

public class PageRequestDTO {

	static Logger logger = LoggerFactory.getLogger(PageRequestDTO.class);
	
	private int page = 1; //시작 페이지 번호
	private int size = 10; //페이지당 레코드 수
	
	private String link;
	
	// 검색 필터링 조건 처리
	private String[] types; //카테고리
	private String keyword;
	private boolean finished;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate from; //시작
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate to; //끝
	
	//페이지 계산
	public int getSkip() {
		//1 page : 1~10
		//2 page : 11~20
		//		...
		//10 page : 91~100
		
		//해당 페이지 시작 레코드 번호 계산 : 
		//1page : (1-1)*10 => 0
		return(page -1)*10;
	}
	
	public String getlink() {
		//검색 항목 처리
		if(link == null) {
			StringBuilder builder = new StringBuilder();
			
			builder.append("page=" + this.page); //페이지 번호
			builder.append("&size=" + this.size); //페이지당 레코드 수
			
			if(types != null && types.length>0) {
				for(int i=0; i<types.length; i++) {
					log.info("types["+i+"]="+types[i]);
					
					builder.append("&types="+types[i]);
				}
			}
			
			//검색 키워드처리
			if (keyword != null) {
				try {
					builder.append("&keyword="+URLEncoder.encode(keyword, "UTF-8").toString());
				} catch (UnsupportedEncodingException e) {log.info(e.getMessage());}
			}
			
			//날짜 처리
			if(from != null) builder.append("&from=" + from.toString());
			if(to != null) builder.append("&to=" + to.toString());
			
			link = builder.toString();
		}
		return link;
		
	}
	
	//조건 검색
	public boolean checkType(String[] types) {
		logger.info("=> Logger, type : "+types);
		log.info("=> Log4j2 type : "+types);
		
		if(types == null || types.length == 0) {
			return false;
		}
		//최소 한 개의 요소가 주어진 조건에 만족하면 true
		return Arrays.stream(types).anyMatch(types::equals);
	}
}