import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Committee {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String stID1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Committee window = new Committee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connect=null;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public JComboBox levelval;
	public JComboBox topicval;
	/**
	 * Create the application.
	 */
	public Committee() {
		initialize();
		connect=ConnectDatabase.Databaseconnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setTitle("COMITTEE MEMEBER");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1364, 706);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Complaints", null, panel,null);
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		
		table = new JTable();
		table.setBounds(329, 5, 0, 0);
		panel.add(table);
		
		JLabel lblNewLabel = new JLabel("Select level :-");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(32, 22, 139, 26);
		panel.add(lblNewLabel);
		
		JLabel lblSelectTopic = new JLabel("Select topic:-");
		lblSelectTopic.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSelectTopic.setBounds(295, 22, 139, 26);
		panel.add(lblSelectTopic);
		
		levelval = new JComboBox();
		levelval.setBounds(163, 22, 120, 25);
		panel.add(levelval);
		levelval.addItem("Institute");
		levelval.addItem("College");
		levelval.addItem("Department");
		
		 topicval = new JComboBox();
		topicval.setBounds(403, 25, 120, 25);
		panel.add(topicval);
		topicval.addItem("Admission");
		topicval.addItem("Finanace");
		topicval.addItem("Infrastructure");
		topicval.addItem("Lecture Schedual");
		topicval.addItem("Examination");
		topicval.addItem("Other");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 59, 588, 118);
		panel.add(scrollPane);
		
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
		
		JButton btnNewButton = new JButton("SHOW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settable();
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton.setBounds(544, 26, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblTitle = new JLabel("Title :-");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTitle.setBounds(32, 190, 139, 26);
		panel.add(lblTitle);
		
		JLabel lblDescription = new JLabel("Description :-");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDescription.setBounds(32, 232, 139, 26);
		panel.add(lblDescription);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setBounds(145, 195, 476, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(145, 237, 476, 69);
		panel.add(textField_1);
		
		JLabel lblActions = new JLabel("Actions :-");
		lblActions.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblActions.setBounds(32, 317, 94, 26);
		panel.add(lblActions);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(145, 317, 476, 20);
		panel.add(textField_2);
		
		JLabel lblStatus = new JLabel("Status :-");
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblStatus.setBounds(32, 351, 94, 26);
		panel.add(lblStatus);
		
		JComboBox status = new JComboBox();
		status.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		status.setBounds(111, 354, 188, 25);
		panel.add(status);
		status.addItem("complaint sent to head");
		status.addItem("action sent to head");
		status.addItem("completed by head");
		status.addItem("rejected by committee");
		status.addItem("rejected by institute head");
		
		
		JButton btnNewButton_1 = new JButton("Send Actions to Head");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int rw=table_1.getSelectedRow();
					String s=(table_1.getModel().getValueAt(rw, 0).toString());
					String qry3="update complaint set status='"+status.getSelectedItem()+"'  where c_id='"+s+"';";
				
				PreparedStatement pr=connect.prepareStatement(qry3);
				
				pr.execute();
				
				JOptionPane.showMessageDialog(null, "Action sent to institute head");
				
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				try{
					int rw=table_1.getSelectedRow();
					String s=(table_1.getModel().getValueAt(rw, 0).toString());
					String qry3="update complaint set c_action='"+textField_2.getText()+"'  where c_id='"+s+"';";
				
				PreparedStatement pr=connect.prepareStatement(qry3);
				
				pr.execute();
				
				JOptionPane.showMessageDialog(null, "Action sent to institute head");
				
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton_1.setBounds(111, 388, 172, 23);
		panel.add(btnNewButton_1);
		
		JButton btnSendComplaintTo = new JButton("Send Complaint to Head");
		btnSendComplaintTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int rw=table_1.getSelectedRow();
					String s=(table_1.getModel().getValueAt(rw, 0).toString());
					String qry3="update complaint set status='"+status.getSelectedItem()+"'  where c_id='"+s+"';";
				
				PreparedStatement pr=connect.prepareStatement(qry3);
				
				pr.execute();
				
				JOptionPane.showMessageDialog(null, "Complaint sent to institute head");
				
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnSendComplaintTo.setBounds(331, 388, 172, 23);
		panel.add(btnSendComplaintTo);
		
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
		logout.setBounds(513, 382, 145, 31);
		panel.add(logout);
	}
	public void settable() {
		try {
			String s1="select c_id,c_title,c_description,c_level,c_topic,status from complaint where (c_level='"+levelval.getSelectedItem()+"') AND (status IS NULL) AND (c_topic='"+topicval.getSelectedItem()+"');";
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
