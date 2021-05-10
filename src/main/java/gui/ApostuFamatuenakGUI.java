package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apostua;
import domain.Bezero;
import domain.Pronostikoa;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ApostuFamatuenakGUI extends JFrame {

	private JPanel contentPane;
	private JTextField diruKop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostuFamatuenakGUI frame = new ApostuFamatuenakGUI();
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
	public ApostuFamatuenakGUI() {

		BLFacade bl = MainGUI.getBusinessLogic();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultListModel model = new DefaultListModel<>();

		List<Pronostikoa> pronostikoak= bl.getPronostikoGuztiak();
		ArrayList<Integer> apostuKop= new ArrayList<Integer>();
		if(apostuKop.size()>0) {
			for (int i = 0; i < 5; i++) {
				if (pronostikoak.get(i) != null) {
					apostuKop.add(pronostikoak.get(i).getApostuak().size());
				}
			}
		} else {
			System.out.println("Ez dira aposturik egin oraindik.");
		}


		txertaketaBidezkoOrdenazioa(pronostikoak, apostuKop);
		for (int i=0; i<pronostikoak.size();i++) {
			model.add(i, pronostikoak.get(i));
		}
		JList list = new JList(model);
		list.setBackground(SystemColor.textHighlight);
		list.setBounds(108, 49, 277, 94);
		contentPane.add(list);

		JLabel lblNewLabel = new JLabel("PRONOSTIKO FAMATUENAK");
		lblNewLabel.setBounds(133, 13, 197, 16);
		contentPane.add(lblNewLabel);


		diruKop = new JTextField();
		diruKop.setBounds(165, 170, 100, 26);
		contentPane.add(diruKop);
		diruKop.setColumns(10);

		JButton apostatuBotoia = new JButton("APOSTATU");
		apostatuBotoia.setBounds(146, 208, 132, 29);
		contentPane.add(apostatuBotoia);
		apostatuBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!list.isSelectionEmpty()) {
					Pronostikoa pr = (Pronostikoa) list.getSelectedValue();
					double dirukop = Double.parseDouble(diruKop.getText());
					bl.apustuaEgin(pr, dirukop, bl.getUser());
				}
			}
		});





		JLabel lblNewLabel_1 = new JLabel("Diru kopurua:");
		lblNewLabel_1.setBounds(43, 175, 122, 16);
		contentPane.add(lblNewLabel_1);

		JButton buttonAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApostuFamatuenakGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		buttonAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		buttonAtzera.setBounds(6, 8, 75, 29);
		contentPane.add(buttonAtzera);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostuFamatuenakGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_2.setBounds(16, 49, 80, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostuFamatuenakGUI.lblNewLabel_3.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_3.setBounds(16, 67, 76, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostuFamatuenakGUI.lblNewLabel_4.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_4.setBounds(16, 85, 75, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostuFamatuenakGUI.lblNewLabel_5.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_5.setBounds(16, 103, 65, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApostuFamatuenakGUI.lblNewLabel_6.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_6.setBounds(16, 120, 65, 16);
		contentPane.add(lblNewLabel_6);




	}

	public void txertaketaBidezkoOrdenazioa(List<Pronostikoa> pronostikoak, ArrayList<Integer> kopuruak) { 
		int n = kopuruak.size();
		int txertaPos, lag;
		Pronostikoa lag2;
		// ordenatuta(arr[0:i])
		for (int i = 1; i < n; i++) { 
			txertaPos = i;
			lag = kopuruak.get(i);
			lag2 = pronostikoak.get(i);
			// lag <= arr[txertaPos:i]
			while (txertaPos > 0 && lag > kopuruak.get(txertaPos-1)) {
				kopuruak.set(txertaPos,kopuruak.get(txertaPos-1));
				pronostikoak.set(txertaPos,pronostikoak.get(txertaPos-1));
				txertaPos--;
			}
			kopuruak.set(txertaPos,lag);
			pronostikoak.set(txertaPos, lag2);
		}
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
