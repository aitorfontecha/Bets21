package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDiru;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI();
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
	public DiruaSartuGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		
		BLFacade bl = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Diru kopurua");
		lblNewLabel.setBounds(58, 110, 104, 16);
		contentPane.add(lblNewLabel);
		
		JLabel labelErrorea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpiniGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		labelErrorea.setForeground(Color.RED);
		labelErrorea.setBounds(261, 388, 172, 16);
		getContentPane().add(labelErrorea);
		labelErrorea.setVisible(false);
		
		JLabel labelGordeta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpiniGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		labelGordeta.setForeground(Color.GREEN);
		labelGordeta.setBounds(320, 406, 99, 16);
		getContentPane().add(labelGordeta);
		labelGordeta.setVisible(false);
		
		textFieldDiru = new JTextField();
		textFieldDiru.setBounds(203, 105, 130, 26);
		contentPane.add(textFieldDiru);
		textFieldDiru.setColumns(10);
		
		jButtonClose.setBounds(new Rectangle(6, 6, 69, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		
		this.getContentPane().add(jButtonClose, null);

		
		JButton btnNewButton = new JButton("Sartu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textFieldDiru.getText().isEmpty()) {
					Integer dirukop = Integer.parseInt(textFieldDiru.getText());
					String username = bl.getUser();
					boolean em= bl.diruaSartu(dirukop, username);
					if (em) {
						labelGordeta.setVisible(true);
					}
					else {
						labelErrorea.setVisible(true);
					}
				}
				else {
					labelErrorea.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(166, 186, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartuGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setBounds(35, 60, 246, 38);
		contentPane.add(lblNewLabel_1);
		
		
		
		
	}
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
