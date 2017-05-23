package kr.co.sist.movie.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class AnnDAO {
	private static AnnDAO a_dao;
	//기준 수정 마스터변경
	public AnnDAO() {
	}//AnnDAO

	public static AnnDAO getInstance(){
		
		if(a_dao == null){
			a_dao = new AnnDAO();
		}//end if
		
		return a_dao;
	}//getInstance
	
	private Connection getConnection() throws SQLException {
		Connection con = null;

		Properties prop = new Properties();
 
		try {
			//경로는 설정해야 맛이지!!! 마스터변경
			//어떻게쓰는거야??
			File file = new File("경로설정ㅋㅋㅋㅋㅋ"); 

			if (file.exists()) {
				prop.load(new FileInputStream(file)); 
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String id = prop.getProperty("dboid");
				String pass = prop.getProperty("dbopwd");
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//end catch

		return con;
	}// getConnection	
	
	
	
}//class
