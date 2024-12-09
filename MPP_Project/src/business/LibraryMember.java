package business;

import java.io.Serializable;
import java.time.LocalDate;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkoutRecord = new CheckoutRecord();
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
	}

	public String getMemberId() {
		return memberId;
	}
	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void checkout(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		// Set the book copy as unavailable
		copy.setAvailable(false);

		// Create a new CheckoutRecordEntry and add the entry into Record for the book copy
		CheckoutRecordEntry entry = new CheckoutRecordEntry(copy, checkoutDate, dueDate);

		// Add the entry to this member's CheckoutRecord
		checkoutRecord.addEntry(entry);
	}

	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
