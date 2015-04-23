package com.webjjang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static String className = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String user = "java", password = "java";

	public static Connection getConnection() {
		Connection con = null;
		try {
			// 1. 드라이버확인
			Class.forName(className);
//			System.out.println("드라이확인끝!");
			// 2. 연결
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("연결 끝");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
