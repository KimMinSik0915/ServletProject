package com.webjjang.util.file;

import java.io.File;

public class FileUtil {

	// 파일이 존재하는 지 확인하는 method
	public static boolean exist(File file) throws Exception {
		
		return file.exists();
		
	}
	
	// 파일이 존재하는지 확인하는 method : 오버로드
	public static boolean exist(String fileName) throws Exception {
		
		return toFile(fileName).exists();
		
	}
	
	// 문자열을 파일 객체로 만들어 주는  method
	public static File toFile(String fileName) throws Exception {
		
		return new File(fileName);
		
	}
	
	// 파일 지우기
	public static boolean delete(File file) throws Exception {
		
		return file.delete();
		
	}

	// 파일의 정보를 문자열로 넣어주면 지워주는 method
	// 파일명은 realPath정보의 파일명을 넘겨주어야 한다.
	public static boolean remove(String fileName) throws Exception {
		
		File file = toFile(fileName);	// 1. 문자열을 파일 객체로 만든다
		
		if(!exist(file)) {				// 2. 파일이 존재하는지 확인한다. : 파일이 존재하지 않는 경우 예외발생
		
			throw new Exception("삭제하려는 파일이 존재하지 않습니다. ");
			
		}
		
		if(!delete(file)) {		// 3. 파일을 삭제한다 : 파일이 존재하는 경우
			
			throw new Exception("삭제하려는 파일이 삭제되지 않았습니다. ");
			
		}
		
		System.out.println("FileUtil.remove() : 파일이 삭제되었습니다.");
		
		return true;		// 4. 결과를 리턴한다.
		
	}
	
}
