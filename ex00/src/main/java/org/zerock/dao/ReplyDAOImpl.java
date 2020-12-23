package org.zerock.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession sqlSession;//mybatist리쿼문 수행객체 보상

	@Override
	public void addReply(ReplyVO vo) {
		this.sqlSession.insert("reply_in",vo);
		//mybatis에서 insert()메서드는 레코드 저장. reply_in은 insert 아이디명
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.sqlSession.selectList("reply_list",bno);
		//mybatis에서 selectList()메서드는 하나 이상의 레코드를 검색해서 컬렉션 List로 반환
		//reply_list는 select 유일 아이디명.
	} //댓글 목록

	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("reply_edit",vo);
		//mybatis에서 update()메서드는 레코드 수정.reply_edit update아이디명
	}

	@Override
	public void delReply(int rno) {
		this.sqlSession.delete("reply_del",rno);
	}

	@Override
	public int getBno(int rno) {
		return this.sqlSession.selectOne("reply_bno", rno);
		//mybatis에서 selectOne은 한개의 레코드 반환
		//reply_bno는 reply.xml에서 설정한 유일한 select아이디명
		//댓글 번호에 해당하는 게시물 번호값 알아내기
	}
}
