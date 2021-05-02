package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Prediction;
import domain.Question;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class SetWinnerPredictionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnClose;
	private JButton btnPlaceBet;
	private DefaultTableModel mod;
	private JScrollPane scrollPane = new JScrollPane();

	private String[] columnNamesPredictions = new String[] {
            "Event","Question","Prediction"

    };
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public SetWinnerPredictionGUI(Question q) {
		setTitle("Bets&Ruin - Set winner prediction");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 318);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		BLFacade facade = StartGUI.getBusinessLogic();
		contentPane.setLayout(null);
		List<Prediction> predictionlist = facade.getPredictions(q);
		mod = new DefaultTableModel(null, columnNamesPredictions);
		mod.setDataVector(null, columnNamesPredictions);
		mod.setColumnCount(4);

		if(predictionlist.isEmpty()) {
			mod.setRowCount(0);
		}
		else {
			mod.setRowCount(0);
			for (Prediction p: predictionlist) {
				Vector<Object> row = new Vector<Object>();
				row.add(p.getQuestion().getEvent().getDescription());
				row.add(p.getQuestion().getQuestion());
				row.add(p.getAnswer());
				row.add(p);

				mod.addRow(row);
			}
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
		}

		btnClose = new JButton("Close");
		btnClose.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnClose.setBackground(new Color(61, 45, 20));
		btnClose.setForeground(new Color(255, 189, 89));
		btnClose.setOpaque(true);
		btnClose.setBorderPainted(false);
		btnClose.setBounds(226, 227, 131, 39);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CloseEventGUI();
				a.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnClose);

		btnPlaceBet = new JButton("Select");
		btnPlaceBet.setFont(new Font("PT Sans", Font.BOLD, 16));
		btnPlaceBet.setForeground(new Color(61, 45, 20));
		btnPlaceBet.setBackground(new Color(255, 189, 89));
		btnPlaceBet.setOpaque(true);
		btnPlaceBet.setBorderPainted(false);
		btnPlaceBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlaceBet.setBounds(65, 227, 131, 39);
		contentPane.add(btnPlaceBet);
		btnPlaceBet.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("Set winner prediction");
		lblNewLabel.setForeground(new Color(61, 45, 20));
		lblNewLabel.setFont(new Font("PT Sans", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(121, 10, 196, 27);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(227, 227, 227));
		panel.setBounds(16, 49, 416, 173);
		contentPane.add(panel);
		panel.setLayout(null);
		
				scrollPane = new JScrollPane();
				scrollPane.setBounds(18, 17, 385, 138);
				panel.add(scrollPane);
				
				
						table = new JTable();
						table.setFont(new Font("PT Sans", Font.PLAIN, 14));
						
						scrollPane.setViewportView(table);
						table.setModel(mod);
						table.getColumnModel().removeColumn(table.getColumnModel().getColumn(3)); // not shown in JTable
						
								table.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										btnPlaceBet.setEnabled(true);
										int i = table.getSelectedRow();
										Prediction p = (Prediction)mod.getValueAt(i, 3);
										System.out.println(p.getAnswer());
										btnPlaceBet.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												facade.setPredictionToWinner(p);
												facade.closeQuestion(q);
											}
										});
									}
								});
		
	}

	public void close(ActionEvent e) {
		this.dispose();
	}

	public void jButton_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
