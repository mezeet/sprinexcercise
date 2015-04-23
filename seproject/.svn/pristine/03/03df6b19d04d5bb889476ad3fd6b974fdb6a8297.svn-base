package com.webjjang.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	// 파일이 존재하는지 알아 내는 메소드
	public boolean isThere(String fileName){
		// String을 File 객체로 변환 후 존재 유무확인
		return convertFile(fileName).exists();
	}
	// String을 File로 변환해 주는 메소드
	public File convertFile(String fileName){
		return new File(fileName);
	}
	// 파일이 존재하지 않는 경우 파일을 생성한다.
	public void makeFile(String fileName){
		// 파일로 생성
		try {
			convertFile(fileName).createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 중복된 파일명인 경우 파일명 뒤에 cnt를 붙여
	// 처리하는 메소드
	public String duplicateProcess(String fileName){
		// 중복처리을 위한 카운드 선언
		int cnt = 1;
		String changeFileName = null;
		// 삽입해야할 위치를 미리 구한다.
		int insertPoint = fileName.lastIndexOf(".");
		// 중복이 되지 않는 파일이름이 나올때까지 무한 반복
		while(true){
			// 새로운 파일명 만들기
			// 문자열 처리를 쉽게 해주는 객체로 생성
			StringBuilder sb = new StringBuilder(fileName);
			changeFileName
			= sb.insert(insertPoint, cnt++).toString();
			// 중복이 되지 않은 파일명이 나오면 리턴한다.
			if(!isThere(changeFileName)){
				return changeFileName;
			}
		}
//		return null;
	}
	
	// 파일 삭제하는 메소드
	public void deleteFile(String fileName){
		// String을 File로 변환 후 삭제
		convertFile(fileName).delete();
	}
	// 파일명을 String으로 받아서 중복되지 않으면
	// 파일명을 그대로, 중복이 되며 처리해서 return한다.
	public String checkDupulicate(String fileName){
		System.out.println("checkDupulicate()");
		// 만약에 저장하려는 파일명이 폴더에 존재하지 않으면
		// 파일명을 그대로 리턴한다.
		if(!isThere(fileName)) return fileName;
		// 파일이 존재하면 처리 : 메소드 호출
		return duplicateProcess(fileName);
	}
}
