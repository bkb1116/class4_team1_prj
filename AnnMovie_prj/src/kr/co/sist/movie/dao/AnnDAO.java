package kr.co.sist.movie.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
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
	public void select_mainView() throws SQLException{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.  
			// 2.
			con = getConnection();
			// 3.
			String select_main = "select movie_Img, movie_Info, movie_score from ann_movie where movie_title='임금님의 사건수첩'";
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
		}//end catch
		
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
		ResultSet rs = null;

		try {
			con = getConnection();
			
			String select_main = "insert into(컬럼명) values(값들) ";
			pstmt = con.prepareStatement(select_main);
			
			rs = pstmt.executeQuery();

			MainVO mvo = null;
				mvo = new MainVO();
				mvo.setMovieImg(rs.getString("movieReview"));
				mvo.setMovieInfo(rs.getString("id"));

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
	}//insert_review
	
	/**
	 * 회원가입(아이디, 패스워드, 이름, 주민번호, 전화번호, 이메일)
	 * @param jv
	 */
	public void insert_join(JoinVO jv) {
		
	}//insert_join
	
	/**
	 * 후기삭제(리뷰, 아이디)
	 * @param drv
	 */
	public void delete_review(DelReviewVO drv) {
		
	}//delete_review
	
	
}//class
