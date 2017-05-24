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

	}// ManagerDAO

	/**
	 * 싱글턴 패턴을 사용한 객체 얻기
	 * @return managerDAO
	 */
	public static ManagerDAO getInstance() {
		if (m_dao == null) {
			m_dao = new ManagerDAO();
		} // end if
		return m_dao;
	}// getInstance

	/**
	 * 드라이버 로딩 및 커넥션 얻기
	 * @return connection
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection con = null;
		Properties prop = new Properties();
		try {
			File file = new File(
					"C:/dev/AnnMovie_prj/class4_team1_prj/AnnMovie_prj/src/kr/co/sist/movie/dao/AnnMovie_db.properties");
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
	 * 예약된 좌석을 모두 반환하는 method
	 * @param res_code( 예매 번호 )
	 * @return total seat
	 * @throws SQLException
	 */
	public String select_seatNum(String res_code) throws SQLException {
		String result;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String selectReserve = "select seat_num" 
					+ " from ANN_SEAT" 
					+ " where res_code='" + res_code + "'"
					+ " order by seat_num";
			pstmt = con.prepareStatement(selectReserve);
			// 4.쿼리문 실행 후 객체 얻기
			rs = pstmt.executeQuery();

			StringBuilder sb = new StringBuilder();

			while (rs.next()) {
				sb.append(rs.getInt("seat_num")).append(" ");
			} // end while

			result = sb.toString();

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

		return result;
	}// select_seatNum

	/**
	 * 좌석 정보 조회하는 method
	 * 좌석이 등록 안 된 경우 0으로 조회됨
	 * @return seat_info
	 * @throws SQLException
	 */
	public int[] select_seatInfo() throws SQLException {
		// 사용가능한 좌석은 35개
		int[] result = new int[35];
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String selectReserve = "select seat_num from ANN_SEAT";
			pstmt = con.prepareStatement(selectReserve);
			// 4.쿼리문 실행 후 객체 얻기
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 배열에 담기 
				result[rs.getInt("seat_num")] = rs.getInt("seat_num");
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

		return result;
	}// select_seatInfo

	////////////////////////////////////////////////////////
	// 						단위 테스트 						 //
	///////////////////////////////////////////////////////
	public static void main(String[] args) {
		ManagerDAO md = new ManagerDAO();
		try {
			// select_reserveNot method
//			 List<UserResNotVO> list = md.select_reserveNot();
//			 System.out.println(list);

			// select_seatNum method
//			String seat_num = md.select_seatNum("SEAT_00003");
//			System.out.println(seat_num);

			//select_seatInfo method
			int[] arr= md.select_seatInfo();
			for (int i = 0; i < arr.length; i++) {
				if(arr[i]!=0){
					System.out.println(arr[i]+" ");
				}
			}// for
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// main

}// class
