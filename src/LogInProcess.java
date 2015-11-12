import java.sql.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class LogInProcess extends JFrame implements ActionListener {
	//--GUI
	private Connection con;
	private Container loginContainer;
	private Font f;
	//--������Ʈ
	private JLabel labelTeamName, labelPassword;
	private JTextField TeamNameField;
	private JPasswordField passwordField;	
	private String command;
	private JButton logInButton, signInButton;
	//SQL
	private ResultSet rset;
	private PreparedStatement query;
	
	
	public LogInProcess(Connection con, Font f){
		this.con = con;
		this.f = f;
		
		loginContainer = this.getContentPane();		
		loginContainer.setLayout(null);//���̾ƿ� ����.	
		
		//---------ID �ؽ�Ʈ �ʵ�
		labelTeamName = new JLabel("���̸� : ");
		labelTeamName.setFont(f);
		labelTeamName.setBounds(31, 40, 70, 20);	
		
		TeamNameField = new JTextField();		
		TeamNameField.setBounds(97, 40, 130, 20);
	
		//---------�н����� �ʵ�
		labelPassword = new JLabel("Password : ");
		labelPassword.setFont(f);
		labelPassword.setBounds(10, 70, 90, 20);		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(97, 70, 130, 20);
		passwordField.addActionListener(this);//����ġ�� �ٷ� �α���
		
		//---------��ư �ʵ�
		logInButton = new JButton("�α���");
		logInButton.addActionListener(this);
		logInButton.setBounds(50, 110, 80, 30);
		
		signInButton = new JButton("ȸ������");
		signInButton.addActionListener(this);
		signInButton.setBounds(140, 110, 90, 30);
		
		
		//---------������Ʈ �߰�
		loginContainer.add(labelTeamName);
		loginContainer.add(labelPassword);		
		loginContainer.add(TeamNameField);
		loginContainer.add(passwordField);
		loginContainer.add(logInButton);
		loginContainer.add(signInButton);
		
		this.setTitle("LogIn Window");		  
		this.setSize(280, 200);
		//this.show();
		this.setResizable(false);
		this.setLocationRelativeTo(null);		
		this.setVisible(true);
	}
	
	public void loginButtonAction(String TeamName, String Password){	
		
		try {		
			query = con.prepareStatement("select TeamName, Password from UserInfo where TeamName = ? and Password = ?");
			query.setString(1, TeamName);
			query.setString(2, Password);			
			rset = query.executeQuery();
			rset.next();
				
			if(TeamName.equals(rset.getString("TeamName")) && Password.equals(rset.getString("Password"))){
				JOptionPane.showMessageDialog(null, "�α��� ����!");					
				this.dispose();
			}				
		}			
		catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "�α��� ����");
			return;	
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		command = e.getActionCommand();//Ŀ�ǵ� ����
		
		String TeamName = this.TeamNameField.getText();
		String Password = this.passwordField.getText();
		
		
		if(command.equals("ȸ������")){
			
			SignInProcess signInFrame = new SignInProcess(this.con,this.f);			
			
			signInFrame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.out.println("�ݱ� ��ư Ŭ��!");
					signInFrame.dispose();
				}
			});
			return;
		}
		
		if(command.equals("�α���") || !Password.isEmpty()){		
			
			if(TeamName.isEmpty() || Password.isEmpty()){
				JOptionPane.showMessageDialog(null, "ID �Ǵ� Password�� �Է� ���ּ���!");
				return;
			}
			
			this.loginButtonAction(TeamName,Password);
			return;
		}
		//����ġ�� �ٷ� ���� ����
	}
}
