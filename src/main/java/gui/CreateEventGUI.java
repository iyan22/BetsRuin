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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

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
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton = new JRadioButton();
	private JRadioButton rdbtnNewRadioButton_1 = new JRadioButton();
	private JRadioButton rdbtnNewRadioButton_2 = new JRadioButton();
	private JRadioButton rdbtnNewRadioButton_3 = new JRadioButton();
	

	/**
	 * Create the frame.
	 */
	public CreateEventGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 422));
		this.setTitle("Create Event");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));
		jLabelEvent.setBounds(new Rectangle(25, 211, 75, 20));
		jTextFieldEvent.setBounds(new Rectangle(100, 211, 429, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonCreate.setText("Create Event"); //$NON-NLS-1$ //$NON-NLS-2$

		jButtonCreate.setBounds(new Rectangle(135, 342, 130, 30));
		jButtonCreate.setEnabled(false);

		jLabelEvent.setText("Description:");
		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		
		jButtonClose.setBounds(new Rectangle(275, 342, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(175, 240, 305, 20));
		jLabelError.setForeground(Color.red);
		
		rdbtnNewRadioButton.setText("Futbol");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(109, 247, 109, 23);
		
		rdbtnNewRadioButton_1.setText("Baloncesto"); 
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(109, 287, 109, 23);
		
		rdbtnNewRadioButton_2.setText("Tenis"); 
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(275, 247, 109, 23);
		
		rdbtnNewRadioButton_3.setText("E-Sport"); 
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(275, 287, 109, 23);
		
		

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldEvent, null);
		this.getContentPane().add(jLabelEvent, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);
		
		getContentPane().add(rdbtnNewRadioButton);
		getContentPane().add(rdbtnNewRadioButton_1);
		getContentPane().add(rdbtnNewRadioButton_2);
		getContentPane().add(rdbtnNewRadioButton_3);
		
		
		BLFacade facade = StartGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
		
		

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		
		
		
		
		JLabel jLabelType = new JLabel("Type"); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelType.setBounds(25, 251, 46, 14);
		getContentPane().add(jLabelType);

		
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
					System.out.println("calendarAnt: "+calendarAnt.getTime());
					System.out.println("calendarAct: "+calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);
						
						BLFacade facade = StartGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
					}



					paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);

					//	Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
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
	
	public static void paintDaysWithEvents(JCalendar jCalendar,Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day is changed.

		
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:datesWithEventsCurrentMonth){

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
			
			boolean found=false;
			for(Event evt: events) {
				if(evt.getDescription().contentEquals(inputQuery)) {
					found=true;
					jLabelError.setText("Event already exists!");
					jLabelError.setForeground(Color.red);
					break;
				}
			}
			if(!found) {
				if(rdbtnNewRadioButton.isSelected()==true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay,rdbtnNewRadioButton.getText());
				}else if(rdbtnNewRadioButton_1.isSelected()==true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay,rdbtnNewRadioButton.getText());
				}else if(rdbtnNewRadioButton_2.isSelected()==true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay,rdbtnNewRadioButton.getText());
				}else if(rdbtnNewRadioButton_3.isSelected()==true) {
					jLabelMsg.setText("Event created successfully!");
					jLabelMsg.setForeground(Color.green);
					facade.createEvent(inputQuery, firstDay,rdbtnNewRadioButton.getText());
				}else {
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

