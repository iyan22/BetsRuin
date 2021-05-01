package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class FollowGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelEvent = new JLabel("");
	private JLabel lblSeguirA = new JLabel("Seguir a:");
	private JRadioButton radioButtonFollow1 = new JRadioButton("");
	private JRadioButton radioButtonFollow2 = new JRadioButton("");
	private JButton btnSeguir = new JButton("Seguir");
	private JButton btnVolver = new JButton("Volver");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JLabel followMessage = new JLabel("");

	/**
	 * Create the frame.
	 */
	public FollowGUI(User u, Event ev) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] events = ev.getDescription().split("-");

		labelEvent.setBounds(10, 46, 456, 19);
		labelEvent.setText(ev.getDescription());
		contentPane.add(labelEvent);

		lblSeguirA.setBounds(10, 116, 68, 14);
		contentPane.add(lblSeguirA);

		radioButtonFollow1.setBounds(78, 112, 109, 23);
		radioButtonFollow1.setText(events[0]);
		buttonGroup.add(radioButtonFollow1);
		contentPane.add(radioButtonFollow1);

		radioButtonFollow2.setBounds(219, 112, 109, 23);
		radioButtonFollow2.setText(events[1]);
		buttonGroup.add(radioButtonFollow2);
		contentPane.add(radioButtonFollow2);

		btnSeguir.setBounds(140, 256, 89, 23);
		contentPane.add(btnSeguir);
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = StartGUI.getBusinessLogic();
				try {
					if(radioButtonFollow1.isSelected()) {
						facade.follow(u, events[0]);
						followMessage.setText("¡Ahora sigues a " + events[0] + "!");
						followMessage.setForeground(Color.green);
					} else if(radioButtonFollow2.isSelected()) {
						facade.follow(u, events[1]);
						followMessage.setText("¡Ahora sigues a " + events[1] + "!");
						followMessage.setForeground(Color.green);
					} else {
						followMessage.setText("No has seleccionado ninguna opción.");
					}
				} catch(AlreadyFollowed ex) {
					followMessage.setText("¡Ya eres seguidor de este equipo!");
					followMessage.setForeground(Color.red);
				}
			}
		});

		btnVolver.setBounds(239, 256, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnVolver);
		followMessage.setHorizontalAlignment(SwingConstants.CENTER);
		followMessage.setBounds(0, 217, 466, 14);

		contentPane.add(followMessage);
	}
	
	public void close(ActionEvent e) {
		this.dispose();
	}
}
