package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Pertsona;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		
		BLFacade bl = MainGUI.getBusinessLogic();
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(79, 88, 77, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(83, 128, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(79, 128, 77, 16);
		contentPane.add(lblNewLabel_2);

		username = new JTextField();
		username.setBounds(179, 83, 130, 26);
		contentPane.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(179, 128, 130, 26);
		contentPane.add(password);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usernameText = username.getText();
				String passText = new String(password.getPassword());
				Pertsona p= bl.isLogin(usernameText, passText);
				bl.setUser(p.getErabiltzailea());
				if (p != null) { // Erabiltzailea existitzen da eta pasahitza zuzena da.
					System.out.println("Erabiltzailea ondo sartu da");
					if (p.getClass().getSimpleName().equals("Administratzaile")) { // Administratzaile moduan sartu
						JFrame a = new AdminOptionGUI();
						a.setVisible(true);
					} else { // Erabiltzaile moduan sartu.
						JFrame a = new GuestOptionGUI();
						a.setVisible(true);
					}

				} else {
					System.out.println("Erabiltzaile hori ez da existitzen");
				}
			}
		});
		btnLogin.setBounds(162, 196, 117, 29);
		contentPane.add(btnLogin);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atzera_actionPerformed(arg0);
			}
		});
		btnNewButton.setBounds(6, 6, 77, 29);
		contentPane.add(btnNewButton);

	}

	private void atzera_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	public String getUser() {
		return username.getText();
	}
}
