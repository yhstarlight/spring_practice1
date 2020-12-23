package org.zerock.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) {
		Map<String, Object> pm = new HashMap<>(); 
		//문자열 키, Object 타입 값만 저장하는 컬렉션 제네릭 자료구조이고 업캐스팅하면 서 pm을 생성
		pm.put("sender", sender);//sender 키 이름에 보낸 사람을 저장
		pm.put("point",point);
		//해당 값을 가져옴
		this.sqlSession.update("pointUp",pm);
	} //메시지를 보낸 사람에게 포인트 점수 10점 업
}
