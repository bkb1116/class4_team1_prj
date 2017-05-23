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
   
   public static void main(String[] args) {
      new ResChkView();
   }//mian


}//class