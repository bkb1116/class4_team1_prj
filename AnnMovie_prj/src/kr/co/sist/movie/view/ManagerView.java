package kr.co.sist.movie.view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.movie.evt.ManagerEvt;

public class ManagerView extends JFrame {
	private JList<String>jl_seatBefore, jl_seatAfter, jl_memberInfo;
	private JLabel jlb_title,jlb_notice, jlb_screen ;
	private JTextArea jta_movieInfo;
	private JTextField jtf_userId,jtf_movieName, jtf_date,jtf_notice,
							jtf_userId2, jtf_choiceSeat;
	private JButton jbt_img, jbt_noticeRegis, jbt_insert, jbt_search, jbt_seatRegis;
	private JScrollPane  jsp_movieInfo, jsp_memberInfo, jsp_seatBefore, jsp_seatAfter;
	private JTabbedPane jtp;
	private DefaultTableModel dtm_seat;
	private DefaultListModel<String> dlm_seatBefore, dlm_seatAfter, dlm_memberInfo;
	private JTable jt_seat;
	private Font font;

	public ManagerView() {
		super("관리자 페이지");
//		String[] columnNames = {"A","B","C","D","E","F","G" };
		String[] columnNames =new String[7];
		String[][] data = {{"","","","","","",""},{"","","","","","",""},
				{"","","","","","",""},{"","","","","","",""},{"","","","","","",""}};
		// 배열을 넣어줌
		dtm_seat = new DefaultTableModel(data, columnNames) {

			// 어나니머스 이너클래스 (테이블의 편집을 막기위하여 사용)
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

		}; // DefaultTableModel
		jt_seat = new JTable(dtm_seat) {// j테이블에 배열들의 값을 넣어줌.
			
			// 컬럼에 이미지를 넣기위하여 사용하는 메소드
			@Override
			public Class<?> getColumnClass(int column) {
				
				return getValueAt(0, column).getClass();
			}// getColumnClass

		}; // jt_seat
		
		dlm_seatBefore = new DefaultListModel<String>();
		dlm_seatAfter = new DefaultListModel<String>();
		dlm_memberInfo = new DefaultListModel<String>();
		
		//선언
		jlb_title=new JLabel("시사회 등록");
		jlb_notice = new JLabel("공지사항") ;     
		 
		   font = new Font("돋움",Font.BOLD,20);
		   jlb_title.setFont(font);
		   jlb_notice.setFont(font);

		jtf_movieName = new JTextField("영화제목입력");
		jtf_date = new JTextField("영화 일정");
		jbt_img = new JButton("영화이미지");// 이미지넣기
		jta_movieInfo = new JTextArea();// 영화설명써서 넣어주기
		jtf_notice = new JTextField("공지사항입력");
		jtf_userId2 = new JTextField();//예매 할때 사용자 id받아오기
		jtf_choiceSeat = new JTextField();//예매할때 선택한 좌석 받아오기
		jbt_noticeRegis = new JButton("등록");
		jbt_insert = new JButton("영화추가");
		jbt_search = new JButton("검색");
		jbt_seatRegis = new JButton("등록");//공
		jl_seatBefore = new JList<String>(dlm_seatBefore);
		jl_seatAfter = new JList<String>(dlm_seatAfter);
		jl_memberInfo = new JList<String>(dlm_memberInfo);
		jlb_screen= new JLabel("스          크          린");
		// TF 선언
		jtf_userId = new JTextField();
		// 스크롤바선언
		jsp_movieInfo = new JScrollPane(jta_movieInfo);// 전체설명창에 스크롤바넣어줌
		jsp_memberInfo = new JScrollPane(jl_memberInfo); // 회원정보창
		jsp_seatAfter = new JScrollPane(jl_seatAfter);//영화 완료자
		jsp_seatBefore = new JScrollPane(jl_seatBefore);//영화 예매자,인원수
		// 패널선언
		JPanel jpFirst = new JPanel();
		JPanel jpTwo = new JPanel();
		// 탭 선언
		jtp = new JTabbedPane();
		jtp.add("관리자텝", jpFirst);
		jtp.addTab("관리자텝", jpTwo);

		// 배치
		
		jpFirst.add(jlb_title);
		jpFirst.add(jtf_movieName);
		jpFirst.add(jtf_date);
		jpFirst.add(jbt_img);
		jpFirst.add(jsp_movieInfo);
		jpFirst.add(jtf_notice);
		jpFirst.add(jbt_noticeRegis);
		jpFirst.add(jlb_notice);
		jpFirst.add(jbt_insert);
		add(jtp);
		dlm_seatAfter.addElement("장규복구(coolgyu) / A1,A2");
		dlm_seatBefore.addElement("장규복구(coolgyu) / 예매인원 : 2 ");
		dlm_memberInfo.addElement("coolgyu / 장규복구 / 010-1234-4567 / qwer@naver.com");
		// 2번 탭 패널
		jpTwo.add(jbt_search);
		jpTwo.add(jlb_screen);
		jpTwo.add(jbt_seatRegis);
		jpTwo.add(jtf_userId);
		jpTwo.add(jsp_memberInfo);
		jpTwo.add(jsp_seatAfter);
		jpTwo.add(jsp_seatBefore);
		jpTwo.add(jtf_userId2);
		jpTwo.add(jtf_choiceSeat);
		jpTwo.add(jt_seat);

		// 이벤트 등록
		ManagerEvt me=new ManagerEvt(this);
		jbt_noticeRegis.addActionListener(me);
		jbt_insert.addActionListener(me);
		jbt_img.addActionListener(me);
		

		// 크기설정
		jt_seat.getTableHeader().setReorderingAllowed(false);
		// 컬럼의 높이 설정
		jt_seat.setRowHeight(37);
		// 컬럼의 넓이 설정
//		jt_seat.getColumnModel().getColumn(0).setPreferre);;// 번호

		//크기설정
		jlb_title.setBounds(30, 100, 250,40);//시사회 등록 라벨
		jtf_movieName.setBounds(30, 150, 465,30);//영화제목입력창
		jtf_date.setBounds(30,190,465,30);//일정입력창
		jbt_img.setBounds(30, 290, 463,350); //이미지
		jbt_insert.setBounds(400, 650, 93, 30);//영화추가버튼
		jsp_movieInfo.setBounds(30, 230, 465, 45);//영화설명
		jlb_notice.setBounds(30, 10, 250, 40);//공지사항 라벨
		jtf_notice.setBounds(30, 50, 395, 30);//공지사항입력창
		jbt_noticeRegis.setBounds(430, 50, 65, 30);//공지사항등록버튼
		
		jpFirst.setBounds(20, 50, 600, 500);//1번페이지 패널
		
		//두번째 탭 패널
		
		jsp_seatBefore.setBounds(30, 20, 230, 150);// 예매한 사람 리스트
		jsp_seatAfter.setBounds(265, 20, 230, 150); //예메 완료된 사람 리스트
		jlb_screen.setBounds(210,165,100,40);
		jtf_userId2.setBounds(260, 395, 86, 30);// 사용자 id 받아오는 TF
		jtf_choiceSeat.setBounds(350, 395, 70, 30);// 선택좌석 받아오는 TF
		jbt_seatRegis.setBounds(423, 395, 70, 30);// 사용자 id 선택좌석, 등록버튼
		
		jtf_userId.setBounds(330, 640, 90, 30); //사용자 id 검색 TF
		jbt_search.setBounds(423, 640, 70, 30); //사용자id 검색버튼
		jsp_memberInfo.setBounds(30, 437, 465, 200); //회원정보 리스트창
		jt_seat.setBounds(30, 200, 465, 184); //좌석
		jpTwo.setBounds(30, 50, 500, 600);
		jpFirst.setLayout(null);
		jpTwo.setLayout(null);
		setBounds(10, 50, 540, 780);
		// 가시화
		setVisible(true);

	}// MangerForm

	

	public JList<String> getJl_seatBefore() {
		return jl_seatBefore;
	}



	public void setJl_seatBefore(JList<String> jl_seatBefore) {
		this.jl_seatBefore = jl_seatBefore;
	}



	public JList<String> getJl_seatAfter() {
		return jl_seatAfter;
	}



	public void setJl_seatAfter(JList<String> jl_seatAfter) {
		this.jl_seatAfter = jl_seatAfter;
	}



	public JList<String> getJl_memberInfo() {
		return jl_memberInfo;
	}



	public void setJl_memberInfo(JList<String> jl_memberInfo) {
		this.jl_memberInfo = jl_memberInfo;
	}



	public JLabel getJlb_title() {
		return jlb_title;
	}



	public void setJlb_title(JLabel jlb_title) {
		this.jlb_title = jlb_title;
	}



	public JLabel getJlb_notice() {
		return jlb_notice;
	}



	public void setJlb_notice(JLabel jlb_notice) {
		this.jlb_notice = jlb_notice;
	}



	public JLabel getJlb_screen() {
		return jlb_screen;
	}



	public void setJlb_screen(JLabel jlb_screen) {
		this.jlb_screen = jlb_screen;
	}



	public JTextArea getJta_movieInfo() {
		return jta_movieInfo;
	}



	public void setJta_movieInfo(JTextArea jta_movieInfo) {
		this.jta_movieInfo = jta_movieInfo;
	}



	public JTextField getJtf_userId() {
		return jtf_userId;
	}



	public void setJtf_userId(JTextField jtf_userId) {
		this.jtf_userId = jtf_userId;
	}



	public JTextField getJtf_movieName() {
		return jtf_movieName;
	}



	public void setJtf_movieName(JTextField jtf_movieName) {
		this.jtf_movieName = jtf_movieName;
	}



	public JTextField getJtf_date() {
		return jtf_date;
	}



	public void setJtf_date(JTextField jtf_date) {
		this.jtf_date = jtf_date;
	}



	public JTextField getJtf_notice() {
		return jtf_notice;
	}



	public void setJtf_notice(JTextField jtf_notice) {
		this.jtf_notice = jtf_notice;
	}



	public JTextField getJtf_userId2() {
		return jtf_userId2;
	}



	public void setJtf_userId2(JTextField jtf_userId2) {
		this.jtf_userId2 = jtf_userId2;
	}



	public JTextField getJtf_choiceSeat() {
		return jtf_choiceSeat;
	}



	public void setJtf_choiceSeat(JTextField jtf_choiceSeat) {
		this.jtf_choiceSeat = jtf_choiceSeat;
	}



	public JButton getJbt_img() {
		return jbt_img;
	}



	public void setJbt_img(JButton jbt_img) {
		this.jbt_img = jbt_img;
	}



	public JButton getJbt_noticeRegis() {
		return jbt_noticeRegis;
	}



	public void setJbt_noticeRegis(JButton jbt_noticeRegis) {
		this.jbt_noticeRegis = jbt_noticeRegis;
	}



	public JButton getJbt_insert() {
		return jbt_insert;
	}



	public void setJbt_insert(JButton jbt_insert) {
		this.jbt_insert = jbt_insert;
	}



	public JButton getJbt_search() {
		return jbt_search;
	}



	public void setJbt_search(JButton jbt_search) {
		this.jbt_search = jbt_search;
	}



	public JButton getJbt_seatRegis() {
		return jbt_seatRegis;
	}



	public void setJbt_seatRegis(JButton jbt_seatRegis) {
		this.jbt_seatRegis = jbt_seatRegis;
	}



	public JScrollPane getJsp_movieInfo() {
		return jsp_movieInfo;
	}



	public void setJsp_movieInfo(JScrollPane jsp_movieInfo) {
		this.jsp_movieInfo = jsp_movieInfo;
	}



	public JScrollPane getJsp_memberInfo() {
		return jsp_memberInfo;
	}



	public void setJsp_memberInfo(JScrollPane jsp_memberInfo) {
		this.jsp_memberInfo = jsp_memberInfo;
	}



	public JScrollPane getJsp_seatBefore() {
		return jsp_seatBefore;
	}



	public void setJsp_seatBefore(JScrollPane jsp_seatBefore) {
		this.jsp_seatBefore = jsp_seatBefore;
	}



	public JScrollPane getJsp_seatAfter() {
		return jsp_seatAfter;
	}



	public void setJsp_seatAfter(JScrollPane jsp_seatAfter) {
		this.jsp_seatAfter = jsp_seatAfter;
	}



	public JTabbedPane getJtp() {
		return jtp;
	}



	public void setJtp(JTabbedPane jtp) {
		this.jtp = jtp;
	}



	public DefaultTableModel getDtm_seat() {
		return dtm_seat;
	}



	public void setDtm_seat(DefaultTableModel dtm_seat) {
		this.dtm_seat = dtm_seat;
	}



	public DefaultListModel<String> getDlm_seatBefore() {
		return dlm_seatBefore;
	}



	public void setDlm_seatBefore(DefaultListModel<String> dlm_seatBefore) {
		this.dlm_seatBefore = dlm_seatBefore;
	}



	public DefaultListModel<String> getDlm_seatAfter() {
		return dlm_seatAfter;
	}



	public void setDlm_seatAfter(DefaultListModel<String> dlm_seatAfter) {
		this.dlm_seatAfter = dlm_seatAfter;
	}



	public DefaultListModel<String> getDlm_memberInfo() {
		return dlm_memberInfo;
	}



	public void setDlm_memberInfo(DefaultListModel<String> dlm_memberInfo) {
		this.dlm_memberInfo = dlm_memberInfo;
	}



	public JTable getJt_seat() {
		return jt_seat;
	}


	public void setJt_seat(JTable jt_seat) {
		this.jt_seat = jt_seat;
	}


	public Font getFont() {
		return font;
	}



	public void setFont(Font font) {
		this.font = font;
	}



	public static void main(String[] args) {

		new ManagerView();
	}// main

}// class