package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class GuestOptionGUI extends JFrame {

	private JPanel contentPane;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestOptionGUI frame = new GuestOptionGUI();
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
	public GuestOptionGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("GuestOptionGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Apustua egin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new ApostuEginGUI();
				a.setVisible(true);
			}
		});
		
		jButtonClose.setBounds(new Rectangle(6, 6, 69, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		
		this.getContentPane().add(jButtonClose, null);
		btnNewButton.setBounds(141, 39, 156, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Egutegia kontsultatu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new FindQuestionsGUI();
				a.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(141, 68, 156, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Dirua sartu");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new DiruaSartuGUI();
				a.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(141, 97, 156, 29);
		contentPane.add(btnNewButton_3);
		
		JButton mugimenduakIkusiButton = new JButton("Mugimenduak ikusi");
		mugimenduakIkusiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new MugimenduakIkusiGUI();
				a.setVisible(true);
			}
		});
		mugimenduakIkusiButton.setBounds(141, 125, 156, 29);
		contentPane.add(mugimenduakIkusiButton);
		
		JButton buttonAnitza = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GuestOptionGUI.btnNewButton_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		buttonAnitza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new ApostuAnitzaEginGUI();
				a.setVisible(true);
			}
		});
		buttonAnitza.setBounds(141, 153, 156, 29);
		contentPane.add(buttonAnitza);
		
		JButton btnKopiatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GuestOptionGUI.btnKopiatu.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnKopiatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new ErabiltzaileaKopiatuGUI();
				a.setVisible(true);
			}
		});
		btnKopiatu.setBounds(141, 180, 156, 29);
		contentPane.add(btnKopiatu);
		
		JButton buttonFamatuenak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GuestOptionGUI.btnNewButton_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		buttonFamatuenak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new ApostuFamatuenakGUI();
				a.setVisible(true);
			}
		});
		buttonFamatuenak.setBounds(141, 208, 156, 29);
		contentPane.add(buttonFamatuenak);
		
		
		
	}
	
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
