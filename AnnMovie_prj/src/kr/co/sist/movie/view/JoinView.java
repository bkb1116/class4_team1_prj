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
		super("ȸ������");
		// ��
		jlb_id = new JLabel("���̵�");
		jlb_pass = new JLabel("��й�ȣ");
		jlb_passChk = new JLabel("��й�ȣ Ȯ��");
		jlb_email = new JLabel("�̸���");
		jlb_phone = new JLabel("��ȭ��ȣ");
		jlb_addr = new JLabel("�ּ�");
		jlb_ssn = new JLabel("�ֹε�Ϲ�ȣ");
		jlb_hyphen = new JLabel("-");
		// �ؽ�Ʈ�ʵ�
		jtf_id = new JTextField();
		jtf_ssn1 = new JTextField();
		jtf_ssn1.setDocument(new JTextFieldLimit(6));
		jtf_email = new JTextField();
		jtf_Phone = new JTextField();
		jtf_addr = new JTextField();
		// ��ư
		jbt_Join = new JButton("���Խ�û");
		jbt_Cancel = new JButton("���");
		jbt_repetIdChk = new JButton("�ߺ�Ȯ��");
		// �н������ʵ�.
		jpf_pass = new JPasswordField();
		jpf_passChk = new JPasswordField();
		jpf_ssn2 = new JPasswordField();
		jpf_ssn2.setDocument(new JPassFieldLimit(7));
		// �г�
		jp_title = new JPanel();
		jp_Content = new JPanel();
		// ��ġ
		jp_title.add(jlb_id);
		jp_title.add(jlb_pass);
		jp_title.add(jlb_passChk);
		jp_title.add(jlb_email);
		jp_title.add(jlb_phone);
		jp_title.add(jlb_addr);
		jp_title.add(jlb_ssn);
		// ���Է��г� ��ġ
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
		// ������ġ
		add(jp_title);
		add(jp_Content);
		add(jbt_Join);
		add(jbt_Cancel);
		
		// �̺�Ʈ ���
		JoinEvt je=new JoinEvt(this);
		
		// ũ�⼳��
		setLayout(null);
		jp_title.setLayout(new GridLayout(7, 1));// �гα׸���
		jp_Content.setLayout(new GridLayout(5, 1));// �гα׸���
		jbt_repetIdChk.setBounds(200, 35, 100, 30);// �ߺ�Ȯ�ι�ư
		jtf_id.setBounds(120, 35, 80, 30);// ���̵��Է�â
		jp_title.setBounds(20, 30, 100, 200); // ���ʾ��̵�~�������� �г�
		jp_Content.setBounds(120, 65, 180, 135);// ������� ���������� �г�
		jbt_Join.setBounds(30, 240, 120, 30);// ���Թ�ư
		jtf_ssn1.setBounds(120, 200, 80, 25);// ���ֹ�
		jlb_hyphen.setBounds(207, 200, 30, 25);// ����Ǭ
		jpf_ssn2.setBounds(220, 200, 80, 25);// ���ֹ�
		jbt_Cancel.setBounds(170, 240, 120, 30);// ��ҹ�ư
		setBounds(0, 0, 350, 350);
		setVisible(true);

	}// SignUpForm

	public static void main(String[] args) {
		new JoinView();
	}// main

}// class