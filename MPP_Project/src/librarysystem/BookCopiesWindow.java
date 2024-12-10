package librarysystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import business.BookCopy;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;

public class BookCopiesWindow extends JPanel implements LibWindow {
	public static final BookCopiesWindow INSTANCE = new BookCopiesWindow();

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JTextField ISBNText;
	private JLabel errorLabel;
	private JTable recordsTable;

	private boolean isInitialized = false;

	private BookCopiesWindow() {
		super(new BorderLayout());
		init();
	}

	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		defineTopPanel();
		defineMiddlePanel();
//		defineLowerPanel();

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		//mainPanel.add(lowerPanel, BorderLayout.SOUTH);
		add(mainPanel);

		isInitialized = true;
	}

	private void defineTopPanel() {
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel titleLabel = new JLabel("Book Copies");
		Util.adjustLabelFont(titleLabel, Util.DARK_BLUE, true);
		topPanel.add(titleLabel);
	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout(10, 10));

		// Input Panel
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 4));
		JLabel isbnLabel = new JLabel("ISBN");
		ISBNText = new JTextField(15);
		JButton showCopiesButton = new JButton("Show Book Copies");

		showCopiesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ISBN = ISBNText.getText();
				try {
					showBookCopiesRecord(ISBN);
				} catch (LibrarySystemException e1) {
					JOptionPane.showMessageDialog(BookCopiesWindow.this, e1.getMessage());
				}
			}
		});

		inputPanel.add(isbnLabel);
		inputPanel.add(ISBNText);
		inputPanel.add(showCopiesButton);

		// Table Panel
		JPanel tablePanel = new JPanel(new BorderLayout());
		recordsTable = new JTable();
		recordsTable.setFillsViewportHeight(true);
		recordsTable.setShowGrid(true);
		recordsTable.setGridColor(Color.LIGHT_GRAY);

		// Enhance Table Appearance
		JTableHeader header = recordsTable.getTableHeader();
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.BOLD, 14));
		recordsTable.setFont(new Font("Arial", Font.PLAIN, 12));
		recordsTable.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(recordsTable);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		middlePanel.add(inputPanel, BorderLayout.NORTH);
		middlePanel.add(tablePanel, BorderLayout.CENTER);
	}

	private void showBookCopiesRecord(String ISBN) throws LibrarySystemException {

		SystemController sc = new SystemController();
		List<BookCopy> bookCopyList = null;

		try {
			bookCopyList = new ArrayList(Arrays.asList(sc.getBookCopyArray(ISBN)));
		} catch(LibrarySystemException e) {

			JOptionPane.showMessageDialog(this,e.getMessage());

		}

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ISBN");
		model.addColumn("Title");
		model.addColumn("Copy Number");
		model.addColumn("Obtained By");
		model.addColumn("Duedate");
		model.addColumn("Overdue");
		if (bookCopyList != null) {
			for (BookCopy entry : bookCopyList) {
				try {
					String isbn = ISBN;
					String checkoutdate="N/A",duedate="N/A",overdue="N/A",obtainedBy="N/A";
					String title = entry.getBook().getTitle();
					String copyNumber = Integer.toString(entry.getCopyNum());

//				Obtained by
					LibraryMember libraryMemberOfBookCopy = entry.getLibraryMemberOfBookCopy();
					obtainedBy = libraryMemberOfBookCopy != null ? libraryMemberOfBookCopy.getMemberId() : "";

//				Finding entry for that book copy from library member
//					CheckoutRecord checkoutRecord = libraryMemberOfBookCopy != null ? sc.getMemberCheckoutRecord(libraryMemberOfBookCopy.getMemberId()) : null;
//					if(checkoutRecord != null) {
//						CheckoutRecordEntry checkoutEntry = checkoutRecord.getEntryByBookCopy(entry);
//						checkoutdate = checkoutEntry.getCheckoutDate().toString();
//						duedate = checkoutEntry.getDueDate().toString();
//						if (new SimpleDateFormat("yyyy-MM-dd").parse(duedate).before(new Date())) {
//							overdue= "Yes";
//						} else {
//							overdue="No";
//						}
//					}


					String[] row = { isbn, title,copyNumber,obtainedBy, duedate,overdue };
					model.addRow(row);
					String format = String.format("%s\t%s\t%s\t%s", isbn, title, checkoutdate, duedate);
					System.out.println(format);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this,e.getMessage());
				}

			}
		}
		recordsTable.setModel(model);
	}

//	private void defineLowerPanel() {
//		lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JButton backButton = new JButton("<== Back to Main");
//		addBackButtonListener(backButton);
//		lowerPanel.add(backButton);
//	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
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
