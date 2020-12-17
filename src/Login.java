import java.awt.EventQueue;

import java.sql.*;
import java.util.Random;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JTextField uname;
	private JPasswordField password;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connect=null;
	private JLabel lblNewLabel_1;
	private JRadioButton radstud;
	private JRadioButton radcom;
	private JRadioButton radhead;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connect=ConnectDatabase.Databaseconnection();
	}
	public String s=null;
	public String un=null;
	public String studentid=null;
	public String comID=null;
	public String headID=null;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100,900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setTitle("LOGIN");
		frame.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(374, 221, 105, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword.setBounds(374, 276, 105, 28);
		frame.getContentPane().add(lblPassword);
		
		uname = new JTextField();
		uname.setBounds(534, 224, 105, 28);
		frame.getContentPane().add(uname);
		uname.setColumns(10);
		
		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(534, 279, 105, 28);
		frame.getContentPane().add(password);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					
						if(radstud.isSelected()) {
							@SuppressWarnings("deprecation")
							String qry="select *from student where username='"+uname.getText()+"' and password='"+password.getText()+"';";
							PreparedStatement pr=connect.prepareStatement(qry);
							
							ResultSet rs=pr.executeQuery();
							int i=0;
							
							while(rs.next()) {
								un=rs.getString("s_id");
								i++;
							}
							
							if(i==1)  {
								studentid=studid();
								JOptionPane.showMessageDialog(null, "LOG IN SUCCESSFULL!!!");
									studentid=studid();
									frame.dispose();
									Student s1=new Student();
									s1.main(null,studentid);
									
								
								}
								
							
							else if(i>1) {
								JOptionPane.showMessageDialog(null, "data duplication found...contact support");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "username or password didn't match .... try again");
							}
						}else if(radcom.isSelected()) {
							@SuppressWarnings("deprecation")
							String qry="select *from commember where username='"+uname.getText()+"' and password='"+password.getText()+"';";
							PreparedStatement pr=connect.prepareStatement(qry);
							
							ResultSet rs=pr.executeQuery();
							int i=0;
							
							while(rs.next()) {
								i++;
							}
							
							if(i==1)  {
								comID=com_id();
								JOptionPane.showMessageDialog(null, "LOG IN SUCCESSFULL!!!");
								
									frame.dispose();
									Committee com=new Committee();
									com.main(null,comID);
								
								
								}
								
							
							else if(i>1) {
								JOptionPane.showMessageDialog(null, "data duplication found...contact support");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "username or password didn't match .... try again");
							}
						}else if(radhead.isSelected()) {
							@SuppressWarnings("deprecation")
							String qry="select *from inshead where username='"+uname.getText()+"' and password='"+password.getText()+"';";
							PreparedStatement pr=connect.prepareStatement(qry);
							
							ResultSet rs=pr.executeQuery();
							int i=0;
							
							while(rs.next()) {
								i++;
							}
							
							if(i==1)  {
								headID=head_id();
								JOptionPane.showMessageDialog(null, "LOG IN SUCCESSFULL!!!");
								
									frame.dispose();
									InsHead ihead=new InsHead();
									ihead.main(null,headID);
								
								
								}
								
							
							else if(i>1) {
								JOptionPane.showMessageDialog(null, "data duplication found...contact support");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "username or password didn't match .... try again");
							}
						}
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(466, 342, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("<<Register here>>");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					frame.dispose();
					Registration n2=new Registration();
					n2.setVisible(true);
					n2.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
					}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(405, 376, 206, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		radstud = new JRadioButton("Student");
		radstud.setBackground(Color.GRAY);
		radstud.setSelected(true);
		radstud.setFont(new Font("Times New Roman", Font.BOLD, 20));
		radstud.setBounds(237, 176, 109, 23);
		frame.getContentPane().add(radstud);
		
		radcom = new JRadioButton("Grevance committee member");
		radcom.setBackground(Color.GRAY);
		radcom.setFont(new Font("Times New Roman", Font.BOLD, 20));
		radcom.setBounds(347, 176, 292, 23);
		frame.getContentPane().add(radcom);
		
		radhead = new JRadioButton("Institute head");
		radhead.setBackground(Color.GRAY);
		radhead.setFont(new Font("Times New Roman", Font.BOLD, 20));
		radhead.setBounds(652, 176, 154, 23);
		frame.getContentPane().add(radhead);
		
		ButtonGroup g1=new ButtonGroup();
		g1.add(radcom);
		g1.add(radhead);
		g1.add(radstud);
	}
	public String studid() {
		try {
			
			String qry="select * from student where username=?;";
			
			PreparedStatement pr=connect.prepareStatement(qry);
			pr.setString(1, uname.getText());
			ResultSet rs=pr.executeQuery();
		
			
			while(rs.next()) {
				s=rs.getString("s_id");
				//System.out.print(s+un);
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, " catch"+e);
			s=null;
			
		}
		return s;
	}
	public String com_id() {
		try {
			
			String qry="select * from commember where username=?;";
			
			PreparedStatement pr=connect.prepareStatement(qry);
			pr.setString(1, uname.getText());
			ResultSet rs=pr.executeQuery();
		
			
			while(rs.next()) {
				s=rs.getString("c_id");
				//System.out.print(s+un);
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, " catch"+e);
			s=null;
			
		}
		return s;
	}
	public String head_id() {
		try {
			
			String qry="select * from inshead where username=?;";
			
			PreparedStatement pr=connect.prepareStatement(qry);
			pr.setString(1, uname.getText());
			ResultSet rs=pr.executeQuery();
		
			
			while(rs.next()) {
				s=rs.getString("h_id");
				//System.out.print(s+un);
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, " catch"+e);
			s=null;
			
		}
		return s;
	}
	
	
}
