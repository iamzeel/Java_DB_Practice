import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsHead {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String stID1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsHead window = new InsHead();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InsHead() {
		connect=ConnectDatabase.Databaseconnection();
		initialize();
	}
	Connection connect=null;
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		frame.setTitle("INSTITUTE HEAD");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1344, 681);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Action Given by Committee", null, panel, null);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel label_4 = new JLabel("Title :-");
		label_4.setBounds(27, 141, 139, 26);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(140, 146, 476, 20);
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		panel.add(textField_3);
		
		JLabel label_5 = new JLabel("Description :-");
		label_5.setBounds(27, 183, 139, 26);
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(140, 188, 476, 69);
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		panel.add(textField_4);
		
		JLabel label_6 = new JLabel("Actions :-");
		label_6.setBounds(27, 268, 94, 26);
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setBounds(140, 268, 476, 94);
		textField_5.setEditable(false);
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_5.setColumns(10);
		panel.add(textField_5);
		
		JButton button = new JButton("Send report to comittee member");
		button.setBounds(219, 373, 266, 23);
		panel.add(button);
	
		JButton logout = new JButton("LOGOUT");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login l1=new Login();
				l1.main(null);
			}
		});
		logout.setForeground(Color.RED);
		logout.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		logout.setBounds(528, 373, 145, 31);
		panel.add(logout);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				settable();
			}
		});
		scrollPane_1.setBounds(25, 11, 622, 122);
		panel.add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent arg0) {
				try{int rw=table.getSelectedRow();
				String s=(table.getModel().getValueAt(rw, 0).toString());
				String qry10="select *from complaint where c_id='"+s+"';";
				PreparedStatement pr=connect.prepareStatement(qry10);
				
				ResultSet rs=pr.executeQuery();
				while(rs.next()) {
					textField_3.setText(rs.getString("c_title"));
					textField_4.setText(rs.getString("c_description"));
					textField_5.setText(rs.getString("c_action"));
					
				}
				}catch(Exception e4) {
					JOptionPane.showMessageDialog(null, e4);
				}
				
			}
		});
		scrollPane_1.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Complaints given by Committee", null, panel_1, null);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		
		JLabel label = new JLabel("Title :-");
		label.setFont(new Font("Times New Roman", Font.BOLD, 17));
		label.setBounds(38, 169, 139, 26);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(151, 174, 476, 20);
		panel_1.add(textField);
		
		JLabel label_1 = new JLabel("Description :-");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		label_1.setBounds(38, 211, 139, 26);
		panel_1.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(151, 216, 476, 69);
		panel_1.add(textField_1);
		
		JLabel label_2 = new JLabel("Actions :-");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		label_2.setBounds(38, 296, 94, 26);
		panel_1.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(151, 296, 476, 20);
		panel_1.add(textField_2);
		
		JLabel label_3 = new JLabel("Status :-");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		label_3.setBounds(38, 330, 94, 26);
		panel_1.add(label_3);
		
		@SuppressWarnings("rawtypes")
		JComboBox status = new JComboBox();
		status.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		status.setBounds(151, 327, 172, 25);
		panel_1.add(status);
		
		status.addItem("completed by head");
		
		status.addItem("rejected by institute head");
		
		JButton btnSendReportTo = new JButton("Send report to comittee");
		btnSendReportTo.setBounds(255, 367, 172, 23);
		panel_1.add(btnSendReportTo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				settable_1();
			}
			
		});
		scrollPane.setBounds(25, 11, 636, 151);
		panel_1.add(scrollPane);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{int rw=table_1.getSelectedRow();
				String s=(table_1.getModel().getValueAt(rw, 0).toString());
				String qry10="select *from complaint where c_id='"+s+"';";
				PreparedStatement pr=connect.prepareStatement(qry10);
				
				ResultSet rs=pr.executeQuery();
				while(rs.next()) {
					textField.setText(rs.getString("c_title"));
					textField_1.setText(rs.getString("c_description"));
					
				}
				}catch(Exception e4) {
					JOptionPane.showMessageDialog(null, e4);
				}
			}
		});
		scrollPane.setViewportView(table_1);
	}
	public void settable() {
		try {
			//JOptionPane.showMessageDialog(null, "clicked");
			String s1="select c_id,c_title,c_description,c_level,c_topic,status from complaint where (status='action sent to head')";
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
			//JOptionPane.showMessageDialog(null, "clicked");
			String s1="select c_id,c_title,c_description,c_level,c_topic,status from complaint where (status='complaint sent to head')";
			PreparedStatement pr=connect.prepareStatement(s1);
			try{
			ResultSet rs=pr.executeQuery();
			
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e2) { }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "setting to table"+e);
		}
	}
}
