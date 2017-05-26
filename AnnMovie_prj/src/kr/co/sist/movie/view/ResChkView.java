package kr.co.sist.movie.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import kr.co.sist.movie.evt.ResChkEvt;

public class ResChkView extends JFrame { 
   private JLabel jl_wTime,jl_wDate,jl_resNum,jl_seatNum,jl_resCnt,jl_mName,
   	jl_wTime2,jl_wDate2,jl_resNum2,jl_seatNum2,jl_resCnt2,jl_mName2;
   private JButton jbt_cancel,jbt_print;
   private JPanel jp_resTitle, jp_resContent;
   
   public ResChkView(){
      super("예매정보(고객용)");
      //라벨
      jl_mName = new JLabel("영화명 : ");
      jl_resNum = new JLabel("예매번호 : ");
      jl_wDate = new JLabel("상영일 : ");
      jl_wTime = new JLabel("상영시간 : ");
      jl_resCnt = new JLabel("관람인원 : ");
      jl_seatNum= new JLabel("좌석번호 : ");
      //Content라벨
      jl_mName2 = new JLabel("값을넣으시오");
      jl_resNum2 = new JLabel();
      jl_wDate2 = new JLabel();
      jl_wTime2 = new JLabel();
      jl_resCnt2 = new JLabel();
      jl_seatNum2 = new JLabel();
      
      //패널선언
      jp_resTitle = new JPanel();
      jp_resContent = new JPanel();
      //버튼 선언
      jbt_cancel = new JButton("뒤로가기");
      jbt_print = new JButton("출력하기");
      
      //배치
      //title패널 
      jp_resTitle.add(jl_mName);
      jp_resTitle.add(jl_resNum);
      jp_resTitle.add(jl_wDate);
      jp_resTitle.add(jl_wTime);
      jp_resTitle.add(jl_resCnt);
      jp_resTitle.add(jl_seatNum);
      
      //content 패널
      jp_resContent.add(jl_mName2);
      jp_resContent.add(jl_resNum2);
      jp_resContent.add(jl_wDate2);
      jp_resContent.add(jl_wTime2);
      jp_resContent.add(jl_resCnt2);
      jp_resContent.add(jl_seatNum2);
    
      //패널배치
      add(jp_resTitle);
      add(jp_resContent);
      //버튼배치
      add(jbt_cancel); //뒤로가기버튼
      add(jbt_print);// 출력하기버튼
      
      // 이벤트 등록
      ResChkEvt rce=new ResChkEvt(this);
      
      setLayout(null);
      jp_resTitle.setLayout(new GridLayout(6,1) );
      jp_resContent.setLayout(new GridLayout(6,1) );
      jp_resTitle.setBounds(30,20,80,230);
      jp_resContent.setBounds(120,20,120,230);
      jbt_print.setBounds(20,260,160,40);
      jbt_cancel.setBounds(200, 260, 160, 40);
      setBounds(20,20,400,350);
      setVisible(true);
      
      
   }//infoForm
   
   public JLabel getJl_wTime() {
	return jl_wTime;
}

public void setJl_wTime(JLabel jl_wTime) {
	this.jl_wTime = jl_wTime;
}

public JLabel getJl_wDate() {
	return jl_wDate;
}

public void setJl_wDate(JLabel jl_wDate) {
	this.jl_wDate = jl_wDate;
}

public JLabel getJl_resNum() {
	return jl_resNum;
}

public void setJl_resNum(JLabel jl_resNum) {
	this.jl_resNum = jl_resNum;
}

public JLabel getJl_seatNum() {
	return jl_seatNum;
}

public void setJl_seatNum(JLabel jl_seatNum) {
	this.jl_seatNum = jl_seatNum;
}

public JLabel getJl_resCnt() {
	return jl_resCnt;
}

public void setJl_resCnt(JLabel jl_resCnt) {
	this.jl_resCnt = jl_resCnt;
}

public JLabel getJl_mName() {
	return jl_mName;
}

public void setJl_mName(JLabel jl_mName) {
	this.jl_mName = jl_mName;
}

public JLabel getJl_wTime2() {
	return jl_wTime2;
}

public void setJl_wTime2(JLabel jl_wTime2) {
	this.jl_wTime2 = jl_wTime2;
}

public JLabel getJl_wDate2() {
	return jl_wDate2;
}

public void setJl_wDate2(JLabel jl_wDate2) {
	this.jl_wDate2 = jl_wDate2;
}

public JLabel getJl_resNum2() {
	return jl_resNum2;
}

public void setJl_resNum2(JLabel jl_resNum2) {
	this.jl_resNum2 = jl_resNum2;
}

public JLabel getJl_seatNum2() {
	return jl_seatNum2;
}

public void setJl_seatNum2(JLabel jl_seatNum2) {
	this.jl_seatNum2 = jl_seatNum2;
}

public JLabel getJl_resCnt2() {
	return jl_resCnt2;
}

public void setJl_resCnt2(JLabel jl_resCnt2) {
	this.jl_resCnt2 = jl_resCnt2;
}

public JLabel getJl_mName2() {
	return jl_mName2;
}

public void setJl_mName2(JLabel jl_mName2) {
	this.jl_mName2 = jl_mName2;
}

public JButton getJbt_cancel() {
	return jbt_cancel;
}

public void setJbt_cancel(JButton jbt_cancel) {
	this.jbt_cancel = jbt_cancel;
}

public JButton getJbt_print() {
	return jbt_print;
}

public void setJbt_print(JButton jbt_print) {
	this.jbt_print = jbt_print;
}

public JPanel getJp_resTitle() {
	return jp_resTitle;
}

public void setJp_resTitle(JPanel jp_resTitle) {
	this.jp_resTitle = jp_resTitle;
}

public JPanel getJp_resContent() {
	return jp_resContent;
}

public void setJp_resContent(JPanel jp_resContent) {
	this.jp_resContent = jp_resContent;
}

public static void main(String[] args) {
      new ResChkView();
   }//mian


}//class