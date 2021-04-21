package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField repitePassword;
	private JTextField year;
	private JTextField day;
	private JComboBox<String> month = null;
	private DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();
	private JTextField creditCard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		BLFacade bl = MainGUI.getBusinessLogic();

		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel username_label = new JLabel("Username:");
		username_label.setBounds(89, 45, 78, 16);
		contentPane.add(username_label);

		JLabel password_label = new JLabel("Password:");
		password_label.setBounds(89, 74, 78, 16);
		contentPane.add(password_label);

		JLabel repitePassword_label = new JLabel("Repeat password:");
		repitePassword_label.setBounds(44, 98, 129, 16);
		contentPane.add(repitePassword_label);

		JLabel errorea = new JLabel("");
		errorea.setForeground(Color.RED);
		errorea.setBounds(117, 236, 273, 16);
		contentPane.add(errorea);

		username = new JTextField();
		username.setBounds(179, 40, 130, 26);
		contentPane.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(179, 69, 130, 26);
		contentPane.add(password);

		repitePassword = new JPasswordField();
		repitePassword.setBounds(179, 93, 130, 26);
		contentPane.add(repitePassword);

		JLabel birth_label = new JLabel("Birth date:");
		birth_label.setBounds(18, 136, 78, 16);
		contentPane.add(birth_label);

		year = new JTextField();
		year.setBounds(127, 131, 40, 26);
		contentPane.add(year);
		year.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Year:");
		lblNewLabel_4.setBounds(89, 136, 40, 16);
		contentPane.add(lblNewLabel_4);

		month = new JComboBox<String>();
		month.setBounds(236, 131, 112, 27);
		contentPane.add(month);
		month.setModel(monthNames);

		monthNames.addElement("January");
		monthNames.addElement("February");
		monthNames.addElement("March");
		monthNames.addElement("April");
		monthNames.addElement("May");
		monthNames.addElement("June");
		monthNames.addElement("July");
		monthNames.addElement("August");
		monthNames.addElement("September");
		monthNames.addElement("October");
		monthNames.addElement("November");
		monthNames.addElement("December");
		month.setSelectedIndex(0);

		JLabel month_label = new JLabel("Month:");
		month_label.setBounds(189, 136, 49, 16);
		contentPane.add(month_label);

		day = new JTextField();
		day.setBounds(399, 131, 30, 26);
		contentPane.add(day);
		day.setColumns(10);

		JLabel day_label = new JLabel("Day:");
		day_label.setBounds(360, 136, 30, 16);
		contentPane.add(day_label);

		JLabel creditCard_label = new JLabel("Kreditu txartela: ");
		creditCard_label.setBounds(44, 169, 112, 16);
		contentPane.add(creditCard_label);

		creditCard = new JTextField();
		creditCard.setBounds(179, 164, 211, 26);
		contentPane.add(creditCard);
		creditCard.setColumns(10);

		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Erabiltzaile berria gordetzen du.
				errorea.setVisible(false);
				java.util.Date date = newDate(Integer.parseInt(year.getText()), month.getSelectedIndex(),
						Integer.parseInt(day.getText()));
				String passText1 = new String(password.getPassword());
				String passText2 = new String(repitePassword.getPassword());

				if (bl.storeGuest(username.getText(), passText1, passText2, date, creditCard.getText())) {
					errorea.setVisible(true);
					errorea.setText("Gordeta!");
				} else { // Erroreak gertatu dira.
					errorea.setVisible(true);
					errorea.setText("Zerbait gaizki bete duzu!");
				}

			}
		});
		registerButton.setBounds(160, 202, 117, 29);
		contentPane.add(registerButton);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atzera_actionPerformed(arg0);
			}
		});
		btnNewButton.setBounds(17, 6, 64, 29);
		contentPane.add(btnNewButton);

	}

	private void atzera_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
