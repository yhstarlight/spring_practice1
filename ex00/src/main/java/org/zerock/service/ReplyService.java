package org.zerock.service;

import java.util.List;

import org.zerock.vo.ReplyVO;

public interface ReplyService {

	void addReply(ReplyVO vo);//public abstract가 생략된 추상 메서드

	List<ReplyVO> listReply(int bno);

	void updateReply(ReplyVO vo);

	void remove(int rno);

}
