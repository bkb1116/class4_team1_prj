package kr.co.sist.movie.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.movie.evt.LoginEvt;

public class LoginView extends JFrame{
   private JLabel jl_id,jl_pw;
   private JTextField jtf_id;
   private JPasswordField jpf_pass;
   private JButton jbt_login,jbt_join;
   //�г� ���ְ� ũ�������ϱ�
   
   
   public LoginView(){
      super("�α��� / ȸ������ â");
      //����
      jl_id = new JLabel("���̵�");
      jl_pw = new JLabel("��й�ȣ");
      
      jtf_id = new JTextField("");
      
      jpf_pass = new JPasswordField();
      
      jbt_login = new JButton("�α���");
      jbt_join = new JButton("ȸ������");
      //��ġ
      //��
      add(jl_id);//���̵�
      add(jl_pw); //���
      //TF,, TP��ġ
      add(jtf_id);//���̵�
      add(jpf_pass);//�н�����
      
      //��ư
      add(jbt_login);//�α��ι�ư
      add(jbt_join);//ȸ�����Թ�ư
      
      // �̺�Ʈ ���
      LoginEvt le=new LoginEvt(this);
      
      //ũ�⼳��
      setLayout(null);

      //ũ�⼳�� �ٽ����ֱ�
      jl_id.setBounds(40,25,50,30);
      jl_pw.setBounds(40,60,60,30);
      jtf_id.setBounds(110,25,130,30);
      jpf_pass.setBounds(110,60,130,30);
      
      
      jbt_login.setBounds(40,100,90,40);
      jbt_join.setBounds(145,100,90,40);
      setBounds(30, 150, 300, 200);
      
      LoginEvt loge = new LoginEvt(this);
      jbt_login.addActionListener(loge);
      
      //����ȭ
      setVisible(true);

      
   }//loginform
   
   
   
   
   public JLabel getJl_id() {
	return jl_id;
}




public void setJl_id(JLabel jl_id) {
	this.jl_id = jl_id;
}




public JLabel getJl_pw() {
	return jl_pw;
}




public void setJl_pw(JLabel jl_pw) {
	this.jl_pw = jl_pw;
}




public JTextField getJtf_id() {
	return jtf_id;
}




public void setJtf_id(JTextField jtf_id) {
	this.jtf_id = jtf_id;
}




public JPasswordField getJpf_pass() {
	return jpf_pass;
}




public void setJpf_pass(JPasswordField jpf_pass) {
	this.jpf_pass = jpf_pass;
}




public JButton getJbt_login() {
	return jbt_login;
}




public void setJbt_login(JButton jbt_login) {
	this.jbt_login = jbt_login;
}




public JButton getJbt_join() {
	return jbt_join;
}




public void setJbt_join(JButton jbt_join) {
	this.jbt_join = jbt_join;
}




public static void main(String[] args) {
      new LoginView();
   }//main

}//class