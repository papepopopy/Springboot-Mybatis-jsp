package com.springstudy.springbootJsp.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.springstudy.springbootJsp.member.vo.MemberVO;
import com.springstudy.springbootJsp.sql.MemberSQL;

@Mapper
public interface MemberJavaSQLMapperInterface {
	
	// 동적SQL => Groovy class일 경우 적용됨.
	// mapping: SQL문 실행할 함수와 SQL문 작성함수 1:1맵핑
	
	// 1-1. 회원목록 List : 어노테이션 적용할 경우
	@Select("select * from t_member order by joindate desc")
	public List<MemberVO> getMemberList();
	
	// 1-2. 회원 조회
	@Select("select * from t_member where id=#{id}")
	public MemberVO getfindIdMemberList(@Param("id") String id);
	
	// -------------------------------------------------------- //
	// SQL Class 맵핑:  SQL 어노테이션에 사용되는 SQL문 모듈화
	// -------------------------------------------------------- //
	
	// 1-3.
	@Select(MemberSQL.MEMBER_LIST)
	public List<MemberVO> getFinalMemberList();
	// 1-4.
	@SelectProvider(type = MemberSQL.class, method = "getMemberList2")
	public List<MemberVO> getMemberList2();
	// 1-5
	@SelectProvider(type = MemberSQL.class, method = "getMemberList3")
	public List<MemberVO> getMemberList3();
	
	// 회원 등록
	// Insert: Auto Increment설정으로 PK, @Options어노테이션
	@SelectProvider(type = MemberSQL.class, method = "insertMember")
	//@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertMember(MemberVO vo );
	
	// 회원수정
	@UpdateProvider(type = MemberSQL.class, method = "updateMember")
	public int updateMember(MemberVO vo);
	// 회원삭제
	@DeleteProvider(type = MemberSQL.class, method = "deleteMember")
	public int deleteMember(@Param("id")String id);
	// 회원조회
	@SelectProvider(type = MemberSQL.class, method = "viewMember")
	public MemberVO viewMember(@Param("id")String id);
	// 중복아이디체크
	@SelectProvider(type = MemberSQL.class, method = "idCheck")
	public String idCheck(@Param("id") String id);
	
	//동적 SQL
	//조건 검색
	@SelectProvider(type = MemberSQL.class, method="getMemberListIf")
	public List<MemberVO> getMemberListIf(@Param("email")String email, @Param("name")String name);
	

}


/*
맵핑할 DB 컬럼 명과 자바 필드가 다른 경우
@Results 어노테이션을 지정하고 그 안에 복수개의 컬럼 맵핑관계를 지정
mybatis.configuration.map-underscore-to-camel-case=true
 
 
ex)

@Select("SELECT * FROM MEMBER_INFO WHERE id=#{id}")
@Results({
    @Result(property="r_id", column="id"),
  //@Result(property="자바 객체 property 명", column="db 테이블 컬럼 명")
})
Optional<Member> getById(@Param("id") String id);
  
 */












