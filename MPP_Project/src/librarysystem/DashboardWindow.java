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
    private static String ADD_BOOK_LABEL = "Add a book";
    private static String CHECKOUT_RECORD_LABEL = "Checkout a record";
    private static String LIBRARY_BOOKS_LABEL = "Books";
    private static String LIBRARY_MEMBERS_LABEL = "Members";
    private static Map<String, JPanel> panels = new HashMap<>();

    private DashboardWindow() {};
    public void init() {
        panels.put(ADD_NEW_MEMBER_LABEL, AddNewLibraryMemberWindow.INSTANCE);
        panels.put(CHECKOUT_BOOK_LABEL, CheckoutBookWindow.INSTANCE);
        panels.put(CHECKOUT_RECORD_LABEL, CheckoutRecordWindow.INSTANCE);
        panels.put(ADD_BOOK_LABEL, AddBookWindow.INSTANCE);

        panels.put(ADD_BOOK_LABEL, AddBookWindow.INSTANCE);
        panels.put(LIBRARY_BOOKS_LABEL, AllBookIdsWindow.INSTANCE);
        panels.put(LIBRARY_MEMBERS_LABEL, AllMemberIdsWindow.INSTANCE);
        setSize(750, 500);

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


        switch (SystemController.currentAuth) {
            case Auth.ADMIN:
                cards.add(panels.get(ADD_NEW_MEMBER_LABEL), ADD_NEW_MEMBER_LABEL);
                cards.add(panels.get(ADD_BOOK_LABEL), ADD_BOOK_LABEL);
                cards.add(panels.get(LIBRARY_BOOKS_LABEL), LIBRARY_BOOKS_LABEL);
                cards.add(panels.get(LIBRARY_MEMBERS_LABEL), LIBRARY_MEMBERS_LABEL);
                cards.add(panels.get(LIBRARY_BOOKS_LABEL), LIBRARY_BOOKS_LABEL);

                break;
            case Auth.LIBRARIAN:
                cards.add(panels.get(CHECKOUT_BOOK_LABEL), CHECKOUT_BOOK_LABEL);
                cards.add(panels.get(CHECKOUT_RECORD_LABEL), CHECKOUT_RECORD_LABEL);
                break;
            case Auth.BOTH:
                cards.add(panels.get(ADD_NEW_MEMBER_LABEL), ADD_NEW_MEMBER_LABEL);
                cards.add(panels.get(ADD_BOOK_LABEL), ADD_BOOK_LABEL);
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
