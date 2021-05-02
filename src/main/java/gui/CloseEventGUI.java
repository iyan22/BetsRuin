package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

//import domain.Event;
import domain.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class CloseEventGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	private JButton seePreds = new JButton("See Predictions"); //$NON-NLS-1$ //$NON-NLS-2$

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton btnCloseEvent = new JButton("Close Event"); //$NON-NLS-1$ //$NON-NLS-2$
	private final JPanel panel = new JPanel();

	public CloseEventGUI() {
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(692, 500));
		this.setTitle("Bets&Ruin - Close event");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jButtonClose.setBounds(new Rectangle(436, 423, 153, 30));
		jButtonClose.setFont(new Font("PT Sans", Font.BOLD, 16));
		jButtonClose.setBackground(new Color(61, 45, 20));
		jButtonClose.setForeground(new Color(255, 189, 89));
		jButtonClose.setOpaque(true);
		jButtonClose.setBorderPainted(false);

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
		this.getContentPane().add(jButtonClose, null);

		BLFacade facade = StartGUI.getBusinessLogic();
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		seePreds = new JButton("Close Question"); 
		seePreds.setBounds(new Rectangle(352, 423, 130, 30));
		seePreds.setBounds(273, 423, 153, 30);
		seePreds.setFont(new Font("PT Sans", Font.BOLD, 16));
		seePreds.setForeground(new Color(61, 45, 20));
		seePreds.setBackground(new Color(255, 189, 89));
		seePreds.setOpaque(true);
		seePreds.setBorderPainted(false);
		getContentPane().add(seePreds);
		seePreds.setEnabled(false);

		btnCloseEvent.setEnabled(false);
		btnCloseEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				facade.closeEvent(ev);
				
				DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
				// jCalendar1.setCalendar(calendarAct);
				Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
				printTableEvent(firstDay, dateformat1);
				
				btnCloseEvent.setEnabled(false);
			}
		});
		btnCloseEvent.setBounds(112, 423, 153, 30);
		btnCloseEvent.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnCloseEvent.setForeground(new Color(61, 45, 20));
		btnCloseEvent.setBackground(new Color(255, 189, 89));
		btnCloseEvent.setOpaque(true);
		btnCloseEvent.setBorderPainted(false);
		getContentPane().add(btnCloseEvent);
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(18, 15, 654, 396);
		
		getContentPane().add(panel);
		panel.setLayout(null);
		jLabelEventDate.setBounds(21, 6, 140, 25);
		panel.add(jLabelEventDate);
		jLabelEventDate.setForeground(new Color(61, 45, 20));
		jLabelEventDate.setFont(new Font("PT Sans", Font.BOLD, 16));
		jLabelEvents.setBounds(275, 10, 259, 16);
		panel.add(jLabelEvents);
		jLabelEvents.setForeground(new Color(61, 45, 20));
		jLabelEvents.setFont(new Font("PT Sans", Font.BOLD, 16));
		
				tableEvents.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						printTableQuestion();
					}
				});
						scrollPaneEvents.setBounds(275, 38, 346, 150);
						panel.add(scrollPaneEvents);
				
						scrollPaneEvents.setViewportView(tableEvents);
						
								tableEvents.setModel(tableModelEvents);
								
										tableQueries.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent e) {
												seePreds.setEnabled(true);
												int i = tableQueries.getSelectedRow();
												Question q = (Question) tableModelQueries.getValueAt(i, 2);
												System.out.println(q.getQuestion());
												seePreds.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														JFrame a = new SetWinnerPredictionGUI(q);
														a.setVisible(true);
														dispose();
													}
												});
											}
										});
												scrollPaneQueries.setBounds(119, 255, 406, 116);
												panel.add(scrollPaneQueries);
										
												scrollPaneQueries.setViewportView(tableQueries);
												
														tableQueries.setModel(tableModelQueries);
														jLabelQueries.setBounds(119, 234, 406, 14);
														panel.add(jLabelQueries);
														jLabelQueries.setForeground(new Color(61, 45, 20));
														jLabelQueries.setFont(new Font("PT Sans", Font.BOLD, 16));
														jCalendar1.getDayChooser().getDayPanel().setBackground(Color.WHITE);
														datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
														CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);
																jCalendar1.setBounds(21, 38, 225, 150);
																panel.add(jCalendar1);
														
																// Code for JCalendar
																this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
																	public void propertyChange(PropertyChangeEvent propertychangeevent) {
														
																		if (propertychangeevent.getPropertyName().equals("locale")) {
																			jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
																		} else if (propertychangeevent.getPropertyName().equals("calendar")) {
																			calendarAnt = (Calendar) propertychangeevent.getOldValue();
																			calendarAct = (Calendar) propertychangeevent.getNewValue();
																			tableModelQueries.setRowCount(0);
																			DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
																			// jCalendar1.setCalendar(calendarAct);
																			Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
														
																			int monthAnt = calendarAnt.get(Calendar.MONTH);
																			int monthAct = calendarAct.get(Calendar.MONTH);
														
																			if (monthAct != monthAnt) {
																				if (monthAct == monthAnt + 2) {
																					// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2
																					// de marzo (se toma como equivalente a 30 de febrero)
																					// Con este código se dejará como 1 de febrero en el JCalendar
																					calendarAct.set(Calendar.MONTH, monthAnt + 1);
																					calendarAct.set(Calendar.DAY_OF_MONTH, 1);
																				}
														
																				jCalendar1.setCalendar(calendarAct);
														
																				BLFacade facade = StartGUI.getBusinessLogic();
														
																				datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
																			}
														
																			CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);
														
																			printTableEvent(firstDay,dateformat1);
														
																		}
																	}
																});
														tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
														tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
								tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
								tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	private void printTableEvent(Date firstDay, DateFormat dateformat1) {
		try {
			tableModelEvents.setDataVector(null, columnNamesEvents);
			tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

			BLFacade facade = StartGUI.getBusinessLogic();

			Vector<domain.Event> events = facade.getEvents(firstDay);

			if (events.isEmpty())
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
						+ dateformat1.format(calendarAct.getTime()));
			else
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
						+ dateformat1.format(calendarAct.getTime()));
			for (domain.Event ev : events) {
				Vector<Object> row = new Vector<Object>();

				System.out.println("Events " + ev);

				row.add(ev.getEventNumber());
				row.add(ev.getDescription());
				row.add(ev); // ev object added in order to obtain it with
								// tableModelEvents.getValueAt(i,2)
				tableModelEvents.addRow(row);
			}
			tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
			tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not
																									// shown
																									// in
																									// JTable
		} catch (Exception e1) {

			jLabelQueries.setText(e1.getMessage());
		}
	}
	
	private void printTableQuestion() {
		int i = tableEvents.getSelectedRow();
		domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
		Vector<Question> queries = ev.getQuestions();

		tableModelQueries.setDataVector(null, columnNamesQueries);
		tableModelQueries.setColumnCount(3);

		if (queries.isEmpty()) {
			btnCloseEvent.setEnabled(true);
			jLabelQueries.setText(
					ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": " + ev.getDescription());
			tableModelQueries.setRowCount(0);
		} else if (ev.areAllQuestionsClosed()) {
			btnCloseEvent.setEnabled(true);
		} else {
			jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " "
					+ ev.getDescription());
			for (Question q : queries) {
				if (q.isOpen()) {
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);
				}

			}
			tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		}
		tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in
																								// JTable
	}
}
