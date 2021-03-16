package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.main.controller.Service;

public class ImageWriteService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	private ImageDAO dao;
	
	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		
		this.dao = (ImageDAO)dao;
		
	}
	
	public ImageWriteService() {
		
		System.out.println("ImageWriteService.ImageWriteService() : 생성완료");
		
	}

	@Override
	public Object service(Object obj) throws Exception {
		
		System.out.println("ImageWriteService 실행");
		
		return dao.write((ImageVO) obj);
		
	}
	
}
