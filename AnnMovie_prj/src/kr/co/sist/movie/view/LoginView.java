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
   //패널 없애고 크기조절하기
   
   
   public LoginView(){
      super("로그인 / 회원가입 창");
      //선언
      jl_id = new JLabel("아이디");
      jl_pw = new JLabel("비밀번호");
      
      jtf_id = new JTextField("");
      
      jpf_pass = new JPasswordField();
      
      jbt_login = new JButton("로그인");
      jbt_join = new JButton("회원가입");
      //배치
      //라벨
      add(jl_id);//아이디
      add(jl_pw); //비번
      //TF,, TP배치
      add(jtf_id);//아이디
      add(jpf_pass);//패스워드
      
      //버튼
      add(jbt_login);//로그인버튼
      add(jbt_join);//회원가입버튼
      
      // 이벤트 등록
      LoginEvt le=new LoginEvt(this);
      
      //크기설정
      setLayout(null);

      //크기설정 다시해주기
      jl_id.setBounds(40,25,50,30);
      jl_pw.setBounds(40,60,60,30);
      jtf_id.setBounds(110,25,130,30);
      jpf_pass.setBounds(110,60,130,30);
      
      
      jbt_login.setBounds(40,100,90,40);
      jbt_join.setBounds(145,100,90,40);
      setBounds(30, 150, 300, 200);
      
      LoginEvt loge = new LoginEvt(this);
      jbt_login.addActionListener(loge);
      
      //가시화
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