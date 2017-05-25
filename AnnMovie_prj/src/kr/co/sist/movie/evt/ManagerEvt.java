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

	// 파일 이미지 업로드
	public void setImg() {
		FileDialog fdImage = new FileDialog(mv, "영화 이미지 선택", FileDialog.LOAD);
		fdImage.setVisible(true);

		String path = fdImage.getDirectory();
		String file = fdImage.getFile();

		if (file != null) {
			// 파일 확장자는 이미지만 가능하게
			String validFile = "jpg,gif,png,bmp,psd";
			if (!validFile.contains(file.substring(file.lastIndexOf(".") + 1))) {
				JOptionPane.showMessageDialog(mv, "선택한것 불가능. 이미지 파일만 가능함");
				return;
			} // end if
			// 이미지 아이콘 생성
			ImageIcon temp = new ImageIcon(path + file);
			// 생성된 이미지 삽입
			mv.getJbt_img().setIcon(temp);
		} // end if
	}// setImg
	
	private void addMovieClose(){
		mv.getJtf_movieName().setText("");
		mv.getJtf_date().setText("");
		mv.getJta_movieInfo().setText("");
		
		mv.dispose();
	}//addMovieClose
	
	// 시사회 등록
	public void addMovie() {
		// 이미지 경로 가져오기
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
			
			JOptionPane.showMessageDialog(mv, "시사회 정상추가 완료");
			
			// 창닫기
			addMovieClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch
		
		
		
	}//insert_movieRegis
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// 공지사항 버튼
		if(ae.getSource() == mv.getJbt_noticeRegis()){

		}// end if
		
		// 영화 이미지 등록
		if(ae.getSource()== mv.getJbt_img() ){
			 setImg();
		}// end if
		
		// 시사회 등록
		if(ae.getSource() == mv.getJbt_insert()){
			addMovie();
		}// end if	
		
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		
	}
}// class
