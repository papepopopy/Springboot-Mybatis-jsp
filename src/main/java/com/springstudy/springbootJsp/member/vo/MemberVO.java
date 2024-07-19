package com.springstudy.springbootJsp.member.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
	// 오라클인 경우: rownum as recnum
	// 회원 리스트 조회시 적용되는 필드 
	private int recnum; 
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
}