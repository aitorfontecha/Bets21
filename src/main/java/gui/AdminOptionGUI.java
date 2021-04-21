package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Event;

public class AdminOptionGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminOptionGUI frame = new AdminOptionGUI();
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
	public AdminOptionGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ################## GALDERAK SORTU #########################
		JButton galderaSortu = new JButton("Galdera sortu");
		galderaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateQuestionGUI(new Vector<Event>());
				a.setVisible(true);
			}
		});
		galderaSortu.setBounds(136, 69, 177, 29);
		contentPane.add(galderaSortu);

		// ###################### GERTAERA SORTU ########################
		JButton gertaeraSortu = new JButton("Gertaera sortu");
		gertaeraSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateEventGUI();
				a.setVisible(true);
			}
		});
		gertaeraSortu.setBounds(136, 37, 177, 29);
		contentPane.add(gertaeraSortu);

		JButton btnNewButton = new JButton("Atzera");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atzera_actionPerformed(arg0);
			}
		});
		btnNewButton.setBounds(6, 6, 77, 29);
		contentPane.add(btnNewButton);
		
		JButton pronostikoa = new JButton("Pronostikoak gehitu");
		pronostikoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new PronostikoaIpiniGUI();
				a.setVisible(true);
			}
		});
		pronostikoa.setBounds(136, 100, 177, 29);
		contentPane.add(pronostikoa);
		
		JButton emaitzaIpiniButton = new JButton("Emaitzak ipini");
		emaitzaIpiniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a= new EmaitzaIpiniGUI();
				a.setVisible(true);
			}
		});
		emaitzaIpiniButton.setBounds(136, 131, 177, 29);
		contentPane.add(emaitzaIpiniButton);
		
		JButton gertaeraEzabatuButtom = new JButton("Gertaera ezabatu");
		gertaeraEzabatuButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a= new GertaeraEzabatuGUI();
				a.setVisible(true);
			}
		});
		gertaeraEzabatuButtom.setBounds(136, 163, 177, 29);
		contentPane.add(gertaeraEzabatuButtom);

	}

	private void atzera_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
