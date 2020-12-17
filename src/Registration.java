import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField contact;
	private JTextField email;
	private JTextField inst;
	private JTextField college;
	private JTextField dept;
	public String s=null;
	public int id=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connect=null;
	/**
	 * Create the frame.
	 */
	public Registration() {
		connect=ConnectDatabase.Databaseconnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 784, 461);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Name :");
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setBounds(149, 133, 88, 27);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Contact No. :");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_1.setBounds(149, 182, 113, 27);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Email Id :");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label_2.setBounds(149, 233, 88, 27);
		panel.add(label_2);
		
		name = new JTextField();
		name.setColumns(100);
		name.setBounds(286, 133, 258, 26);
		panel.add(name);
		
		contact = new JTextField();
		contact.setColumns(100);
		contact.setBounds(286, 182, 258, 26);
		panel.add(contact);
		
		email = new JTextField();
		email.setColumns(100);
		email.setBounds(286, 233, 258, 26);
		panel.add(email);
		
		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(emailcheck(email.getText()) && phonecheck(contact.getText())){
				try{
					String qry10="select s_id from student";
			
			PreparedStatement pr=connect.prepareStatement(qry10);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				s=rs.getString("s_id");
			}
			id=Integer.parseInt(s);
			id++;
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
				try{String qry10="insert into student (s_name,s_id,contact,email,username,password,s_institute,s_college,s_dept)values(?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement pr=connect.prepareStatement(qry10);
				pr.setString(1, name.getText());
				
				pr.setString(2, String.valueOf(id));
				pr.setString(3, contact.getText());
				pr.setString(4, email.getText());
				pr.setString(5,contact.getText());
				pr.setString(6, String.valueOf(id)+name.getText());
				pr.setString(7,inst.getText());
				pr.setString(8,college.getText());
				pr.setString(9,dept.getText());
				pr.execute();
				JOptionPane.showMessageDialog(null, "Your username is "+contact.getText()+" and password is : "+String.valueOf(id)+name.getText());
			
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
				else if(!phonecheck(contact.getText())){
					JOptionPane.showMessageDialog(null, "Please enter valid Contact Number!");
					}
				else if(!emailcheck(email.getText())){
					JOptionPane.showMessageDialog(null, "Please enter valid Email ID!");
					}
				}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 20));
		button.setBounds(286, 414, 131, 36);
		panel.add(button);
		
		JLabel lblInstitute = new JLabel("Institute :");
		lblInstitute.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblInstitute.setBounds(149, 281, 88, 27);
		panel.add(lblInstitute);
		
		JLabel lblCollege = new JLabel("College :");
		lblCollege.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCollege.setBounds(149, 332, 88, 27);
		panel.add(lblCollege);
		
		JLabel lblDepartment = new JLabel("Department :");
		lblDepartment.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDepartment.setBounds(149, 382, 113, 27);
		panel.add(lblDepartment);
		
		inst = new JTextField();
		inst.setColumns(100);
		inst.setBounds(286, 282, 258, 26);
		panel.add(inst);
		
		college = new JTextField();
		college.setColumns(100);
		college.setBounds(286, 333, 258, 26);
		panel.add(college);
		
		dept = new JTextField();
		dept.setColumns(100);
		dept.setBounds(286, 377, 258, 26);
		panel.add(dept);
		
		JLabel lblNewLabel = new JLabel("<< LOGIN");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login n1=new Login();
				n1.main(null);
			}
		});
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(149, 37, 202, 27);
		panel.add(lblNewLabel);
	}
	public int mail=0;
	public boolean emailcheck(String s8) {
		char[] a=s8.toCharArray();
		for(int i=0;i<s8.length();i++) 
		{
			if(a[i]=='@') {
				mail=1;
				}
		
		}
		if(mail==1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public int phone=0;
	public boolean phonecheck(String s8) {
		if(s8.length()<10) {
			return false;
		}
		else {
			return true;
		}
		
	}
}
