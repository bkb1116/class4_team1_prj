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

import kr.co.sist.movie.vo.AddReviewVO;
import kr.co.sist.movie.vo.DelReviewVO;
import kr.co.sist.movie.vo.JoinVO;
import kr.co.sist.movie.vo.LoginVO;
import kr.co.sist.movie.vo.MainVO;
import kr.co.sist.movie.vo.ResInfoVO;
import kr.co.sist.movie.vo.ReserveVO;

public class AnnDAO {
	private static AnnDAO a_dao;

	// ���� ���� �����ͺ���
	public AnnDAO() {
	}// AnnDAO

	public static AnnDAO getInstance() {

		if (a_dao == null) {
			a_dao = new AnnDAO();
		} // end if

		return a_dao;
	}// getInstance

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

				try {// ����̹��ε�
					Class.forName(driver);
					con = DriverManager.getConnection(url, id, pw);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} // catch
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end catch

		return con;
	}// getConnection

	////////////////////// ����ȭ�� ����(�̹���, ����, ��ȭ����) //////////////////////
	public void select_mainView() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.
			// 2.
			con = getConnection();
			// 3.
			String select_main = "select movie_Img, movie_Info, movie_score from ann_movie where ������ ��ȭ";
			pstmt = con.prepareStatement(select_main);
			// 4.
			rs = pstmt.executeQuery();

			MainVO mvo = null;
			mvo = new MainVO();
			mvo.setMovieImg(rs.getString("movie_Img"));
			mvo.setMovieInfo(rs.getString("movie_Info"));
			mvo.setAvgScore(rs.getString("movie_score"));

		} finally {
			// 5.
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		} // end catch

	}// select_mainView

	/**
	 * �α��ν� ���Ե��ִ»������ �ƴ��� üũ
	 * 
	 * @param lv
	 * @return
	 * @throws SQLException
	 */
	public boolean select_memberChk(LoginVO lv) throws SQLException {
		boolean result = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.
			// 2.
			con = getConnection();
			// 3.
			String user_id = lv.getId();
			String user_pw = lv.getPassword();

			String select_main = "select user_id,user_pw from ann_customer" + " where user_id='" + user_id
					+ "' and user_pw='" + user_pw + "'";
			pstmt = con.prepareStatement(select_main);
			// 4.
			rs = pstmt.executeQuery();

			result = rs.next();

		} finally {
			// 5.
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		} // end catch

		return result;
	} // select_memberChk

	/**
	 * ���� ��ü ���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<AddReviewVO> select_totalReview() throws SQLException {
		List<AddReviewVO> list = new ArrayList<AddReviewVO>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String select_totalReview = "select movie_avg, review,user_id from ann_review";
			pstmt = con.prepareStatement(select_totalReview);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();

			AddReviewVO arv = null;
			while (rs.next()) {
				arv = new AddReviewVO();
				// �����ڿ� ������ ���� ���� ������ �˼� ����.
				// setter�� �̿��ؼ� ��� �������� ������ �˼� �ִ�
				// �Ǽ��� ���� �� �ִ�.
				arv.setId(rs.getString("user_id"));
				arv.setMovieScore(rs.getString("movie_avg"));
				arv.setMovieReview(rs.getString("review"));

				list.add(arv);

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
	}// select_totalReview

	/**
	 * ���� ���� ��¿�
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ResInfoVO select_reserveInfo() throws SQLException {

		ResInfoVO riv = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String select_reserveInfo = "select am.movie_title,am.movie_info,am.movie_opendate,ar.user_id,ar.res_code,ar.seat_quan,ans.seat_num,ar.movie_code"
					+ " from ann_movie am,ann_reserve ar,ann_seat ans"
					+ " where (am.movie_code=ar.movie_code) and (ar.res_code=ans.res_code)";
			pstmt = con.prepareStatement(select_reserveInfo);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				riv = new ResInfoVO();
				// �����ڿ� ������ ���� ���� ������ �˼� ����.
				// setter�� �̿��ؼ� ��� �������� ������ �˼� �ִ�
				// �Ǽ��� ���� �� �ִ�.
				// movie_title,am.movie_info,am.movie_opendate,ar.user_id,ar.res_code,ar.seat_quan,ans.seat_num,ar.movie_code
				riv.setMovieTitle(rs.getString("movie_title"));
				riv.setMovieInfo(rs.getString("movie_info"));
				riv.setOpenDate(rs.getString("movie_opendate"));
				riv.setId(rs.getString("user_id"));
				riv.setResCode(rs.getString("res_code"));
				riv.setSeatQuan(rs.getInt("seat_quan"));
				riv.setSeatNum(rs.getInt("seat_num"));
				riv.setMovie_code(rs.getString("movie_code"));

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
		return riv;
	}// select_resurveInfo

	/**
	 * id �ߺ�Ȯ��
	 * 
	 * @author user
	 * @param id
	 */
	public boolean select_overlapChk(String id) throws SQLException {
		boolean flag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String overlapChk = "select user_id from ann_customer where user_id='" + id + "'";
			pstmt = con.prepareStatement(overlapChk);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();

			flag = rs.next();
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

		return flag;
	}// selectMenuList

	//////////////////////////////////////////////////////////
	// ���� �׽�Ʈ //
	//////////////////////////////////////////////////////////

	public static void main(String[] args) {
		AnnDAO ad = new AnnDAO();
		LoginVO lv = new LoginVO();
		lv.setId("kim");
		lv.setPassword("1234111");

		try {
			System.out.println(ad.select_memberChk(lv));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// main

}// class
