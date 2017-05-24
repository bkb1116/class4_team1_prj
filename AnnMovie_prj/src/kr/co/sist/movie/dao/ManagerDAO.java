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

import kr.co.sist.movie.vo.UserResYesVO;

public class ManagerDAO {
	private static ManagerDAO m_dao;

	public ManagerDAO() {
	}// ManagerDAO

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
			File file = new File(
					"C:/dev/AnnMovie/class4_team1_prj/AnnMovie_prj/src/kr/co/sist/movie/dao/menu_db.properties");
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

	public List<UserResYesVO> select_reserveYes() throws SQLException {
		List<UserResYesVO> list = new ArrayList<UserResYesVO>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String select_reserveYes = "select ar.user_id, ac.user_name,ans.seat_num1, ans.seat_num2 "
					+ " from ann_reserve ar, ann_customer ac, ann_seat ans "
					+ " where (ar.user_id=ac.user_id) and (ar.seat_code=ans.seat_code) and res_chk='Y'";
			pstmt = con.prepareStatement(select_reserveYes);
			// 4.쿼리문 실행 후 객체 얻기
			rs = pstmt.executeQuery();

			UserResYesVO uryv = null;
			while (rs.next()) {
				uryv = new UserResYesVO();
				// 생성자에 넣으면 무슨 값이 들어갔는지 알수 없다.
				// setter를 이용해서 어디에 무슨값이 들어갔는지 알수 있다
				// 실수를 줄일 수 있다.
				uryv.setId(rs.getString("user_id"));
				uryv.setName(rs.getString("user_name"));
				uryv.setSeatNum1(rs.getString("seat_num1"));
				uryv.setSeatNum2(rs.getString("seat_num2"));

				list.add(uryv);

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

	public static void main(String[] args) {
		ManagerDAO md = new ManagerDAO();
		try {
			List<UserResYesVO> list = md.select_reserveYes();
			System.out.println(list);
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}// main
}// class
