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
