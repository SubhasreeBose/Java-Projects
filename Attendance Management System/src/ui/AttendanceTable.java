package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import oracle.jdbc.OracleTypes;
import util.DateLabelFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class AttendanceTable {

	private JFrame attendance;
	private JTable table;
	static Statement st;
	static Connection con;
	String course_id="";

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceTable window = new AttendanceTable();
					window.attendance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public AttendanceTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
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
			con.setAutoCommit(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		attendance = new JFrame();
		attendance.getContentPane().setForeground(new Color(0, 0, 0));
		attendance.getContentPane().setBackground(new Color(255, 255, 255));
		attendance.setBounds(100, 100, 850, 700);
		attendance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		attendance.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 330, 672, 235);
		attendance.getContentPane().add(scrollPane);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.BOLD, 11));
		datePicker.getJFormattedTextField().setForeground(new Color(30, 144, 255));
		datePicker.setDoubleClickAction(true);
		datePicker.setBounds(610, 202, 143, 23);
        attendance.getContentPane().add(datePicker);

        
        JLabel lblSection = new JLabel("Section");
        lblSection.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
        lblSection.setForeground(new Color(0, 0, 205));
		lblSection.setBounds(468, 162, 55, 14);
		attendance.getContentPane().add(lblSection);

		JComboBox section = new JComboBox();
		section.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		section.setForeground(new Color(30, 144, 255));
		section.setModel(new DefaultComboBoxModel(new String[] {"A", "B"}));
		section.setBounds(610, 158, 143, 23);
		attendance.getContentPane().add(section);
		
		JComboBox dept = new JComboBox();
		dept.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		dept.setForeground(new Color(30, 144, 255));
		dept.setModel(new DefaultComboBoxModel(new String[] {"CSE", "IT", "ECE"}));
		dept.setBounds(610, 103, 143, 23);
		attendance.getContentPane().add(dept);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblYear.setForeground(new Color(0, 0, 205));
		lblYear.setBounds(81, 162, 60, 14);
		attendance.getContentPane().add(lblYear);
		
		JComboBox year = new JComboBox();
		year.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		year.setForeground(new Color(30, 144, 255));
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		year.setBounds(223, 158, 143, 23);
		attendance.getContentPane().add(year);
		
		JLabel lblCourse = new JLabel(" Select Course");
		lblCourse.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblCourse.setForeground(new Color(0, 0, 205));
		lblCourse.setBounds(81, 110, 89, 14);
		attendance.getContentPane().add(lblCourse);
		
		JComboBox course = new JComboBox();
		course.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		course.setForeground(new Color(30, 144, 255));
		course.setModel(new DefaultComboBoxModel(new String[] {"B.Tech", "M.Tech"}));
		course.setBounds(223, 106, 143, 23);
		attendance.getContentPane().add(course);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblSemester.setForeground(new Color(0, 0, 205));
		lblSemester.setBounds(81, 211, 76, 14);
		attendance.getContentPane().add(lblSemester);
		JLabel lblPeriod = new JLabel("Period");
		lblPeriod.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblPeriod.setForeground(new Color(0, 0, 205));
		lblPeriod.setBounds(81, 590, 55, 14);
		attendance.getContentPane().add(lblPeriod);
		
		JComboBox period = new JComboBox();
		period.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		period.setForeground(new Color(30, 144, 255));
		period.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		period.setBounds(166, 586, 143, 23);
		attendance.getContentPane().add(period);
		attendance.setVisible(true);
		
		JComboBox sem = new JComboBox();
		sem.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		sem.setForeground(new Color(30, 144, 255));
		sem.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		sem.setBounds(223, 207, 143, 23);
		attendance.getContentPane().add(sem);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(255, 250, 250));
		table.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Class Roll No", "University Roll No.", "Name", "Status"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);

		JButton btnSubmit = new JButton("Update Attendance");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSubmit.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				
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
				try {
					//st.executeQuery("select uroll,period_id,status,adate from attend");
					st.execute("create or replace procedure "
							+ "attendance_update(erol number,state number,period varchar2,atdate date,cid varchar2,se varchar2) is "
							+ "str_query varchar2(200);"
							+ "flag number;"
							+ "begin str_query:='select count(*) from attend where adate = '''||atdate||''' and course_id ='''||cid||''' and section = '''||se||''' and eroll='||erol;"
							+ "execute immediate str_query into flag;"
							+ "dbms_output.put_line(flag);"
							+ "if flag =0 then "
							+ "str_query:='insert into attend(eroll,course_id,adate,section,'||period||') values('||erol||','''||cid||''','''||atdate||''','''||se||''','||state||')';"
							+ "else "
							+ "str_query:='update attend set '||period||' = '||state||' where adate = '''||atdate||''' and course_id ='''||cid||''' and section = '''||se||''' and eroll='||erol;"
							+ "end if;"
							+ "execute immediate str_query;"
							+ "end;");

					//CallableStatement pc = con.prepareCall ("begin attendance; end;");

					CallableStatement pc = con.prepareCall ("{call attendance_update(?,?,?,?,?,?)}");
					for(int i=0;i<table.getRowCount();i++)
					//for(int i=0;i<1;i++)
					{
						
						int val;
						
						if(table.getModel().getValueAt(i, 3)!=null){
							if(Boolean.parseBoolean(table.getModel().getValueAt(i, 3).toString())== true)
								val=Integer.parseInt(period.getSelectedItem().toString());
							else
								val=-Integer.parseInt(period.getSelectedItem().toString());
						}
						else
							val=-Integer.parseInt(period.getSelectedItem().toString());
						
						int roll=Integer.parseInt(table.getModel().getValueAt(i, 0).toString());
						
						pc.setInt(1, roll);
						pc.setInt(2, val);
						pc.setString(3,"Period"+period.getSelectedItem() );
						pc.setString(4,date);
						pc.setString(5,course_id);
						pc.setString(6,section.getSelectedItem().toString());
						pc.executeUpdate();
						
						
					}


				} catch (SQLException error) {
					// TODO Auto-generated catch block
					error.printStackTrace();
				}

			}
		});
		btnSubmit.setBounds(596, 586, 157, 43);
		attendance.getContentPane().add(btnSubmit);

		
		JButton btnSubmit_1 = new JButton("View Students");
		btnSubmit_1.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnSubmit_1.setForeground(new Color(0, 0, 0));
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSubmit_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					btnSubmit_1.setEnabled(false);
					//st.executeQuery("select uroll,period_id,status,adate from attend");
					//st.execute("create or replace procedure attendance (catCur OUT SYS_REFCURSOR) is begin open catcur for select uroll,period_id,status,adate from attend  ;end;");
					st.execute("create or replace procedure test_attendance "
							+ "(catCur OUT SYS_REFCURSOR ,d varchar2,y number,s number,c varchar2,se varchar2)"
							+ " is i varchar2(10);"
							+ "str_query varchar2(200);"
							+ "begin str_query:='select id from course where ( dept='''||d ||''' and year='||y||' and semester ='||s ||' and course_name = '''||c||''' )';"
							+ "execute immediate str_query into i;"
							+ "str_query:='select eroll,croll,name,course_id from student where course_id='''||i||''' and section ='''||se||'''';"
							+ "open catcur for str_query;"
							+ "end;");
					//CallableStatement pc = con.prepareCall ("begin attendance; end;");

					CallableStatement pc = con.prepareCall ("{call test_attendance(?,?,?,?,?,?)}");
					pc.registerOutParameter(1, OracleTypes.CURSOR);
					pc.setString(2, dept.getSelectedItem().toString());
					pc.setInt(3, Integer.parseInt(year.getSelectedItem().toString()));
					pc.setInt(4, Integer.parseInt(sem.getSelectedItem().toString()));
					pc.setString(5, course.getSelectedItem().toString());
					pc.setString(6, section.getSelectedItem().toString());
					pc.execute ();


					ResultSet rs=(ResultSet)pc.getObject(1);
					if(rs!=null) {
						while(rs.next()) {
							/*Object row[][]=new Object[1][4];
							row[q][0]=rs.getString(1)+"";
							row[q][1]=st.getResultSet().getString(2);
							System.out.println(row[q][1]);
							row[q][2]=st.getResultSet().getString(3);
							row[q][3]=st.getResultSet().getString(4);*/

							Vector <Object>v=new Vector<Object>();
							for(int i=0;i<3;i++)
								v.add(i, rs.getObject(i+1));
							course_id=rs.getObject(4).toString();
							
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.addRow(v);
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
		btnSubmit_1.setBounds(345, 261, 143, 43);
		attendance.getContentPane().add(btnSubmit_1);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblDepartment.setForeground(new Color(0, 0, 205));
		lblDepartment.setBounds(468, 107, 76, 14);
		attendance.getContentPane().add(lblDepartment);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblDate.setForeground(new Color(0, 0, 205));
		lblDate.setBounds(468, 206, 45, 14);
		attendance.getContentPane().add(lblDate);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Subhasree\\git\\oracle\\Dependencies\\goodbye-blue-shades-stripes.png"));
		lblNewLabel.setBounds(0, 32, 834, 50);
		attendance.getContentPane().add(lblNewLabel);
		
		JLabel lblRecordAttendance = new JLabel("RECORD ATTENDANCE");
		lblRecordAttendance.setForeground(new Color(0, 0, 139));
		lblRecordAttendance.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		lblRecordAttendance.setBounds(318, 22, 197, 14);
		attendance.getContentPane().add(lblRecordAttendance);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSubmit_1.setEnabled(true);
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					dm.removeRow(i);
				}
			}
		});
		btnAddNew.setForeground(new Color(30, 144, 255));
		btnAddNew.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		btnAddNew.setBounds(727, 19, 97, 23);
		attendance.getContentPane().add(btnAddNew);
		
		JLabel time = new JLabel("");
		time.setBounds(345, 591, 46, 14);
		attendance.getContentPane().add(time);
		attendance.setVisible(true);
		attendance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
}
