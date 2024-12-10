package librarysystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import business.LibrarySystemException;
import business.SystemController;

public class AddBookCopyWindow extends JPanel implements LibWindow {
	public static AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
	private boolean isInitialized = false;
	private JPanel mainPanel;
	private JTextField isbnIDText;
	private JTextField totalCopyField;

	private AddBookCopyWindow() {
		super(new BorderLayout());
		init();
	}

	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(20, 20)); // Add padding
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add margins around the panel
		defineTopPanel();
		defineMiddlePanel();
		//defineLowerPanel();
		add(mainPanel);
		isInitialized = true;
	}

	private void defineTopPanel() {
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel("Add Book Copy");
		Util.adjustLabelFont(titleLabel, Util.DARK_BLUE, true);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		titleLabel.setForeground(Color.decode("#1a73e8")); // Blue color
		topPanel.add(titleLabel);
		mainPanel.add(topPanel, BorderLayout.NORTH);
	}

	private void defineMiddlePanel() {
		JPanel middlePanel = new JPanel(new GridLayout(3, 2, 15, 15)); // Grid layout for better alignment
		JLabel isbnLabel = new JLabel("ISBN:");
		JLabel copiesLabel = new JLabel("Total Copies:");
		isbnIDText = new JTextField(15);
		totalCopyField = new JTextField(15);
		JButton addBookCopyButton = new JButton("Add Copy");

		addBookCopyButton.setBackground(Color.decode("#4CAF50")); // Green background
		addBookCopyButton.setForeground(Color.DARK_GRAY);
		addBookCopyButton.setFocusPainted(false);
		addBookCopyButton.setFont(new Font("SansSerif", Font.BOLD, 14));

		addBookCopyButton.addActionListener(e -> handleAddCopyAction());

		middlePanel.add(isbnLabel);
		middlePanel.add(isbnIDText);
		middlePanel.add(copiesLabel);
		middlePanel.add(totalCopyField);
		middlePanel.add(new JLabel()); // Empty cell for spacing
		middlePanel.add(addBookCopyButton);

		middlePanel.setBorder(BorderFactory.createTitledBorder("Enter Book Details"));
		mainPanel.add(middlePanel, BorderLayout.CENTER);
	}

//	private void defineLowerPanel() {
//		JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JButton backButton = new JButton("<== Back to Main");
//
//		backButton.setBackground(Color.decode("#f44336")); // Red background
//		backButton.setForeground(Color.WHITE);
//		backButton.setFocusPainted(false);
//		backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
//
//		backButton.addActionListener(evt -> {
//			LibrarySystem.hideAllWindows();
//			LibrarySystem.INSTANCE.setVisible(true);
//		});
//
//		lowerPanel.add(backButton);
//		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
//	}

	private void handleAddCopyAction() {
		String isbn = isbnIDText.getText().trim();
		String numCopiesStr = totalCopyField.getText().trim();
		int numCopies;

		if (isbn.isEmpty() || numCopiesStr.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			numCopies = Integer.parseInt(numCopiesStr);
			if (numCopies <= 0) {
				JOptionPane.showMessageDialog(this, "Number of copies should be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Number of copies must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			new SystemController().addBookCopy(isbn, numCopies);
			clearFields();
			JOptionPane.showMessageDialog(this, "Added book copy successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (LibrarySystemException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearFields() {
		isbnIDText.setText("");
		totalCopyField.setText("");
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
