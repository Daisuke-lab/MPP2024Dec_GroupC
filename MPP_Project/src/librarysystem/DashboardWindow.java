package librarysystem;

import business.SystemController;
import dataaccess.Auth;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class DashboardWindow extends JFrame implements LibWindow  {
    JList<String> linkList;
    JPanel cards;
    private boolean isInitialized = false;
    public static  DashboardWindow INSTANCE = new DashboardWindow();
    private static String ADD_NEW_MEMBER_LABEL = "Add a new member";
    private static String CHECKOUT_BOOK_LABEL = "Checkout a book";
    private static String COPY_BOOK_LABEL = "Copy a book";
    private static String ADD_COPY_BOOK_LABEL = "Book Copies";
    private static String CHECKOUT_RECORD_LABEL = "Checkout a record";
    private static Map<String, JPanel> panels = new HashMap<>();

    private DashboardWindow() {};
    public void init() {
        panels.put(CHECKOUT_BOOK_LABEL, CheckoutBookWindow.INSTANCE);
        panels.put(CHECKOUT_RECORD_LABEL, CheckoutRecordWindow.INSTANCE);
        panels.put(COPY_BOOK_LABEL, BookCopiesWindow.INSTANCE);
        panels.put(ADD_COPY_BOOK_LABEL, AddBookCopyWindow.INSTANCE);
        setSize(500, 350);

        linkList = new JList<String>(panels.keySet().toArray(new String[0]));
        createPanels();
        // set up split panes
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
        splitPane.setDividerLocation(150);
        //default layout for JFrame is BorderLayout; add method
        //adds to contentpane
        add(splitPane, BorderLayout.CENTER);
        isInitialized = true;
    }

    @Override
    public boolean isInitialized() {

        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;

    }


    public void createPanels() {
        cards = new JPanel(new CardLayout());
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Item 1 Panel");
        panel1.add(label1);

        switch (SystemController.currentAuth) {
            case Auth.ADMIN:
                cards.add(panels.get(COPY_BOOK_LABEL), COPY_BOOK_LABEL);
                cards.add(panels.get(ADD_COPY_BOOK_LABEL), ADD_COPY_BOOK_LABEL);
                break;
            case Auth.LIBRARIAN:
                cards.add(panels.get(CHECKOUT_BOOK_LABEL), CHECKOUT_BOOK_LABEL);
                cards.add(panels.get(CHECKOUT_RECORD_LABEL), CHECKOUT_RECORD_LABEL);
                break;
            case Auth.BOTH:
                cards.add(panels.get(CHECKOUT_BOOK_LABEL), CHECKOUT_BOOK_LABEL);
                cards.add(panels.get(CHECKOUT_RECORD_LABEL), CHECKOUT_RECORD_LABEL);
                break;
            default:
                LibrarySystem.hideAllWindows();
                LoginWindow.INSTANCE.init();
                Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
                LoginWindow.INSTANCE.setVisible(true);
                return;
        }


        //connect JList elements to CardLayout panels
        linkList.addListSelectionListener(event -> {
            String value = linkList.getSelectedValue().toString();
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, value);
        });

    }
}
