package com.webjjang.member.controller;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Controller;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class MemberController implements Controller {

	private final String MODULE = "member";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		PageObject pageObject = PageObject.getInstance(request);
		
		request.setAttribute("pageObject", pageObject);
		
		switch (AuthorityFilter.url) {
		case "/" + MODULE + "/loginForm.do":
			
			jspInfo = MODULE + "/loginForm";
			
			break;

		default:
			break;
		}
		
		
		return jspInfo;
	}

}
