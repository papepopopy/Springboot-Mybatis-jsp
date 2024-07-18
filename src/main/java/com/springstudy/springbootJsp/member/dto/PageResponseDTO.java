package com.springstudy.springbootJsp.member.dto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Getter
@ToString
@Log4j2 
//읽기
public class PageResponseDTO<E> {

//응답
	static Logger logger = LoggerFactory.getLogger(PageResponseDTO.class);
	
	private int page;
	private int size;
	private int total;
	
	//해당 페이지에 대한 레코드 시작 번호, 마지막 번호
	private int recStartNum;
	private int recEndNum;
	
	private int start; //시작 페이지 번호
	private int end; //마지막 페이지 번호
	
	/* 끝과 끝이 해당된다. */
	private boolean prev; //이전 페이지 존재 여부
	private boolean next; //다음 페이지 존재 여부

	private List<E> memberList;
	
	//생성자 :  페이지 초기화 설정 (시작페이지 설정)
	@Builder(builderMethodName = "withAll")
	public PageResponseDTO(PageRequestDTO pageRequestDTO, 
			List<E> memberList, int total) {
		this.page = pageRequestDTO.getPage();
		this.size = pageRequestDTO.getSize();

		this.total = total;
		this.memberList = memberList;
		
		//해당 블럭 페이지 범위 계산 : 1 block : 10 page
		//Math.ceil(숫자) : (자리올림/10.0)*10;
		
		this.end = (int)(Math.ceil(this.page/10.0))*10; //10으로 나눈 후 반올림
		this.start = this.end - 9; //마지막 번호-9
	
		//1024/10 => 102.4 => 103 page
		int last = (int) Math.ceil(total/(double)size);
		
		//마지막 페이지 번호가 전체 끝페이지 번호보다 작으면 
		//마지막 페이지 번호를 블럭의 끝 번호로 지정
		
		//마지막 페이지 번호 > 전체 끝페이지 번호 ? 전체 끝페이지 번호 : 마지막 페이지 번호;
		this.end = end > last ? last : end;
		
		//페이지 블럭이 1 초과 => true(이전 생김)
		this.prev = this.start > 1;
		
		//현재 페이지의 끝 번호까지의 레코드 수가 전체 레코드 총 개수보다 크면 true(생김)
		this.next = total > this.end * this.size;
		
		log.info("현재 페이지 : " + this.page);
		
		log.info("시작 페이지 : " + this.start);
		log.info("끝 페이지 : " + this.end);
		
		log.info("이전 버튼 : " + this.prev);
		log.info("다음 버튼 : " + this.next);
	}
	
	public int pageStartNum() {
		return 0;
	}
	public int pageEndNum() {
		return 0;
	}
}
