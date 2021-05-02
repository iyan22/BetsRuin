package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.User;
import exceptions.AlreadyFollowed;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FollowGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelEvent = new JLabel("");
	private JLabel lblSeguirA = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FollowTo") + ";");
	private JRadioButton radioButtonFollow1 = new JRadioButton("");
	private JRadioButton radioButtonFollow2 = new JRadioButton("");
	private JButton btnSeguir = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Follow"));
	private JButton btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JLabel followMessage = new JLabel("");

	/**
	 * Create the frame.
	 */
	public FollowGUI(User u, Event ev) {
		setTitle("Bets&Ruin - Follow");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 249);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] events = ev.getDescription().split("-");
		labelEvent.setHorizontalAlignment(SwingConstants.CENTER);
		labelEvent.setFont(new Font("PT Sans", Font.BOLD, 16));

		labelEvent.setBounds(10, 21, 456, 34);
		labelEvent.setText(ev.getDescription());
		contentPane.add(labelEvent);
		btnSeguir.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnSeguir.setForeground(new Color(61, 45, 20));
		btnSeguir.setBackground(new Color(255, 189, 89));
		btnSeguir.setOpaque(true);
		btnSeguir.setBorderPainted(false);

		btnSeguir.setBounds(96, 158, 118, 43);
		contentPane.add(btnSeguir);
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = StartGUI.getBusinessLogic();
				try {
					if(radioButtonFollow1.isSelected()) {
						facade.follow(u, events[0]);
						followMessage.setText(ResourceBundle.getBundle("Etiquetas").getString("Following") + events[0]);
						followMessage.setForeground(Color.green);
					} else if(radioButtonFollow2.isSelected()) {
						facade.follow(u, events[1]);
						followMessage.setText(ResourceBundle.getBundle("Etiquetas").getString("Following") + events[1]);
						followMessage.setForeground(Color.green);
					} else {
						followMessage.setText(ResourceBundle.getBundle("Etiquetas").getString("NoSelect"));
					}
				} catch(AlreadyFollowed ex) {
					followMessage.setText(ResourceBundle.getBundle("Etiquetas").getString("AlreadyFollow"));
					followMessage.setForeground(Color.red);
				}
			}
		});
		btnVolver.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnVolver.setBackground(new Color(61, 45, 20));
		btnVolver.setForeground(new Color(255, 189, 89));
		btnVolver.setOpaque(true);
		btnVolver.setBorderPainted(false);
		btnVolver.setBounds(242, 158, 118, 43);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnVolver);
		followMessage.setFont(new Font("PT Sans", Font.BOLD, 16));
		followMessage.setHorizontalAlignment(SwingConstants.CENTER);
		followMessage.setBounds(10, 128, 456, 22);

		contentPane.add(followMessage);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(49, 77, 380, 43);
		contentPane.add(panel);
		panel.setLayout(null);
		lblSeguirA.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblSeguirA.setBounds(16, 12, 84, 16);
		panel.add(lblSeguirA);
		radioButtonFollow1.setFont(new Font("PT Sans", Font.PLAIN, 16));
		radioButtonFollow1.setBounds(100, 8, 140, 23);
		panel.add(radioButtonFollow1);
		radioButtonFollow1.setText(events[0]);
		buttonGroup.add(radioButtonFollow1);
		radioButtonFollow2.setFont(new Font("PT Sans", Font.PLAIN, 16));
		radioButtonFollow2.setBounds(241, 8, 133, 23);
		panel.add(radioButtonFollow2);
		radioButtonFollow2.setText(events[1]);
		buttonGroup.add(radioButtonFollow2);
	}
	
	public void close(ActionEvent e) {
		this.dispose();
	}
}
