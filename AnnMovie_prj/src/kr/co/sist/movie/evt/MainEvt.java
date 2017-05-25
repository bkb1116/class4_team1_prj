package kr.co.sist.movie.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.movie.dao.AnnDAO;
import kr.co.sist.movie.view.JoinView;
import kr.co.sist.movie.view.LoginView;
import kr.co.sist.movie.view.MainView;
import kr.co.sist.movie.view.ManagerView;
import kr.co.sist.movie.view.ResChkView;
import kr.co.sist.movie.vo.AddReviewVO;

public class MainEvt extends WindowAdapter implements ActionListener, ItemListener {
	
	private AnnDAO a_dao;
	private MainView mv;
	private JoinView jv;
	private LoginView lv;
	private ManagerView mgv;
	private ResChkView rcv;
	
	private String id;
	
	public MainEvt(MainView mv) {
		this.mv=mv;
		a_dao = AnnDAO.getInstance();
	}//MainEvt

	/**
	 * 로그인 클릭시 이벤트 처리
	 */
	public void loginGo(){
		new LoginView();
	}//loginGo
	
	/**
	 * 예매확인 클릭시 이벤트 처리
	 */
	public void reserveChk(){
		rcv=new ResChkView();
	}//reserveChk

	/**
	 * 예매하기 이벤트 처리
	 */
	public void addReserve(){
		
	}//addReserve
	
	/**
	 * 후기남기기 클릭시 이벤트 처리
	 */
	public void addReview(){
		AddReviewVO arvo;
		
		id=lv.getJtf_id().getText();
		//String menu= maf.getJtfMenu().getText();//메뉴명
		String movie_review=mv.getJtf_Review().getText();
		String movie_score=(String) mv.getJcb_grade().getSelectedItem();
		
	}//addReview
	
	/**
	 * 후기삭제 이벤트처리
	 */
	public void delReview(){
		
	}//delReview
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}//itemStateChanged

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource()== mv.getJbt_join() ){
			//회원가입버튼????
			new JoinView();
		}
		
		if(e.getSource()==mv.getJbt_login()){
			loginGo();
		}
		
		if(e.getSource()==mv.getJbt_reserve()){
			addReserve();
		}
		
		if(e.getSource()==mv.getJbt_reserveChk()){
			reserveChk();
		}
		
		if(e.getSource()==mv.getJbt_review()){
			addReview();
		}
		
		if(e.getSource()==mv.getJbt_manager()){
			new ManagerView();
		} 
		
	}//actionPerformed

}//class
