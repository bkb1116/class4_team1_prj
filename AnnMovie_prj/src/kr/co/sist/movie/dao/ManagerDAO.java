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
	 * �Էµ� ������� ����� ��ȸ<br>
	 * �̸�,��ȭ��ȣ�� ��ȸ�Ͽ� UserSeachVO�� �����ϰ� 
	 * List�� �߰��Ͽ� ��ȯ�ϴ� ���� �Ѵ�.
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
			String userSearch ="select user_name,user_tell from ann_customer";
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
	///////////// �����׽�Ʈ �޼ҵ� ����/////////////////
	public static void main(String[] args) {
		try {
			ManagerDAO mda = ManagerDAO.getInstance();
			List<UserSearchVO> list = mda.select_searchUser();
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// main
		///////////// �����׽�Ʈ �޼ҵ� �� /////////////////
}//class
