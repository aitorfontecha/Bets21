package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BezeroaAdapter;
import domain.Bezero;

public class ApostuakIkusiGUI extends JFrame {

    private JPanel contentPane;
    private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

    private Bezero bezeroa;
    GuestOptionGUI aurrekoa;

    public ApostuakIkusiGUI(Bezero bezeroa) {
        try {
            this.bezeroa = bezeroa;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jbInit() {
        BLFacade facade = MainGUI.getBusinessLogic();
        BezeroaAdapter model = new BezeroaAdapter(facade.getPertsona(bezeroa.getErabiltzailea()));

        JFrame j = new JFrame();
        JTable table = new JTable(model);
        j.getContentPane().add(new JScrollPane(table));

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.pack();
        j.setVisible(true);
        
        jButtonClose.setBounds(new Rectangle(517, 424, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);
    }
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}