package com.springstudy.springbootJsp.member.dto;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	private int recnum;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	//mybatis : localdate (형식 변환)
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate joinLocaleDate;
	
	private String uuid;
	public void toLocaleDate() {
		this.joinLocaleDate = this.joinDate.toLocalDate();
	}

}
