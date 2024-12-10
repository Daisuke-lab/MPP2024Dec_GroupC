package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public void checkoutBook(String memberId, String isbn) throws CheckoutException;
	public CheckoutRecord getMemberCheckoutRecord(String memberId) throws CheckoutException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
}
