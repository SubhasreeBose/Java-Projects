package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentMenu {

	private JFrame frmMenu;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMenu window = new StudentMenu();
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
	public StudentMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setResizable(false);
		frmMenu.setTitle("Menu");
		frmMenu.getContentPane().setBackground(new Color(30, 144, 255));
		frmMenu.setBounds(300, 300, 424, 276);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("WELCOME:");
		lblWelcome.setForeground(new Color(250, 250, 210));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblWelcome.setBounds(145, 30, 127, 27);
		frmMenu.getContentPane().add(lblWelcome);
		
		JButton attendance = new JButton("Check Percentage of Attendance");
		attendance.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		attendance.setForeground(new Color(30, 144, 255));
		attendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		attendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Percentage window1 = new Percentage();
				
			}
		});
		attendance.setBounds(45, 156, 328, 50);
		frmMenu.getContentPane().add(attendance);
		
		JButton button_2 = new JButton("View Schedule");
		button_2.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		button_2.setForeground(new Color(30, 144, 255));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewStudentSchedule window = new ViewStudentSchedule();
			}
		});
		button_2.setBounds(45, 81, 328, 50);
		frmMenu.getContentPane().add(button_2);
		frmMenu.setVisible(true);
	}

}
