package ui;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;



import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class Login {

	private JFrame frmLogin;
	private JTextField name;
	static Statement st;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setBackground(new Color(30, 144, 255));
		frmLogin.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("EMAIL ID:");
		lblName.setForeground(new Color(250, 250, 210));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(75, 82, 80, 22);
		frmLogin.getContentPane().add(lblName);

		frmLogin.setBounds(300, 300, 424, 276);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(new Color(250, 250, 210));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(75, 126, 82, 20);
		frmLogin.getContentPane().add(lblPassword);

		name = new JTextField();
		name.setBounds(188, 83, 156, 20);
		frmLogin.getContentPane().add(name);
		name.setColumns(10);

		JCheckBox student = new JCheckBox("Continue as student");
		student.setForeground(new Color(250, 250, 210));
		student.setBackground(new Color(30, 144, 255));
		student.setToolTipText("Doesnot require username or password");
		student.setBounds(73, 174, 165, 23);
		frmLogin.getContentPane().add(student);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setForeground(new Color(30, 144, 255));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(!student.isSelected()){

						if(password.getText().equalsIgnoreCase("admin") && name.getText().equalsIgnoreCase("admin")){
							System.out.println("Admin Login Successfull");
							
							AdminMenu window = new AdminMenu();
						}
						else{
							if(isNumeric(password.getText())){
								st.executeUpdate("select * from lecturer where lecturer_id="+password.getText().trim()+" and email='"+name.getText().trim()+"'");
								ResultSet rs=st.getResultSet();
								rs.next();
								System.out.println(rs.getRow()+"");
								if(rs.getRow()==1){
									System.out.println("Login Successfull");
									

									TeacherMenu window1 = new TeacherMenu();


								}
								else{
									System.out.println("Invalid Password");
									JOptionPane.showMessageDialog(null,"Invalid Password!");
								}
							}
							else{
								System.out.println("Invalid Password");
								JOptionPane.showMessageDialog(null,"Invalid Password!");}
								
						}
					}
					else{
						System.out.println("Student Login Successfull");
						
						StudentMenu window = new StudentMenu();	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogin.setBounds(248, 170, 99, 30);
		frmLogin.getContentPane().add(btnLogin);

		JLabel lblLogin = new JLabel("LOGIN ");
		lblLogin.setForeground(new Color(250, 250, 210));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(154, 31, 99, 40);
		frmLogin.getContentPane().add(lblLogin);



		password = new JTextField();
		password.setBounds(188, 126, 156, 20);
		frmLogin.getContentPane().add(password);
		password.setColumns(10);
	}



	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			int d = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
}

