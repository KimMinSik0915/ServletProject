package com.webjjang.test.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// 자바에서 File을 읽어 와서 Byte 단위의 데이터들을 차례로 지정된 파일에 저장하는 프로그램
public class FileOutputStreamExample {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("FileOutputExample.main()");
		
		// Driver:/Folder/Folder/.../File : 구분의 의미로 "/" 한개 사용
		
		// Driver:\폴더  : \n : 줄바꿈의 특수문자 => \뒤에 오는 글자 한개와 특수문자로 인식한다.
		
		// 그래서 \\를 사용해서 \로 인식 시킨다.
		
		// 읽어 오는 파일의 정보 : 절대 위치가 필요 = driver:/Folder.../File
		//String orginalFileNmae = "c:/Temp/Name.txt";
		String orginalFileNmae = "c:/Temp/icon.gif";
		
		// 저장할 파일의 정보 : 프로그램을 실행하기 전에는 존재하지 않는다.
		// 저장을 하려면 파일을 없도도 괜찮지만 폴더는 존재해야 한다.
		//String targetFileNmae = "c:/Temp/out/Name.txt";
		String targetFileNmae = "c:/Temp/out/i.gif";
		
		
		
		// 만약에 저장하려는 파일이 존재하면 파일이 존재한 다고 출력하고 빠져나온다.
		// 저장하려는 파일명으로 파일 객체를 만든다.
		File outFile = new File(targetFileNmae);
		
		// 존재하면(중복) 중복됨을 출력하고 프로그램을 종료한다.
		if(outFile.exists()) {
			
			System.out.println("파일이 중복이 됩니다. 파일명 : " + outFile.getName());
			
			return;
			
		}
		
		
		
		// 파일 읽기 객체와 저장하는 객체를 생성해 쥰다
		FileInputStream fis = new FileInputStream(orginalFileNmae);
		
		FileOutputStream fos = new FileOutputStream(targetFileNmae);
		
		int readByteNo = 0; // byte(char) -> 숫자로 운영 : int에 넣을 수 있다. 아래에 ReadBytes에 읽이온 개수를  저장하는는 변수
		
		// 한번에 읽어오는 문자의 수 : 100개
		byte[] readBytes = new byte[100];
		
		// 데이터를 읽어와서 저장하는 반복문 : 데이터가 있는만큼 처리하도록 한다.
		// 1byte씩 읽기 : read(),
		// read(byte[]) : buffer(byte[100]) = 버퍼의 단위만큼 한꺼번에 읽는다. 처리는 한개씩
		// readByteNo : 읽어온 데이터의 길이
		// 문자열인 경우 모두 + 숫자이거나 0이 나와야 한다
		// 읽었을 떄 =1 : 모두 읽어서 읽을 것이 더이상 없다.
		while((readByteNo = fis.read(readBytes)) != -1) {
			
			// 불러온 데이터를 저장한다.
			fos.write(readBytes, 0, readByteNo);
			
		}
		
		// 버퍼에 남은 데이터를 파일로 보내서 저장하게 한다.
		fos.flush();
		
		// 자원 반환
		fos.close();
		fis.close();
		
		System.out.println("복사가 잘 되었습니다.");
		
	}

}
