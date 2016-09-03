package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.OracleTypes;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import util.DateLabelFormatter;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ViewAttendanceReport {

	private JFrame frmStudentConsolidateReport;
	private JTable studentTable;
	private JLabel lblDepartment;
	private JLabel lblCourse_1;
	private JLabel lblSemester;
	static Statement st;
	static Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {



		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAttendanceReport window = new ViewAttendanceReport();
					window.frmStudentConsolidateReport.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewAttendanceReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		frmStudentConsolidateReport = new JFrame();
		frmStudentConsolidateReport.getContentPane().setBackground(new Color(255, 250, 250));
		frmStudentConsolidateReport.setBackground(new Color(255, 250, 250));
		frmStudentConsolidateReport.setResizable(false);
		frmStudentConsolidateReport.setTitle("Student Attendance Report");
		frmStudentConsolidateReport.setBounds(100, 100, 900, 491);
		frmStudentConsolidateReport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentConsolidateReport.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 250, 250));
		scrollPane.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		scrollPane.setBounds(64, 290, 806, 161);
		frmStudentConsolidateReport.getContentPane().add(scrollPane);

		studentTable = new JTable();
		studentTable.setFillsViewportHeight(true);
		studentTable.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		studentTable.setRowSelectionAllowed(false);
		studentTable.setEnabled(false);
		studentTable.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(studentTable);
		studentTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date", "Enrollment No.", "Class Roll No", "Name", "1st Period", "2nd Period", "3rd Period", "4th Period", "5th Period", "6th Period"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		studentTable.getColumnModel().getColumn(1).setResizable(false);
		studentTable.getColumnModel().getColumn(1).setPreferredWidth(83);
		studentTable.getColumnModel().getColumn(2).setResizable(false);
		studentTable.getColumnModel().getColumn(3).setResizable(false);
		studentTable.getColumnModel().getColumn(3).setPreferredWidth(108);
		studentTable.getColumnModel().getColumn(4).setResizable(false);
		studentTable.getColumnModel().getColumn(4).setPreferredWidth(58);
		studentTable.getColumnModel().getColumn(5).setResizable(false);
		studentTable.getColumnModel().getColumn(5).setPreferredWidth(61);
		studentTable.getColumnModel().getColumn(6).setPreferredWidth(62);
		studentTable.getColumnModel().getColumn(7).setPreferredWidth(60);
		studentTable.getColumnModel().getColumn(8).setPreferredWidth(59);
		studentTable.getColumnModel().getColumn(9).setPreferredWidth(59);

		JRadioButton rdDate = new JRadioButton("Search By Dates");
		rdDate.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		rdDate.setBackground(new Color(255, 250, 250));

		rdDate.setBounds(64, 101, 169, 23);

		JPanel datepanel = new JPanel();
		datepanel.setBackground(new Color(255, 250, 250));
		datepanel.setBounds(64, 131, 433, 148);
		frmStudentConsolidateReport.getContentPane().add(datepanel);
		datepanel.setLayout(null);

		JPanel subjpanel = new JPanel();
		subjpanel.setBackground(new Color(255, 250, 250));
		subjpanel.setBounds(652, 144, 149, 135);
		frmStudentConsolidateReport.getContentPane().add(subjpanel);
		subjpanel.setLayout(null);
		subjpanel.setVisible(false);
		datepanel.setVisible(false);

		JRadioButton rdSubj = new JRadioButton("Search By Subject");
		rdSubj.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		rdSubj.setBackground(new Color(255, 250, 250));
		rdSubj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdDate.setSelected(false);
				datepanel.setVisible(false);
				subjpanel.setVisible(true);
				DefaultTableModel dm = (DefaultTableModel) studentTable.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					dm.removeRow(i);
				}





			}
		});
		rdDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				datepanel.setVisible(true);
				subjpanel.setVisible(false);
				rdSubj.setSelected(false);

				DefaultTableModel dm = (DefaultTableModel) studentTable.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					dm.removeRow(i);
				}
			}
		});
		rdSubj.setBounds(652, 101, 169, 23);
		frmStudentConsolidateReport.getContentPane().add(rdSubj);
		frmStudentConsolidateReport.getContentPane().add(rdDate);



		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);


		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(208, 52, 150, 82);
		datepanel.add(panel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Choose Date", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setShowYearButtons(true);
		datePicker.setBounds(6, 46, 125, 23);
		panel.add(datePicker);
		datePicker.setDoubleClickAction(true);

		JCheckBox all = new JCheckBox("All Dates");
		all.setBounds(9, 16, 93, 23);
		panel.add(all);
		all.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		all.setBackground(new Color(255, 250, 250));

		JLabel lblyear = new JLabel("Year:");
		lblyear.setBounds(10, 47, 89, 23);
		datepanel.add(lblyear);
		lblyear.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));

		lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(10, 77, 89, 23);
		datepanel.add(lblDepartment);
		lblDepartment.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));

		lblCourse_1 = new JLabel("Course:");
		lblCourse_1.setBounds(10, 14, 89, 23);
		datepanel.add(lblCourse_1);
		lblCourse_1.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));

		lblSemester = new JLabel("Semester:");
		lblSemester.setBounds(10, 111, 89, 23);
		datepanel.add(lblSemester);
		lblSemester.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));

		JComboBox course = new JComboBox();
		course.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		course.setBounds(109, 14, 79, 23);
		datepanel.add(course);
		course.setModel(new DefaultComboBoxModel(new String[] {"B.Tech", "M.Tech"}));

		JComboBox year = new JComboBox();
		year.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		year.setBounds(109, 47, 79, 23);
		datepanel.add(year);
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));

		JComboBox department = new JComboBox();
		department.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		department.setBounds(109, 77, 79, 23);
		datepanel.add(department);
		department.setModel(new DefaultComboBoxModel(new String[] {"CSE", "ECE", "IT"}));

		JComboBox semester = new JComboBox();
		semester.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		semester.setBounds(109, 111, 79, 23);
		datepanel.add(semester);
		semester.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));

		JComboBox section = new JComboBox();
		section.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		section.setBounds(279, 14, 79, 23);
		datepanel.add(section);
		section.setModel(new DefaultComboBoxModel(new String[] {"A", "B"}));

		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblSection.setBounds(208, 0, 60, 50);
		datepanel.add(lblSection);

		JButton btnSubmit = new JButton("Go");
		btnSubmit.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnSubmit.setBounds(371, 103, 52, 31);
		datepanel.add(btnSubmit);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					btnSubmit.setEnabled(false);
					studentTable.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
									"Date", "Enrollment No.", "Class Roll No", "Name", "1st Period", "2nd Period", "3rd Period", "4th Period", "5th Period", "6th Period"
							}
							) {
						Class[] columnTypes = new Class[] {
								String.class, String.class, String.class, String.class, String.class, String.class, Object.class, Object.class, Object.class, Object.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
					studentTable.getColumnModel().getColumn(1).setResizable(false);
					studentTable.getColumnModel().getColumn(1).setPreferredWidth(83);
					studentTable.getColumnModel().getColumn(2).setResizable(false);
					studentTable.getColumnModel().getColumn(3).setResizable(false);
					studentTable.getColumnModel().getColumn(3).setPreferredWidth(108);
					studentTable.getColumnModel().getColumn(4).setResizable(false);
					studentTable.getColumnModel().getColumn(4).setPreferredWidth(58);
					studentTable.getColumnModel().getColumn(5).setResizable(false);
					studentTable.getColumnModel().getColumn(5).setPreferredWidth(61);
					studentTable.getColumnModel().getColumn(6).setPreferredWidth(62);
					studentTable.getColumnModel().getColumn(7).setPreferredWidth(60);
					studentTable.getColumnModel().getColumn(8).setPreferredWidth(59);
					studentTable.getColumnModel().getColumn(9).setPreferredWidth(59);

					//st.executeQuery("select uroll,period_id,status,adate from attend");
					//st.execute("create or replace procedure attendance (catCur OUT SYS_REFCURSOR) is begin open catcur for select eroll,period_id,status,adate from attend ;end;");


					/*st.execute("create or replace procedure attendance "
							+ "(catCur OUT SYS_REFCURSOR ,d varchar2,y number,s number,c varchar2,se varchar2)"
							+ " is i varchar2(10);str_query varchar2(200);"
							+ "begin"
							+"str_query:='select id from course where "
							+ "( dept='''||d ||''' and year='||y||' and semester ='||s ||' and course_name = '''||c||''' )';"
							+ "execute immediate str_query into i;"
							+"str_query:='select a.eroll,a.croll,a.name,b.period1,b.period2,b.period3,b.period4,b.period5,"
							+ "b.period6 from student a inner join attend b on"
							+ " a.eroll=b.eroll where a.period1=0 where course_id='||i;"
							+"open catcur for str_query;end;");*/
					//CallableStatement pc = con.prepareCall ("begin attendance; end;");

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

					CallableStatement pc =null;
					if(all.isSelected())
						pc = con.prepareCall ("{call attendance(?,?,?,?,?,?)}");
					else
						pc = con.prepareCall ("{call attendance_date(?,?,?,?,?,?,?)}");
					pc.registerOutParameter(1, OracleTypes.CURSOR);
					pc.setString(2, department.getSelectedItem().toString());
					pc.setInt(3, Integer.parseInt(year.getSelectedItem().toString()));
					pc.setInt(4, Integer.parseInt(semester.getSelectedItem().toString()));
					pc.setString(5, course.getSelectedItem().toString());
					pc.setString(6, section.getSelectedItem().toString());
					if(!all.isSelected())
						pc.setString(7, date);
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
							String status;
							Vector <Object>v=new Vector<Object>();
							for(int i=0;i<10;i++){
								if(i==0)
									v.add(i, rs.getObject(i+1).toString().substring(0, 10));
								else if(i>3){
									if(Integer.parseInt(rs.getObject(i+1).toString())>0)
										status="P";
									else if (Integer.parseInt(rs.getObject(i+1).toString())<0)
										status="A";
									else
										status="NA";

									v.add(i, status);
								}
								else
									v.add(i, rs.getObject(i+1));
							}
							DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
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

		JButton more = new JButton("View More");
		more.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		more.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		more.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdDate.isSelected())
				{



				}
				if(rdSubj.isSelected())
				{

				}
				btnSubmit.setEnabled(true);
				DefaultTableModel dm = (DefaultTableModel) studentTable.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					dm.removeRow(i);
				}
			}
		});

		more.setBounds(743, 16, 127, 31);
		frmStudentConsolidateReport.getContentPane().add(more);



		JComboBox subject = new JComboBox();
		subject.setBounds(39, 48, 71, 22);
		subjpanel.add(subject);
		subject.setModel(new DefaultComboBoxModel(new String[] {"CS101", "CS102", "CS103", "CS104", "CS105", "CS106", "CS107", "CS108", "CS109", "CS110"}));

		JLabel lblFilterBySubject = new JLabel("Choose Subject Code");
		lblFilterBySubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilterBySubject.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		lblFilterBySubject.setBounds(14, 13, 121, 14);
		subjpanel.add(lblFilterBySubject);

		JButton btnGet = new JButton("Go");
		btnGet.setBounds(39, 81, 71, 22);
		subjpanel.add(btnGet);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Subhasree\\git\\oracle\\Dependencies\\goodbye-blue-shades-stripes.png"));
		lblNewLabel.setBounds(10, 58, 884, 14);
		frmStudentConsolidateReport.getContentPane().add(lblNewLabel);
		
		JLabel lblView = new JLabel(" View Attendance Report");
		lblView.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		lblView.setBackground(new Color(255, 250, 250));
		lblView.setBounds(356, 19, 182, 23);
		frmStudentConsolidateReport.getContentPane().add(lblView);
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CallableStatement pc1,pc2;
				int row=0;
				try {
					btnSubmit.setEnabled(false);
					studentTable.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
									"Date", "Enrollment No.","Name", "1st Period", "2nd Period", "3rd Period", "4th Period", "5th Period", "6th Period"
							}
							) {
						Class[] columnTypes = new Class[] {
								String.class, String.class,  String.class,String.class, String.class, Object.class, Object.class, Object.class, Object.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});

					//studentTable.setShowVerticalLines(false);
					pc1 = con.prepareCall ("{call getDates(?)}");
					pc1.registerOutParameter(1, OracleTypes.CURSOR);
					pc1.execute ();
					ResultSet rs=(ResultSet)pc1.getObject(1);
					if(rs!=null) {
						while(rs.next()) {

							String input_date=rs.getString(1);

							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
							Date tempDate=simpleDateFormat.parse(input_date);
							/*SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
							  Date dt1=format1.parse(input_date);*/
							DateFormat format2=new SimpleDateFormat("EE"); 
							String finalDay=format2.format(tempDate);
							finalDay=finalDay.substring(0, 2).toUpperCase();
							SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-YYYY"); 
							String finalDate=outputDateFormat.format(tempDate);


							try {
								pc2 = con.prepareCall ("{call testDynamicNumbers(?,?,?,?)}");
								pc2.registerOutParameter(1, OracleTypes.CURSOR);
								pc2.setString(2, finalDate);
								pc2.setString(3, finalDay);
								pc2.setString(4,subject.getSelectedItem().toString());
								System.out.println(finalDate);

								pc2.execute ();


								ResultSet rs1=(ResultSet)pc2.getObject(1);
								if(rs1!=null) {
									while(rs1.next()) {
										Vector <Object> v = new Vector<Object>();
										v.add(rs1.getString(1).substring(0,10));
										DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
										model.addRow(v);

										//studentTable.setValueAt(rs1.getString(1), row, 0);
										studentTable.setValueAt(rs1.getInt(2), row, 1);
										studentTable.setValueAt(rs1.getString(3), row, 2);
										System.out.print(rs1.getInt(2)+" "+rs1.getString(1));
										for(int i=4;i<=rs1.getMetaData().getColumnCount();i++){
											String status;
											int s=rs1.getInt(i);
											System.out.print(s+"|");
											if(s>0)
												status="P";
											else if(s<0)
												status="A";
											else
												status="No class";
											if(s!=0)
												studentTable.setValueAt(status, row, Math.abs(rs1.getInt(i))+2);
										}
										System.out.println();
										row++;
									}

								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}




						}
					}



					pc1.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();	
				}





			}
		});




		frmStudentConsolidateReport.setVisible(true);
		frmStudentConsolidateReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
