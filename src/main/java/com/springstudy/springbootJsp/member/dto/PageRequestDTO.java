package com.springstudy.springbootJsp.member.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


//페이징 처리
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class PageRequestDTO {

	static Logger logger = LoggerFactory.getLogger(PageRequestDTO.class);
	
	// 시작 페이지 번호
	private int page = 1;
	// 페이지당 레코드 수
	private int size = 10;
	
	private String link;
	
	// -------------------------------- //
	// Oracle일 경우 
	// -------------------------------- //
	// 해당페이지에 대한 레코드 시작번호, 마지막 번호
	// 1page : 1~10 레코드(row)
	// 2page : 11~20 레코드(row)
	// 100page : 101~110 레코드(row)
	private int recStartNum;
	private int recEndNum;
	// -------------------------------- //
	
	
	// 검색 필터링 조건 처리
	private String[] types; // 이름으로 검색, 이메일 검색,....
	private String keyword;
	private boolean finished;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate from;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate to;
	
	// -------------------------------- //
	// Oracle 일 경우 
	// -------------------------------- //
	// 해당 페이지에 대한 레코드 번호의 범위 계산
	public void pageStartToEndNumber() {
		this.recStartNum = getSkip()+1;
		this.recEndNum = getSkip()+10;
		// -------------------------------- //
	}
	
	
	
	// 해당 페이지대한 시작 레코드번호 마지막 레코드번호 계산
	public int getSkip() {
		// 1 page : 1~10
		// 2 page : 11~20
		// 10page : 91~100
		
		// 해당페이지의 시작 레코드 번호를 계산
		// 1page : (1-1)*10=>0+1=>1
		// 2page : (2-1)*10=>10+1=>11
		return (page-1)*10;
	}
	
	public String getLink() {
		
		// 검색 항목 처리
		if (link == null) {
			StringBuilder builder = new StringBuilder();
			
			builder.append("page="+this.page);		// 요청페이지 번호
			builder.append("&size="+this.size);		// 1페이지에 보여줄 레코드 수
			
			if (types != null && types.length > 0) {
				for (int i=0; i<types.length; i++) {
					log.info("types["+i+"]="+types[i]);
					
					builder.append("&types="+types[i]);
				}
			}
			
			// 검색 키워드 처리
			if (keyword != null) {
				try {
					builder.append("&keyword="+URLEncoder.encode(keyword, "UTF-8").toString());
				} catch (UnsupportedEncodingException e) {log.info(e.getMessage());}
			}
			
			// page=1&size=10&types=name&keyword=인코딩된 단어
			
			// 날짜 처리
			if (from != null) builder.append("&from="+from.toString());
			if (to != null) builder.append("&to="+to.toString());
			
			// page=1&size=10&types=name&keyword=인코딩된 단어&from=...&to=...
			link = builder.toString();
		}
		return link;	// page=1&size=10&types=name&keyword=인코딩된 단어&from=...&to=...
	}
	
	
	// 조건 검색
	public boolean checkType(String[] type) {
		logger.info("=>Logger, type: "+ type);
		log.info("=> Log4j2 type: "+ type);
		
		if ( types == null || types.length == 0) {
			return false;
		}
		
		//내부 동작
		//boolean flag =false;
		// 최소 한 개의 요소가 주어진 조건에 만족하면 true
		return Arrays.stream(types).anyMatch(type::equals);
//		for (int i=0; i<types.length; i++) {
//			if(types[i].equals("n")) {
//				return true;
//			}
//		}
//		return flag
	}
}