package com.webjjang.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface Controller {

	// String return : JSP나 SendRedirect의 정보
	public String execute(HttpServletRequest request) throws Exception;
	
}
