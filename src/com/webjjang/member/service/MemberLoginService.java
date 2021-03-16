package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.LoginVO;

public class MemberLoginService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	private MemberDAO dao;
	
	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		
		this.dao = (MemberDAO)dao;
		
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		
		return dao.login((LoginVO) obj); 
		
	}
	
}
