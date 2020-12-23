package org.zerock.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.GongjiVO;

@Repository
public class AdminGongjiDAOImpl implements AdminGongjiDAO {

	@Autowired
	private SqlSession sqlSession; //bybatis 쿼리문 수행 객체를 생성

	@Override
	public void insertGongji(GongjiVO g) {
		this.sqlSession.insert("ag_in",g);
		//bybatis에서 insert()메서드는 레코드를 저장. ag_in은 insert 유일 아이디명
	}
	
}
