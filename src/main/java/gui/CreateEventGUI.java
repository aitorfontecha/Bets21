package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;

public class CreateEventGUI extends JFrame {

	private JPanel contentPane;
	private JTextField partidoIzena;
	private JTextField eguna;
	private JTextField urtea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventGUI frame = new CreateEventGUI();
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
	public CreateEventGUI() {
		
		BLFacade bl = MainGUI.getBusinessLogic();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		partidoIzena = new JTextField();
		partidoIzena.setBounds(209, 61, 113, 20);
		contentPane.add(partidoIzena);
		partidoIzena.setColumns(10);

		eguna = new JTextField();
		eguna.setBounds(75, 125, 49, 20);
		contentPane.add(eguna);
		eguna.setColumns(10);

		JLabel errorea = new JLabel("");
		errorea.setForeground(Color.RED);
		errorea.setBounds(87, 174, 309, 26);
		contentPane.add(errorea);

		JComboBox<String> hilabetea = new JComboBox<String>();
		hilabetea.setBounds(209, 125, 96, 22);
		contentPane.add(hilabetea);
		DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();
		hilabetea.setModel(monthNames);

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
		hilabetea.setSelectedIndex(0);

		urtea = new JTextField();
		urtea.setBounds(354, 125, 55, 20);
		contentPane.add(urtea);
		urtea.setColumns(10);

		JLabel gertaera = new JLabel("Partida");
		gertaera.setBounds(87, 64, 49, 14);
		contentPane.add(gertaera);

		JLabel egunaPartida = new JLabel("Eguna");
		egunaPartida.setBounds(24, 128, 49, 14);
		contentPane.add(egunaPartida);

		JLabel hilabetePartida = new JLabel("Hilabetea");
		hilabetePartida.setBounds(136, 128, 71, 14);
		contentPane.add(hilabetePartida);

		JLabel hilabeteaPartida = new JLabel("Urtea");
		hilabeteaPartida.setBounds(317, 128, 49, 14);
		contentPane.add(hilabeteaPartida);

		JButton gertaeraSortu = new JButton("Sortu");
		gertaeraSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorea.setVisible(false);
				try {
					Date data = UtilDate.newDate(Integer.parseInt(urtea.getText()), hilabetea.getSelectedIndex(),
							Integer.parseInt(eguna.getText()));
					Date gaurkoa = new Date();
					String formato = "yyyy";
					SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
					int unekoUrtea = Integer.parseInt(dateFormat.format(gaurkoa));
					int gertaeraUrtea = Integer.parseInt(dateFormat.format(data));
					if (unekoUrtea <= gertaeraUrtea) {
						Event gertaera = new Event(partidoIzena.getText(), data);
						if (bl.existEvent(data, gertaera)) { // Gertaera dagoeneko existitzen da.
							errorea.setVisible(true);
							errorea.setText("Dagoeneko gertaera existitzen da!");
							System.out.println("Dagoeneko gertaera existitzen da!");
						} else { // Gertaera gordetzen da.
							errorea.setVisible(true);
							errorea.setText("Gordeta!");
							boolean b = bl.storeEvent(gertaera);
						}
					} else {
						errorea.setVisible(true);
						errorea.setText("Data hori iraungi da!");
						System.out.println("Data hori iraungi da");
					}
				} catch (Exception exce) {
					errorea.setVisible(true);
					errorea.setText("Data gaizki dago!");
				}

			}
		});
		gertaeraSortu.setBounds(182, 212, 113, 36);
		contentPane.add(gertaeraSortu);

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
}
