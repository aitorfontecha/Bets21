package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.ExtendedIterator;
import businessLogic.ExtendedIteratorImplementation;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class PronostikoaIpiniGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<Question> jComboBoxQuestions = new JComboBox<Question>();
	DefaultComboBoxModel<Question> modelQuestions = new DefaultComboBoxModel<Question>();

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JLabel jLabelError = new JLabel();

	private List<Date> datesWithEventsCurrentMonth = new Vector<Date>();


	private final JButton buttonGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PronostikoaIpiniGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textFieldPronostikoa;
	private JTextField textFieldKuota;

	public PronostikoaIpiniGUI() {

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

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

		BLFacade bl = MainGUI.getBusinessLogic();
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(295, 93, 78, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(517, 424, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
				//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: " + calendarAnt.getTime());
					System.out.println("calendarAct: " + calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2
							// de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
					}

					paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

					// Date firstDay = UtilDate.trim(new
					// Date(jCalendar.getCalendar().getTime().getTime()));
					Date firstDay = UtilDate.trim(calendarAct.getTime());

					try {
						BLFacade facade = MainGUI.getBusinessLogic();

						ExtendedIterator<Event> events = facade.getEvents(firstDay);
						events.goFirst();

						if (!events.hasNext())
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarAct.getTime()));
						else
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						jComboBoxEvents.removeAllItems();
						System.out.println("Events " + events);

						while (events.hasNext()) {
							modelEvents.addElement(events.next());
						}
						jComboBoxEvents.repaint();

						if (events.hasNext())
							buttonGehitu.setEnabled(false);
						else
							buttonGehitu.setEnabled(true);

					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
			}
		});

		this.getContentPane().add(jCalendar1, null);
		//Gertaeraren galderak lortu
		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(285, 47, 250, 20));
		getContentPane().add(jComboBoxEvents);
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jComboBoxQuestions.removeAllItems();
				int eNum = jComboBoxEvents.getSelectedIndex();
				Event e = jComboBoxEvents.getItemAt(eNum);
				if (e!=null) {
					List<Question> galderak= e.getQuestions();
					if( galderak!=null) {
						for (domain.Question q : galderak)
							if (q.getQuestion()!=null) {
								modelQuestions.addElement(q);
							}
						jComboBoxEvents.repaint();
					}
				}



			}
		});

		jComboBoxQuestions.setModel(modelQuestions);
		jComboBoxQuestions.setBounds(new Rectangle(285, 119, 250, 20));
		getContentPane().add(jComboBoxQuestions);
		buttonGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textFieldKuota.getText().isEmpty()) {
					double kuota = Double.parseDouble(textFieldKuota.getText());
					Pronostikoa pronostiko = new Pronostikoa (textFieldPronostikoa.getText(), kuota);
					int indizea= jComboBoxQuestions.getSelectedIndex();
					Question galdera= jComboBoxQuestions.getItemAt(indizea);
					if(galdera!=null) {
						Pronostikoa pr= bl.pronostikoaIpini(galdera, pronostiko);
						if (pr!=null) {
							labelGordeta.setVisible(true);
						}
						else {
							labelErrorea.setVisible(true);
						}
					}
				}else {
					labelErrorea.setVisible(true);
				}
			}

		});


		buttonGehitu.setBounds(274, 325, 130, 51);

		getContentPane().add(buttonGehitu);


		textFieldPronostikoa = new JTextField();
		//textFieldPronostikoa.setText(ResourceBundle.getBundle("Etiquetas").getString("PronostikoaIpiniGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldPronostikoa.setBounds(215, 246, 296, 26);
		getContentPane().add(textFieldPronostikoa);
		textFieldPronostikoa.setColumns(10);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PronostikoaIpiniGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(46, 251, 134, 16);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PronostikoaIpiniGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setBounds(62, 294, 61, 16);
		getContentPane().add(lblNewLabel_1);

		textFieldKuota = new JTextField();
		//textFieldKuota.setText(ResourceBundle.getBundle("Etiquetas").getString("PronostikoaIpiniGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldKuota.setBounds(215, 287, 296, 26);
		getContentPane().add(textFieldKuota);
		textFieldKuota.setColumns(10);


	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, List<Date> datesWithEventsCurrentMonth2) {
		// For each day with events in current month, the background color for that day
		// is changed.

		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d : datesWithEventsCurrentMonth2) {

			calendar.setTime(d);
			System.out.println(d);

			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
			//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

	}
}
