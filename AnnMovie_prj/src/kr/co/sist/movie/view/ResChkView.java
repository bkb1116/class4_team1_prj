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
      super("��������(����)");
      //��
      jl_mName = new JLabel("��ȭ�� : ");
      jl_resNum = new JLabel("���Ź�ȣ : ");
      jl_wDate = new JLabel("���� : ");
      jl_wTime = new JLabel("�󿵽ð� : ");
      jl_resCnt = new JLabel("�����ο� : ");
      jl_seatNum= new JLabel("�¼���ȣ : ");
      //Content��
      jl_mName2 = new JLabel("���������ÿ�");
      jl_resNum2 = new JLabel();
      jl_wDate2 = new JLabel();
      jl_wTime2 = new JLabel();
      jl_resCnt2 = new JLabel();
      jl_seatNum2 = new JLabel();
      
      //�гμ���
      jp_resTitle = new JPanel();
      jp_resContent = new JPanel();
      //��ư ����
      jbt_cancel = new JButton("�ڷΰ���");
      jbt_print = new JButton("����ϱ�");
      
      //��ġ
      //title�г� 
      jp_resTitle.add(jl_mName);
      jp_resTitle.add(jl_resNum);
      jp_resTitle.add(jl_wDate);
      jp_resTitle.add(jl_wTime);
      jp_resTitle.add(jl_resCnt);
      jp_resTitle.add(jl_seatNum);
      
      //content �г�
      jp_resContent.add(jl_mName2);
      jp_resContent.add(jl_resNum2);
      jp_resContent.add(jl_wDate2);
      jp_resContent.add(jl_wTime2);
      jp_resContent.add(jl_resCnt2);
      jp_resContent.add(jl_seatNum2);
    
      //�гι�ġ
      add(jp_resTitle);
      add(jp_resContent);
      //��ư��ġ
      add(jbt_cancel); //�ڷΰ����ư
      add(jbt_print);// ����ϱ��ư
      
      // �̺�Ʈ ���
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