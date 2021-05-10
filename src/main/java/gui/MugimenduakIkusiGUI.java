package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apostua;
import domain.Bezero;
import domain.Pertsona;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.SystemColor;

public class MugimenduakIkusiGUI extends JFrame {

	private JPanel contentPane;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MugimenduakIkusiGUI frame = new MugimenduakIkusiGUI();
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
	public MugimenduakIkusiGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakIkusiGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$

		BLFacade bl = MainGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jButtonClose.setBounds(new Rectangle(6, 6, 69, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		
		this.getContentPane().add(jButtonClose, null);
	
		

		JLabel lblNewLabel = new JLabel("Dirua:");
		lblNewLabel.setBounds(48, 110, 47, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Apustuak:");
		lblNewLabel_1.setBounds(46, 138, 83, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Izena:");
		lblNewLabel_2.setBounds(48, 71, 47, 16);
		contentPane.add(lblNewLabel_2);

		
		DefaultListModel model1= new DefaultListModel<>();
		JList apustuList = new JList(model1);
		apustuList.setVisibleRowCount(50);
		apustuList.setBackground(SystemColor.textHighlight);
		apustuList.setBounds(48, 166, 166, 100);
		contentPane.add(apustuList);
		
		
		
		DefaultListModel model2= new DefaultListModel<>();
		JList diruList = new JList(model2);
		diruList.setBackground(SystemColor.textHighlight);
		diruList.setBounds(250, 166, 174, 101);
		contentPane.add(diruList);
		
		String username= bl.getUser();
		Bezero p =bl.getPertsona(username);
		double dirukop = p.getDiruKop();
		String dirukop2= Double.toString(dirukop);
		Vector<Apostua> apustuak= p.getApostuak();
		String apustuString= "";
		for (int i=0; i<apustuak.size();i++) {
			model1.add(i, apustuak.get(i));		
		}
		
		Vector<Double> mugimenduak= p.getMugimenduak();
		for (int i=0; i<p.getMugimenduak().size();i++) {
			model2.add(i, p.getMugimenduak().get(i));		
		}

		JLabel label_Izena = new JLabel();
		label_Izena.setBounds(146, 59, 236, 28);
		contentPane.add(label_Izena);
		label_Izena.setText(username);
		label_Izena.setVisible(true);

		JLabel label_dirua = new JLabel();
		label_dirua.setBounds(146, 98, 228, 28);
		contentPane.add(label_dirua);
		label_dirua.setText(String.valueOf(dirukop));
		label_dirua.setVisible(true);

		JLabel lblNewLabel_6 = new JLabel("ZURE MUGIMENDUAK");
		lblNewLabel_6.setBounds(157, 6, 174, 36);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakIkusiGUI.lblNewLabel_3.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_3.setBounds(240, 138, 116, 16);
		contentPane.add(lblNewLabel_3);
		
		
		lblNewLabel_6.setVisible(true);
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
