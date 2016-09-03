package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class AdminMenu {

	private JFrame frmMenu;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu window = new AdminMenu();
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
	public AdminMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setResizable(false);
		frmMenu.setTitle("Menu");
		frmMenu.getContentPane().setBackground(new Color(100, 149, 237));
		frmMenu.setBounds(300, 300, 404, 276);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JLabel lblWelcomeAdmin = new JLabel("WELCOME ADMIN");
		lblWelcomeAdmin.setForeground(new Color(250, 250, 210));
		lblWelcomeAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeAdmin.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		lblWelcomeAdmin.setBounds(116, 43, 166, 23);
		frmMenu.getContentPane().add(lblWelcomeAdmin);
		
		JButton enroll = new JButton("Enroll Student");
		enroll.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
		enroll.setBackground(new Color(250, 250, 210));
		enroll.setForeground(new Color(30, 144, 255));
		
		enroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EnrollStudent window = new EnrollStudent();
			}
		});
		enroll.setBounds(91, 116, 216, 33);
		frmMenu.getContentPane().add(enroll);
		frmMenu.setVisible(true);
		frmMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
