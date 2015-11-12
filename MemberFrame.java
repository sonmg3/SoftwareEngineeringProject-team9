import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class MemberFrame extends JFrame implements ActionListener {
	
	private Connection con = null;
	private ResultSet rset=null;
	private PreparedStatement query=null;
	private String inputID,inputPASS,Email,HP;
	private JTextField textfieldID,textfieldEmail,textfieldHP;
	private JPasswordField passwordfield;
	private int idCheck = 0;// 1�̸� ��밡�� 0�̸� ����X
	
 public MemberFrame(Connection con) {
	 this.con = con;
     Container cp = getContentPane();
     cp.setLayout(new GridLayout(6, 1));
     
     JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     
     JLabel label = new JLabel("ID:");
     textfieldID = new JTextField();
     textfieldID.setColumns(10);     
     JButton button = new JButton("ID Ȯ��");
     button.addActionListener(this);
     
     panel.add(label);
     panel.add(textfieldID);
     panel.add(button);
     
     cp.add(panel);
     
     panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     
     label = new JLabel("PASSWORD:");
     passwordfield = new JPasswordField();
     passwordfield.setColumns(10);
     
     panel.add(label);
     panel.add(passwordfield);
     
     cp.add(panel);     
 
     panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     
     label = new JLabel("�̸���:");
     textfieldEmail = new JTextField();
     textfieldEmail.setColumns(20);
     
     panel.add(label);
     panel.add(textfieldEmail);
     
     cp.add(panel);     
     
     panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     
     label = new JLabel("H.P:");
     textfieldHP = new JTextField();
     textfieldHP.setColumns(11);
     
     panel.add(label);
     panel.add(textfieldHP);
     cp.add(panel);
     
     panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
     
     button = new JButton("����");
     button.addActionListener(this);
     
     panel.add(button);
     
     cp.add(panel);
 }
 
 @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e) {
     String command = e.getActionCommand();
     
     if (command.equals("ID Ȯ��")){
    	 
    	 inputID = textfieldID.getText();
    	 
    	 try {
			query = con.prepareStatement("select id from testmembertable where id = ?");
			query.setString(1, inputID);
			rset = query.executeQuery();		
			rset.next();
			
			if(rset.getString("id").equals(inputID)){
				JOptionPane.showMessageDialog(null, "�ߺ� ���̵��Դϴ�.");				
				return;
			}
			
			
		} catch (SQLException e1) {	
			
			if(inputID.isEmpty()){
				JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���");
				return;
			}
			else{
				JOptionPane.showMessageDialog(null, "��밡���� ���̵� �Դϴ�");
				this.idCheck = 1;
				return;				
			}			
		}        
         
         
     } else if (command.equals("����")) {
         
         int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
         
         if(this.idCheck == 0 && result != JOptionPane.NO_OPTION){
        	 JOptionPane.showMessageDialog(null, "ID Ȯ���� �����ּ��� ");
        	 return;
         }
         
         if (result == JOptionPane.YES_OPTION) {
        	        	
        	 inputPASS = this.passwordfield.getText();
        	 Email = this.textfieldEmail.getText();
        	 HP = this.textfieldHP.getText();
        	 System.out.println("ID:"+inputID+"PASS: "+inputPASS+Email+HP);
        	try {
        		String query1 = "INSERT INTO testmembertable(ID,PASSWORD,Email,HP)";
        		query1 += "VALUES (?, ?, ?, ?)";
				query = con.prepareStatement(query1);
				query.setString(1, inputID);	
				query.setString(2, inputPASS);
				query.setString(3, Email);				
				query.setString(4, HP);
				query.executeUpdate();
	 						
	 			
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
 			
            JOptionPane.showMessageDialog(null, "ȸ�� ������ �����մϴ�.");             
             this.dispose();
         }
     }
    }
 

}