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
import domain.User;
import javax.swing.JButton;

public class SeePredictionsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnClose;
	private JButton btnPlaceBet;
	private DefaultTableModel mod;
	private User user;
	private JScrollPane scrollPane = new JScrollPane();

	private String[] columnNamesPredictions = new String[] {
            "Event","Question","Prediction"

    };

	/**
	 * Create the frame.
	 */
	public SeePredictionsGUI(Question q, User u) {
		this.user = u;
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		BLFacade facade = StartGUI.getBusinessLogic();
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 419, 146);
		contentPane.add(scrollPane);


		table = new JTable();
		List<Prediction> predictionlist= facade.getPredictions(q);
		
		scrollPane.setViewportView(table);
		mod = new DefaultTableModel(null, columnNamesPredictions);
		mod.setDataVector(null, columnNamesPredictions);
		mod.setColumnCount(4);
		table.setModel(mod);

		if(predictionlist.isEmpty()) {
			mod.setRowCount(0);
		}
		else {
			mod.setRowCount(0);
			for(Prediction p: predictionlist) {
				Vector<Object> row= new Vector<Object>();
				row.add(p.getQuestion().getEvent().getDescription());
				row.add(p.getQuestion().getQuestion());
				row.add(p.getAnswer());
				row.add(p);

				mod.addRow(row);
			}
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
		}
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
						JFrame a = new PlaceBetGUI(p,user);
						a.setVisible(true);
					}
				});
			}
		});

		btnClose = new JButton("Close");
		btnClose.setBounds(226, 227, 89, 23);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		contentPane.add(btnClose);

		btnPlaceBet = new JButton("Place Bet");
		btnPlaceBet.setBounds(108, 227, 89, 23);
		contentPane.add(btnPlaceBet);
		btnPlaceBet.setEnabled(false);
		
	}

	public void close(ActionEvent e) {
		this.dispose();
	}

	public void jButton_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
