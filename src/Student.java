import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Student {

	private JFrame frame;
	private JTextField othertopic;
	private JTextField title;
	private JTextField description;
	public String s0=null;
	public int i=0;
	public JComboBox complaintID;
	public JComboBox complaintIDsolved;
	public static String stID1=null;
	public String studins=null;
	public String studcollege=null;
	public String studdep=null;
	public JButton trigger;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String stID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stID1=stID;
					Student window = new Student();
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}
	Connection connect=null;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable solvedtable;
	/**
	 * Create the application.
	 */
	public Student() {
		
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setTitle("Student");
		frame.setBackground(Color.YELLOW);
		
		connect=ConnectDatabase.Databaseconnection();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1346, 686);
		frame.getContentPane().add(tabbedPane);
		try {
			
			String s1="select *from student where s_id='"+stID1+"'";
			PreparedStatement pr=connect.prepareStatement(s1);
			
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				studins=rs.getString("s_institute");
				studcollege=rs.getString("s_college");
				studdep=rs.getString("s_dept");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "getting values of student"+e);
		}
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Register Complaint", null, panel, null);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Select level :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(71, 39, 132, 31);
		panel.add(lblNewLabel);
		
		JComboBox level = new JComboBox();
		level.setBounds(202, 43, 122, 28);
		panel.add(level);
		level.addItem("Institute");
		level.addItem("College");
		level.addItem("Department");
		
		JLabel lblSelectTopic = new JLabel("Select Topic :");
		lblSelectTopic.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSelectTopic.setBounds(71, 99, 132, 31);
		panel.add(lblSelectTopic);
		
		JComboBox topic = new JComboBox();
		topic.addItem("Admission");
		topic.addItem("Finanace");
		topic.addItem("Infrastructure");
		topic.addItem("Lecture Schedual");
		topic.addItem("Examination");
		topic.addItem("Other");
		topic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(topic.getSelectedItem().equals("Other")) {
					othertopic.setEnabled(true);
				}else {
					othertopic.setEnabled(false);
				}
			}
		});
		topic.setBounds(202, 102, 122, 31);
		panel.add(topic);
		
		
		JLabel lblNewLabel_1 = new JLabel("If other :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(339, 99, 68, 31);
		panel.add(lblNewLabel_1);
		
		othertopic = new JTextField();
		othertopic.setBounds(406, 103, 161, 28);
		panel.add(othertopic);
		othertopic.setColumns(100);
		othertopic.setEnabled(false);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(71, 156, 84, 31);
		panel.add(lblTitle);
		
		title = new JTextField();
		title.setColumns(200);
		title.setBounds(202, 159, 205, 28);
		panel.add(title);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDescription.setBounds(71, 213, 132, 31);
		panel.add(lblDescription);
		
		description = new JTextField();
		description.setColumns(1000);
		description.setBounds(202, 213, 205, 116);
		panel.add(description);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String qry10="select *from complaint;";
					
					PreparedStatement pr=connect.prepareStatement(qry10);
					ResultSet rs=pr.executeQuery();
					while(rs.next()) {
						s0=rs.getString("c_id");
					}
					i=Integer.valueOf(s0);
					i++;
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					
				}
				try{String qry10="insert into complaint (c_title,c_id,c_description,c_level,c_topic,s_id)values(?,?,?,?,?,?)";
				
				PreparedStatement pr=connect.prepareStatement(qry10);
				pr.setString(1, title.getText());
				
				pr.setString(2, String.valueOf(i));
				pr.setString(3, description.getText());
				pr.setString(4, String.valueOf(level.getSelectedItem()));
				
				if((String.valueOf(topic.getSelectedItem()).equals("Other"))) {
				pr.setString(5, othertopic.getText());}
				else {
					pr.setString(5, String.valueOf(topic.getSelectedItem()));
				}
				Login st_id=new Login();
				
		
					pr.setString(6, stID1);
				
					
				pr.execute();
				JOptionPane.showMessageDialog(null, "Your complaint registered successfully. Your complaint ID is : "+String.valueOf(i));
				inputID();
				trigger.doClick();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(202, 358, 145, 31);
		panel.add(btnNewButton);
		
		JButton logout = new JButton("LOGOUT");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login l1=new Login();
				l1.main(null);			}
		});
		logout.setForeground(Color.RED);
		logout.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		logout.setBounds(533, 11, 145, 31);
		panel.add(logout);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Check complaint status", null, panel_1, null);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel_2 = new JLabel("Select your complaint ID :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(97, 53, 236, 24);
		panel_1.add(lblNewLabel_2);
		
		complaintID = new JComboBox();
		complaintID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				settable();
				
				
			}
			
		});
		complaintID.setBounds(343, 53, 140, 25);
		panel_1.add(complaintID);
		
		complaintIDsolved = new JComboBox();
		complaintIDsolved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setsolvedtable();
				
				
			}
			
		});
		complaintIDsolved.setBounds(343, 223, 140, 25);
		panel_1.add(complaintIDsolved);
		
		inputID();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(53, 110, 500, 102);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 102);
		panel_3.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_4 = new JLabel("Ongoing complaints :-");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_4.setBounds(53, 85, 245, 24);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblSolvedComplaints = new JLabel("Solved complaints :-");
		lblSolvedComplaints.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSolvedComplaints.setBounds(53, 223, 245, 24);
		panel_1.add(lblSolvedComplaints);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(53, 258, 500, 102);
		panel_1.add(scrollPane_4);
		
		solvedtable = new JTable();
		scrollPane_4.setViewportView(solvedtable);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				trigger.doClick();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				trigger.doClick();
			}
		});
		tabbedPane.addTab("Related Complaints", null, panel_2, null);
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel_3 = new JLabel("Institute level :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(75, 11, 200, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblColegeLevel = new JLabel("College level :");
		lblColegeLevel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblColegeLevel.setBounds(75, 153, 200, 20);
		panel_2.add(lblColegeLevel);
		
		JLabel lblDepartmentLevel = new JLabel("Department level :");
		lblDepartmentLevel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDepartmentLevel.setBounds(75, 277, 200, 20);
		panel_2.add(lblDepartmentLevel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(85, 36, 500, 100);
		panel_2.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(75, 190, 500, 80);
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(75, 308, 500, 80);
		panel_2.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		trigger = new JButton("New button");
		trigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settable_1();
			    settable_2(); settable_3();
			    
			}
		});
		trigger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				settable_1();
				settable_2();settable_3();
				
			}
		});
		trigger.setBounds(600, 348, 1, 1);
		panel_2.add(trigger);
	}
	@SuppressWarnings("unchecked")
	public void inputID() {
		complaintID.removeAllItems();
		try {
			
			String s1="select *from complaint where (s_id='"+stID1+"') AND (status IS NULL)";
			PreparedStatement pr=connect.prepareStatement(s1);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				complaintID.addItem(rs.getString("c_id"));
			}
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, " adding item"+e1);
		}
		complaintIDsolved.removeAllItems();
		try {
			
			String s1="select * from complaint where (s_id='"+stID1+"') AND (status IS NOT NULL);";
			PreparedStatement pr=connect.prepareStatement(s1);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				try {
					complaintIDsolved.addItem(rs.getString("c_id"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
			}
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, " adding item solved "+e1);
		}
	}
	public void settable() {
		try {
			String s1="select c_title,c_description,c_level,c_topic,status from complaint where ( c_id='"+String.valueOf( complaintID.getSelectedItem())+"') AND (status IS NULL);";
			PreparedStatement pr=connect.prepareStatement(s1);
			try{
			ResultSet rs=pr.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e2) { }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "setting to table"+e);
		}
	}
	public void settable_1() {
		
		try {
			String s1="select c_title,c_description from complaint where (c_level='Institute') AND (status IS NULL)";
			PreparedStatement pr=connect.prepareStatement(s1);
			
			ResultSet rs=pr.executeQuery();
			
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "setting to table"+e);
		}
	}
	public void settable_2() {
		
		try {
			String s1="select c_title,c_description from complaint where (c_level='College') AND (status IS NULL)";
			PreparedStatement pr=connect.prepareStatement(s1);
			
			ResultSet rs=pr.executeQuery();
			
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "setting to table"+e);
		}
	}
	public void settable_3() {
		
		try {
			String s1="select c_title,c_description from complaint where (c_level='Department') AND (status IS NULL)";
			PreparedStatement pr=connect.prepareStatement(s1);
			
			ResultSet rs=pr.executeQuery();
			
			table_3.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "setting to table"+e);
		}
	}
	public void setsolvedtable() {
	
		
		try {
			String s1="select c_title,c_description,c_level,c_topic,status from complaint where ( c_id='"+String.valueOf( complaintID.getSelectedItem())+"') AND (status IS NOT NULL)";
			PreparedStatement pr=connect.prepareStatement(s1); 
			
			ResultSet rs=pr.executeQuery();
			
			try {
				solvedtable.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				// TODO Auto-generated catch block
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "setting to solved table"+e);
		}
	}
}
