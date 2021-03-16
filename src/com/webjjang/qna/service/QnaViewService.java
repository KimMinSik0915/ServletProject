package com.webjjang.qna.service;

import com.webjjang.qna.dao.QnaDAO;
import com.webjjang.main.controller.Service;

public class QnaViewService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	private QnaDAO dao;
	
	
	
	public QnaViewService() {
		
		System.out.println("QnaViewService.QnaViewService() : 질문답변보기 객체 생성");
		
	}

	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		
		this.dao = (QnaDAO)dao;
		
		System.out.println("QnaViewService.setDAO [dao] : " + dao);
		
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		// 글 보기와 글 수정 모두 사용
		// 글 보기 일때는 조회수 1 증가(List -> View)
		// 글 수정일 때는 증가하지 않는다.(Update -> View)
		// 데이터는 2개가 넘어오게 된다.([글번호 , 증가여부])
		// obj[0] = 글 번호, obj[1] = 증가 여부
		
		Object[] objs = (Object[]) obj;
		
		Long no = (Long)objs[0];
		
		Long inc = (Long)objs[1];
				
		if (inc == 1) {
		
			dao.increase(no);
			
		}
		
		return dao.view(no); 
		
	}
	
}
