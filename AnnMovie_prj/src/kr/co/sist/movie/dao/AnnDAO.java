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
	 * 커넥션 작업
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
				
				try {// 드라이버로딩
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
	 * 메인화면 정보(이미지, 평점, 영화정보)
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
	
	
	public boolean select_memberChk(LoginVO lv) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
		// 1.
		// 2.
			con=getConnection();
		// 3.
			String select_mem=" ";
			pstmt=con.prepareStatement(select_mem);
		// 4.
			pstmt.setString(1, lv.getId()); 
			pstmt.setString(2, lv.getPassword());

			pstmt.executeUpdate();
		} finally {
		// 5.
			
			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
		return false;
	}//select_memberChk
	
	/**
	 * @param id
	 * @return
	 */
	public boolean select_overlapChk(String id){
		return false;
		
	}//select_overlapChk
	
	/**
	 * @return
	 */
	public List<AddReviewVO> select_totalReview(){
		return null;
		
	}//select_totalReview
	
	/**
	 * 예매정보 조회(고객용)
	 * @return
	 */
	public ResInfoVO select_reserveInfo(){
		return null;
		
	}//select_reserveInfo
	
	/**
	 * 예매하기
	 * @param rv
	 */
	public void insert_reserve(ReserveVO rv) {
		
	}//insert_reserve
	
	/**
	 * DB에 후기 추가(평점, 리뷰, 아이디)
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
						+"values('REV_000016', 'kim', (select movie_code from ann_movie where movie_title='임금님의 사건수첩'), 'N', '쿨하게 보겠어용', 3, sysdate );";
				pstmt=con.prepareStatement(insertOrdering);
			// 4.
				pstmt.setString(1, arv.getId());
//				pstmt.setString(2,"ANN_000001");//무비코드 조인해야하나...
				pstmt.setString(2, arv.getMovieReview());
				pstmt.setInt(3, arv.getMovieScore());

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
	 * 회원가입(id, 이름, 비밀번호, 주민번호, 전화번호, 이메일, 성별, 가입일)
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
	 * 후기삭제(리뷰, 아이디)
	 * @param drv
	 */
	public void delete_review(DelReviewVO drv) {
		
	}//delete_review
	
	public static void main(String[] args) {
		AnnDAO ad=new AnnDAO();
	
		try {
			AnnDAO ado=new AnnDAO();
			AddReviewVO arv=new AddReviewVO();
			
			arv.setId("쿨규임당");
			
			arv.setMovieReview("쿨하게 보겠어용");
			arv.setMovieScore(3);
			
			ado.insert_review(arv);
			
			System.out.println("chkq");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}//class
