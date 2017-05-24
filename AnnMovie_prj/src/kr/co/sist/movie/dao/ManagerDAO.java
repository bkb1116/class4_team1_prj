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

import kr.co.sist.movie.vo.UserSearchVO;
//import kr.co.sist.movie.vo.*;


public class ManagerDAO {
	private static ManagerDAO m_dao;
	
	public ManagerDAO() {
		
	}//managerDao
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
			File file = new File("C:/dev/AnnMovie/class4_team1_prj/AnnMovie_prj/src/kr/co/sist/movie/dao/Ann_db.properties");
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
	 * 입력된 사용자의 목록을 조회<br>
	 * 이름,전화번호를 조회하여 UserSeachVO에 저장하고 
	 * List에 추가하여 반환하는 일을 한다.
	 * @return : List<UserSeachVO>
	 * @throws SQLException
	 */
	public List<UserSearchVO> select_searchUser() throws SQLException {
		List<UserSearchVO> list = new ArrayList<UserSearchVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String userSearch ="select user_name,user_tell from ann_customer";
			pstmt = con.prepareStatement(userSearch);
			// 4.쿼리문 실행 후 객체 얻기
			rs = pstmt.executeQuery();
			UserSearchVO usv = null;
			while (rs.next()) {
				// 셋터로 받아오면 실수를 많이 줄일 수 있음.
				// 이렇게 쓰는게 가독성이 좋음, 값의 꼬임을 많이 줄일 수 있음.
				usv = new UserSearchVO();
				usv.setName(rs.getString("user_name"));
				usv.setPhone(rs.getString("user_tell"));
				usv.setEmail(rs.getString("user_email"));
				list.add(usv);
			} // while
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
	///////////// 단위테스트 메소드 시작/////////////////
	public static void main(String[] args) {
		try {
			ManagerDAO mda = ManagerDAO.getInstance();
			List<UserSearchVO> list = mda.select_searchUser();
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// main
		///////////// 단위테스트 메소드 끝 /////////////////
}//class
