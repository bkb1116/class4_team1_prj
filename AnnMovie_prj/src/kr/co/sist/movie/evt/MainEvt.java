package kr.co.sist.movie.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.movie.view.MainView;

public class MainEvt extends WindowAdapter implements ActionListener, ItemListener {
	
	private MainView mv;
	
	public MainEvt(MainView mv) {
		this.mv=mv;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
