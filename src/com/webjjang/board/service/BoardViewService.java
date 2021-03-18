package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.main.controller.Service;

public class BoardViewService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	private BoardDAO dao;
	
	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		
		this.dao = (BoardDAO)dao;
		
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		
		System.out.println("BoardViewService.service [dao] : " + dao);
		
		// 글 보기와 글 수정 모두 사용
		// 글 보기 일때는 조회수 1 증가(List -> View)
		// 글 수정일 때는 증가하지 않는다.(Update -> View)
		// 데이터는 2개가 넘어오게 된다.(Class, (Array[]))
		// obj[0] = 글 번호, obj[1] = 증가 여부
		Object[] objs = (Object[]) obj;
		
		Long no = (Long)objs[0];
		
		Long inc = (Long)objs[1];
				
		if (inc== 1)
			
			dao.increase(no);
			
		return dao.view(no); 
		
	}
	
}
