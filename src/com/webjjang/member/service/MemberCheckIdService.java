package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;

public class MemberCheckIdService implements Service {

	private MemberDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {		// 넘어오는 Data는 ID || type = String
		// TODO Auto-generated method stub
		
		System.out.println("MemberViewService.service [obs] : " + obj);
		
		return dao.checkId((String) obj);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub

		System.out.println("MemberViewService.setDAo : " + dao);
		
		this.dao = (MemberDAO) dao;
		
	}

}
