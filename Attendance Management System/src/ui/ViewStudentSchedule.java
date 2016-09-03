package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.OracleTypes;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import util.DateLabelFormatter;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.ImageIcon;

public class ViewStudentSchedule {

	private JFrame frmStudentSchedule;
	private JTable studentSchedule;
	private JLabel lblCourse;
	private JLabel lblYear;
	private JComboBox course;
	private JComboBox year;
	private JLabel lblSemester;
	private JLabel lblSection;
	private JComboBox sem;
	private JComboBox section;
	private JLabel lblDepartment;
	private JComboBox dept;
	private JButton btnSubmit;
	static Statement st;
	static Connection con;
	private JLabel label;
	private JLabel lblViewStudentSchedule;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {


		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudentSchedule window = new ViewStudentSchedule();
					window.frmStudentSchedule.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ViewStudentSchedule() {
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
			st=con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frmStudentSchedule = new JFrame();
		
		frmStudentSchedule.getContentPane().setBackground(new Color(255, 255, 255));
		frmStudentSchedule.setBounds(100, 100, 691, 329);
		frmStudentSchedule.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentSchedule.getContentPane().setLayout(null);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 153, 592, 110);
		frmStudentSchedule.getContentPane().add(scrollPane);

		studentSchedule = new JTable();
		studentSchedule.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(studentSchedule);
		studentSchedule.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Day", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		studentSchedule.getColumnModel().getColumn(0).setResizable(false);
		studentSchedule.getColumnModel().getColumn(1).setResizable(false);
		studentSchedule.getColumnModel().getColumn(2).setResizable(false);
		studentSchedule.getColumnModel().getColumn(3).setResizable(false);
		studentSchedule.getColumnModel().getColumn(4).setResizable(false);
		studentSchedule.getColumnModel().getColumn(5).setResizable(false);
		studentSchedule.getColumnModel().getColumn(6).setResizable(false);
		studentSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=studentSchedule.rowAtPoint(arg0.getPoint());
				int col= studentSchedule.columnAtPoint(arg0.getPoint());
				String str="";
				
				if(studentSchedule.getValueAt(row,col)!=null)
				{
					str=studentSchedule.getValueAt(row,col).toString();
					
				}

				if(str!=null)

					try{
						/*st.execute("create or replace procedure get_subject(sscur OUT SYS_REFCURSOR,s varchar2)"
							+ " is str_query varchar2(200);begin"
+"str_query:='select a.name,b.course_name,b.dept,b.year,b.semester from"
+ " subject a inner join course b on a.course_id=b.id "
+"where a.name='''||s||'''';open sscur for str_query;end;");*/
						
						
						ResultSet rs_time=st.executeQuery("select time_in,time_out from period where id="+col);
						rs_time.next();
						String time_in=rs_time.getString("time_in");
						String time_out=rs_time.getString("time_out");
						CallableStatement pc = con.prepareCall ("{call get_subject(?,?)}");

						//pc.registerOutParameter(1, OracleTypes.OTHER);

						con.setAutoCommit(true);

						pc.registerOutParameter(1, OracleTypes.CURSOR);
						pc.setString(2, str);
						pc.execute ();
						ResultSet rs=(ResultSet)pc.getObject(1);
						if(rs!=null) {
							
							while(rs.next()) {
								System.out.print(str);
								JOptionPane.showMessageDialog(null,"Duration: ("+time_in+"- "+time_out+")\n"+
										studentSchedule.getValueAt(row,col).toString()+"\n"+rs.getString(1)+"\n"
										+rs.getString(2)+"\n"+rs.getString(3)+"\n"+ "Year: "+rs.getInt(4));
							}				
							
						}
						else
							System.out.println("no result");

					}catch (SQLException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}


		}
	});

		lblCourse = new JLabel("Course");
		lblCourse.setForeground(new Color(30, 144, 255));
		lblCourse.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblCourse.setBounds(42, 65, 46, 14);
		frmStudentSchedule.getContentPane().add(lblCourse);

		lblYear = new JLabel("Year");
		lblYear.setForeground(new Color(30, 144, 255));
		lblYear.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblYear.setBounds(42, 110, 46, 14);
		frmStudentSchedule.getContentPane().add(lblYear);

		course = new JComboBox();
		course.setForeground(new Color(30, 144, 255));
		course.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		course.setModel(new DefaultComboBoxModel(new String[] {"B.Tech", "M.Tech"}));
		course.setBounds(129, 62, 67, 20);
		frmStudentSchedule.getContentPane().add(course);

		year = new JComboBox();
		year.setForeground(new Color(30, 144, 255));
		year.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		year.setBounds(129, 107, 67, 20);
		frmStudentSchedule.getContentPane().add(year);

		lblSemester = new JLabel("Semester");
		lblSemester.setForeground(new Color(30, 144, 255));
		lblSemester.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblSemester.setBounds(235, 65, 67, 14);
		frmStudentSchedule.getContentPane().add(lblSemester);

		lblSection = new JLabel("Section");
		lblSection.setForeground(new Color(30, 144, 255));
		lblSection.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblSection.setBounds(235, 110, 67, 14);
		frmStudentSchedule.getContentPane().add(lblSection);

		sem = new JComboBox();
		sem.setForeground(new Color(30, 144, 255));
		sem.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		sem.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		sem.setBounds(328, 59, 67, 20);
		frmStudentSchedule.getContentPane().add(sem);

		section = new JComboBox();
		section.setForeground(new Color(30, 144, 255));
		section.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		section.setModel(new DefaultComboBoxModel(new String[] {"A", "B"}));
		section.setBounds(328, 104, 67, 20);
		frmStudentSchedule.getContentPane().add(section);

		lblDepartment = new JLabel("Department");
		lblDepartment.setForeground(new Color(30, 144, 255));
		lblDepartment.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblDepartment.setBounds(461, 65, 83, 14);
		frmStudentSchedule.getContentPane().add(lblDepartment);

		dept = new JComboBox();
		dept.setForeground(new Color(30, 144, 255));
		dept.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		dept.setModel(new DefaultComboBoxModel(new String[] {"CSE", "ECE", "IT"}));
		dept.setBounds(555, 62, 67, 20);
		frmStudentSchedule.getContentPane().add(dept);

		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(30, 144, 255));
		btnSubmit.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String month = "";
				switch(model.getMonth()) {
				case 0: month = "Jan";
				break;
				case 1: month = "Feb";
				break;
				case 2: month = "Mar";
				break;
				case 3: month = "Apr";
				break;
				case 4: month = "May";
				break;
				case 5: month = "Jun";
				break;
				case 6: month = "Jul";
				break;
				case 7: month = "Aug";
				break;
				case 8: month = "Sep";
				break;
				case 9: month = "Oct";
				break;
				case 10: month = "Nov";
				break;
				case 11: month = "Dec";
				break;
				}
				String date = model.getDay()+"-"+month+"-"+model.getYear();
				System.out.println(date);

				try{
					CallableStatement pc = con.prepareCall ("{call proc_student_schedule(?,?,?,?,?,?)}");
					pc.registerOutParameter(1, OracleTypes.CURSOR);
					pc.setString(2, dept.getSelectedItem().toString());
					pc.setInt(3, Integer.parseInt(year.getSelectedItem().toString()));
					pc.setInt(4, Integer.parseInt(sem.getSelectedItem().toString()));
					pc.setString(5, course.getSelectedItem().toString());
					pc.setString(6, section.getSelectedItem().toString());
					pc.execute ();

					Vector <Object>v=new Vector<Object>();
					int flag=1;
					ResultSet rs=(ResultSet)pc.getObject(1);
					if(rs!=null) {
						while(rs.next()) {
							/*Object row[][]=new Object[1][4];
						row[q][0]=rs.getString(1)+"";
						row[q][1]=st.getResultSet().getString(2);
						System.out.println(row[q][1]);
						row[q][2]=st.getResultSet().getString(3);
						row[q][3]=st.getResultSet().getString(4);*/


							
						/*	if(flag==1)
								v.add(0, rs.getObject("Day"));
							flag++;
								v.add(flag-1, rs.getObject("subject_code"));
							DefaultTableModel studmodel = (DefaultTableModel) studentSchedule.getModel();
							if(flag==7)
							{
								
							studmodel.addRow(v);
							flag=1;
							v=new Vector<Object>();
							
							}*/
							
							
							
							DefaultTableModel teachermodel = (DefaultTableModel) studentSchedule.getModel();
							teachermodel.setValueAt("Mon", 0, 0);
							teachermodel.setValueAt("Tue", 1, 0);
							teachermodel.setValueAt("Wed", 2, 0);
							teachermodel.setValueAt("Thu", 3, 0);
							teachermodel.setValueAt("Fri", 4, 0);
							if(rs.getObject("Day").toString().equalsIgnoreCase("MO"))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString(), 0,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase("TU"))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString(), 1,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase(("WE")))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString(), 2,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase(("TH")))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString(), 3,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase(("FR")))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString(), 4,Integer.parseInt((rs.getObject("Period").toString())));

							}
						}
					}
					else
						System.out.println("no result");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(461, 106, 161, 23);
		frmStudentSchedule.getContentPane().add(btnSubmit);
		
		label = new JLabel("New label");
		label.setIcon(new ImageIcon("C:\\Users\\Subhasree\\git\\oracle\\Dependencies\\goodbye-blue-shades-stripes.png"));
		label.setBounds(0, 26, 677, 23);
		frmStudentSchedule.getContentPane().add(label);
		
		lblViewStudentSchedule = new JLabel("View Student Schedule");
		lblViewStudentSchedule.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		lblViewStudentSchedule.setBounds(258, 11, 161, 14);
		frmStudentSchedule.getContentPane().add(lblViewStudentSchedule);
		frmStudentSchedule.setVisible(true);
		frmStudentSchedule.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
