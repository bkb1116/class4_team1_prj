package kr.co.sist.movie.evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import kr.co.sist.movie.dao.AnnDAO;
import kr.co.sist.movie.view.JoinView;
import kr.co.sist.movie.view.LoginView;
import kr.co.sist.movie.view.MainView;
import kr.co.sist.movie.view.ManagerView;
import kr.co.sist.movie.view.ResChkView;
import kr.co.sist.movie.vo.AddReviewVO;
import kr.co.sist.movie.vo.DelReviewVO;
import kr.co.sist.movie.vo.MainVO;
import kr.co.sist.movie.vo.MovieRegisVO;
import kr.co.sist.movie.vo.ResInfoVO;
import kr.co.sist.movie.vo.UserTotalVO;

public class MainEvt extends WindowAdapter implements ActionListener, ItemListener {
	
	private AnnDAO a_dao;
	private MainView mv;
	private JoinView jv;
	private LoginView lv;
	private ManagerView mgv;
	private ResChkView rcv;
	private MainVO mvo;
	private String path = "c:/dev/temp_img/";
	private String id;
	
	public MainEvt(MainView mv) {
		this.mv=mv;
		a_dao = AnnDAO.getInstance();
		mainPage();
	}//MainEvt

	/**
	 * 전체 후기 넣어주는 method 
	 */
	private void totalReview(){
	
		try {
			List<AddReviewVO> reviewAll = a_dao.select_totalReview();
			AddReviewVO arv = null;
			DefaultListModel<String> list = mv.getDlm_reviewAll();
			
			// 리스트 초기화 시킨후 값 넣기
			list.clear();
			String[] str = null ; 
			for (int i = 0; i < reviewAll.size(); i++) {
				str = new String[reviewAll.size()] ; 
				arv = reviewAll.get(i);
				str[i] = arv.getMovieScore()+" / "+arv.getId()+" / "+arv.getMovieReview();
				list.addElement(str[i]);
			}// for
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		
		
	}// totalReview
	
	private void mainPage(){
		try {
			mvo= a_dao.select_mainView();
			
			String movieTitle=mvo.getMovieTitle();//영화 제목
			String movieCode=mvo.getMovieCode();//영화 코드
			String movieImg=mvo.getMovieImg();//영화 이미지
			int movieScore=mvo.getAvgScore();//영화 평점.
			
			
			ImageIcon temp=new ImageIcon(path+"img.jpg");//이미지 경로
		
			mv.getJlb_avg().setText(String.valueOf(movieScore+"/"+"5"));//영화 평점
			mv.getJlb_img().setIcon(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//mainView
	
	
	/** 
	 * 로그인 클릭시 이벤트 처리
	 */
	public void loginGo(){
		new LoginView();//////////완료
	}//loginGo
	
	/**
	 * 예매확인 클릭시 이벤트 처리
	 */
	public void reserveChk(){
		rcv=new ResChkView(); ////////////완료
		
		
		try {
			ResInfoVO riv= a_dao.select_reserveInfo();
			
			rcv.getJl_mName2().setText(riv.getMovieTitle());
			rcv.getJl_resNum2().setText(riv.getResCode());
			rcv.getJl_wDate2().setText(riv.getOpenDate());
			rcv.getJl_wTime2().setText("11시 30분");
			rcv.getJl_resCnt2().setText(riv.getSeatQuan().toString());
			rcv.getJl_seatNum2().setText(riv.getSeatNum().toString());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}//reserveChk

	/**
	 * 예매하기 이벤트 처리
	 */
	public void addReserve(){
//		System.out.println(mv.getJcb_peopleNum().getSelectedItem());
		
		String peopleNum=mv.getJcb_peopleNum().getSelectedItem().toString();
		String id=mv.getUser_id();
//		String movieCode=
		
		
	}//addReserve
	
	/**
	 * 후기남기기 클릭시 이벤트 처리
	 * @throws SQLException 
	 */
	public void addReview() {
		AddReviewVO arvo = new AddReviewVO();
		
		String user_id=mv.getUser_id();
		String movie_review=mv.getJtf_Review().getText();
		String movie_score=(String) mv.getJcb_grade().getSelectedItem();
		
		arvo.setId(user_id);
		arvo.setMovieReview(movie_review);
		arvo.setMovieScore(movie_score);
		
		try {
			a_dao.insert_review(arvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("찍힘");
	}//addReview
	
	/**
	 * 후기삭제 이벤트처리
	 */
	public void delReview(){
		DelReviewVO drv=new DelReviewVO();
		
		String id=mv.getUser_id();
		String movieReview = mv.getJtf_Review().getText(); 
		
		drv.setId(id);
		drv.setMovieReview(movieReview);
		
		try {
			a_dao.delete_review(drv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//delReview
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}//itemStateChanged

	@Override
	public void actionPerformed(ActionEvent e) {
		//회원가입
		if( e.getSource()== mv.getJbt_join() ){
			new JoinView();
		}
		//로그인
		if(e.getSource()==mv.getJbt_login()){
			loginGo();
		}
		//예매하기
		if(e.getSource()==mv.getJbt_reserve()){
			addReserve();
		}
		//예매확인
		if(e.getSource()==mv.getJbt_reserveChk()){
			reserveChk();
		}
		//후기작성
		if(e.getSource()==mv.getJbt_review()){
			addReview();
		}
		//관리자
		if(e.getSource()==mv.getJbt_manager()){
			new ManagerView();
		} 
		
	}//actionPerformed

}//class