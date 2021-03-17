package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.MemberVO;

public class MemberRegisterService implements Service {
	
	MemberDAO dao;

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.register((MemberVO) obj);
		
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub

		this.dao = (MemberDAO) dao;
		
	}

}
