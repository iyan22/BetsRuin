package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class StartGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JButton jButtonLogin;
	private JButton jButtonRegister;
	private JButton btnClose;
	protected JLabel jLabelLogo;
	private JButton btnSpanish;
	private JButton btnBasque;
	private JButton btnEnglish;

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	/**
	 * Create the frame.
	 */
	public StartGUI() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(406, 477);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Start"));

	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.WHITE);
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton1());
			jContentPane.add(getBoton2());
			jContentPane.add(getClose());
			jContentPane.add(getbtnSpanish());
			jContentPane.add(getbtnBasque());
			jContentPane.add(getbtnEnglish());

		}
		return jContentPane;
	}

	private JLabel getLblNewLabel() {
		if (jLabelLogo == null) {
			jLabelLogo = new JLabel();
			jLabelLogo.setIcon(new ImageIcon(StartGUI.class.getResource("/img/LogoBetsRuinL.png")));
			jLabelLogo.setBounds(119, 42, 165, 163);
			jLabelLogo.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelLogo.setForeground(Color.BLACK);
			jLabelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelLogo;
	}

	private JButton getClose() {
		if (btnClose == null) {
			btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
			btnClose.setBackground(new Color(227, 227, 227));
			btnClose.setOpaque(true);
			btnClose.setBorderPainted(false);
			btnClose.setFont(new Font("PT Sans", Font.BOLD, 16));
			btnClose.setBounds(152, 400, 85, 21);
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close(e);
				}
			});

		}
		return btnClose;
	}

	private JButton getBoton1() {
		if (jButtonLogin == null) {
			jButtonLogin = new JButton();
			jButtonLogin.setForeground(new Color(255, 189, 89));
			jButtonLogin.setBackground(new Color(61, 45, 20));
			jButtonLogin.setFont(new Font("PT Sans", Font.BOLD, 22));
			jButtonLogin.setOpaque(true);
			jButtonLogin.setBorderPainted(false);
			jButtonLogin.setBounds(29, 236, 338, 63);
			jButtonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			jButtonLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new LoginGUI();
					closeWindow(e);
					a.setVisible(true);

				}
			});
			this.dispose();
		}
		return jButtonLogin;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonRegister == null) {
			jButtonRegister = new JButton();
			jButtonRegister.setBackground(new Color(255, 189, 89));
			jButtonRegister.setForeground(new Color(61, 45, 20));
			jButtonRegister.setOpaque(true);
			jButtonRegister.setBorderPainted(false);
			jButtonRegister.setFont(new Font("PT Sans", Font.BOLD, 22));
			jButtonRegister.setBounds(29, 311, 338, 63);
			jButtonRegister.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
			jButtonRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new RegisterGUI();
					closeWindow(e);
					a.setVisible(true);
				}
			});
		}
		return jButtonRegister;
	}

	private void closeWindow(ActionEvent e) {
		this.setVisible(false);
	}

	public void close(ActionEvent e) {
		this.dispose();
	}

	private JButton getbtnSpanish() {
		btnSpanish = new JButton(); // $NON-NLS-1$ //$NON-NLS-2$
		btnSpanish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: " + Locale.getDefault());
				redibujar();
			}
		});
		btnSpanish.setForeground(Color.BLACK);
		btnSpanish.setBorderPainted(false);
		btnSpanish.setBackground(Color.WHITE);
		btnSpanish.setIcon(new ImageIcon(StartGUI.class.getResource("/img/Spanish.png")));
		btnSpanish.setBounds(300, 6, 33, 21);
		return btnSpanish;
	}

	private JButton getbtnBasque() {
		btnBasque = new JButton();
		btnBasque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: " + Locale.getDefault());
				redibujar();
			}
		});
		btnBasque.setIcon(new ImageIcon(StartGUI.class.getResource("/img/Basque.png")));
		btnBasque.setForeground(Color.BLACK);
		btnBasque.setBorderPainted(false);
		btnBasque.setBackground(Color.WHITE);
		btnBasque.setBounds(327, 6, 40, 21);
		return btnBasque;
	}

	private JButton getbtnEnglish() {
		btnEnglish = new JButton();
		btnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: " + Locale.getDefault());
				redibujar();
			}
		});
		btnEnglish.setIcon(new ImageIcon(StartGUI.class.getResource("/img/English.png")));
		btnEnglish.setForeground(Color.BLACK);
		btnEnglish.setBorderPainted(false);
		btnEnglish.setBackground(Color.WHITE);
		btnEnglish.setBounds(360, 6, 40, 21);
		return btnEnglish;
	}

	private void redibujar() {
		jButtonRegister.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		jButtonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Start"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
}
