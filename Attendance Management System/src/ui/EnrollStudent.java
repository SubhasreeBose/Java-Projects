package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

public class EnrollStudent {

	private JFrame frame;
	private JTextField name;
	private JTextField email;
	static Statement st;
	static Connection con;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnrollStudent window = new EnrollStudent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public EnrollStudent() {
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
		catch(Exception e) {
		}

		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			con.setAutoCommit(true);
			st=con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(0, 0, 139));
		frame.setForeground(new Color(30, 144, 255));
		frame.setBounds(100, 100, 370, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setBounds(63, 103, 81, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setBounds(64, 142, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblDepartment = new JLabel("Department ");
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepartment.setForeground(new Color(0, 0, 139));
		lblDepartment.setBounds(63, 180, 81, 14);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCourse.setForeground(new Color(0, 0, 139));
		lblCourse.setBounds(63, 213, 46, 14);
		frame.getContentPane().add(lblCourse);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblYear.setForeground(new Color(0, 0, 139));
		lblYear.setBounds(63, 247, 46, 14);
		frame.getContentPane().add(lblYear);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSemester.setForeground(new Color(0, 0, 139));
		lblSemester.setBounds(64, 280, 63, 14);
		frame.getContentPane().add(lblSemester);
		
		name = new JTextField();
		name.setForeground(new Color(30, 144, 255));
		name.setBounds(183, 97, 114, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setForeground(new Color(30, 144, 255));
		email.setColumns(10);
		email.setBounds(183, 136, 114, 20);
		frame.getContentPane().add(email);
		
		JComboBox dept = new JComboBox();
		dept.setForeground(new Color(30, 144, 255));
		dept.setModel(new DefaultComboBoxModel(new String[] {"CSE", "IT", "ECE"}));
		dept.setBounds(183, 177, 114, 20);
		frame.getContentPane().add(dept);
		
		JComboBox course = new JComboBox();
		course.setForeground(new Color(30, 144, 255));
		course.setModel(new DefaultComboBoxModel(new String[] {"B.Tech", "M.Tech"}));
		course.setBounds(183, 210, 114, 20);
		frame.getContentPane().add(course);
		
		JComboBox year = new JComboBox();
		year.setForeground(new Color(30, 144, 255));
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		year.setBounds(183, 244, 114, 20);
		frame.getContentPane().add(year);
		
		JComboBox sem = new JComboBox();
		sem.setForeground(new Color(30, 144, 255));
		sem.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		sem.setBounds(183, 277, 114, 20);
		frame.getContentPane().add(sem);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setForeground(new Color(30, 144, 255));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try
				{
				
				//st.execute("create or replace procedure proc_insert(n varchar2, e varchar2,d varchar2,y number,s number,c varchar2,se varchar2) is i varchar2(10); str_query varchar2(200);begin str_query:='select id from course where ( dept='''||d ||''' and year='||y||' and semester ='||s ||' and course_name = '''||c||''' )';execute immediate str_query into i;str_query:='insert into student(name, email,course_id,section) values('''||n||''' ,'''|| e||''','''|| i||''','''||se||''')';execute immediate str_query ;end;");

				//CallableStatement pc = con.prepareCall ("begin attendance; end;");

				CallableStatement pc = con.prepareCall ("{call proc_insert(?,?,?,?,?,?)}");
				con.setAutoCommit(true);
				pc.setString(1, name.getText());
				pc.setString(2, email.getText());
				pc.setString(3, dept.getSelectedItem().toString());
				pc.setInt(4, Integer.parseInt(year.getSelectedItem().toString()));
				pc.setInt(5, Integer.parseInt(sem.getSelectedItem().toString()));
				pc.setString(6, course.getSelectedItem().toString());
				//pc.setString(7, section.getSelectedItem().toString());
				if(pc.executeUpdate()==1)
					btnSubmit.setEnabled(false);;
				
				}
				catch(Exception e)
				{
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Email-id already registered with us.");
					
				}
				
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSubmit.setBounds(63, 324, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnMore = new JButton("Add New");
		btnMore.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMore.setForeground(new Color(30, 144, 255));
		btnMore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSubmit.setEnabled(true);
			}
		});
		btnMore.setBounds(208, 324, 89, 23);
		frame.getContentPane().add(btnMore);
		
		JLabel lblEnterNewStudent = new JLabel("Enter new Student");
		lblEnterNewStudent.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEnterNewStudent.setForeground(new Color(0, 0, 128));
		lblEnterNewStudent.setBounds(109, 11, 188, 14);
		frame.getContentPane().add(lblEnterNewStudent);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Subhasree\\git\\oracle\\Dependencies\\goodbye-blue-shades-stripes.png"));
		lblNewLabel_2.setBounds(0, 41, 354, 14);
		frame.getContentPane().add(lblNewLabel_2);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
}
