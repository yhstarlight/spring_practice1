package org.zerock.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.dao.AdminGongjiDAO;
import org.zerock.vo.GongjiVO;

@Service
public class AdminGongjiServiceImpl implements AdminGongjiService {

	@Inject
	private AdminGongjiDAO adminGongiDao;

	@Override
	public void insertG(GongjiVO g) {
		adminGongiDao.insertGongji(g);
	}
}
