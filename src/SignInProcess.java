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
	//----������Ʈ
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
		
		//-----���̸� �ʵ�
		labelTeamName = new JLabel("���̸� : ");
		labelTeamName.setFont(f);
		labelTeamName.setBounds(31, 40, 70, 20);
		
		TeamNameField = new JTextField();
		TeamNameField.setBounds(91, 40, 130, 20);
		
		//-----�н����� �ʵ� 
		labelPassword = new JLabel("Password : ");
		labelPassword.setFont(f);
		labelPassword.setBounds(10, 80, 100, 20);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(91, 80, 130, 20);
		
		//-----�н����� Ȯ�� �ʵ�
		labelPasswordChk = new JLabel("Password Ȯ�� : ");
		labelPasswordChk.setFont(f);
		labelPasswordChk.setBounds(5, 105, 130, 20);
		
		passwordChkField = new JPasswordField();
		passwordChkField.setBounds(120, 105, 130, 20);
		
		//-----��ȭ��ȣ �ʵ�
		labelHP = new JLabel("HP : ");
		labelHP.setFont(f);
		labelHP.setBounds(57, 140, 70, 20);
		
		HPField = new JTextField();
		HPField.setBounds(91, 140, 130, 20);				
		
		//-----�̸��� �ʵ�
		labelEmail = new JLabel("E-mail : ");
		labelEmail.setFont(f);
		labelEmail.setBounds(24, 170, 80, 20);
		
		EmailField = new JTextField();
		EmailField.setBounds(91, 170, 130, 20);
		
		//------��ư �ʵ�
		duplChkButton = new JButton("Ȯ��");
		duplChkButton.addActionListener(this);
		duplChkButton.setBounds(221, 40, 70, 20);
		
		comfirmButton = new JButton("�Ϸ�");
		comfirmButton.addActionListener(this);
		comfirmButton.setBounds(61, 220, 80, 30);
		
		cancelButton = new JButton("���");
		cancelButton.addActionListener(this);
		cancelButton.setBounds(151, 220, 80, 30);
		
		//-----������Ʈ �߰�
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
		//������ ����
		this.setTitle("SignIn Window");		  
		this.setSize(300, 300);
		this.show();
		this.setResizable(false);
		this.setLocationRelativeTo(null);		
>>>>>>> origin/master
		this.setVisible(true);
	}	
	
	@Override//��ư �̺�Ʈ
	public void actionPerformed(ActionEvent e) {
		//�н����� Ȯ�� �˻�, //ID �ߺ� Ȯ��
		
		command = e.getActionCommand();
		
		if(command.equals("Ȯ��")){
			inputTeamName = TeamNameField.getText();
			checkTeamName = dupliChkButton();//IDȮ�ι�ư Ŭ���� �ߺ��˻� �Լ� ȣ��			
		}
		
		if(command.equals("�Ϸ�")){
			
			//���̵� �ߺ� üũ
			if(checkTeamName == false){
				JOptionPane.showMessageDialog(null, "���̸� Ȯ���� ���ּ���.");
				return;
			}
			
			//��й�ȣ Ȯ�� üũ
			inputPass = passwordField.getText();
			String inputPassChk = passwordChkField.getText();
			
			if(inputPass.isEmpty()){
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
				return;
			}
			else{
				if(!inputPass.equals(inputPassChk)){		
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
					return;
				}
			}
			
			//��ȭ��ȣ, �̸��� �Է� üũ
			HP = HPField.getText();
			Email = EmailField.getText();
			
			if(HP.isEmpty() || Email.isEmpty()){
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ �Ǵ� �̸����� �Է� ���ּ���.");
				return;
			}
			
			insertDataBase();
			JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
			this.dispose();
			
		}
		
		if(command.equals("���")){
			this.dispose();
		}
	}
	
	public static SignInProcess getInstance(Connection con, Font f){//ȸ������ ��ü 1ȸ ������ ��ü�����Լ�
		if(instance == null){
			instance = new SignInProcess(con,f);
			return instance;
		}
		else
			return instance;
	}
	
	//���̸� �ߺ� üũ
	public boolean dupliChkButton(){		
		
		try {
			query = con.prepareStatement("select TeamName from User where TeamName = ?");
			query.setString(1, inputTeamName);
			rset = query.executeQuery();		
			rset.next();
						
			if(rset.getString("TeamName").equals(inputTeamName)){
				JOptionPane.showMessageDialog(null, "���̸� ���̵��Դϴ�.");
				return false;
			}
			
			
		}catch (SQLException e) {	
			
			if(inputTeamName.isEmpty()){
				JOptionPane.showMessageDialog(null, "���̸��� �Է����ּ���");
				return false;
			}
			else{
				JOptionPane.showMessageDialog(null, "��밡���� ���̵� �Դϴ�");
				return true;
			}			
		}
		return false;
	}	
	
	//ȸ������ ������ ���̽� ����
	public void insertDataBase(){
		
		int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
                
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
