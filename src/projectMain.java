import java.awt.Font;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JOptionPane;


public class projectMain {	

	public static void main(String[] args) {
		
		Connection con = null;
		Font f = new Font("����ü", Font.PLAIN, 15);		
		/*
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �˻� ����");		
			
			Statement stmt = null;
			ResultSet rs = null;
			//String url = "jdbc:mysql://119.18.83.91";
			String url = "jdbc:mysql://192.168.200.192";
			String id = "admin";
			String pass = "team9";
			
			//con = DriverManager.getConnection(url, id, pass);
			System.out.println("������ ���̽� ���ῡ �����Ͽ����ϴ�.");			
			
			stmt = con.createStatement();
			ResultSet rset;
			PreparedStatement query = con.prepareStatement("use dsmmDB");
			rset = query.executeQuery();
			System.out.println("�����ͺ��̽� ���� ����");	
			
		}catch(ClassNotFoundException e){
			System.out.println("error = " + e);
		}catch(SQLException sqle){			
			JOptionPane.showMessageDialog(null, "������ ���̽� ����");
			System.exit(0);
		}
		//---������ ���̽� ���� �Ϸ�
		*/
		LogInProcess loginFrame = new LogInProcess(con,f);		
		
		loginFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.out.println("�ݱ� ��ư Ŭ��!");
				loginFrame.dispose();
			}
		});
		
	}	
}
