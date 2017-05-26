package kr.co.sist.movie.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import kr.co.sist.movie.dao.AnnDAO;
import kr.co.sist.movie.view.JoinView;
import kr.co.sist.movie.view.LoginView;
import kr.co.sist.movie.view.MainView;
import kr.co.sist.movie.vo.LoginVO;

public class LoginEvt extends WindowAdapter implements ActionListener {

	private LoginView lv;

	public LoginEvt(LoginView lv) {
		this.lv = lv;

	}// LoginEvt

	private void userChk() {
		// �Է��� ���̵�� ����� ���̺��� �޾ƿ´�.
		String userId = lv.getJtf_id().getText().trim();
		String userPw = new String(lv.getJpf_pass().getPassword()).trim();

		if (userId.equals("")) {// ���̵� �����̸�
			JOptionPane.showMessageDialog(lv, "���̵� �Է����ּ���");
			lv.getJtf_id().requestFocus();
			return;
		} // end if

		if (userPw.equals("")) { // ��й�ȣ�� �����̶��
			JOptionPane.showMessageDialog(lv, "��й�ȣ�� �Է����ּ���");
			lv.getJpf_pass().requestFocus();
			return;
		} // end if
		
		// �ؽ�Ʈ�ʵ忡 �Է��� ���� DB�� ����ִ� id�� ��й�ȣ�� ��ġ�ϸ�
		// ����â�� �����
		AnnDAO ad = AnnDAO.getInstance();
		LoginVO lvo = new LoginVO();
		lvo.setId(userId);
		lvo.setPassword(userPw);

		try {
			boolean flag;
			flag = ad.select_memberChk(lvo);
			if (flag) {
				new MainView();
				lv.dispose(); // �α���â�� �ݾ� �������� �����ϵ��� �����.
			} else {
				JOptionPane.showMessageDialog(lv, "���̵�� ��й�ȣ�� Ȯ�����ּ���");
			} // end if
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch

	}// processLogin

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lv.getJbt_login()) {
			userChk();
		} // end if
		
		if (ae.getSource() == lv.getJbt_join()) {
			new JoinView();
			lv.dispose();
		} // end if
	}// actionPerformed

}// class