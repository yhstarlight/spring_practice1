package org.zerock.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MyBatisTest {
	@Autowired //자동 의존성 주입
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void testFactory() {
		System.out.println(sqlFactory);
	}
	
	@Test
	public void testSession() throws Exception{
		try(SqlSession sqlSession=sqlFactory.openSession()){
			//sqlSession이 mybatis 쿼리문 수행 객체이다.
			System.out.println(sqlSession);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
