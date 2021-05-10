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
import javax.swing.JScrollPane;
import java.awt.Color;

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
		lblNewLabel.setBounds(48, 104, 47, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Izena:");
		lblNewLabel_2.setBounds(48, 65, 47, 16);
		contentPane.add(lblNewLabel_2);

		
		DefaultListModel model1= new DefaultListModel<>();
		
		
		
		DefaultListModel model2= new DefaultListModel<>();

		Bezero p =bl.getPertsona(bl.getUser());
		double dirukop = p.getDiruKop();
		String dirukop2= Double.toString(dirukop);
		Vector<Apostua> apustuak= p.getApostuak();
		String apustuString= "";
		for (int i=0; i<apustuak.size();i++) {
			model1.add(i, apustuak.get(i));		
		}
		
		Vector<Double> mugimenduak= (Vector<Double>) bl.getMugimenduak(bl.getUser());
		for (int i=0; i<mugimenduak.size();i++) {
			model2.add(i, mugimenduak.get(i));
		}

		JLabel label_Izena = new JLabel();
		label_Izena.setBounds(146, 59, 236, 28);
		contentPane.add(label_Izena);
		label_Izena.setText(bl.getUser());
		label_Izena.setVisible(true);

		JLabel label_dirua = new JLabel();
		label_dirua.setBounds(146, 98, 228, 28);
		contentPane.add(label_dirua);
		label_dirua.setText(String.valueOf(dirukop));
		label_dirua.setVisible(true);

		JLabel lblNewLabel_6 = new JLabel("ZURE MUGIMENDUAK");
		lblNewLabel_6.setBounds(157, 6, 174, 36);
		contentPane.add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 136, 167, 110);
		contentPane.add(scrollPane);
		JList apustuList = new JList(model1);
		scrollPane.setViewportView(apustuList);
		apustuList.setVisibleRowCount(50);
		apustuList.setBackground(new Color(100, 149, 237));
		
				JLabel lblNewLabel_1 = new JLabel("Apustuak:");
				scrollPane.setColumnHeaderView(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(240, 136, 167, 110);
		contentPane.add(scrollPane_1);
		JList diruList = new JList(model2);
		scrollPane_1.setViewportView(diruList);
		diruList.setBackground(new Color(100, 149, 237));
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakIkusiGUI.lblNewLabel_3.text"));
		scrollPane_1.setColumnHeaderView(lblNewLabel_3);
		
		
		lblNewLabel_6.setVisible(true);
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
