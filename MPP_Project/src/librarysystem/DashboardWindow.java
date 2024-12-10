package librarysystem;

import business.SystemController;
import dataaccess.Auth;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardWindow extends JFrame implements LibWindow  {
    JList<String> linkList;
    JPanel cards;
    private boolean isInitialized = false;
    public static  DashboardWindow INSTANCE = new DashboardWindow();
    private static String ADD_NEW_MEMBER_LABEL = "Add a new member";
    private static String CHECKOUT_BOOK_LABEL = "Checkout a book";
    private static String COPY_BOOK_LABEL = "Book copies";
    private static String ADD_BOOK_LABEL = "Add Book";
    private static String ADD_COPY_BOOK_LABEL = "Add Book Copies";
    private static String CHECKOUT_RECORD_LABEL = "Checkout a record";
    private static String LIBRARY_BOOKS_LABEL = "Books";
    private static String LIBRARY_MEMBERS_LABEL = "Members";
    private static List<String> panels = new ArrayList<>();

    private DashboardWindow() {};
    public void init() {

        setSize(750, 500);
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
                cards.add(AddNewLibraryMemberWindow.INSTANCE, ADD_NEW_MEMBER_LABEL);
                cards.add(AddBookWindow.INSTANCE, ADD_BOOK_LABEL);
                cards.add(AllBookIdsWindow.INSTANCE, LIBRARY_BOOKS_LABEL);
                cards.add(AllMemberIdsWindow.INSTANCE, LIBRARY_MEMBERS_LABEL);
                cards.add(BookCopiesWindow.INSTANCE, COPY_BOOK_LABEL);
                cards.add(AddBookCopyWindow.INSTANCE, ADD_COPY_BOOK_LABEL);
                panels.addAll(new ArrayList<>(Arrays.asList(new String[]{ADD_NEW_MEMBER_LABEL, ADD_BOOK_LABEL, LIBRARY_BOOKS_LABEL, LIBRARY_MEMBERS_LABEL, COPY_BOOK_LABEL, ADD_COPY_BOOK_LABEL})));
                break;
            case Auth.LIBRARIAN:
                cards.add(CheckoutBookWindow.INSTANCE, CHECKOUT_BOOK_LABEL);
                cards.add(CheckoutRecordWindow.INSTANCE, CHECKOUT_RECORD_LABEL);
                panels.addAll(new ArrayList<>(Arrays.asList(new String[]{CHECKOUT_BOOK_LABEL, CHECKOUT_RECORD_LABEL})));
                break;
            case Auth.BOTH:
                cards.add(AddNewLibraryMemberWindow.INSTANCE, ADD_NEW_MEMBER_LABEL);
                cards.add(AddBookWindow.INSTANCE, ADD_BOOK_LABEL);
                cards.add(AllBookIdsWindow.INSTANCE, LIBRARY_BOOKS_LABEL);
                cards.add(AllMemberIdsWindow.INSTANCE, LIBRARY_MEMBERS_LABEL);
                cards.add(BookCopiesWindow.INSTANCE, COPY_BOOK_LABEL);
                cards.add(AddBookCopyWindow.INSTANCE, ADD_COPY_BOOK_LABEL);
                cards.add(CheckoutBookWindow.INSTANCE, CHECKOUT_BOOK_LABEL);
                cards.add(CheckoutRecordWindow.INSTANCE, CHECKOUT_RECORD_LABEL);
                panels.addAll(new ArrayList<>(Arrays.asList(new String[]{ADD_NEW_MEMBER_LABEL, ADD_BOOK_LABEL, LIBRARY_BOOKS_LABEL, LIBRARY_MEMBERS_LABEL, COPY_BOOK_LABEL, ADD_COPY_BOOK_LABEL, CHECKOUT_BOOK_LABEL, CHECKOUT_RECORD_LABEL})));
                break;
            default:
                LibrarySystem.hideAllWindows();
                LoginWindow.INSTANCE.init();
                Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
                LoginWindow.INSTANCE.setVisible(true);
                return;
        }

        linkList = new JList<String>(panels.toArray(new String[0]));


        //connect JList elements to CardLayout panels
        linkList.addListSelectionListener(event -> {
            String value = linkList.getSelectedValue().toString();
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, value);
        });

    }
}
