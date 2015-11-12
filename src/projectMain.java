import java.awt.Font;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JOptionPane;


public class projectMain {	

	public static void main(String[] args) {
		
		Connection con = null;
		Font f = new Font("바탕체", Font.PLAIN, 15);		
		/*
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 검색 성공");		
			
			Statement stmt = null;
			ResultSet rs = null;
			//String url = "jdbc:mysql://119.18.83.91";
			String url = "jdbc:mysql://192.168.200.192";
			String id = "admin";
			String pass = "team9";
			
			//con = DriverManager.getConnection(url, id, pass);
			System.out.println("데이터 베이스 연결에 성공하였습니다.");			
			
			stmt = con.createStatement();
			ResultSet rset;
			PreparedStatement query = con.prepareStatement("use dsmmDB");
			rset = query.executeQuery();
			System.out.println("데이터베이스 변경 성공");	
			
		}catch(ClassNotFoundException e){
			System.out.println("error = " + e);
		}catch(SQLException sqle){			
			JOptionPane.showMessageDialog(null, "데이터 베이스 에러");
			System.exit(0);
		}
		//---데이터 베이스 연결 완료
		*/
		LogInProcess loginFrame = new LogInProcess(con,f);		
		
		loginFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.out.println("닫기 버튼 클릭!");
				loginFrame.dispose();
			}
		});
		
	}	
}
