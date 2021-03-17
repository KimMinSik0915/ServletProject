package com.webjjang.notice.service;

import com.webjjang.main.controller.Service;
import com.webjjang.notice.dao.NoticeDAO;

public class NoticeViewService implements Service {

	private NoticeDAO dao;
	
	public NoticeViewService() {
		
		System.out.println("NoticeViewService 생성완료 ");
		
	}
	
	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		
		System.out.println("NoticeViewService.setDao : " + dao);
		
		this.dao = (NoticeDAO) dao;
		
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("NoticeViewService.service [dao] : " + dao);
		
		System.out.println("NoticeViewService.service() : " + obj);
		
		return dao.view((long) obj); 
		
	}


}
