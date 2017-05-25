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
	
	public AnnDAO() {
	}//AnnDAO

	public static AnnDAO getInstance(){
		
		if(a_dao == null){
			a_dao = new AnnDAO();
		}//end if
		 
		return a_dao;
	}//getInstance
	
	/**
	 * Ŀ�ؼ� �۾�
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection con = null;

		Properties prop = new Properties(); 
  
		try { 
			File file = new File("C:/Users/sist/git/class4_team1_prj/AnnMovie_prj/src/kr/co/sist/movie/dao/AnnMovie_db.properties"); 

			if (file.exists()) {
				prop.load(new FileInputStream(file)); 
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String id = prop.getProperty("dboid");
				String pass = prop.getProperty("dbopwd");
				
				try {// ����̹��ε�
					Class.forName(driver);
					con = DriverManager.getConnection(url, id, pass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} // catch
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}//end catch

		return con;
	}// getConnection	 

	
	
	/**
	 * ����ȭ�� ����(�̹���, ����, ��ȭ����)
	 * @throws SQLException
	 */
	public MainVO select_mainView() throws SQLException{

		MainVO mvo= new MainVO();


		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.  
			// 2.
			con = getConnection();
			// 3.
			String select_main = "select * from ann_movie where movie_code='ANN_000001'";
			pstmt = con.prepareStatement(select_main);
			// 4.
			rs = pstmt.executeQuery();

			rs.next();
			mvo.setMovieImg(rs.getString("movie_Img"));
			mvo.setMovieInfo(rs.getString("movie_Info"));
			mvo.setAvgScore(rs.getInt("movie_score"));
			
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
		}//end catch
		
		return mvo;
	}//select_mainView
	
	
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
	
	
	/**
	 * DB�� �ı� �߰�(����, ����, ���̵�)
	 * @param arv
	 * @throws SQLException 
	 */
	public void insert_review(AddReviewVO arv) throws SQLException {
		
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
			// 1.
			// 2.
				con=getConnection();
			// 3.
				String insertOrdering="insert into ann_review(review_code, user_id, movie_code, notice, review, movie_avg, review_date )"
						+"values('REV_000016', 'kim', (select movie_code from ann_movie where movie_title='�ӱݴ��� ��Ǽ�ø'), 'N', '���ϰ� ���ھ��', 3, sysdate );";
				pstmt=con.prepareStatement(insertOrdering);
			// 4.
				pstmt.setString(1, arv.getId());
//				pstmt.setString(2,"ANN_000001");//�����ڵ� �����ؾ��ϳ�...
				pstmt.setString(2, arv.getMovieReview());
				pstmt.setString(3, arv.getMovieScore());

				pstmt.executeUpdate();
			} finally {
			// 5.
				if (pstmt != null) {
					pstmt.close();
				} // end if

				if (con != null) {
					con.close();
				} // end if
			}//end finally
			
	}//insert_review
	
	/**
	 * ȸ������(id, �̸�, ��й�ȣ, �ֹι�ȣ, ��ȭ��ȣ, �̸���, ����, ������)
	 * @param jv
	 * @throws SQLException 
	 */
	public void insert_join(JoinVO jv) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null; 
		try {
		// 1.
		// 2.
			con=getConnection();
		// 3.
			String insert_join="insert into ann_customer(user_id, user_name, user_pw, user_ssn, user_tell, user_email, user_gender, user_date ) "
					+ "values(?,?,?,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(insert_join);
		// 4.
			pstmt.setString(1, jv.getId());
			pstmt.setString(2, jv.getName());
			pstmt.setString(3, jv.getPasswd());
			pstmt.setString(4, jv.getSsn());
			pstmt.setString(5, jv.getPhone());
			pstmt.setString(6, jv.getEmail());
			pstmt.setString(7, jv.getGender());

			pstmt.executeUpdate();
		} finally {
		// 5.
			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}//end finally
		
	}//insert_join
	
	/**
	 * �ı����(����, ���̵�)
	 * @param drv
	 * @throws SQLException 
	 */
	public void delete_review(DelReviewVO drv) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null; 
		try {
		// 1. 
		// 2.
			con=getConnection();
		// 3.
			String delete_review="delete from ann_review where (user_id=?) and (review=?)";
			pstmt=con.prepareStatement(delete_review);
		// 4.
			pstmt.setString(1, drv.getId());
			pstmt.setString(2, drv.getMovieReview());

			pstmt.executeUpdate();
		} finally {
		// 5.
			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}//end finally
		
	}//delete_review
	
	public static void main(String[] args) {
		AnnDAO ad=new AnnDAO();
	
		try {
			AnnDAO ado=new AnnDAO();
			DelReviewVO drv=new DelReviewVO();
			
			drv.setId("cool");
			drv.setMovieReview("��դ���");
			
			ado.delete_review(drv);
			
			System.out.println("chkq");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}//class
