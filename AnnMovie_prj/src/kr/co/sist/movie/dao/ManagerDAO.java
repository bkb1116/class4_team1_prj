package kr.co.sist.movie.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import kr.co.sist.movie.vo.UserResNotVO;

public class ManagerDAO {
	private static ManagerDAO m_dao;
	
	public ManagerDAO() {
		
	}//ManagerDAO
	
	public static ManagerDAO getInstance() {
		if (m_dao == null) {
			m_dao = new ManagerDAO();
		} // end if
		return m_dao;
	}// getInstance
	
	private Connection getConnection() throws SQLException {
		Connection con = null;
		Properties prop = new Properties();
		try {
			File file = new File("C:/dev/AnnMovie_prj/class4_team1_prj/AnnMovie_prj/src/kr/co/sist/movie/dao/menu_db.properties");
			if (file.exists()) {
				prop.load(new FileInputStream(file));
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String id = prop.getProperty("dboid");
				String pw = prop.getProperty("dbopwd");

				try {// 드라이버로딩
					Class.forName(driver);
					con = DriverManager.getConnection(url, id, pw);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} // catch
			} else {
				JOptionPane.showMessageDialog(null, "경로를 확인하세요");
			} // end else if
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // catch

		return con;
	}// getConnection
	
	
	/**
	 * 좌석배정이 안된 사람 조회후 VO로 반환하는 method
	 * @return UserResNotVO 
	 * @throws SQLException
	 */
	public List<UserResNotVO> select_reserveNot() throws SQLException {
		List<UserResNotVO> list = new ArrayList<UserResNotVO>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String selectReserve = "select ac.user_id,ac.user_name,ar.seat_quan"
					+ " from ANN_CUSTOMER ac, ANN_RESERVE ar"
					+ " where (ac.user_id=ar.user_id)"
					+ " and ar.res_chk='N'";
			pstmt = con.prepareStatement(selectReserve);
			// 4.쿼리문 실행 후 객체 얻기
			rs = pstmt.executeQuery();

			UserResNotVO urnv = null;
			while (rs.next()) {
				urnv = new UserResNotVO();
				urnv.setId(rs.getString("user_id"));
				urnv.setName(rs.getString("user_name"));
				urnv.setSeatQuan(rs.getInt("seat_quan"));
				
				list.add(urnv);
			} // end while

		} finally {
			// 5
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (rs != null) {
				rs.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // finally
		
		return list;
	}// selectMenuList
	  
	
	/**
	 * 좌석 정보 조회하는 method
	 * @return
	 */
	public String[] select_seatInfo(){
		// 사용가능한 좌석은 35개
		String[] result = new String[35];
		
		
		
		return result;
	}
	
	public static void main(String[] args) {
		ManagerDAO md = new ManagerDAO();
		try {
			List<UserResNotVO> list = md.select_reserveNot();
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch
	}// main
	
	

}// class
