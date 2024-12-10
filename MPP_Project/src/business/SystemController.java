package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}

	// Error Handling Implementation needed!
	public void checkoutBook(String memberId, String isbn) throws CheckoutException {
		DataAccess da = new DataAccessFacade();

		// Search for member
		LibraryMember member = da.searchMember(memberId);
		if (member == null) {
			throw new CheckoutException(CheckoutException.CheckoutErrorType.MEMBER_NOT_FOUND);
		}

		// Search for book
		Book book = da.searchBook(isbn);
		if (book == null) {
			throw new CheckoutException(CheckoutException.CheckoutErrorType.BOOK_NOT_FOUND);
		}

		// Check if the book has an available copy
		BookCopy availableCopy = book.getNextAvailableCopy();
		if (availableCopy == null) {
			throw new CheckoutException(CheckoutException.CheckoutErrorType.NO_AVAILABLE_COPY);
		}

		// Determine the maximum checkout length for the book
		int checkoutLength = book.getMaxCheckoutLength();
		// Perform checkout operation
		LocalDate checkoutDate = LocalDate.now();
		LocalDate dueDate = checkoutDate.plusDays(checkoutLength);

		// Add the checkout entry to the member's CheckoutRecord
		member.checkout(availableCopy, checkoutDate, dueDate);

		da.saveMember(member);
		da.saveBook(book);
	}

	public CheckoutRecord getMemberCheckoutRecord(String memberID) throws CheckoutException {
		DataAccess da = new DataAccessFacade();

		LibraryMember member = da.searchMember(memberID);
		if(member == null) {
			throw new CheckoutException(CheckoutException.CheckoutErrorType.MEMBER_NOT_FOUND);
		}

		return member.getCheckoutRecord();
	}

	public BookCopy[] getBookCopyArray(String ISBN) throws LibrarySystemException{
		DataAccess da = new DataAccessFacade();
		Book book = da.searchBook(ISBN);
		if(book==null) {
			throw new LibrarySystemException("Book not found");
		}
		return book.getCopies();};

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	public static String[][] allBooks() {

		DataAccess da = new DataAccessFacade();
		List<Book> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().values());
		String[][] results = new String[retval.size()][5];
		int i = 0;
		for (Book lb : retval) {
			String[] value = new String[5];
			value[0] = (i+1)+"";
			value[1] = lb.getIsbn();
			value[2] = lb.getTitle();
			value[3] = lb.getMaxCheckoutLength()+"";
			value[4] = lb.getNumCopies()+"";
			results[i] = value;
			i++;
		}

		return results;
	}


	@Override
	public void addBookCopy(String isbn, int noOfCopies) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		Book book = null;
		try {
			if(noOfCopies <=0) throw new NullPointerException("Copies cannot be zero!");

			try {
				book = da.searchBook(isbn);
				if(book == null) throw new LibrarySystemException("Book doesn't exist!");
			} catch(LibrarySystemException e) {
				throw new LibrarySystemException(e.getMessage());
			}


			for(int i=0; i<noOfCopies; i++) {
				BookCopy c = new BookCopy(book, book.getNumCopies()+1, true);
				book.addCopy(c);
				da.saveBook(book);
			}
		} catch (NullPointerException e) {
			throw new LibrarySystemException(e.getMessage());
		}
	}




}
