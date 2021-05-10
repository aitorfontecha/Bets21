package gui;

import businessLogic.BLFacade;
import domain.Bezero;
import domain.Pertsona;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class ErabiltzaileaKopiatuGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErabiltzaileaKopiatuGUI frame = new ErabiltzaileaKopiatuGUI();
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
	public ErabiltzaileaKopiatuGUI() {

		BLFacade bl = MainGUI.getBusinessLogic();

		setTitle("Erabiltzailea Kopiatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Aukeratu kopiatu nahi duzun bezeroaren izena:");
		lblNewLabel.setBounds(30, 35, 348, 28);
		contentPane.add(lblNewLabel);

		JButton kopiatuButton = new JButton("Kopiatu erabiltzailea.");
		kopiatuButton.setBackground(Color.WHITE);
		kopiatuButton.setBounds(132, 192, 170, 21);
		contentPane.add(kopiatuButton);

		DefaultListModel model = new DefaultListModel<>();
		
		JButton buttonAtzera = new JButton("Atzera");
		buttonAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		buttonAtzera.setBackground(Color.WHITE);
		buttonAtzera.setBounds(6, 6, 77, 29);
		contentPane.add(buttonAtzera);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 415, 107);
		contentPane.add(scrollPane);
		JList erabiltzaileList = new JList(model);
		scrollPane.setViewportView(erabiltzaileList);
		erabiltzaileList.setBackground(new Color(100, 149, 237));

		JLabel error = new JLabel("New label");
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setForeground(Color.GREEN);
		error.setBounds(6, 223, 415, 13);
		contentPane.add(error);
		error.setVisible(false);

		List<Pertsona> bezeroak = bl.getBezeroak();
		for(Pertsona p : bezeroak) {
			System.out.println(p.getErabiltzailea());
			if (!p.getErabiltzailea().equals("admin") && !p.getErabiltzailea().equals(bl.getUser())) {
				model.addElement(p);
			}
		}




		kopiatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!erabiltzaileList.isSelectionEmpty()) {
					Bezero kop = (Bezero) erabiltzaileList.getSelectedValue();
					bl.kopiatu(kop,bl.getPertsona(bl.getUser()));
					error.setText("Erabiltzailea kopiatu da.");
					error.setVisible(true);
					
				} else {
					error.setText("Ez da erabiltzailerik aukeratu.");
					error.setForeground(Color.RED);
					error.setVisible(true);
				}
			}
		});




	}
	
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
