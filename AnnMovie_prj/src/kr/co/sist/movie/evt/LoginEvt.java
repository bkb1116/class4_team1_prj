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
		// 입력한 아이디와 비번을 테이블에서 받아온다.
		String userId = lv.getJtf_id().getText().trim();
		String userPw = new String(lv.getJpf_pass().getPassword()).trim();

		if (userId.equals("")) {// 아이디가 공백이면
			JOptionPane.showMessageDialog(lv, "아이디를 입력해주세요");
			lv.getJtf_id().requestFocus();
			return;
		} // end if

		if (userPw.equals("")) { // 비밀번호가 공백이라면
			JOptionPane.showMessageDialog(lv, "비밀번호를 입력해주세요");
			lv.getJpf_pass().requestFocus();
			return;
		} // end if
		
		// 텍스트필드에 입력한 값이 DB에 들어있는 id와 비밀번호가 일치하면
		// 메인창을 띄워줌
		AnnDAO ad = AnnDAO.getInstance();
		LoginVO lvo = new LoginVO();
		lvo.setId(userId);
		lvo.setPassword(userPw);

		try {
			boolean flag;
			flag = ad.select_memberChk(lvo);
			if (flag) {
				new MainView();
				lv.dispose(); // 로그인창을 닫아 다음으로 진행하도록 만든다.
			} else {
				JOptionPane.showMessageDialog(lv, "아이디와 비밀번호를 확인해주세요");
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