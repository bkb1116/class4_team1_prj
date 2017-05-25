package kr.co.sist.movie.evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.movie.dao.ManagerDAO;
import kr.co.sist.movie.view.ManagerView;
import kr.co.sist.movie.vo.MovieRegisVO;

public class ManagerEvt extends WindowAdapter implements ActionListener, ItemListener {
	
	private ManagerView mv;
	public ManagerEvt(ManagerView mv) {
		this.mv=mv;
	}

	// ���� �̹��� ���ε�
	public void setImg() {
		FileDialog fdImage = new FileDialog(mv, "��ȭ �̹��� ����", FileDialog.LOAD);
		fdImage.setVisible(true);

		String path = fdImage.getDirectory();
		String file = fdImage.getFile();

		if (file != null) {
			// ���� Ȯ���ڴ� �̹����� �����ϰ�
			String validFile = "jpg,gif,png,bmp,psd";
			if (!validFile.contains(file.substring(file.lastIndexOf(".") + 1))) {
				JOptionPane.showMessageDialog(mv, "�����Ѱ� �Ұ���. �̹��� ���ϸ� ������");
				return;
			} // end if
			// �̹��� ������ ����
			ImageIcon temp = new ImageIcon(path + file);
			// ������ �̹��� ����
			mv.getJbt_img().setIcon(temp);
		} // end if
	}// setImg
	
	private void addMovieClose(){
		mv.getJtf_movieName().setText("");
		mv.getJtf_date().setText("");
		mv.getJta_movieInfo().setText("");
		
		mv.dispose();
	}//addMovieClose
	
	// �û�ȸ ���
	public void addMovie() {
		// �̹��� ��� ��������
		ImageIcon icon = (ImageIcon) mv.getJbt_img().getIcon();
		File file = new File(icon.toString());
		String tempFile = file.getName();
		
		String movie_title = mv.getJtf_movieName().getText();
		String movie_date = mv.getJtf_date().getText();	
		String movie_info = mv.getJta_movieInfo().getText();
	
		MovieRegisVO mrv = new MovieRegisVO();
		ManagerDAO md = ManagerDAO.getInstance();
		
		try {
			
			mrv.setMovieName(movie_title);
			mrv.setMovieDate(movie_date);
			mrv.setMovieInfo(movie_info);
			mrv.setMovieImg(tempFile);
			
			md.insert_movieRegis(mrv);
			
			JOptionPane.showMessageDialog(mv, "�û�ȸ �����߰� �Ϸ�");
			
			// â�ݱ�
			addMovieClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch
		
		
		
	}//insert_movieRegis
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// �������� ��ư
		if(ae.getSource() == mv.getJbt_noticeRegis()){

		}// end if
		
		// ��ȭ �̹��� ���
		if(ae.getSource()== mv.getJbt_img() ){
			 setImg();
		}// end if
		
		// �û�ȸ ���
		if(ae.getSource() == mv.getJbt_insert()){
			addMovie();
		}// end if	
		
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		
	}
}// class
