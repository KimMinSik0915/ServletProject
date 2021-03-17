package com.webjjang.notice.service;

import com.webjjang.main.controller.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.vo.NoticeVO;

public class NoticeUpdateService implements Service {

	private NoticeDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.update((NoticeVO) obj);
		
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		
		this.dao = (NoticeDAO) dao;

	}

}
