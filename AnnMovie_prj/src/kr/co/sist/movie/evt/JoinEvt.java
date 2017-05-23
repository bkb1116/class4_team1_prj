package kr.co.sist.movie.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.movie.view.JoinView;

public class JoinEvt extends WindowAdapter implements ActionListener {
	
	private JoinView jv;
	
	public JoinEvt(JoinView jv) {
		this.jv=jv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
