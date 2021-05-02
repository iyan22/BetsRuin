package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Event;
import domain.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MainUserGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;


	protected JLabel jLabelLogo;
	private JButton btnReturn;
	private User u;
	private Event event;
	private JButton UserZone;
	private JButton btnBaloncesto;
	private JButton btnTenis;
	private JLabel jLabelWelcome;
	private JLabel lblUsername;
	private JPanel panelCategorias;
	private JButton btnFutbol;
	private JLabel lblCategorias;
	private JPanel separador1;
	private JPanel separador2;
	private JPanel separador3;
	private JPanel panelNotificaciones;
	private JLabel lblEventosInteres;
	private JButton btnEspanol;
	private JLabel lblEspanol;
	private JLabel lblEuskara;
	private JLabel lblEnglish;
	private JPanel panelIdiomas;
	private JButton btnEuskara;
	private JButton btnEnglish;
	
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private DefaultTableModel tableModelEvents;
	private JTable tableEvents = new JTable();
	private String[] columnNamesEvents = new String[] {
			"#", 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), "Date"

	};
	private JButton btnApostar;

	/**
	 * This is the default constructor
	 */
	public MainUserGUI(User u) {
		super();
		setResizable(false);
		this.u=u;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(0);
			}
		});

		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(1027, 809);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.WHITE);
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getUserZone());
			jContentPane.add(getBoton3());
			jContentPane.add(getJLabelWelcome());
			jContentPane.add(getLblUsername());
			jContentPane.add(getBtnReturn());
			jContentPane.add(getPanelCategorias());
			jContentPane.add(getSeparador1());
			jContentPane.add(getSeparador2());
			jContentPane.add(getSeparador3());
			jContentPane.add(getPanelNotificaciones());
			jContentPane.add(getPanelIdiomas());
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setFont(new Font("PT Sans", Font.BOLD, 22));
			jButtonQueryQueries.setBackground(new Color(61, 45, 20));
			jButtonQueryQueries.setForeground(new Color(255, 189, 89));
			jButtonQueryQueries.setOpaque(true);
			jButtonQueryQueries.setBorderPainted(false);
			jButtonQueryQueries.setBounds(26, 211, 647, 49);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("KnowEvents"));
			jButtonQueryQueries.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindQuestionsGUI(u);
					a.setVisible(true);
					dispose(e);
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	public void dispose(ActionEvent e) {
		this.dispose();
	}


	private JLabel getLblNewLabel() {
		if (jLabelLogo == null) {
			jLabelLogo = new JLabel();
			jLabelLogo.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/LogoBetsRuinL.png")));
			jLabelLogo.setBounds(43, 26, 172, 161);
			jLabelLogo.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelLogo.setForeground(Color.BLACK);
			jLabelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelLogo;
	}
	
	private JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnReturn.setBackground(new Color(227, 227, 227));
			btnReturn.setOpaque(true);
			btnReturn.setBorderPainted(false);
			btnReturn.setFont(new Font("PT Sans", Font.BOLD, 14));
			btnReturn.setBounds(26, 740, 973, 29);
			btnReturn.setText(ResourceBundle.getBundle("Etiquetas").getString("Logout")); //$NON-NLS-1$ //$NON-NLS-2$
			btnReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close(e);
				}
			});
		}
		return btnReturn;
	}
	public void close(ActionEvent e) {
		JFrame a = new StartGUI();
		a.setVisible(true);
		this.dispose();
	}
	private JButton getUserZone() {
		if (UserZone == null) {
			UserZone = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			UserZone.setForeground(new Color(255, 189, 89));
			UserZone.setBackground(new Color(61, 45, 20));
			UserZone.setFont(new Font("Lucida Grande", Font.BOLD, 22));
			UserZone.setBorderPainted(false);
			UserZone.setOpaque(true);
			UserZone.setText(ResourceBundle.getBundle("Etiquetas").getString("UserZone")); //$NON-NLS-1$ //$NON-NLS-2$
			UserZone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					UserZoneGUI usZ= new UserZoneGUI(u);
					usZ.setVisible(true);
					
				}
			});
			UserZone.setBounds(233, 115, 440, 62);
		}
		return UserZone;
	}
	private JButton getBtnBaloncesto() {
		if (btnBaloncesto == null) {
			btnBaloncesto = new JButton();
			btnBaloncesto.setBounds(327, 60, 300, 173);
			btnBaloncesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindEventTypeGUI(u,"Baloncesto");
					a.setVisible(true);
					dispose(e);
				}
			});
			btnBaloncesto.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/BaloncestoES.png")));
			btnBaloncesto.setBorderPainted(false);
		}
		return btnBaloncesto;
	}
	private JButton getBtnTenis() {
		if (btnTenis == null) {
			btnTenis = new JButton();
			btnTenis.setBounds(16, 245, 300, 173);
			btnTenis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindEventTypeGUI(u,"Tenis");
					a.setVisible(true);
					dispose(e);
				}
			});
			btnTenis.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/TenisES.png")));
			btnTenis.setBorderPainted(false);
		}
		return btnTenis;
	}
	private JLabel getJLabelWelcome() {
		if (jLabelWelcome == null) {
			jLabelWelcome = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Wellcome")); //$NON-NLS-1$ //$NON-NLS-2$
			jLabelWelcome.setForeground(new Color(61, 45, 20));
			jLabelWelcome.setFont(new Font("PT Sans", Font.BOLD, 24));
			jLabelWelcome.setBounds(248, 53, 141, 32);
		}
		return jLabelWelcome;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel(u.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			lblUsername.setForeground(new Color(255, 189, 89));
			lblUsername.setVerticalAlignment(SwingConstants.BOTTOM);
			lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsername.setFont(new Font("PT Sans", Font.BOLD, 32));
			lblUsername.setBounds(371, 26, 300, 62);
		}
		return lblUsername;
	}
	private JPanel getPanelCategorias() {
		if (panelCategorias == null) {
			panelCategorias = new JPanel();
			panelCategorias.setBackground(new Color(227, 227, 227));
			panelCategorias.setBounds(26, 296, 647, 432);
			panelCategorias.setLayout(null);
			panelCategorias.add(getLblCategorias());
			
			JButton btnEsports = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnEsports.setBounds(328, 245, 300, 173);
			panelCategorias.add(btnEsports);
			btnEsports.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindEventTypeGUI(u,"E-Sport");
					a.setVisible(true);
					dispose(e);
				}
			});
			btnEsports.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/EsportsES.png")));
			btnEsports.setBorderPainted(false);
			panelCategorias.add(getBtnTenis());
			panelCategorias.add(getBtnBaloncesto());
			panelCategorias.add(getBtnFutbol());
		}
		return panelCategorias;
	}
	private JButton getBtnFutbol() {
		if (btnFutbol == null) {
			btnFutbol = new JButton();
			btnFutbol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindEventTypeGUI(u,"Futbol");
					a.setVisible(true);
					dispose(e);
				}
			});
			btnFutbol.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/FutbolES.png")));
			btnFutbol.setBorderPainted(false);
			btnFutbol.setBounds(16, 60, 300, 173);
		}
		return btnFutbol;
	}
	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Enjoy")); //$NON-NLS-1$ //$NON-NLS-2$
			lblCategorias.setForeground(new Color(61, 45, 20));
			lblCategorias.setBounds(16, 16, 611, 32);
			lblCategorias.setFont(new Font("PT Sans", Font.BOLD, 24));
		}
		return lblCategorias;
	}
	private JPanel getSeparador1() {
		if (separador1 == null) {
			separador1 = new JPanel();
			separador1.setBackground(new Color(255, 189, 89));
			separador1.setBounds(26, 189, 647, 10);
		}
		return separador1;
	}
	private JPanel getSeparador2() {
		if (separador2 == null) {
			separador2 = new JPanel();
			separador2.setBackground(new Color(255, 189, 89));
			separador2.setBounds(233, 93, 440, 10);
		}
		return separador2;
	}
	private JPanel getSeparador3() {
		if (separador3 == null) {
			separador3 = new JPanel();
			separador3.setBackground(new Color(255, 189, 89));
			separador3.setBounds(26, 272, 647, 10);
		}
		return separador3;
	}
	private JPanel getPanelNotificaciones() {
		if (panelNotificaciones == null) {
			panelNotificaciones = new JPanel();
			panelNotificaciones.setLayout(null);
			panelNotificaciones.setBackground(new Color(227, 227, 227));
			panelNotificaciones.setBounds(699, 115, 300, 613);
			panelNotificaciones.add(getLblEventosInteres());
			
			scrollPaneEvents.setBounds(10, 62, 280, 496);
			fillEvents(scrollPaneEvents);
			panelNotificaciones.add(scrollPaneEvents);
			panelNotificaciones.add(getBtnApostar());
		}
		return panelNotificaciones;
	}
	
	private void fillEvents(JScrollPane scrollPane) {
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
		tableEvents.setFont(new Font("PT Sans", Font.PLAIN, 16));
		scrollPane.setViewportView(tableEvents);
		tableEvents.setModel(tableModelEvents);
		try {
			tableModelEvents.setDataVector(null, columnNamesEvents);
			tableModelEvents.setColumnCount(4); // another column added to allocate ev objects
			BLFacade facade = StartGUI.getBusinessLogic();
			Vector<Event> events = facade.activeFollowedEvents(u);
			for (Event ev:events){
				Vector<Object> row = new Vector<Object>();
				System.out.println("Events "+ev);
				row.add(ev.getEventNumber());
				row.add(ev.getDescription());
				
				Date date = ev.getEventDate();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String formatted = format.format(date);
				
				row.add(formatted);
				row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
				tableModelEvents.addRow(row);		
			}
			tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableEvents.getColumnModel().getColumn(1).setPreferredWidth(200);
			tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(3)); // not shown in JTable
		} catch (Exception e1) {
			
		}
	}
	private JLabel getLblEventosInteres() {
		if (lblEventosInteres == null) {
			lblEventosInteres = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Interesting")); //$NON-NLS-1$ //$NON-NLS-2$
			lblEventosInteres.setForeground(new Color(61, 45, 20));
			lblEventosInteres.setFont(new Font("PT Sans", Font.BOLD, 24));
			lblEventosInteres.setBounds(17, 243, 277, 44);
		}
		return lblEventosInteres;
	}
	private JLabel getLblEspanol() {
		if (lblEspanol == null) {
			lblEspanol = new JLabel("¿Nos entiendes?"); //$NON-NLS-1$ //$NON-NLS-2$
			lblEspanol.setBounds(68, 7, 99, 19);
			lblEspanol.setForeground(new Color(61, 45, 20));
			lblEspanol.setFont(new Font("PT Sans", Font.BOLD, 14));
		}
		return lblEspanol;
	}
	private JLabel getLblEuskara() {
		if (lblEuskara == null) {
			lblEuskara = new JLabel("Ulertzen?"); //$NON-NLS-1$ //$NON-NLS-2$
			lblEuskara.setBounds(89, 31, 57, 19);
			lblEuskara.setForeground(new Color(61, 45, 20));
			lblEuskara.setFont(new Font("PT Sans", Font.BOLD, 14));
		}
		return lblEuskara;
	}
	private JLabel getLblEnglish() {
		if (lblEnglish == null) {
			lblEnglish = new JLabel("Do you understand us?"); //$NON-NLS-1$ //$NON-NLS-2$
			lblEnglish.setBounds(43, 54, 142, 19);
			lblEnglish.setForeground(new Color(61, 45, 20));
			lblEnglish.setFont(new Font("PT Sans", Font.BOLD, 14));
		}
		return lblEnglish;
	}
	private JPanel getPanelIdiomas() {
		if (panelIdiomas == null) {
			panelIdiomas = new JPanel();
			panelIdiomas.setBounds(699, 24, 300, 79);
			panelIdiomas.setLayout(null);
			panelIdiomas.add(getLblEnglish());
			panelIdiomas.add(getLblEuskara());
			panelIdiomas.add(getLblEspanol());
			panelIdiomas.add(getBtnEspanol());
			panelIdiomas.add(getBtnEnglish());
			panelIdiomas.add(getBtnEuskara());
		}
		return panelIdiomas;
	}
	private JButton getBtnEspanol() {
		if (btnEspanol == null) {
			btnEspanol = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnEspanol.setBounds(231, 0, 40, 28);
			btnEspanol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			btnEspanol.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/Spanish.png")));
			btnEspanol.setBorderPainted(false);
		}
		return btnEspanol;
	}
	private JButton getBtnEuskara() {
		if (btnEuskara == null) {
			btnEuskara = new JButton();
			btnEuskara.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/Basque.png")));
			btnEuskara.setBounds(231, 22, 40, 28);
			btnEuskara.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			btnEuskara.setSelectedIcon(new ImageIcon("/Users/iyanalvarez/eclipse-workspace/Bets/src/main/resources/img/Basque.png"));
			btnEuskara.setBorderPainted(false);
		}
		return btnEuskara;
	}
	private JButton getBtnEnglish() {
		if (btnEnglish == null) {
			btnEnglish = new JButton();
			btnEnglish.setIcon(new ImageIcon(MainUserGUI.class.getResource("/img/English.png")));
			btnEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			btnEnglish.setBorderPainted(false);
			btnEnglish.setBounds(231, 49, 40, 28);
		}
		return btnEnglish;
	}
	private JButton getBtnApostar() {
		if (btnApostar == null) {
			btnApostar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet")); //$NON-NLS-1$ //$NON-NLS-2$
			btnApostar.setFont(new Font("PT Sans", Font.BOLD, 14));
			btnApostar.setEnabled(false);
			btnApostar.setForeground(new Color(61, 45, 20));
			btnApostar.setBackground(new Color(255, 189, 89));
			btnApostar.setOpaque(true);
			btnApostar.setBorderPainted(false);
			
			tableEvents.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = tableEvents.getSelectedRow();
					Event ev = (Event) tableModelEvents.getValueAt(i,3); // obtain ev object
					event=ev;
					if(event!=null) btnApostar.setEnabled(true);
				}
			});
			
			btnApostar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new DirectAccessEventGUI(event, u);
					a.setVisible(true);
				}
			});
			btnApostar.setBounds(10, 570, 280, 32);
		}
		return btnApostar;
	}
	
	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("KnowEvents"));
		btnReturn.setText(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
		UserZone.setText(ResourceBundle.getBundle("Etiquetas").getString("UserZone"));
		jLabelWelcome.setText(ResourceBundle.getBundle("Etiquetas").getString("Wellcome")); 
		lblCategorias.setText(ResourceBundle.getBundle("Etiquetas").getString("Enjoy"));
		lblEventosInteres.setText(ResourceBundle.getBundle("Etiquetas").getString("Interesting"));
		btnApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
	}
} // @jve:decl-index=0:visual-constraint="0,0"

