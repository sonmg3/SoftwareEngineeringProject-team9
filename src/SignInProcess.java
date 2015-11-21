import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


public class SignInProcess extends JFrame implements ActionListener{
	
	//----GUI
	private Connection con;
	private ResultSet rset=null;
	private PreparedStatement query=null;
	private Container signInContainer;
	private Font f;	
	public static SignInProcess instance;
	//----컴포넌트
	private JLabel labelTeamName,labelPassword,labelPasswordChk; 
	private JLabel labelHP,labelEmail;
	private JTextField TeamNameField,HPField,EmailField;
	private JPasswordField passwordField,passwordChkField;
	private String command;
	private JButton comfirmButton, cancelButton, duplChkButton;
	//----SQL & etc
	private boolean checkTeamName,checkPassword;
	private String inputTeamName,inputPass,HP,Email;
	
	private SignInProcess(Connection con,Font f){
		
		this.con = con;	
		this.f = f;
		
		signInContainer = this.getContentPane();
		signInContainer.setLayout(null);
		
		//-----팀이름 필드
		labelTeamName = new JLabel("팀이름 : ");
		labelTeamName.setFont(f);
		labelTeamName.setBounds(31, 40, 70, 20);
		
		TeamNameField = new JTextField();
		TeamNameField.setBounds(91, 40, 130, 20);
		
		//-----패스워드 필드 
		labelPassword = new JLabel("Password : ");
		labelPassword.setFont(f);
		labelPassword.setBounds(10, 80, 100, 20);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(91, 80, 130, 20);
		
		//-----패스워드 확인 필드
		labelPasswordChk = new JLabel("Password 확인 : ");
		labelPasswordChk.setFont(f);
		labelPasswordChk.setBounds(5, 105, 130, 20);
		
		passwordChkField = new JPasswordField();
		passwordChkField.setBounds(120, 105, 130, 20);
		
		//-----전화번호 필드
		labelHP = new JLabel("HP : ");
		labelHP.setFont(f);
		labelHP.setBounds(57, 140, 70, 20);
		
		HPField = new JTextField();
		HPField.setBounds(91, 140, 130, 20);				
		
		//-----이메일 필드
		labelEmail = new JLabel("E-mail : ");
		labelEmail.setFont(f);
		labelEmail.setBounds(24, 170, 80, 20);
		
		EmailField = new JTextField();
		EmailField.setBounds(91, 170, 130, 20);
		
		//------버튼 필드
		duplChkButton = new JButton("확인");
		duplChkButton.addActionListener(this);
		duplChkButton.setBounds(221, 40, 70, 20);
		
		comfirmButton = new JButton("완료");
		comfirmButton.addActionListener(this);
		comfirmButton.setBounds(61, 220, 80, 30);
		
		cancelButton = new JButton("취소");
		cancelButton.addActionListener(this);
		cancelButton.setBounds(151, 220, 80, 30);
		
		//-----컴포넌트 추가
		signInContainer.add(labelTeamName);
		signInContainer.add(TeamNameField);
		signInContainer.add(labelPassword);
		signInContainer.add(passwordField);
		signInContainer.add(labelPasswordChk);
		signInContainer.add(passwordChkField);
		signInContainer.add(labelHP);
		signInContainer.add(HPField);
		signInContainer.add(labelEmail);
		signInContainer.add(EmailField);
		signInContainer.add(duplChkButton);
		signInContainer.add(comfirmButton);
		signInContainer.add(cancelButton);
		
<<<<<<< HEAD
		this.setTitle("SignIn Window");		  
		this.setSize(300, 300);
		//this.show();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
=======
		//윈도우 생성
		this.setTitle("SignIn Window");		  
		this.setSize(300, 300);
		this.show();
		this.setResizable(false);
		this.setLocationRelativeTo(null);		
>>>>>>> origin/master
		this.setVisible(true);
	}	
	
	@Override//버튼 이벤트
	public void actionPerformed(ActionEvent e) {
		//패스워드 확인 검사, //ID 중복 확인
		
		command = e.getActionCommand();
		
		if(command.equals("확인")){
			inputTeamName = TeamNameField.getText();
			checkTeamName = dupliChkButton();//ID확인버튼 클릭시 중복검사 함수 호출			
		}
		
		if(command.equals("완료")){
			
			//아이디 중복 체크
			if(checkTeamName == false){
				JOptionPane.showMessageDialog(null, "팀이름 확인을 해주세요.");
				return;
			}
			
			//비밀번호 확인 체크
			inputPass = passwordField.getText();
			String inputPassChk = passwordChkField.getText();
			
			if(inputPass.isEmpty()){
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
				return;
			}
			else{
				if(!inputPass.equals(inputPassChk)){		
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
					return;
				}
			}
			
			//전화번호, 이메일 입력 체크
			HP = HPField.getText();
			Email = EmailField.getText();
			
			if(HP.isEmpty() || Email.isEmpty()){
				JOptionPane.showMessageDialog(null, "전화번호 또는 이메일을 입력 해주세요.");
				return;
			}
			
			insertDataBase();
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			this.dispose();
			
		}
		
		if(command.equals("취소")){
			this.dispose();
		}
	}
	
	public static SignInProcess getInstance(Connection con, Font f){//회원가입 객체 1회 생성시 객체생성함수
		if(instance == null){
			instance = new SignInProcess(con,f);
			return instance;
		}
		else
			return instance;
	}
	
	//팀이름 중복 체크
	public boolean dupliChkButton(){		
		
		try {
			query = con.prepareStatement("select TeamName from User where TeamName = ?");
			query.setString(1, inputTeamName);
			rset = query.executeQuery();		
			rset.next();
						
			if(rset.getString("TeamName").equals(inputTeamName)){
				JOptionPane.showMessageDialog(null, "팀이름 아이디입니다.");
				return false;
			}
			
			
		}catch (SQLException e) {	
			
			if(inputTeamName.isEmpty()){
				JOptionPane.showMessageDialog(null, "팀이름을 입력해주세요");
				return false;
			}
			else{
				JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다");
				return true;
			}			
		}
		return false;
	}	
	
	//회원정보 데이터 베이스 저장
	public void insertDataBase(){
		
		int result = JOptionPane.showConfirmDialog(null, "가입하시겠습니까?", "가입 확인", JOptionPane.YES_NO_OPTION);
                
        if (result == JOptionPane.YES_OPTION) {    	
       	
       	try {
       		String query1 = "INSERT INTO User(TeamName,Password,PhoneNumber,Email)";
       		query1 += "VALUES (?, ?, ?, ?)";
				query = con.prepareStatement(query1);
				query.setString(1, inputTeamName);	
				query.setString(2, inputPass);
				query.setString(3, HP);				
				query.setString(4, Email);
				query.executeUpdate();	 						
	 			
			} catch (SQLException e1) {	
				 JOptionPane.showMessageDialog(null, "DataBase Error!");
				 System.exit(ERROR);
			}       		
        }
	}
}
