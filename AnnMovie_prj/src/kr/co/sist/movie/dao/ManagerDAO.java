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
	 * �̱��� ������ ����� ��ü ���
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
	 * ����̹� �ε� �� Ŀ�ؼ� ���
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

				try {// ����̹��ε�
					Class.forName(driver);
					con = DriverManager.getConnection(url, id, pw);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} // catch
			} else {
				JOptionPane.showMessageDialog(null, "��θ� Ȯ���ϼ���");
			} // end else if
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // catch

		return con;
	}// getConnection

	/**
	 * ������ �¼������� ���� ������
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
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String select_reserveYes = "select ar.user_id, ac.user_name,ans.seat_num1, ans.seat_num2 "
					+ " from ann_reserve ar, ann_customer ac, ann_seat ans "
					+ " where (ar.user_id=ac.user_id) and (ar.seat_code=ans.seat_code) and res_chk='Y'";
			pstmt = con.prepareStatement(select_reserveYes);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();

			UserResYesVO uryv = null;
			while (rs.next()) {
				uryv = new UserResYesVO();
				// �����ڿ� ������ ���� ���� ������ �˼� ����.
				// setter�� �̿��ؼ� ��� �������� ������ �˼� �ִ�
				// �Ǽ��� ���� �� �ִ�.
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
	 * �¼������� �ȵ� ��� ��ȸ�� VO�� ��ȯ�ϴ� method
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
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String selectReserve = "select ac.user_id,ac.user_name,ar.seat_quan"
					+ " from ANN_CUSTOMER ac, ANN_RESERVE ar" + " where (ac.user_id=ar.user_id)"
					+ " and ar.res_chk='N'";
			pstmt = con.prepareStatement(selectReserve);
			// 4.������ ���� �� ��ü ���
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
	 * ����� �¼��� ��� ��ȯ�ϴ� method
	 * 
	 * @param res_code(
	 *            ���� ��ȣ )
	 * @return total seat
	 * @throws SQLException
	 */
	public String select_seatNum(String res_code) throws SQLException {
		String result;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String selectReserve = "select seat_num" + " from ANN_SEAT" + " where res_code='" + res_code + "'"
					+ " order by seat_num";
			pstmt = con.prepareStatement(selectReserve);
			// 4.������ ���� �� ��ü ���
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
	 * �¼� ���� ��ȸ�ϴ� method �¼��� ��� �� �� ��� 0���� ��ȸ��
	 * 
	 * @return seat_info
	 * @throws SQLException
	 */
	public int[] select_seatInfo() throws SQLException {
		// ��밡���� �¼��� 35��
		int[] result = new int[35];

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String selectReserve = "select seat_num from ANN_SEAT";
			pstmt = con.prepareStatement(selectReserve);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// �迭�� ���
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
	 * res_code�� ��ȯ�ϴ� method
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
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String selectReserve = "select res_code from ann_reserve" + " where user_id='" + id + "'";
			pstmt = con.prepareStatement(selectReserve);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();

			// �� ȣ��
			rs.next();

			// �� �Ҵ�
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
	 * �Էµ� ������� ����� ��ȸ<br>
	 * �̸�,��ȭ��ȣ�� ��ȸ�Ͽ� UserSeachVO�� �����ϰ� List�� �߰��Ͽ� ��ȯ�ϴ� ���� �Ѵ�.
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
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String userSearch = "select user_name,user_tell,user_email " + "from ann_customer";
			pstmt = con.prepareStatement(userSearch);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();
			UserSearchVO usv = null;
			while (rs.next()) {
				// ���ͷ� �޾ƿ��� �Ǽ��� ���� ���� �� ����.
				// �̷��� ���°� �������� ����, ���� ������ ���� ���� �� ����.
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
	 * ��ü ȸ�� �˻�
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
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String select_totalUser = "select user_id,user_name,user_gender,user_tell,user_email"
					+ " from ann_customer";
			pstmt = con.prepareStatement(select_totalUser);
			// 4.������ ���� �� ��ü ���
			rs = pstmt.executeQuery();

			UserTotalVO utv = null;
			while (rs.next()) {
				utv = new UserTotalVO();
				// �����ڿ� ������ ���� ���� ������ �˼� ����.
				// setter�� �̿��ؼ� ��� �������� ������ �˼� �ִ�
				// �Ǽ��� ���� �� �ִ�.
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
	 * ��ȭ �̸�,����,�û�ȸ��¥, �̹��� �߰�
	 * 
	 * @author user
	 */
	////////////////////////// MobieRegisVO//////////////////
	public void insert_movieRegis(MovieRegisVO mrv) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1.����̹��ε�
			// 2.Ŀ�ؼ� ���
			con = getConnection();
			// 3.������ ���� ��ü ���
			String manager_Movieregis = "insert into ann_movie" + "(MOVIE_CODE,MOVIE_TITLE,MOVIE_INFO, MOVIE_SCORE,"
					+ "MOVIE_IMG, MOVIE_OPENDATE, MOVIE_DATE)" + "values(moviecode,?,?,0," + "?,?,sysdate)";
			pstmt = con.prepareStatement(manager_Movieregis);
			// 4.������ ���� �� ��ü ���
			// ���ͷ� �޾ƿ��� �Ǽ��� ���� ���� �� ����.
			// �̷��� ���°� �������� ����, ���� ������ ���� ���� �� ����.
			// ���ͷ� �޾ƿ��� �Ǽ��� ���� ���� �� ����.
			// �̷��� ���°� �������� ����, ���� ������ ���� ���� �� ����.
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
	 * �¼� ����ϴ� method 
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
			// Ŀ�ؼ� ���
			con = getConnection();

			// res_code method�� �̿���
			// res_code���
			String res_code = select_resCode(srv.getId());
			// 3.
			// ������ ���� ��ü ���
			String insertOrdering = "insert into" + " ann_seat(res_code,seat_num)" + " values(?,?)";
			pstmt = con.prepareStatement(insertOrdering);

			// ���ε� ������ ���Ҵ�
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
	// ���� �׽�Ʈ //
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
