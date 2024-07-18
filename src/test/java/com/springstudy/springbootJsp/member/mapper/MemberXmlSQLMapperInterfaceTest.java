package com.springstudy.springbootJsp.member.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.springstudy.springbootJsp.member.vo.MemberVO;

import lombok.extern.log4j.Log4j2;

@Commit //테스트 서버이기에 롤백이 되어지지 않게 커밋 추가
@SpringBootTest
@Log4j2
class MemberXmlSQLMapperInterfaceTest {

	@Autowired
	private MemberXmlSQLMapperInterface memberMapperXml;
	
	@RepeatedTest(value=1024, name= "{DisplayName}{currentRepetition}/{totalRepetition}") // (반복문 사용 시)
	@Test
	@DisplayName("회원등록 테스트")
	@Transactional
	void testInsertMember(RepetitionInfo repetitionInfo) {
			int i = repetitionInfo.getCurrentRepetition();
			try {
				MemberVO vo = MemberVO.builder()
						.id("m"+i).pwd("1234").email("test"+i+"@gmail.com")
						.name("홍길동"+i)
						.build();
				
					memberMapperXml.insertMember(vo);
			} catch (Exception e) {}
	}
					
//	void testInsertMember() {
//				for(int i=2; i<10; i++) {
//					MemberVO vo = MemberVO.builder()
//						.id("m"+i).pwd("1234").email("test"+i+"@gmail.com")
//						.name("홍길동"+i)
//						.build();
//				
//					memberMapperXml.insertMember(vo);
//				}
				
//			} catch (Exception e) {}
//	}

}
