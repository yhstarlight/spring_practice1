package org.zerock.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dao.MessageDAO;
import org.zerock.dao.PointDAO;
import org.zerock.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDao;
	
	@Inject
	private PointDAO pointDao;

	//AOP를 통한 트랜잭션 적용 대상
	@Transactional
	@Override
	public void addMessage(MessageVO vo) {
		this.messageDao.create(vo);
		this.pointDao.updatePoint(vo.getSender(), 10);
		//메세지를 보낸 사람에게 포인터 점수 10
	}
}
