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
	
	
	/**
	    * 로그인시 가입되있는사람인지 아닌지 체크
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
	    * 리뷰 전체 목록
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
	         // 1.드라이버로딩
	         // 2.커넥션 얻기
	         con = getConnection();
	         // 3.쿼리문 생성 객체 얻기
	         String select_totalReview = "select movie_avg, review,user_id from ann_review";
	         pstmt = con.prepareStatement(select_totalReview);
	         // 4.쿼리문 실행 후 객체 얻기
	         rs = pstmt.executeQuery();

	         AddReviewVO arv = null;
	         while (rs.next()) {
	            arv = new AddReviewVO();
	            // 생성자에 넣으면 무슨 값이 들어갔는지 알수 없다.
	            // setter를 이용해서 어디에 무슨값이 들어갔는지 알수 있다
	            // 실수를 줄일 수 있다.
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
	    * 예약 정보 출력용
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
	         // 1.드라이버로딩
	         // 2.커넥션 얻기
	         con = getConnection();
	         // 3.쿼리문 생성 객체 얻기
	         String select_reserveInfo = "select am.movie_title,am.movie_info,am.movie_opendate,ar.user_id,ar.res_code,ar.seat_quan,ans.seat_num,ar.movie_code"
	               + " from ann_movie am,ann_reserve ar,ann_seat ans"
	               + " where (am.movie_code=ar.movie_code) and (ar.res_code=ans.res_code)";
	         pstmt = con.prepareStatement(select_reserveInfo);
	         // 4.쿼리문 실행 후 객체 얻기
	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	            riv = new ResInfoVO();
	            // 생성자에 넣으면 무슨 값이 들어갔는지 알수 없다.
	            // setter를 이용해서 어디에 무슨값이 들어갔는지 알수 있다
	            // 실수를 줄일 수 있다.
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
	    * id 중복확인
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
	         // 1.드라이버로딩
	         // 2.커넥션 얻기
	         con = getConnection();
	         // 3.쿼리문 생성 객체 얻기
	         String overlapChk = "select user_id from ann_customer where user_id='" + id + "'";
	         pstmt = con.prepareStatement(overlapChk);
	         // 4.쿼리문 실행 후 객체 얻기
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
			drv.setMovieReview("우왕ㅋ굳");
			
			ado.delete_review(drv);
			
			System.out.println("chkq");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}//class
