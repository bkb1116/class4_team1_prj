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
	private JLabel jlb_id, jlb_pass, jlb_passChk, jlb_email, jlb_phone, jlb_addr, jlb_ssn, jlb_hyphen, jlb_name;
	private JTextField jtf_id,jtf_email, jtf_Phone, jtf_addr, jtf_ssn1,jtf_ssn2;
	private JPasswordField jpf_pass, jpf_passChk;

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
		jtf_ssn2 = new JTextField();
		jtf_ssn2.setDocument(new JPassFieldLimit(7));
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
		add(jtf_ssn2);
		add(jlb_hyphen);
		// ������ġ
		add(jp_title);
		add(jp_Content);
		add(jbt_Join);
		add(jbt_Cancel);
		
		// �̺�Ʈ ���
		JoinEvt je=new JoinEvt(this);
		 jbt_repetIdChk.addActionListener(je);
		 jbt_Join.addActionListener(je);
		
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
		jtf_ssn2.setBounds(220, 200, 80, 25);// ���ֹ�
		jbt_Cancel.setBounds(170, 240, 120, 30);// ��ҹ�ư
		setBounds(0, 0, 350, 350);
		setVisible(true);

	}// SignUpForm

	public JLabel getJlb_id() {
		return jlb_id;
	}

	public void setJlb_id(JLabel jlb_id) {
		this.jlb_id = jlb_id;
	}

	public JLabel getJlb_pass() {
		return jlb_pass;
	}

	public void setJlb_pass(JLabel jlb_pass) {
		this.jlb_pass = jlb_pass;
	}

	public JLabel getJlb_passChk() {
		return jlb_passChk;
	}

	public void setJlb_passChk(JLabel jlb_passChk) {
		this.jlb_passChk = jlb_passChk;
	}

	public JLabel getJlb_email() {
		return jlb_email;
	}

	public void setJlb_email(JLabel jlb_email) {
		this.jlb_email = jlb_email;
	}

	public JLabel getJlb_phone() {
		return jlb_phone;
	}

	public void setJlb_phone(JLabel jlb_phone) {
		this.jlb_phone = jlb_phone;
	}

	public JLabel getJlb_addr() {
		return jlb_addr;
	}

	public void setJlb_addr(JLabel jlb_addr) {
		this.jlb_addr = jlb_addr;
	}

	public JLabel getJlb_ssn() {
		return jlb_ssn;
	}

	public void setJlb_ssn(JLabel jlb_ssn) {
		this.jlb_ssn = jlb_ssn;
	}

	public JLabel getJlb_hyphen() {
		return jlb_hyphen;
	}

	public void setJlb_hyphen(JLabel jlb_hyphen) {
		this.jlb_hyphen = jlb_hyphen;
	}

	public JTextField getJtf_id() {
		return jtf_id;
	}

	public void setJtf_id(JTextField jtf_id) {
		this.jtf_id = jtf_id;
	}

	public JTextField getJtf_email() {
		return jtf_email;
	}

	public void setJtf_email(JTextField jtf_email) {
		this.jtf_email = jtf_email;
	}

	public JTextField getJtf_Phone() {
		return jtf_Phone;
	}

	public void setJtf_Phone(JTextField jtf_Phone) {
		this.jtf_Phone = jtf_Phone;
	}

	public JTextField getJtf_addr() {
		return jtf_addr;
	}

	public void setJtf_addr(JTextField jtf_addr) {
		this.jtf_addr = jtf_addr;
	}

	public JTextField getJtf_ssn1() {
		return jtf_ssn1;
	}

	public void setJtf_ssn1(JTextField jtf_ssn1) {
		this.jtf_ssn1 = jtf_ssn1;
	}

	public JPasswordField getJpf_pass() {
		return jpf_pass;
	}

	public void setJpf_pass(JPasswordField jpf_pass) {
		this.jpf_pass = jpf_pass;
	}

	public JPasswordField getJpf_passChk() {
		return jpf_passChk;
	}

	public void setJpf_passChk(JPasswordField jpf_passChk) {
		this.jpf_passChk = jpf_passChk;
	}

	public JTextField getJtf_ssn2() {
		return jtf_ssn2;
	}

	public void setJtf_ssn2(JTextField jpf_ssn2) {
		this.jtf_ssn2 = jtf_ssn2;
	}

	public JButton getJbt_Cancel() {
		return jbt_Cancel;
	}

	public void setJbt_Cancel(JButton jbt_Cancel) {
		this.jbt_Cancel = jbt_Cancel;
	}

	public JButton getJbt_Join() {
		return jbt_Join;
	}

	public void setJbt_Join(JButton jbt_Join) {
		this.jbt_Join = jbt_Join;
	}

	public JButton getJbt_repetIdChk() {
		return jbt_repetIdChk;
	}

	public void setJbt_repetIdChk(JButton jbt_repetIdChk) {
		this.jbt_repetIdChk = jbt_repetIdChk;
	}

	public JPanel getJp_title() {
		return jp_title;
	}

	public void setJp_title(JPanel jp_title) {
		this.jp_title = jp_title;
	}

	public JPanel getJp_Content() {
		return jp_Content;
	}

	public void setJp_Content(JPanel jp_Content) {
		this.jp_Content = jp_Content;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public static void main(String[] args) {
		new JoinView();
	}// main

}// class