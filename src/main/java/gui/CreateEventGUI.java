package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

public class CreateEventGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEvent = new JLabel();
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JTextField jTextFieldEvent = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton("Submit");
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JPanel panel = new JPanel();
	private final JLabel lblType = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateEventGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JRadioButton rdbtnNewRadioButton = new JRadioButton();
	private final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton();
	private final JRadioButton rdbtnNewRadioButton_2 = new JRadioButton();
	private final JRadioButton rdbtnNewRadioButton_3 = new JRadioButton();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public CreateEventGUI() {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(593, 430));
		this.setTitle("Bets&Ruin - Create Event");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jTextFieldEvent.setFont(new Font("PT Sans", Font.PLAIN, 14));
		jTextFieldEvent.setBounds(new Rectangle(157, 211, 396, 20));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonCreate.setFont(new Font("PT Sans", Font.BOLD, 16));
		jButtonCreate.setText("Create Event"); //$NON-NLS-1$ //$NON-NLS-2$

		jButtonCreate.setBounds(new Rectangle(114, 347, 148, 41));
		jButtonCreate.setForeground(new Color(61, 45, 20));
		jButtonCreate.setBackground(new Color(255, 189, 89));
		jButtonCreate.setOpaque(true);
		jButtonCreate.setBorderPainted(false);
		jButtonCreate.setEnabled(false);
		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setFont(new Font("PT Sans", Font.BOLD, 16));
		jButtonClose.setBounds(new Rectangle(289, 347, 148, 41));
		jButtonClose.setBackground(new Color(61, 45, 20));
		jButtonClose.setForeground(new Color(255, 189, 89));
		jButtonClose.setOpaque(true);
		jButtonClose.setBorderPainted(false);
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldEvent, null);

		BLFacade facade = StartGUI.getBusinessLogic();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(18, 18, 552, 318);

		getContentPane().add(panel);
		panel.setLayout(null);
		jLabelEventDate.setForeground(new Color(61, 45, 20));
		jLabelEventDate.setBounds(22, 6, 226, 25);
		panel.add(jLabelEventDate);
		jLabelEventDate.setFont(new Font("PT Sans", Font.BOLD, 16));
		jLabelListOfEvents.setForeground(new Color(61, 45, 20));
		jLabelListOfEvents.setBounds(269, 8, 277, 20);
		panel.add(jLabelListOfEvents);
		jLabelListOfEvents.setFont(new Font("PT Sans", Font.BOLD, 16));
		jComboBoxEvents.setBackground(Color.WHITE);
		jComboBoxEvents.setBounds(260, 36, 277, 20);
		panel.add(jComboBoxEvents);
		jComboBoxEvents.setFont(new Font("PT Sans", Font.PLAIN, 14));

		jComboBoxEvents.setModel(modelEvents);
		jLabelEvent.setForeground(new Color(61, 45, 20));
		jLabelEvent.setBounds(22, 195, 113, 20);
		panel.add(jLabelEvent);
		jLabelEvent.setFont(new Font("PT Sans", Font.BOLD, 16));

		jLabelEvent.setText("Description:");
		jCalendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		jCalendar.getMonthChooser().getComboBox().setFont(new Font("PT Sans", Font.PLAIN, 14));
		jCalendar.getYearChooser().getSpinner().setFont(new Font("PT Sans", Font.PLAIN, 14));
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);
		jCalendar.setBounds(22, 34, 226, 149);
		panel.add(jCalendar);
		jLabelError.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelError.setFont(new Font("PT Sans", Font.BOLD, 16));
		jLabelError.setBounds(22, 282, 515, 25);
		panel.add(jLabelError);
		jLabelError.setForeground(Color.RED);
		lblType.setForeground(new Color(61, 45, 20));
		lblType.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblType.setBounds(22, 226, 113, 14);
		panel.add(lblType);
		rdbtnNewRadioButton.setForeground(new Color(61, 45, 20));
		rdbtnNewRadioButton.setFont(new Font("PT Sans", Font.BOLD, 16));

		rdbtnNewRadioButton.setText("Futbol");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(139, 226, 143, 23);
		panel.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton_1.setForeground(new Color(61, 45, 20));
		rdbtnNewRadioButton_1.setFont(new Font("PT Sans", Font.BOLD, 15));

		rdbtnNewRadioButton_1.setText("Baloncesto");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(294, 226, 143, 23);
		panel.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_2.setForeground(new Color(61, 45, 20));
		rdbtnNewRadioButton_2.setFont(new Font("PT Sans", Font.BOLD, 16));

		rdbtnNewRadioButton_2.setText("Tenis");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(139, 252, 143, 23);
		panel.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_3.setForeground(new Color(61, 45, 20));
		rdbtnNewRadioButton_3.setFont(new Font("PT Sans", Font.BOLD, 16));

		rdbtnNewRadioButton_3.setText("E-Sport");
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(294, 252, 143, 23);
		panel.add(rdbtnNewRadioButton_3);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: " + calendarAnt.getTime());
					System.out.println("calendarAct: " + calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente,
							// devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar.setCalendar(calendarAct);

						BLFacade facade = StartGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
					}

					paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

					// Date firstDay = UtilDate.trim(new
					// Date(jCalendar.getCalendar().getTime().getTime()));
					Date firstDay = UtilDate.trim(calendarAct.getTime());

					try {
						BLFacade facade = StartGUI.getBusinessLogic();

						Vector<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarAct.getTime()));
						else
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						jComboBoxEvents.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							modelEvents.addElement(ev);
						jComboBoxEvents.repaint();

						jButtonCreate.setEnabled(true);

					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
			}
		});
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, Vector<Date> datesWithEventsCurrentMonth) {
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

		for (Date d : datesWithEventsCurrentMonth) {

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

	private void jButtonCreate_actionPerformed(ActionEvent e) {

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldEvent.getText();

			Date firstDay = UtilDate.trim(calendarAct.getTime());

			BLFacade facade = StartGUI.getBusinessLogic();

			Vector<domain.Event> events = facade.getEvents(firstDay);

			boolean found = false;
			for (Event evt : events) {
				if (evt.getDescription().contentEquals(inputQuery)) {
					found = true;
					jLabelError.setText("Event already exists!");
					jLabelError.setForeground(Color.red);
					break;
				}
			}
			if (!found) {
				if (rdbtnNewRadioButton.isSelected() == true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay, rdbtnNewRadioButton.getText());
				} else if (rdbtnNewRadioButton_1.isSelected() == true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay, rdbtnNewRadioButton_1.getText());
				} else if (rdbtnNewRadioButton_2.isSelected() == true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay, rdbtnNewRadioButton_2.getText());
				} else if (rdbtnNewRadioButton_3.isSelected() == true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay, rdbtnNewRadioButton_3.getText());
				} else {
					jLabelMsg.setText("Choose the event type!");
					jLabelMsg.setForeground(Color.red);
				}
			}

		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
