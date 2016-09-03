package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ViewTeacherSchedule {

	private JFrame frmTeacherSchedule;
	private JTable studentSchedule;
	private JButton btnSubmit;
	static Statement st;
	static Connection con;
	private JLabel lblEmailId;
	private JTextField email;
	private JLabel lblNewLabel;
	private JLabel lblTeacherSchedule;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTeacherSchedule window = new ViewTeacherSchedule();
					window.frmTeacherSchedule.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewTeacherSchedule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTeacherSchedule = new JFrame();
		frmTeacherSchedule.getContentPane().setBackground(new Color(255, 255, 255));
		frmTeacherSchedule.setBounds(100, 100, 693, 423);
		frmTeacherSchedule.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeacherSchedule.getContentPane().setLayout(null);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 168, 539, 115);
		frmTeacherSchedule.getContentPane().add(scrollPane);

		studentSchedule = new JTable();
		studentSchedule.setFont(new Font("Tahoma", Font.BOLD, 12));
		studentSchedule.setForeground(new Color(30, 144, 255));
		studentSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=studentSchedule.rowAtPoint(arg0.getPoint());
				int col= studentSchedule.columnAtPoint(arg0.getPoint());
				String str="";
				int k=0;
				if(studentSchedule.getValueAt(row,col)!=null)
				{
					str=studentSchedule.getValueAt(row,col).toString();
					k=str.indexOf("(");
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
						
						//pc.registerOutParameter(1, OracleTypes.OTHER);

						con.setAutoCommit(true);
						CallableStatement pc = con.prepareCall ("{call get_subject(?,?)}");
					
						//pc.registerOutParameter(1, OracleTypes.OTHER);

						con.setAutoCommit(true);

						pc.registerOutParameter(1, OracleTypes.CURSOR);
						pc.setString(2, str.substring(0,k));
						
						
						pc.execute ();
						ResultSet rs=(ResultSet)pc.getObject(1);
						
						if(rs!=null) {
							
							while(rs.next()) {
								System.out.print(str.substring(0,k));
								JOptionPane.showMessageDialog(null,"Duration: ("+time_in+"- "+time_out+")\n"+
										studentSchedule.getValueAt(row,col).toString()+"\n"+rs.getString(1)+"\n"
										+rs.getString(2)+"\n"+rs.getString(3)+"\n"+ "Year: "+rs.getInt(4)+"\n"
										);
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
						"Day", "Period 1\n", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6"
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

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSubmit.setForeground(new Color(30, 144, 255));
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
					CallableStatement pc = con.prepareCall ("{call proc_teacher_schedule(?,?)}");
					pc.registerOutParameter(1, OracleTypes.CURSOR);
					pc.setString(2, email.getText().toString());
					pc.execute ();

					Vector <Object>v=new Vector<Object>();
					int flag=1;
					ResultSet rs=(ResultSet)pc.getObject(1);
					if(rs!=null) {
						while(rs.next()) {
							System.out.println("Entered");
							/*Object row[][]=new Object[1][4];
						row[q][0]=rs.getString(1)+"";
						row[q][1]=st.getResultSet().getString(2);
						System.out.println(row[q][1]);
						row[q][2]=st.getResultSet().getString(3);
						row[q][3]=st.getResultSet().getString(4);*/



							//if(flag==1)
							/*v.add(0, rs.getObject("Day"));
							System.out.println(v.get(0).toString());
							//flag++;
							v.add(1, rs.getObject("subject_code"));

							//if(flag==7)
							{

								teachermodel.addRow(v);

								//flag=1;
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
								teachermodel.setValueAt(rs.getObject("Subject_code").toString() + "("+rs.getObject("Sec").toString()+")", 0,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase("TU"))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString()+"("+rs.getObject("Sec").toString()+")", 1,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase(("WE")))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString()+"("+rs.getObject("Sec").toString()+")", 2,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase(("TH")))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString()+"("+rs.getObject("Sec").toString()+")", 3,Integer.parseInt((rs.getObject("Period").toString())));

							}
							if(rs.getObject("Day").toString().equalsIgnoreCase(("FR")))
							{
								teachermodel.setValueAt(rs.getObject("Subject_code").toString()+"("+rs.getObject("Sec").toString()+")", 4,Integer.parseInt((rs.getObject("Period").toString())));

							}

						}
					}
					else
						System.out.println("no result");
					pc.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(519, 117, 89, 23);
		frmTeacherSchedule.getContentPane().add(btnSubmit);

		lblEmailId = new JLabel("Email  Id");
		lblEmailId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmailId.setForeground(new Color(30, 144, 255));
		lblEmailId.setBounds(67, 114, 103, 29);
		frmTeacherSchedule.getContentPane().add(lblEmailId);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.BOLD, 12));
		email.setForeground(new Color(30, 144, 255));
		email.setBounds(193, 117, 223, 23);
		frmTeacherSchedule.getContentPane().add(email);
		email.setColumns(10);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Subhasree\\git\\oracle\\Dependencies\\goodbye-blue-shades-stripes.png"));
		lblNewLabel.setBounds(0, 37, 677, 50);
		frmTeacherSchedule.getContentPane().add(lblNewLabel);
		
		lblTeacherSchedule = new JLabel("Teacher Schedule");
		lblTeacherSchedule.setForeground(new Color(0, 0, 139));
		lblTeacherSchedule.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTeacherSchedule.setBounds(264, 11, 223, 50);
		frmTeacherSchedule.getContentPane().add(lblTeacherSchedule);
}
}
