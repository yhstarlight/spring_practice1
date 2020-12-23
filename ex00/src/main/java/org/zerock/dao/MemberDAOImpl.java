package org.zerock.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.MemberVO;

@Repository //@Repository 애노테이션은 DAO를 스프링에서 인식하게 한다.
public class MemberDAOImpl implements MemberDAO {

	@Autowired //의존성 주입=>DI
	private SqlSession sqlSession;//mybatis 쿼리문 수행 객체 생성
	
	@Override
	public void insertMember(MemberVO m) {
		this.sqlSession.insert("m_in",m);
		/*
		 *mybatis에서 insert()메서드는 레코드 저장, m_in은 member.xml매퍼 태그에서
		 *설정할 insert아이디명. 이 아이디명은 중복되어서는 안된다. 
		 */
	} //회원 저장
	
}
