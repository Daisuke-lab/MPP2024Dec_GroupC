package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();

	// Use Case 3 - Checkout a Book
	public LibraryMember searchMember(String memberId);
	public Book searchBook(String isbn);
	public void saveMember(LibraryMember member);
	public void saveNewMember(LibraryMember member);
	public void saveBook(Book book);
}
