package com.webjjang.message.serviec;

import com.webjjang.message.dao.MessageDAO;
import com.webjjang.main.controller.Service;

public class MessageGetMessageCntService implements Service {

	// dao가 필요하다 : 밖에서 생성한 후 넣어 준다.
	// 1. 생성자, 2. setter()
	
	private MessageDAO dao;
	
	
	// 기본 생성자 만들기 : 확인시 필요하다.
	public MessageGetMessageCntService() {
		// TODO Auto-generated constructor stub
		System.out.println("MessageGetMessageCntService.MessageGetMessageCntService() : 생성완료");		// 서버가 시작될 떄 확인 안나올때 Init.init() 확인해보기
		
	}
	
	
	@Override
	public void setDAO(Object dao) {		// object로 받을 수 있다.
		// NullPointerException이 발생되는 이유
		// dao가 Null일 수도 있다. 그러나 list()를 소출 할 ㅜ 없기 때문에
		// setDAO에 의해서 dao를 넣는데 이상이생겼다 --> Init.init()을 확인해라
		
		System.out.println("MessageGetMessageCntService.setDAO().dao : " + dao);	// 서버가 시작될때 확인한다.
		
		this.dao = (MessageDAO)dao;		// Init.inti()에서 호출 할 때 DAO를 확인하기 위해 null이 나오면 NullPointExceptrion을 발생시킨다
		
	}
	
	// URL 요청에 따른 처리
	// 넘어오는 데이터 String id(Accepter) ==> 로그인한 정보(Session에서 가져오기)
	@Override
	public Object service(Object obj) throws Exception {
		
		// 넘어오는 데이터 확인
		System.out.println("MessageGetMessageCntService.obj : " + obj);

		return dao.getMessageCnt((String)obj);	// dao.write()실행 후 결과를 리턴해 준다.
		
	}
	
}
