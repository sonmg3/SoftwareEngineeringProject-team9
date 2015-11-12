import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


public class SignInProcess extends JFrame implements ActionListener{
	
	//----GUI
	private Connection con;
	private Container signInContainer;
	private Font f;	
	//----컴포넌트
	private JLabel labelTeamName,labelPassword,labelPasswordChk; 
	private JLabel labelHP,labelEmail;
	private JTextField TeamNameField,HPField,EmailField;
	private JPasswordField passwordField,passwordChkField;
	private String command;
	private JButton approvalButton, cancelButton, duplChkButton;
	//----SQL
	public SignInProcess(Connection con,Font f){
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
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

}
