package kr.co.sist.movie.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.movie.view.LoginView;

public class LoginEvt extends WindowAdapter implements ActionListener {
	
	private LoginView lv;
	
	public LoginEvt(LoginView lv) {
		this.lv=lv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
