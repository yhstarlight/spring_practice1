package org.zerock.dao;

import java.util.List;

import org.zerock.vo.BoardVO;

public interface BoardDAO {

	void insertBoard(BoardVO b); //추상 메서드 => {}가 없고 실행문장이 없고, 호출 불가
	//반드시 자손 클래스에서 오버라이딩.

	int getCount();

	List<BoardVO> getList(BoardVO b);

	void updateHit(int bno);

	BoardVO getCont(int bno);

	void editBoard(BoardVO eb);

	void delBoard(int bno);

	void updateReplyCnt(int bno, int amount);

}
