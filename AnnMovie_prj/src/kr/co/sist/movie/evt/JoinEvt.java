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

		// 아이디 중복 체크
		boolean idChk = ad.select_overlapChk(id);
		if (idChk) {
			JOptionPane.showMessageDialog(jv, "사용중인 아이디 입니다");

		} else {
			JOptionPane.showMessageDialog(jv, "사용가능한 아이디 입니다");
			flag = true;
		} // end if

		// 아이디가 입력되지 않았을 때
		if (id.equals("")) {
			JOptionPane.showMessageDialog(jv, "아이디는 반드시 입력해주세요");
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

			// 비밀번호,이메일,전화번호,주소,주민번호가 입력되지 않았을 때
			if (pass.equals("") || passChk.equals("")) {
				JOptionPane.showMessageDialog(jv, "비밀번호는 반드시 입력해 주셔야 합니다.");
				return;
			} // end if
			if (email.equals("")) {
				JOptionPane.showMessageDialog(jv, "이메일은 반드시 입력해 주셔야 합니다.");
			} // end if
			if (phone.equals("")) {
				JOptionPane.showMessageDialog(jv, "전화번호는 반드시 입력해 주셔야 합니다");
			} // end if
			if (addr.equals("")) {
				JOptionPane.showMessageDialog(jv, "주소는 반드시 입력해 주셔야 합니다.");
			} // end if
			if (ssn1.equals("") || ssn2.equals("")) {
				JOptionPane.showMessageDialog(jv, "주민번호는 반드시 입력해 주셔야 합니다.");
			} // end if

			// 주민번호의 자릿수가 맞지 않을 때 뜨는 알림창
			if (ssn1.length() < 6) {
				JOptionPane.showMessageDialog(jv, "주민번호 앞자리는 6자리 입니다.");
			} // end if
			if (ssn2.length() < 7) {
				JOptionPane.showMessageDialog(jv, "주민번호 뒷자리는 7자리 입니다.");
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
			JOptionPane.showMessageDialog(jv, "아이디 중복체크를 먼저 해주세요.");
		}

	}// addUser

	@Override
	public void actionPerformed(ActionEvent ae) {

		// 중복확인
		if (ae.getSource() == jv.getJbt_repetIdChk()) {
			try {
				joinChk();
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
		} // end if

		// 취소 버튼
		if (ae.getSource() == jv.getJbt_Cancel()) {
			int cancle = JOptionPane.showConfirmDialog(jv, "가입을 취소하시겠습니까?");
			switch (cancle) {
			case JOptionPane.OK_OPTION:
				jv.dispose();
			}// end switch
		} // end if

		// 가입신청 버튼
		if (ae.getSource() == jv.getJbt_Join()) {
			try {
				addUser();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// JOptionPane.showMessageDialog(jv, "가입이 완료되었습니다. 로그인 해주세요.");
		} // end if

	}// actionPerformed

}// class
