package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.MemberVO;

public class MemberGradeModifyService implements Service {

	private MemberDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("MemberGradeModifyService.service().obj : " + obj);
		
		System.out.println("MemberGradeModifyService.service().dao : " + dao);	// DAO가 null이면 dao.gradeModify()를 사용하려고 하면 NullPointException이 발생한다.
		
		return dao.gradeModify((MemberVO) obj);
		
	}

	@Override
	public void setDAO(Object dao) {	// Init.init() 초기화 후 실행 - 서버가 시작이 되면서 확인 -> eclipse console에서 확인
		// TODO Auto-generated method stub

		System.out.println("MeberGradeModifyService.setDAO() : " + dao);
		
		this.dao = (MemberDAO) dao;
		
	}

}
