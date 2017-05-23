package kr.co.sist.movie.view;

import java.awt.Font;

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

   private JList<String> jl_review;
   private JComboBox<String> jcb_grade,jcb_peopleNum;
   private JLabel jlb_img,jlb_avg,jlb_peopleNum,jlb_avg2;
   private JTextField jtf_Review;
   private JButton jbt_reserve,jbt_review,jbt_login,Jbt_reserveChk,jbt_join,jbt_manager;
   private JScrollPane jsp_review;
   private Font font;
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


   public static void main(String[] args) {
      new MainView();
   }//main

}//class
