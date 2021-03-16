package com.webjjang.message.serviec;

import com.webjjang.main.controller.Service;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.util.PageObject;

public class MessageListService implements Service{

	private MessageDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		long totalRow = dao.getTotalRow();
		
		PageObject pageObject = (PageObject) obj;
		
		pageObject.setTotalRow(totalRow);
		
		return dao.list(pageObject);
		
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		
		this.dao = (MessageDAO) dao;
		
	}

}
