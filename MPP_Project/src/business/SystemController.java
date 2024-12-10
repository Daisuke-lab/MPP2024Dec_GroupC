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

	public void addBook(String isbn, String title,List<String> authorList,String borrowDays,String bookCopiesUnit) throws LibrarySystemException {
		if (borrowDays.trim().isEmpty() || isbn.trim().isEmpty() || title.trim().isEmpty() ) {
			throw new LibrarySystemException("All fields must be non-empty!");
		}

		try {
			Integer.parseInt(borrowDays);
		}
		catch(NumberFormatException ex) {
			throw new LibrarySystemException("Borrow days must be an integer");
		}

		try {
			int numCopies = Integer.parseInt(bookCopiesUnit);
			if(numCopies <= 0) {
				throw new LibrarySystemException("Number of copies should be greater than 0");
			}
		}
		catch(NumberFormatException ex) {
			throw new LibrarySystemException("Number of copies must be an integer");
		}

		// Isbn must be consist of 8 characters
		if (isbn.length() != 8) {
			throw new LibrarySystemException("ISBN has consist of 8 characters");
		}
		char[] chs = isbn.toCharArray();
		for (char ch : chs) {
			if ((ch < '0' || ch > '9') && ch != '-') {
				throw new LibrarySystemException("isbn must be numeric");
			}
		}

		int checkoutDuration = Integer.parseInt(borrowDays);

		if(checkoutDuration !=7 && checkoutDuration != 21) {
			throw new LibrarySystemException("Borrowing Length should be either 7 or 21 days!");
		}

		String regex = "(\\d{2})-(\\d{5})";
		if (!isbn.matches(regex)) {
			throw new LibrarySystemException(
					"ISBN must start with two numbers and end with five numbers, concat with hyphen ");
		}
		DataAccess dataAccess = new DataAccessFacade();
		Book book = dataAccess.searchBook(isbn);
		if (book != null) {
			throw new LibrarySystemException("Book with this ISBN already exist!");
		}
		DataAccess da = new DataAccessFacade();
		List<Author> authorNewList = new ArrayList<>();

		Author authorObj;

		for (String author : authorList) {
			authorObj = new Author(author, "", "", new Address("", "", "", ""), "Best author");
			authorNewList.add(authorObj);
		}

		book = new Book(isbn,title,checkoutDuration,authorNewList);
		da.saveBook(book);

		if(Integer.parseInt(bookCopiesUnit)>1)
			addBookCopy(isbn, Integer.parseInt(bookCopiesUnit)-1);
	}
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
	public void addNewLibraryMember(String fname, String lname, String mId, String tel, String street, String city,
									String state,
									String zip) throws LibrarySystemException {
		DataAccess dataAccess = new DataAccessFacade();
		LibraryMember libraryMember = dataAccess.searchMember(mId);
		if (libraryMember != null) {
//			JOptionPane.showMessageDialog(CheckOutBookWindow.INSTANCE, "Member ID NOT found");
			throw new LibrarySystemException("Member already exist");
		}

		if (fname.trim().isEmpty() || lname.trim().isEmpty() || mId.trim().isEmpty() || tel.trim().isEmpty()
				|| street.trim().isEmpty() || city.trim().isEmpty() || state.trim().isEmpty() || zip.trim().isEmpty()) {
			throw new LibrarySystemException("All fields must be non-empty!");
		}

		String zipcoderegex = "^\\d{5}";
		if (!zip.matches(zipcoderegex)) {
			throw new LibrarySystemException("ZipCode is illegal");
		}
		String telePhoneRegex = "^\\d{3}-\\d{3}-\\d{4}$";
		if (!tel.matches(telePhoneRegex)) {
			throw new LibrarySystemException("telephone number input is illegal");
		}
		String mIDRegexString = "^\\d{4}";
		if (!mId.matches(mIDRegexString)) {
			throw new LibrarySystemException("Member ID should be four digits");
		}

		char[] chs = mId.toCharArray();
		for (char ch : chs) {
			if (ch < '0' || ch > '9') {
				throw new LibrarySystemException("Member Id must be numeric");
			}
		}

		Address address = new Address(street, city, state, zip);
		LibraryMember member = new LibraryMember(mId, fname, lname, tel, address);

//		DataAccess da = new DataAccessFacade();
		dataAccess.saveNewMember(member);
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
	public static String[][] allMembers() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().values());
		String[][] results = new String[retval.size()][5];
		int i = 0;
		for (LibraryMember lb : retval) {
			String[] value = new String[5];
			value[0] = (i+1)+"";
			value[1] = lb.getMemberId();
			value[2] = lb.getFirstName();
			value[3] = lb.getLastName();
			value[4] = lb.getTelephone();
			results[i] = value;
			i++;
		}

		return results;
	}


}
