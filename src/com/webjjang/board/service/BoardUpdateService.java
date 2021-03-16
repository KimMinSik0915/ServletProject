package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Service;

public class BoardUpdateService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	private BoardDAO dao;
	
	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		
		this.dao = (BoardDAO)dao;
		
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		
		return dao.update((BoardVO) obj);
		
	}
	
}
