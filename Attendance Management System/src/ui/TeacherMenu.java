package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class TeacherMenu {

	private JFrame frmMenu;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMenu window = new TeacherMenu();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public TeacherMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("Menu");
		frmMenu.setResizable(false);
		frmMenu.getContentPane().setBackground(new Color(255, 255, 255));
		frmMenu.setBounds(300, 300, 424, 276);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);

		JLabel lblWelcome = new JLabel("WELCOME:");
		lblWelcome.setForeground(new Color(30, 144, 255));
		lblWelcome.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(145, 16, 127, 27);
		frmMenu.getContentPane().add(lblWelcome);

		JButton button = new JButton("View Atendance Report");
		button.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		button.setForeground(new Color(30, 144, 255));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ViewAttendanceReport window1 = new ViewAttendanceReport();
				//window1.frmStudentConsolidateReport.setVisible(true);
				
			}
		});
		button.setBounds(94, 176, 229, 40);
		frmMenu.getContentPane().add(button);

		JButton button_1 = new JButton("Record Attendance");
		button_1.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		button_1.setForeground(new Color(30, 144, 255));
		button_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				AttendanceTable at=new AttendanceTable();
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(94, 102, 229, 40);
		frmMenu.getContentPane().add(button_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Subhasree\\git\\oracle\\Dependencies\\goodbye-blue-shades-stripes.png"));
		label.setBounds(10, 54, 408, 14);
		frmMenu.getContentPane().add(label);
		frmMenu.setVisible(true);


	}

}
