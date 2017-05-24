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
	//기준 수정 마스터변경
	public AnnDAO() {
	}//AnnDAO

	public static AnnDAO getInstance(){
		
		if(a_dao == null){
			a_dao = new AnnDAO();
		}//end if
		
		return a_dao;
	}//getInstance
///////////////test 1/////////////////////
///////////////test 1/////////////////////
///////////////test 1/////////////////////
///////////////test 1/////////////////////
///////////////test 1///////////////////// 
	
	
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
	
	////////////////////// 메인화면 정보(이미지, 평점, 영화정보) //////////////////////
	public void select_mainView() throws SQLException{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.  
			// 2.
			con = getConnection();
			// 3.
			String select_main = "select movie_Img, movie_Info, movie_score from ann_movie where 선택한 영화";
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
	
	/*
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
	}//select_memberChk
	
	public boolean select_overlapChk(String id){
		
	}//select_overlapChk
	
	public List<AddReviewVO> select_totalReview(){
		
	}//select_totalReview
	
	public ResInfoVO select_reserveInfo(){
		
	}//select_reserveInfo
	
	public void insert_reserve(ReserveVO rv) {
		
	}//insert_reserve
	
	public void insert_review(AddReviewVO arv) {
		
	}//insert_review
	
	public void insert_join(JoinVO jv) {
		
	}//insert_join
	
	public void delete_review(DelReviewVO drv) {
		
	}//delete_review
	*/
	
}//class
