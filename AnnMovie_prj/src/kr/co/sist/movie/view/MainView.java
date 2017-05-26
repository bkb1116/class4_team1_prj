package kr.co.sist.movie.view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.movie.evt.MainEvt;

public class MainView extends JFrame{
	
   private String user_id; 
   private JList<String> jl_review;
   private JComboBox<String> jcb_grade,jcb_peopleNum;
   private JLabel jlb_img,jlb_avg,jlb_peopleNum,jlb_avg2;
   private JTextField jtf_Review;
   private JButton jbt_reserve,jbt_review,jbt_login,Jbt_reserveChk,jbt_join,jbt_manager;
   private JScrollPane jsp_review;
   private Font font;
   private DefaultListModel<String> dlm_reviewAll;
   
   public MainView(){
      super("��ȭ���� ������");
      //�� 
      jlb_img = new JLabel("��ȭ�̹���");//�̹����ֱ�
      jlb_avg = new JLabel("����!");//�����ֱ�
      jlb_peopleNum = new JLabel("���� �ο��� ����");
      jlb_avg2 = new JLabel("�������");
      //�޺��ڽ�
      jcb_grade = new JComboBox<String>(); 
      jcb_peopleNum = new JComboBox<String>();
      jl_review = new JList<String>();
      //TF ����
      jtf_Review = new JTextField("�ı��Է�â");
      //��ư����.
      jbt_reserve = new JButton("�����ϱ�");
      jbt_login = new JButton("�α���");
      jbt_manager = new JButton("������");
      jbt_review = new JButton("�Է�");
      Jbt_reserveChk = new JButton("������ȸ");
      jbt_join = new JButton("ȸ������");
      //��ũ�ѹټ���
      jsp_review = new JScrollPane(jl_review);//��ü�ı�â�� ��ũ�ѹٳ־���
      
      //�󺧱۾�ũ��
      font = new Font("����",Font.BOLD,40);
      jlb_avg.setFont(font);
      //�󺧿� �̹��� �ֱ�
      
      //��ġ
      add(jlb_img);
      add(jlb_peopleNum);
      add(jlb_avg);
      add(jlb_avg2);
      //�޺��ڽ�
      add(jcb_grade);
      add(jcb_peopleNum);
      //��ư
      add(jbt_join);
      add(jbt_login);
      add(jbt_review);
      add(jbt_reserve);
      add(Jbt_reserveChk);
      add(jbt_manager);
      //�ı�
      add(jtf_Review);
      add(jsp_review);
      //�̺�Ʈ ���
      MainEvt me=new MainEvt(this);
      jbt_login.addActionListener(me);
      jbt_reserve.addActionListener(me);
      jbt_join.addActionListener(me);
      jbt_manager.addActionListener(me);
      jbt_review.addActionListener(me);
      Jbt_reserveChk.addActionListener(me);
      jcb_peopleNum.addItemListener(me);
      
      //��ü �ı� ����Ʈ
      dlm_reviewAll=new DefaultListModel<String>();
      jl_review = new JList<String>(dlm_reviewAll);
      //�޺��ڽ��� 1~5�Ǽ��� �־��ֱ�
      jcb_grade.addItem("�����ֱ�");
      jcb_grade.addItem("1");
      jcb_grade.addItem("2");
      jcb_grade.addItem("3");
      jcb_grade.addItem("4");
      jcb_grade.addItem("5");
      
      //�ο����� �޺��ڽ�
      jcb_peopleNum.addItem("�ο�����");
      jcb_peopleNum.addItem("1");
      jcb_peopleNum.addItem("2");
      
      //ũ�⼳��
      jlb_img.setBounds(20,20,460,350);//��ȭ�̹���
      jcb_grade.setBounds(10,410,80,30);//�����ִ�â.
      jtf_Review.setBounds(100,410,360,30); //�ı��Է�â
      //��ư
      jbt_login.setBounds(480,10,90,20);//�α���
      jbt_join.setBounds(370,10,100,20);//ȸ������
      jbt_manager.setBounds(280,10,80,20); //�����ڹ�ư
      jbt_review.setBounds(470,410,100,30);//�Է�
      
      jlb_avg.setBounds(470,80,100,60);//����
      jlb_avg2.setBounds(470,140,100,30); //�������â
      jlb_peopleNum.setBounds(470,230,100,30);//�����ο�������
      jcb_peopleNum.setBounds(470,260,100,30);//�����ο�����
      jbt_reserve.setBounds(470,300,100,30);//���Ź�ư
      Jbt_reserveChk.setBounds(470,340,100,30);//������ȸ
      
      jsp_review.setBounds(10,450,560,100); //��ü�����ı�
      
      //���
      setLayout(null);
      setBounds(10, 50, 600, 600);
      //����ȭ
      setResizable(false);
      setVisible(true);
      
   }//end MainPage

   

   public JComboBox<String> getJcb_grade() {
	return jcb_grade;
}



public void setJcb_grade(JComboBox<String> jcb_grade) {
	this.jcb_grade = jcb_grade;
}



public JTextField getJtf_Review() {
	return jtf_Review;
}



public void setJtf_Review(JTextField jtf_Review) {
	this.jtf_Review = jtf_Review;
}



public JButton getJbt_reserve() {
	return jbt_reserve;
}



public JButton getJbt_review() {
	return jbt_review;
}



public JButton getJbt_login() {
	return jbt_login;
}



public JButton getJbt_reserveChk() {
	return Jbt_reserveChk;
}



public JButton getJbt_join() {
	return jbt_join;
}



public JButton getJbt_manager() {
	return jbt_manager;
}




public String getUser_id() {
	return user_id;
}



public void setUser_id(String user_id) {
	this.user_id = user_id;
}




public JComboBox<String> getJcb_peopleNum() {
	return jcb_peopleNum;
}



public void setJcb_peopleNum(JComboBox<String> jcb_peopleNum) {
	this.jcb_peopleNum = jcb_peopleNum;
}



public JLabel getJlb_img() {
	return jlb_img;
}



public void setJlb_img(JLabel jlb_img) {
	this.jlb_img = jlb_img;
}




public JLabel getJlb_avg2() {
	return jlb_avg2;
}



public void setJlb_avg2(JLabel jlb_avg2) {
	this.jlb_avg2 = jlb_avg2;
}



public JLabel getJlb_avg() {
	return jlb_avg;
}



public void setJlb_avg(JLabel jlb_avg) {
	this.jlb_avg = jlb_avg;
}



public DefaultListModel<String> getDlm_reviewAll() {
	return dlm_reviewAll;
}



public void setDlm_reviewAll(DefaultListModel<String> dlm_reviewAll) {
	this.dlm_reviewAll = dlm_reviewAll;
}



public static void main(String[] args) {
      new MainView();
   }//main

}//class
