package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Question;
import domain.User;
import domain.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class FindQuestionsGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton seePreds = new JButton("See Predictions"); //$NON-NLS-1$ //$NON-NLS-2$

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));	
	private JButton btnSeguirEquipo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FollowTeam")); //$NON-NLS-1$ //$NON-NLS-2$
	private JLabel jLabelFollow = new JLabel();

	private User u;
	private Event evToFollow;
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
	private final JPanel panelSeleccion = new JPanel();
	private final JLabel jLabelQueryLogo = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	private final JPanel panelInfo = new JPanel();
	private final JLabel lblSaldo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Founds")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JPanel panelNormas = new JPanel();
	private final JLabel lblNormas = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Rules")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lbljuej = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Rules2"));  //$NON-NLS-1$ //$NON-NLS-2$

	public FindQuestionsGUI(User u) {
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		this.u=u;
		try {
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(732, 720));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jButtonClose.setFont(new Font("PT Sans", Font.BOLD, 16));
		jButtonClose.setForeground(new Color(255, 189, 89));
		jButtonClose.setBackground(new Color(61, 45, 20));
		jButtonClose.setFont(new Font("PT Sans", Font.BOLD, 22));
		jButtonClose.setOpaque(true);
		jButtonClose.setBorderPainted(false);
		jButtonClose.setBounds(new Rectangle(458, 635, 159, 43));

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

		seePreds = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SeePrediction"));
		seePreds.setEnabled(false);
		seePreds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		seePreds.setFont(new Font("PT Sans", Font.BOLD, 16));
		seePreds.setBounds(new Rectangle(352, 423, 130, 30));
		seePreds.setBounds(99, 637, 159, 43);
		seePreds.setBackground(new Color(255, 189, 89));
		seePreds.setForeground(new Color(61, 45, 20));
		seePreds.setOpaque(true);
		seePreds.setBorderPainted(false);
		getContentPane().add(seePreds);
		panelSeleccion.setBounds(31, 190, 666, 434);

		getContentPane().add(panelSeleccion);

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seePreds.setEnabled(true);

				int i = tableQueries.getSelectedRow();
				Question q = (Question) tableModelQueries.getValueAt(i, 2);
				System.out.println(q.getQuestion());
				seePreds.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFrame a = new SeePredictionsGUI(q, u);
						a.setVisible(true);
					}
				});
			}
		});
		panelSeleccion.setLayout(null);
		scrollPaneQueries.setBounds(182, 242, 447, 142);
		panelSeleccion.add(scrollPaneQueries);


		scrollPaneQueries.setViewportView(tableQueries);

		tableQueries.setModel(tableModelQueries);
		jLabelQueries.setForeground(new Color(61, 45, 20));
		jLabelQueries.setBounds(182, 218, 409, 22);
		panelSeleccion.add(jLabelQueries);
		jLabelQueries.setFont(new Font("PT Sans", Font.BOLD, 16));
		jLabelQueryLogo.setIcon(new ImageIcon(FindQuestionsGUI.class.getResource("/img/QuestionLogoL.png")));
		jLabelQueryLogo.setFont(new Font("PT Sans", Font.BOLD, 16));
		jLabelQueryLogo.setBounds(21, 223, 151, 161);

		panelSeleccion.add(jLabelQueryLogo);
		jLabelEvents.setBounds(261, 28, 343, 16);
		panelSeleccion.add(jLabelEvents);
		jLabelEvents.setForeground(new Color(61, 45, 20));
		jLabelEvents.setFont(new Font("PT Sans", Font.BOLD, 16));
		

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				Event ev = (Event) tableModelEvents.getValueAt(i,2); // obtain ev object
				
				evToFollow = ev;
				if(ev!=null) btnSeguirEquipo.setEnabled(true);
				
				Vector<Question> queries = ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty()) {
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
					tableModelQueries.setRowCount(0);
				}
				else {
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());
					for (Question q:queries){
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
		scrollPaneEvents.setBounds(258, 51, 371, 145);
		panelSeleccion.add(scrollPaneEvents);

		scrollPaneEvents.setViewportView(tableEvents);

		tableEvents.setModel(tableModelEvents);
		jCalendar1.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		jCalendar1.setBounds(21, 51, 215, 145);
		panelSeleccion.add(jCalendar1);
		jLabelEventDate.setBounds(21, 24, 225, 25);
		panelSeleccion.add(jLabelEventDate);
		jLabelEventDate.setForeground(new Color(61, 45, 20));
		jLabelEventDate.setFont(new Font("PT Sans", Font.BOLD, 16));

		jLabelFollow.setText(ResourceBundle.getBundle("Etiquetas").getString("FollowText")); 
		jLabelFollow.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelFollow.setForeground(new Color(61, 45, 20));
		jLabelFollow.setFont(new Font("Dialog", Font.BOLD, 16));
		jLabelFollow.setBounds(21, 396, 608, 27);
		panelSeleccion.add(jLabelFollow);

		JLabel lblLogo = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblLogo.setIcon(new ImageIcon(FindQuestionsGUI.class.getResource("/img/LogoBetsRuinL.png")));
		lblLogo.setBounds(31, 22, 174, 156);
		getContentPane().add(lblLogo);

		JPanel panelIdiomas = new JPanel();
		panelIdiomas.setLayout(null);
		panelIdiomas.setBounds(397, 22, 300, 79);
		getContentPane().add(panelIdiomas);

		JLabel lblEnglish = new JLabel("Do you understand us?");
		lblEnglish.setForeground(new Color(61, 45, 20));
		lblEnglish.setFont(new Font("PT Sans", Font.BOLD, 14));
		lblEnglish.setBounds(43, 54, 142, 19);
		panelIdiomas.add(lblEnglish);

		JLabel lblEuskara = new JLabel("Ulertzen?");
		lblEuskara.setForeground(new Color(61, 45, 20));
		lblEuskara.setFont(new Font("PT Sans", Font.BOLD, 14));
		lblEuskara.setBounds(89, 31, 57, 19);
		panelIdiomas.add(lblEuskara);

		JLabel lblEspanol = new JLabel("¿Nos entiendes?");
		lblEspanol.setForeground(new Color(61, 45, 20));
		lblEspanol.setFont(new Font("PT Sans", Font.BOLD, 14));
		lblEspanol.setBounds(68, 7, 99, 19);
		panelIdiomas.add(lblEspanol);

		JButton btnEspanol = new JButton();
		btnEspanol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				redibujar();
			}
		});
		btnEspanol.setIcon(new ImageIcon(FindQuestionsGUI.class.getResource("/img/Spanish.png")));
		btnEspanol.setBorderPainted(false);
		btnEspanol.setBounds(231, 0, 40, 28);
		panelIdiomas.add(btnEspanol);
		
		JButton btnEuskara = new JButton();
		btnEuskara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: "+Locale.getDefault());
				redibujar();	
			}
		});
		btnEuskara.setIcon(new ImageIcon(FindQuestionsGUI.class.getResource("/img/Basque.png")));
		btnEuskara.setBorderPainted(false);
		btnEuskara.setBounds(231, 26, 40, 28);
		panelIdiomas.add(btnEuskara);
		
		JButton btnEnglish = new JButton();
		btnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				redibujar();	
			}
		});
		btnEnglish.setIcon(new ImageIcon(FindQuestionsGUI.class.getResource("/img/English.png")));
		btnEnglish.setBorderPainted(false);
		btnEnglish.setBounds(231, 49, 40, 28);
		panelIdiomas.add(btnEnglish);
		panelInfo.setBounds(397, 113, 300, 65);

		getContentPane().add(panelInfo);
		panelInfo.setLayout(null);
		lblUser.setBounds(17, 9, 67, 19);
		lblUser.setForeground(new Color(61, 45, 20));
		lblUser.setFont(new Font("PT Sans", Font.BOLD, 14));

		panelInfo.add(lblUser);
		lblSaldo.setBounds(17, 40, 67, 19);
		lblSaldo.setForeground(new Color(61, 45, 20));
		lblSaldo.setFont(new Font("PT Sans", Font.BOLD, 14));

		panelInfo.add(lblSaldo);

		JLabel lblUserVar = new JLabel(u.getUsername());
		lblUserVar.setForeground(new Color(61, 45, 20));
		lblUserVar.setFont(new Font("PT Sans", Font.BOLD, 14));
		lblUserVar.setBounds(95, 9, 188, 19);
		panelInfo.add(lblUserVar);

		JLabel lblSaldoVar = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.lblSaldoVar.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblSaldoVar.setForeground(new Color(61, 45, 20));
		lblSaldoVar.setFont(new Font("PT Sans", Font.BOLD, 14));
		lblSaldoVar.setBounds(96, 40, 187, 19);
		panelInfo.add(lblSaldoVar);
		panelNormas.setLayout(null);
		panelNormas.setBounds(200, 22, 189, 156);

		getContentPane().add(panelNormas);
		lblNormas.setForeground(new Color(61, 45, 20));
		lblNormas.setFont(new Font("PT Sans", Font.BOLD, 18));
		lblNormas.setBounds(16, 20, 131, 19);

		panelNormas.add(lblNormas);
		lbljuej.setForeground(new Color(61, 45, 20));
		lbljuej.setFont(new Font("PT Sans", Font.BOLD, 14));
		lbljuej.setBounds(16, 40, 163, 99);

		panelNormas.add(lbljuej);

		btnSeguirEquipo.setOpaque(true);
		btnSeguirEquipo.setEnabled(false);
		btnSeguirEquipo.setForeground(new Color(61, 45, 20));
		btnSeguirEquipo.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnSeguirEquipo.setBounds(new Rectangle(352, 423, 130, 30));
		btnSeguirEquipo.setBorderPainted(false);
		btnSeguirEquipo.setBackground(new Color(255, 189, 89));
		btnSeguirEquipo.setBounds(278, 637, 159, 43);
		btnSeguirEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				JFrame followTeam = new FollowGUI(u, evToFollow);
				followTeam.setVisible(true);
			}
		});
		getContentPane().add(btnSeguirEquipo);

		// Code for JCalendar
		jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar")) {
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
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						jCalendar1.setCalendar(calendarAct);
						BLFacade facade = StartGUI.getBusinessLogic();
						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
					}
					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects
						BLFacade facade = StartGUI.getBusinessLogic();
						Vector<Event> events = facade.getEvents(firstDay);
						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (Event ev:events){
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
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);


	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
		JFrame main = new MainUserGUI(u);
		main.setVisible(true);
	}
	
	private void redibujar() {
		jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
		jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
		jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		ResourceBundle.getBundle("Etiquetas").getString("EventN"); 
		ResourceBundle.getBundle("Etiquetas").getString("Event"); 
		ResourceBundle.getBundle("Etiquetas").getString("QueryN"); 
		ResourceBundle.getBundle("Etiquetas").getString("Query");	
		lblSaldo.setText(ResourceBundle.getBundle("Etiquetas").getString("Founds")); //$NON-NLS-1$ //$NON-NLS-2$
		lblUser.setText(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblNormas.setText(ResourceBundle.getBundle("Etiquetas").getString("Rules")); //$NON-NLS-1$ //$NON-NLS-2$
		lbljuej.setText(ResourceBundle.getBundle("Etiquetas").getString("Rules2"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		seePreds.setText(ResourceBundle.getBundle("Etiquetas").getString("SeePrediction"));
		btnSeguirEquipo.setText(ResourceBundle.getBundle("Etiquetas").getString("FollowTeam"));
		jLabelFollow.setText(ResourceBundle.getBundle("Etiquetas").getString("FollowText"));
	}
}
