package kr.co.sist.movie.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Document;

import kr.co.sist.movie.evt.JoinEvt;

public class JoinView extends JFrame {
	private JLabel jlb_id, jlb_pass, jlb_passChk, jlb_email, jlb_phone, jlb_addr, jlb_ssn, jlb_hyphen;
	private JTextField jtf_id, jtf_email, jtf_Phone, jtf_addr, jtf_ssn1;
	private JPasswordField jpf_pass, jpf_passChk, jpf_ssn2;

	private JButton jbt_Cancel, jbt_Join, jbt_repetIdChk;
	private JPanel jp_title, jp_Content;
	private int limit;

	// private textfield.setDocument(new JTextFieldLimit(10));
	public JoinView() {
		super("회원가입");
		// 라벨
		jlb_id = new JLabel("아이디");
		jlb_pass = new JLabel("비밀번호");
		jlb_passChk = new JLabel("비밀번호 확인");
		jlb_email = new JLabel("이메일");
		jlb_phone = new JLabel("전화번호");
		jlb_addr = new JLabel("주소");
		jlb_ssn = new JLabel("주민등록번호");
		jlb_hyphen = new JLabel("-");
		// 텍스트필드
		jtf_id = new JTextField();
		jtf_ssn1 = new JTextField();
		jtf_ssn1.setDocument(new JTextFieldLimit(6));
		jtf_email = new JTextField();
		jtf_Phone = new JTextField();
		jtf_addr = new JTextField();
		// 버튼
		jbt_Join = new JButton("가입신청");
		jbt_Cancel = new JButton("취소");
		jbt_repetIdChk = new JButton("중복확인");
		// 패스워드필드.
		jpf_pass = new JPasswordField();
		jpf_passChk = new JPasswordField();
		jpf_ssn2 = new JPasswordField();
		jpf_ssn2.setDocument(new JPassFieldLimit(7));
		// 패널
		jp_title = new JPanel();
		jp_Content = new JPanel();
		// 배치
		jp_title.add(jlb_id);
		jp_title.add(jlb_pass);
		jp_title.add(jlb_passChk);
		jp_title.add(jlb_email);
		jp_title.add(jlb_phone);
		jp_title.add(jlb_addr);
		jp_title.add(jlb_ssn);
		// 값입력패널 배치
		add(jtf_id);
		add(jbt_repetIdChk);
		jp_Content.add(jpf_pass);
		jp_Content.add(jpf_passChk);
		jp_Content.add(jtf_Phone);
		jp_Content.add(jtf_email);
		jp_Content.add(jtf_addr);
		add(jtf_ssn1);
		add(jpf_ssn2);
		add(jlb_hyphen);
		// 최종배치
		add(jp_title);
		add(jp_Content);
		add(jbt_Join);
		add(jbt_Cancel);
		
		// 이벤트 등록
		JoinEvt je=new JoinEvt(this);
		
		// 크기설정
		setLayout(null);
		jp_title.setLayout(new GridLayout(7, 1));// 패널그리드
		jp_Content.setLayout(new GridLayout(5, 1));// 패널그리드
		jbt_repetIdChk.setBounds(200, 35, 100, 30);// 중복확인버튼
		jtf_id.setBounds(120, 35, 80, 30);// 아이디입력창
		jp_title.setBounds(20, 30, 100, 200); // 앞쪽아이디~마지막라벨 패널
		jp_Content.setBounds(120, 65, 180, 135);// 비번부터 마지막까지 패널
		jbt_Join.setBounds(30, 240, 120, 30);// 가입버튼
		jtf_ssn1.setBounds(120, 200, 80, 25);// 앞주민
		jlb_hyphen.setBounds(207, 200, 30, 25);// 하이푼
		jpf_ssn2.setBounds(220, 200, 80, 25);// 뒷주민
		jbt_Cancel.setBounds(170, 240, 120, 30);// 취소버튼
		setBounds(0, 0, 350, 350);
		setVisible(true);

	}// SignUpForm

	public static void main(String[] args) {
		new JoinView();
	}// main

}// class