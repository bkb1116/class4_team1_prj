package kr.co.sist.movie.evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kr.co.sist.movie.dao.ManagerDAO;
import kr.co.sist.movie.view.ManagerView;
import kr.co.sist.movie.vo.MovieRegisVO;
import kr.co.sist.movie.vo.UserResNotVO;
import kr.co.sist.movie.vo.UserResYesVO;
import kr.co.sist.movie.vo.UserTotalVO;

public class ManagerEvt extends WindowAdapter implements ActionListener,ListSelectionListener {
	
	private ManagerView mv; // VIEW
	private ManagerDAO md; // DAO 
	public ManagerEvt(ManagerView mv) {
		this.mv=mv;
		this.md = ManagerDAO.getInstance();
		reserveNot();
		reserveYes();
		totalUser();
	}
	
	/**
	 * 파일 이미지 업로드 
	 */
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
	
	/**
	 * 시사회 추가 설정값 초기화
	 */
	private void addMovieClose(){
		// 이미지 초기화
		String path = "C:/dev/AnnMovie_prj/class4_team1_prj/AnnMovie_prj/img/basic.jpg";
		ImageIcon icon = new ImageIcon(path);
		
		// 텍스트 초기화
		mv.getJbt_img().setIcon(icon);
		mv.getJtf_movieName().setText("");
		mv.getJtf_date().setText("");
		mv.getJta_movieInfo().setText("");
		
	}//addMovieClose
	
	
	/**
	 * 시사회 등록하는 method
	 */
	public void addMovie() {
		// 이미지 경로 가져오기
		ImageIcon icon = (ImageIcon) mv.getJbt_img().getIcon();
		File file = new File(icon.toString());
		String tempFile = file.getName();
		
		
		// 초기 이미지
		if (tempFile.equals("default.jpg")) {
			JOptionPane.showMessageDialog(mv, "기본이미지는 사용할 수 없습니다");
			return;
		} // end if
		
		// 파일을 사용위치에 복붙.
		try {
			// 원본파일 복.붙
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream("C:/dev/AnnMovie_prj/class4_team1_prj/AnnMovie_prj/img/"+file.getName());
			
			// 하드디스크가 한번회전에서 읽는 크기만큼
			// 읽어들이도록 해서 속도를 개선한다.
			byte[] temp = new byte[512];
			
			int readData = 0;
			while((readData=fis.read(temp))!=-1 ){
				fos.write(temp,0,readData);
			}//while
			
			// 파일 분출
			fos.flush();
			
			// 연결 끊기
			if(fis!=null){fis.close();}// end if
			if(fos!=null){fos.close();} // end if
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // catch

		String movie_title = mv.getJtf_movieName().getText();
		String movie_date = mv.getJtf_date().getText();	
		String movie_info = mv.getJta_movieInfo().getText();
	
		MovieRegisVO mrv = new MovieRegisVO();

		try {
			mrv.setMovieName(movie_title);
			mrv.setMovieDate(movie_date);
			mrv.setMovieInfo(movie_info);
			mrv.setMovieImg(tempFile);
			
			md.insert_movieRegis(mrv);
			JOptionPane.showMessageDialog(mv, "시사회 정상추가 완료");
			
			// 초기화
			addMovieClose();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch
		
	}//insert_movieRegis
	
	/**
	 * 좌석 배정 안된사람 리스트
	 */
	private void reserveNot(){
		try {
			List<UserResNotVO> notList = md.select_reserveNot();
			UserResNotVO urnv = null;
			DefaultListModel<String> list = mv.getDlm_seatBefore();
			
			// 리스트 초기화 시킨후 값 넣기
			list.clear();
			String[] str = null ; 
			for (int i = 0; i < notList.size(); i++) {
				urnv = notList.get(i);
				str = new String[notList.size()] ; 
				str[i]=urnv.getName()+","+urnv.getId()+","+urnv.getSeatQuan(); 
				list.addElement(str[i]);
			}// for
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch
	}// reserveNot
	
	/**
	 * 좌석배정 완료된 사람 목록
	 */
	private void reserveYes(){
		try {
			List<UserResYesVO> yesList = md.select_reserveYes();
			UserResYesVO urnv = null;
			DefaultListModel<String> list = mv.getDlm_seatAfter();
			
			// 리스트 초기화 시킨후 값 넣기
			list.clear();
			String[] str = null ; 
			for (int i = 0; i < yesList.size(); i++) {
				str = new String[yesList.size()] ; 
				urnv = yesList.get(i);
				str[i]=urnv.getName()+","+urnv.getId()+","+urnv.getSeatNum(); 
				list.addElement(str[i]);
			}// for
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// reserveNot
	
	
	/**
	 * 전체 회원 목록
	 */
	private void totalUser(){
		try {
			List<UserTotalVO> totalList = md.select_totalUser();
			UserTotalVO utv = null;
			DefaultListModel<String> list = mv.getDlm_memberInfo();
			
			// 리스트 초기화 시킨후 값 넣기
			list.clear();
			String[] str = null ; 
			for (int i = 0; i < totalList.size(); i++) {
				str = new String[totalList.size()] ; 
				utv = totalList.get(i);
				str[i]=utv.getName()+" / "+utv.getId()+" / "+utv.getGender()+" / "
				+utv.getPhone()+" / "+utv.getEmail(); 
				list.addElement(str[i]);
			}// for
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// reserveNot
	
	
	/**
	 * 특정 회원 검색
	 */
	private void searchUser(){
		try {
			List<UserTotalVO> searchList = md.select_searchUser(mv.getJtf_userId().getText());
			UserTotalVO utv = null;
			DefaultListModel<String> list = mv.getDlm_memberInfo();
			
			// 리스트 초기화 시킨후 값 넣기
			list.clear();
			String[] str = null ;
			
			if(searchList.size()==0){
				list.addElement("검색결과가 없습니다");
			}//end if
			
			for (int i = 0; i < searchList.size(); i++) {
				str = new String[searchList.size()] ; 
				utv = searchList.get(i);
				str[i]=utv.getName()+" / "+utv.getId()+" / "+utv.getGender()+" / "
				+utv.getPhone()+" / "+utv.getEmail(); 
				list.addElement(str[i]);
			}// for
			
			// 입력창 초기화
			mv.getJtf_userId().setText("");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// searchUser
	
	
	
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
		
		// 사용자 검색
		if(ae.getSource() == mv.getJbt_search()){
			searchUser();
		}// end if	
		
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		//System.exit(0);
	}// windowClosing

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if(lse.getSource() == mv.getJl_seatBefore()){
			String list_item = mv.getJl_seatBefore().getSelectedValue();
			String[] arr = list_item.split(",");
			
			mv.getJtf_userId2().setText(arr[1]);
		}// end if
	}//valueChanged
	

}// class
