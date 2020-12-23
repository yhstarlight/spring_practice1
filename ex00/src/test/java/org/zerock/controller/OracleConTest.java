package org.zerock.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConTest {
	
	private static final String DRIVER = 
			"oracle.jdbc.OracleDriver";
	//oracle.jdbc는 패키지명, oracleDriver는 오라클 jdbc 드라이버 클래스명이다.
	
	private static final String URL =
			"jdbc:oracle:thin:@127.0.0.1:1521:xe";
	//오라클 접속 주소, 1521은 포트번호, xe는 디비명
	
	private static final String USER = "day"; //오라클 접속 사용자
	private static final String PW = "day";//사용자 비번
	
	@Test //junit test => 연습
	public void testCon() throws Exception{
		Class.forName(DRIVER);//jdbc드라이버 클래스 로드
		try(Connection con =
				DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con); //디비 연결 객체 con주소가 이클립스 콘솔모드에 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 jdk1.7이후 부터는 AutoCloseable 인터페이스를 구현상속받은 자손은 finally문에서 명시적으로
		 close()를 하지 않아도 자동으로 닫힌다.
		 */
	}
}
