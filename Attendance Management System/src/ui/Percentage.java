package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Percentage {

	private JFrame frame;
	private JTextField eroll;
	private JTextField percentage;
	static Statement st;
	private JLabel label;
	private JLabel lblViewPercentage;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Percentage window = new Percentage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Percentage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e)
		{
			
		
		}
		
		try {
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			st=con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 250, 250));
		frame.setBounds(300, 300, 424, 276);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		eroll = new JTextField();
		eroll.setBounds(230, 74, 125, 20);
		frame.getContentPane().add(eroll);
		eroll.setColumns(10);
		
		JLabel lblEnrollmentNumber = new JLabel("Enrollment Number");
		lblEnrollmentNumber.setForeground(new Color(0, 0, 139));
		lblEnrollmentNumber.setBounds(86, 66, 114, 36);
		frame.getContentPane().add(lblEnrollmentNumber);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					st.execute("select round(cal_percentage("+Integer.parseInt(eroll.getText().trim()) +"),1) as percentage from dual");
					ResultSet rs=st.getResultSet();
					rs.next();
					percentage.setText(rs.getString("percentage"));
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCheck.setBounds(141, 132, 125, 23);
		frame.getContentPane().add(btnCheck);
		
		percentage = new JTextField();
		percentage.setBounds(230, 193, 125, 20);
		frame.getContentPane().add(percentage);
		percentage.setColumns(10);
		
		JLabel lblPercentage = new JLabel("Percentage ");
		lblPercentage.setForeground(new Color(0, 0, 139));
		lblPercentage.setBounds(86, 185, 114, 36);
		frame.getContentPane().add(lblPercentage);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Subhasree\\git\\oracle\\Dependencies\\goodbye-blue-shades-stripes.png"));
		label.setBounds(10, 41, 388, 14);
		frame.getContentPane().add(label);
		
		lblViewPercentage = new JLabel("View Percentage");
		lblViewPercentage.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblViewPercentage.setForeground(new Color(0, 0, 128));
		lblViewPercentage.setBounds(154, 11, 138, 14);
		frame.getContentPane().add(lblViewPercentage);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
