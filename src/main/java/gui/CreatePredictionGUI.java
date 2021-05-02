package gui;

import java.awt.Dimension;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Question;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;

public class CreatePredictionGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jAddPred = new JButton("Add Prediction");

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;


	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")
			
	};
	private final JPanel panel = new JPanel();

	public CreatePredictionGUI()
	{
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(684, 500));
		this.setTitle("Bets&Ruin - Create Prediction");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jButtonClose.setFont(new Font("PT Sans", Font.BOLD, 16));
		jButtonClose.setBounds(new Rectangle(351, 415, 161, 38));
		jButtonClose.setBackground(new Color(61, 45, 20));
		jButtonClose.setForeground(new Color(255, 189, 89));
		jButtonClose.setOpaque(true);
		jButtonClose.setBorderPainted(false);

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);

		BLFacade facade = StartGUI.getBusinessLogic();
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		jAddPred = new JButton("Add Prediction");
		jAddPred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		jAddPred.setFont(new Font("PT Sans", Font.BOLD, 16));
		jAddPred.setForeground(new Color(61, 45, 20));
		jAddPred.setBackground(new Color(255, 189, 89));
		jAddPred.setOpaque(true);
		jAddPred.setBorderPainted(false);
		jAddPred.setBounds(new Rectangle(352, 423, 130, 30));
		jAddPred.setBounds(155, 415, 163, 38);
		getContentPane().add(jAddPred);
		jAddPred.setEnabled(false);
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(24, 15, 634, 377);
		
		getContentPane().add(panel);
		panel.setLayout(null);
		jLabelEventDate.setBounds(19, 10, 225, 25);
		panel.add(jLabelEventDate);
		jLabelEventDate.setForeground(new Color(61, 45, 20));
		jLabelEventDate.setFont(new Font("PT Sans", Font.BOLD, 16));
		jLabelEvents.setBounds(271, 14, 259, 16);
		panel.add(jLabelEvents);
		jLabelEvents.setForeground(new Color(61, 45, 20));
		jLabelEvents.setFont(new Font("PT Sans", Font.BOLD, 16));
				tableEvents.setFont(new Font("PT Sans", Font.PLAIN, 14));
		
				tableEvents.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=tableEvents.getSelectedRow();
						domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
						Vector<Question> queries=ev.getQuestions();
		
						tableModelQueries.setDataVector(null, columnNamesQueries);
						tableModelQueries.setColumnCount(3);
		
						if (queries.isEmpty())
						{
							jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
							tableModelQueries.setRowCount(0);
						}
		
						else 
						{
							jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());
							for (domain.Question q:queries){
								Vector<Object> row = new Vector<Object>();
		
								row.add(q.getQuestionNumber());
								row.add(q.getQuestion());
								row.add(q);
								tableModelQueries.addRow(row);
								
							}
							tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
							tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
							 
						}
						tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in JTable
						
					}
				});
						scrollPaneEvents.setBounds(271, 38, 346, 150);
						panel.add(scrollPaneEvents);
				
						scrollPaneEvents.setViewportView(tableEvents);
						
								tableEvents.setModel(tableModelEvents);
								jLabelQueries.setBounds(113, 217, 406, 14);
								panel.add(jLabelQueries);
								jLabelQueries.setForeground(new Color(61, 45, 20));
								jLabelQueries.setFont(new Font("PT Sans", Font.BOLD, 16));
										tableQueries.setFont(new Font("PT Sans", Font.PLAIN, 14));
								
										tableQueries.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent e) {
												jAddPred.setEnabled(true);
												int i = tableQueries.getSelectedRow();
												Question q = (Question)tableModelQueries.getValueAt(i, 2);
												System.out.println(q.getQuestion());
												jAddPred.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														JFrame a = new AddPredictionGUI(q);
														a.setVisible(true);
													}
												});
											}
										});
												scrollPaneQueries.setBounds(113, 243, 406, 116);
												panel.add(scrollPaneQueries);
										
										
												scrollPaneQueries.setViewportView(tableQueries);
												
														tableQueries.setModel(tableModelQueries);
														jCalendar1.getDayChooser().getDayPanel().setBackground(Color.WHITE);
														datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
														CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
																jCalendar1.setBounds(16, 38, 225, 150);
																panel.add(jCalendar1);
														
																// Code for JCalendar
																this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
																{
																	public void propertyChange(PropertyChangeEvent propertychangeevent)
																	{
														
																		if (propertychangeevent.getPropertyName().equals("locale"))
																		{
																			jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
																		}
																		else if (propertychangeevent.getPropertyName().equals("calendar"))
																		{
																			calendarAnt = (Calendar) propertychangeevent.getOldValue();
																			calendarAct = (Calendar) propertychangeevent.getNewValue();
																			tableModelQueries.setRowCount(0);
																			DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
																			//					jCalendar1.setCalendar(calendarAct);
																			Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
														
														
														
																			int monthAnt = calendarAnt.get(Calendar.MONTH);
																			int monthAct = calendarAct.get(Calendar.MONTH);
														
																			if (monthAct!=monthAnt) {
																				if (monthAct==monthAnt+2) {
																					// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
																					// Con este código se dejará como 1 de febrero en el JCalendar
																					calendarAct.set(Calendar.MONTH, monthAnt+1);
																					calendarAct.set(Calendar.DAY_OF_MONTH, 1);
																				}						
														
																				jCalendar1.setCalendar(calendarAct);
														
																				BLFacade facade = StartGUI.getBusinessLogic();
														
																				datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
																			}
														
														
														
																			CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
														
														
														
																			try {
																				tableModelEvents.setDataVector(null, columnNamesEvents);
																				tableModelEvents.setColumnCount(3); // another column added to allocate ev objects
																				
														
																				BLFacade facade=StartGUI.getBusinessLogic();
														
																				Vector<domain.Event> events=facade.getEvents(firstDay);
														
																				if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
																				else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
																				for (domain.Event ev:events){
																					Vector<Object> row = new Vector<Object>();
														
																					System.out.println("Events "+ev);
														
																					row.add(ev.getEventNumber());
																					row.add(ev.getDescription());
																					row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
																					tableModelEvents.addRow(row);		
																				}
																				tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
																				tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
																				tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
																			} catch (Exception e1) {
														
																				jLabelQueries.setText(e1.getMessage());
																			}
														
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
}
