import java.sql.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class LogInProcess extends JFrame implements ActionListener {
	//--GUI
	private Connection con;
	private Container loginContainer;
	private Font f;
	//--컴포넌트
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
		loginContainer.setLayout(null);//레이아웃 없음.	
		
		//---------ID 텍스트 필드
		labelTeamName = new JLabel("팀이름 : ");
		labelTeamName.setFont(f);
		labelTeamName.setBounds(31, 40, 70, 20);	
		
		TeamNameField = new JTextField();		
		TeamNameField.setBounds(97, 40, 130, 20);
	
		//---------패스워드 필드
		labelPassword = new JLabel("Password : ");
		labelPassword.setFont(f);
		labelPassword.setBounds(10, 70, 90, 20);		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(97, 70, 130, 20);
		passwordField.addActionListener(this);//엔터치면 바로 로그인
		
		//---------버튼 필드
		logInButton = new JButton("로그인");
		logInButton.addActionListener(this);
		logInButton.setBounds(50, 110, 80, 30);
		
		signInButton = new JButton("회원가입");
		signInButton.addActionListener(this);
		signInButton.setBounds(140, 110, 90, 30);
		
		
		//---------컴포넌트 추가
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
				JOptionPane.showMessageDialog(null, "로그인 성공!");					
				this.dispose();
			}				
		}			
		catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "로그인 실패");
			return;	
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		command = e.getActionCommand();//커맨드 받음
		
		String TeamName = this.TeamNameField.getText();
		String Password = this.passwordField.getText();
		
		
		if(command.equals("회원가입")){
			
			SignInProcess signInFrame = new SignInProcess(this.con,this.f);			
			
			signInFrame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.out.println("닫기 버튼 클릭!");
					signInFrame.dispose();
				}
			});
			return;
		}
		
		if(command.equals("로그인") || !Password.isEmpty()){		
			
			if(TeamName.isEmpty() || Password.isEmpty()){
				JOptionPane.showMessageDialog(null, "ID 또는 Password를 입력 해주세요!");
				return;
			}
			
			this.loginButtonAction(TeamName,Password);
			return;
		}
		//엔터치면 바로 여기 들어옴
	}
}
