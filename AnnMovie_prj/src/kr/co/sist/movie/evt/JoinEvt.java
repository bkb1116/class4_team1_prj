package kr.co.sist.movie.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.movie.dao.AnnDAO;
import kr.co.sist.movie.view.JTextFieldLimit;
import kr.co.sist.movie.view.JoinView;
import kr.co.sist.movie.vo.JoinVO;

public class JoinEvt extends WindowAdapter implements ActionListener {

	private JoinView jv;
	private boolean flag = false;

	public JoinEvt(JoinView jv) {
		this.jv = jv;
	}// JoinEvt

	public void joinChk() throws SQLException {
		AnnDAO ad = AnnDAO.getInstance();
		String id = jv.getJtf_id().getText().trim();

		// ���̵� �ߺ� üũ
		boolean idChk = ad.select_overlapChk(id);
		if (idChk) {
			JOptionPane.showMessageDialog(jv, "������� ���̵� �Դϴ�");

		} else {
			JOptionPane.showMessageDialog(jv, "��밡���� ���̵� �Դϴ�");
			flag = true;
		} // end if

		// ���̵� �Էµ��� �ʾ��� ��
		if (id.equals("")) {
			JOptionPane.showMessageDialog(jv, "���̵�� �ݵ�� �Է����ּ���");
			return;
		} // end if
	}// joinChk

	public void addUser() throws SQLException {
		if (flag) {

			String id = jv.getJtf_id().getText();
			String pass = jv.getJpf_pass().getText();
			String passChk = jv.getJpf_passChk().getPassword().toString();
			String email = jv.getJtf_email().getText().trim();
			String phone = jv.getJtf_Phone().getText().trim();
			String addr = jv.getJtf_addr().getText().trim();
			String ssn1 = jv.getJtf_ssn1().getText().trim();
			String ssn2 = jv.getJtf_ssn2().getText().toString();

			// ��й�ȣ,�̸���,��ȭ��ȣ,�ּ�,�ֹι�ȣ�� �Էµ��� �ʾ��� ��
			if (pass.equals("") || passChk.equals("")) {
				JOptionPane.showMessageDialog(jv, "��й�ȣ�� �ݵ�� �Է��� �ּž� �մϴ�.");
				return;
			} // end if
			if (email.equals("")) {
				JOptionPane.showMessageDialog(jv, "�̸����� �ݵ�� �Է��� �ּž� �մϴ�.");
			} // end if
			if (phone.equals("")) {
				JOptionPane.showMessageDialog(jv, "��ȭ��ȣ�� �ݵ�� �Է��� �ּž� �մϴ�");
			} // end if
			if (addr.equals("")) {
				JOptionPane.showMessageDialog(jv, "�ּҴ� �ݵ�� �Է��� �ּž� �մϴ�.");
			} // end if
			if (ssn1.equals("") || ssn2.equals("")) {
				JOptionPane.showMessageDialog(jv, "�ֹι�ȣ�� �ݵ�� �Է��� �ּž� �մϴ�.");
			} // end if

			// �ֹι�ȣ�� �ڸ����� ���� ���� �� �ߴ� �˸�â
			if (ssn1.length() < 6) {
				JOptionPane.showMessageDialog(jv, "�ֹι�ȣ ���ڸ��� 6�ڸ� �Դϴ�.");
			} // end if
			if (ssn2.length() < 7) {
				JOptionPane.showMessageDialog(jv, "�ֹι�ȣ ���ڸ��� 7�ڸ� �Դϴ�.");
			} // end if

			AnnDAO ad = AnnDAO.getInstance();

			System.out.println(id);
			System.out.println(pass);
			System.out.println(passChk);
			System.out.println(email);
			System.out.println(phone);
			System.out.println(addr);
			System.out.println(ssn1);
			System.out.println(ssn2);

			JoinVO jv = new JoinVO();
			jv.setId(id);
			jv.setPasswd(pass);
			jv.setEmail(email);
			//jv.setName(name);
			jv.setPhone(phone);
			jv.setAddr(addr);
			jv.setSsn(ssn1);

			ad.insert_join(jv);

		} else {
			JOptionPane.showMessageDialog(jv, "���̵� �ߺ�üũ�� ���� ���ּ���.");
		}

	}// addUser

	@Override
	public void actionPerformed(ActionEvent ae) {

		// �ߺ�Ȯ��
		if (ae.getSource() == jv.getJbt_repetIdChk()) {
			try {
				joinChk();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
		} // end if

		// ��� ��ư
		if (ae.getSource() == jv.getJbt_Cancel()) {
			int cancle = JOptionPane.showConfirmDialog(jv, "������ ����Ͻðڽ��ϱ�?");
			switch (cancle) {
			case JOptionPane.OK_OPTION:
				jv.dispose();
			}// end switch
		} // end if

		// ���Խ�û ��ư
		if (ae.getSource() == jv.getJbt_Join()) {
			try {
				addUser();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// JOptionPane.showMessageDialog(jv, "������ �Ϸ�Ǿ����ϴ�. �α��� ���ּ���.");
		} // end if

	}// actionPerformed

}// class
