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
	 * ���� �̹��� ���ε� 
	 */
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
	
	/**
	 * �û�ȸ �߰� ������ �ʱ�ȭ
	 */
	private void addMovieClose(){
		// �̹��� �ʱ�ȭ
		String path = "C:/dev/AnnMovie_prj/class4_team1_prj/AnnMovie_prj/img/basic.jpg";
		ImageIcon icon = new ImageIcon(path);
		
		// �ؽ�Ʈ �ʱ�ȭ
		mv.getJbt_img().setIcon(icon);
		mv.getJtf_movieName().setText("");
		mv.getJtf_date().setText("");
		mv.getJta_movieInfo().setText("");
		
	}//addMovieClose
	
	
	/**
	 * �û�ȸ ����ϴ� method
	 */
	public void addMovie() {
		// �̹��� ��� ��������
		ImageIcon icon = (ImageIcon) mv.getJbt_img().getIcon();
		File file = new File(icon.toString());
		String tempFile = file.getName();
		
		
		// �ʱ� �̹���
		if (tempFile.equals("default.jpg")) {
			JOptionPane.showMessageDialog(mv, "�⺻�̹����� ����� �� �����ϴ�");
			return;
		} // end if
		
		// ������ �����ġ�� ����.
		try {
			// �������� ��.��
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream("C:/dev/AnnMovie_prj/class4_team1_prj/AnnMovie_prj/img/"+file.getName());
			
			// �ϵ��ũ�� �ѹ�ȸ������ �д� ũ�⸸ŭ
			// �о���̵��� �ؼ� �ӵ��� �����Ѵ�.
			byte[] temp = new byte[512];
			
			int readData = 0;
			while((readData=fis.read(temp))!=-1 ){
				fos.write(temp,0,readData);
			}//while
			
			// ���� ����
			fos.flush();
			
			// ���� ����
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
			JOptionPane.showMessageDialog(mv, "�û�ȸ �����߰� �Ϸ�");
			
			// �ʱ�ȭ
			addMovieClose();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch
		
	}//insert_movieRegis
	
	/**
	 * �¼� ���� �ȵȻ�� ����Ʈ
	 */
	private void reserveNot(){
		try {
			List<UserResNotVO> notList = md.select_reserveNot();
			UserResNotVO urnv = null;
			DefaultListModel<String> list = mv.getDlm_seatBefore();
			
			// ����Ʈ �ʱ�ȭ ��Ų�� �� �ֱ�
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
	 * �¼����� �Ϸ�� ��� ���
	 */
	private void reserveYes(){
		try {
			List<UserResYesVO> yesList = md.select_reserveYes();
			UserResYesVO urnv = null;
			DefaultListModel<String> list = mv.getDlm_seatAfter();
			
			// ����Ʈ �ʱ�ȭ ��Ų�� �� �ֱ�
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
	 * ��ü ȸ�� ���
	 */
	private void totalUser(){
		try {
			List<UserTotalVO> totalList = md.select_totalUser();
			UserTotalVO utv = null;
			DefaultListModel<String> list = mv.getDlm_memberInfo();
			
			// ����Ʈ �ʱ�ȭ ��Ų�� �� �ֱ�
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
	 * Ư�� ȸ�� �˻�
	 */
	private void searchUser(){
		try {
			List<UserTotalVO> searchList = md.select_searchUser(mv.getJtf_userId().getText());
			UserTotalVO utv = null;
			DefaultListModel<String> list = mv.getDlm_memberInfo();
			
			// ����Ʈ �ʱ�ȭ ��Ų�� �� �ֱ�
			list.clear();
			String[] str = null ;
			
			if(searchList.size()==0){
				list.addElement("�˻������ �����ϴ�");
			}//end if
			
			for (int i = 0; i < searchList.size(); i++) {
				str = new String[searchList.size()] ; 
				utv = searchList.get(i);
				str[i]=utv.getName()+" / "+utv.getId()+" / "+utv.getGender()+" / "
				+utv.getPhone()+" / "+utv.getEmail(); 
				list.addElement(str[i]);
			}// for
			
			// �Է�â �ʱ�ȭ
			mv.getJtf_userId().setText("");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
	}// searchUser
	
	
	
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
		
		// ����� �˻�
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
