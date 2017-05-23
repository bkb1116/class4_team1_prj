package kr.co.sist.movie.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.movie.view.ResChkView;

public class ResChkEvt extends WindowAdapter implements ActionListener {
	
	private ResChkView rcv;
	
	public ResChkEvt(ResChkView rcv) {
		this.rcv=rcv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
