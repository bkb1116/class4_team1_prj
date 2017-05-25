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

import kr.co.sist.movie.vo.MovieRegisVO;
import kr.co.sist.movie.vo.SeatRegisVO;
import kr.co.sist.movie.vo.UserResNotVO;
import kr.co.sist.movie.vo.UserResYesVO;
import kr.co.sist.movie.vo.UserSearchVO;
import kr.co.sist.movie.vo.UserTotalVO;

public class ManagerDAO {
	private static ManagerDAO m_dao;

	public ManagerDAO() {

	}// ManagerDAO

	/**
	 * 싱글턴 패턴을 사용한 객체 얻기
	 * 
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
	 * 
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
	 * 예약후 좌석배정을 받은 사람목록
	 * 
	 * @return
	 * @throws SQLException
	 */
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
	}// select_reserveYes

	/**
	 * 좌석배정이 안된 사람 조회후 VO로 반환하는 method
	 * 
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
					+ " from ANN_CUSTOMER ac, ANN_RESERVE ar" + " where (ac.user_id=ar.user_id)"
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
	 * 
	 * @param res_code(
	 *            예매 번호 )
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
			String selectReserve = "select seat_num" + " from ANN_SEAT" + " where res_code='" + res_code + "'"
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
	 * 좌석 정보 조회하는 method 좌석이 등록 안 된 경우 0으로 조회됨
	 * 
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

	/**
	 * res_code를 반환하는 method
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public String select_resCode(String id) throws SQLException {
		String result;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String selectReserve = "select res_code from ann_reserve" + " where user_id='" + id + "'";
			pstmt = con.prepareStatement(selectReserve);
			// 4.쿼리문 실행 후 객체 얻기
			rs = pstmt.executeQuery();

			// 값 호출
			rs.next();

			// 값 할당
			result = rs.getString("res_code"); 

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

	/**
	 * 입력된 사용자의 목록을 조회<br>
	 * 이름,전화번호를 조회하여 UserSeachVO에 저장하고 List에 추가하여 반환하는 일을 한다.
	 * 
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
			String userSearch = "select user_name,user_tell,user_email " + "from ann_customer";
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

	/**
	 * 전체 회원 검색
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<UserTotalVO> select_totalUser() throws SQLException {
		List<UserTotalVO> list = new ArrayList<UserTotalVO>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String select_totalUser = "select user_id,user_name,user_gender,user_tell,user_email"
					+ " from ann_customer";
			pstmt = con.prepareStatement(select_totalUser);
			// 4.쿼리문 실행 후 객체 얻기
			rs = pstmt.executeQuery();

			UserTotalVO utv = null;
			while (rs.next()) {
				utv = new UserTotalVO();
				// 생성자에 넣으면 무슨 값이 들어갔는지 알수 없다.
				// setter를 이용해서 어디에 무슨값이 들어갔는지 알수 있다
				// 실수를 줄일 수 있다.
				// id,name,gender,phone,email;
				utv.setId(rs.getString("user_id"));
				utv.setName(rs.getString("user_name"));
				utv.setGender(rs.getString("user_gender"));
				utv.setPhone(rs.getString("user_tell"));
				utv.setEmail(rs.getString("user_email"));

				list.add(utv);

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
	}// select_totalUser

	/**
	 * 영화 이름,설명,시사회날짜, 이미지 추가
	 * 
	 * @author user
	 */
	////////////////////////// MobieRegisVO//////////////////
	public void insert_movieRegis(MovieRegisVO mrv) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1.드라이버로딩
			// 2.커넥션 얻기
			con = getConnection();
			// 3.쿼리문 생성 객체 얻기
			String manager_Movieregis = "insert into ann_movie" + "(MOVIE_CODE,MOVIE_TITLE,MOVIE_INFO, MOVIE_SCORE,"
					+ "MOVIE_IMG, MOVIE_OPENDATE, MOVIE_DATE)" + "values(moviecode,?,?,0," + "?,?,sysdate)";
			pstmt = con.prepareStatement(manager_Movieregis);
			// 4.쿼리문 실행 후 객체 얻기
			// 셋터로 받아오면 실수를 많이 줄일 수 있음.
			// 이렇게 쓰는게 가독성이 좋음, 값의 꼬임을 많이 줄일 수 있음.
			// 셋터로 받아오면 실수를 많이 줄일 수 있음.
			// 이렇게 쓰는게 가독성이 좋음, 값의 꼬임을 많이 줄일 수 있음.
			pstmt.setString(1, mrv.getMovieName());//
			pstmt.setString(2, mrv.getMovieInfo());//
			pstmt.setString(3, mrv.getMovieDate());//
			pstmt.setString(4, mrv.getMovieImg());//

			pstmt.executeUpdate();
		} finally {
			// 5
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // finally

	}// insert_movieRegis

	/**
	 * 좌석 등록하는 method 
	 * 
	 * @param srv
	 * @throws SQLException
	 */
	public void insert_seat(SeatRegisVO srv) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.
			// 2.
			// 커넥션 얻기
			con = getConnection();

			// res_code method를 이용한
			// res_code얻기
			String res_code = select_resCode(srv.getId());
			// 3.
			// 쿼리문 수행 객체 얻기
			String insertOrdering = "insert into" + " ann_seat(res_code,seat_num)" + " values(?,?)";
			pstmt = con.prepareStatement(insertOrdering);

			// 바인드 변수에 값할당
			pstmt.setString(1, res_code);
			pstmt.setInt(2, srv.getSeatNum());

			// 4.
			pstmt.executeUpdate();

		} finally {
			// 5.
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}// insertOrder

	////////////////////////////////////////////////////////
	// 단위 테스트 //
	///////////////////////////////////////////////////////
	public static void main(String[] args) {
		ManagerDAO md = new ManagerDAO();
		try {
			// select_reserveNot method
			// List<UserResNotVO> list = md.select_reserveNot();
			// System.out.println(list);

			// select_seatNum method
			// String seat_num = md.select_seatNum("SEAT_00003");
			// System.out.println(seat_num);

			// select_seatInfo method
			// int[] arr= md.select_seatInfo();
			// for (int i = 0; i < arr.length; i++) {
			// if(arr[i]!=0){
			// System.out.println(arr[i]+" ");
			// }
			// }// for

			// select_resCode method
			// String rs = md.select_resCode("cool");
			// System.out.println(rs);

			// insert_seat method
			SeatRegisVO srv = new SeatRegisVO();
			srv.setId("cool");
			srv.setSeatNum(22);
			md.insert_seat(srv);

		} catch (SQLException e) {
			e.printStackTrace();
		} // catch

	}// main

}// class
