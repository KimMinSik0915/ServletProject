package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.main.controller.Service;

import com.webjjang.util.PageObject;

public class BoardListService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	BoardDAO dao;
	
	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		// NullPointerException이 발생되는 이유
		// dao가 Null일 수도 있다. 그러나 list()를 소출 할 ㅜ 없기 때문에
		// setDAO에 의해서 dao를 넣는데 이상이생겼다 --> Init.init()을 확인해라
		
		this.dao = (BoardDAO)dao;
		
	}
	
	// URL 요청에 따른 처리
	// 넘어오는 데이터가 PageObject ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		
		// 넘어오는 데이터 확인
		
		System.out.println("BoardListService.obj : " + obj);
		
		int totalRow = (int) dao.getTotalRow();
		
		PageObject pageObject = (PageObject) obj;
		
		pageObject.setTotalRow(totalRow);
		
		// 전체 페이지 세팅후 page객체 출력
		System.out.println("BoardListService.pageObject : " + pageObject);
		
		return dao.list(pageObject);
		
	}
	
}
