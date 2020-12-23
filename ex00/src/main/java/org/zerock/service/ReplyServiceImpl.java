package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dao.BoardDAO;
import org.zerock.dao.ReplyDAO;
import org.zerock.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	private ReplyDAO replyDao; // 의존성 주입

	@Autowired
	private BoardDAO boardDao;

	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		// 이클립스에서 해당메서드에 트랜잭션을 적용하며면 스트링의 aop의
		// arround입에 해당하는 위아래 화살표 메서드 왼쪽 첫머리 부분에 만들어진다.
		// arround 타입은 스프링의 aop의 advice 적용 범위를 나타낸다.
		
		/*
		 * 스피링 aop 용어정리
		 * 1. advice : 실제 기능을 구현한 객체
		 * 2. arround 타입 : target메서드 호출 전 이후 모두 가장 광범위하게 사용됨.
		 * 3. target : 대상 메서드를 가지는 객체
		 */
		this.replyDao.addReply(vo);
		this.boardDao.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.replyDao.listReply(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
	}

	@Transactional
	@Override
	public void remove(int rno) {
		int bno = this.replyDao.getBno(rno); //댓글 번호로 게시글 번호값을 구함
		this.replyDao.delReply(rno);//댓글 삭제
		this.boardDao.updateReplyCnt(bno, -1);
		//댓글이 삭제되면 댓글 개수의 1 감소
	}
}
