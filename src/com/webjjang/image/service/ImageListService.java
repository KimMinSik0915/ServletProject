package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.main.controller.Service;
import com.webjjang.util.PageObject;

public class ImageListService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	private ImageDAO dao;
	
	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		
		this.dao = (ImageDAO)dao;
		
	}
	
	public ImageListService() {
		
		System.out.println("ImageListService.ImageWriteService() : 생성완료");
		
	}

	// 넘어오는 data - PageObject ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		
		System.out.println("ImageListeService.obj : " + obj);
		
		// pageObject의 전체 데이터 갯수 세팅하기 : 화면에 보여질 페이지 정보가 계산된다.
		((PageObject)obj).setTotalRow(dao.getTotalRow());
		
		
		return dao.list((PageObject) obj);
		
	}
	
}
