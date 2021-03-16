package com.webjjang.notice.controller;

import javax.servlet.http.HttpServletRequest;

public class NoticeController {

	public String execute(HttpServletRequest request) throws Exception {
		
		System.out.println("NoticeController.execute() : 실행");
		
		return "notice/list";
		
	}
	
}
