package librarysystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.SystemController;


public class AllBookIdsWindow extends JPanel implements LibWindow {
	private static final long serialVersionUID = 1L;
	public static final AllBookIdsWindow INSTANCE = new AllBookIdsWindow();
	ControllerInterface ci = new SystemController();
	private boolean isInitialized = false;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private String[] columnNames = { "N", "ISBN", "Title", "Max Checkout Day","Copies"};
	private JTable table;

	private AllBookIdsWindow() {
		super(new BorderLayout());
		init();
	}

	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
		add(mainPanel);
		isInitialized = true;
	}

	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel AllIDsLabel = new JLabel("All Books");
		Util.adjustLabelFont(AllIDsLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(AllIDsLabel);
	}

	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		String[][] data = SystemController.allBooks();

		table = new JTable(new DefaultTableModel(data, columnNames));
		table. getColumnModel().getColumn(0).setPreferredWidth(20);
		table.setEnabled(false);
		table.setBounds(30, 40, 200, 200);

		// adding it to JScrollPane
		JScrollPane sp = new JScrollPane(table);
		middlePanel.add(sp);
	}

	public void reloadBooks() {
		String[][] data = SystemController.allBooks();
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		// Clear All
		for (int i = 0; i < data.length; i++) {
		}
		for (int i = table.getRowCount() - 1; i > -1; i--) {
			defaultTableModel.removeRow(i);
		}
		// Add All
		for (int i = 0; i < data.length; i++) {
			defaultTableModel.insertRow(i, data[i]);
		}
	}

	public void defineLowerPanel() {
		lowerPanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		lowerPanel.setLayout(fl);
		JButton backButton = new JButton("<== Back to Main");
		lowerPanel.setVisible(false);
	}

	@Override
	public boolean isInitialized() {

		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}
}
