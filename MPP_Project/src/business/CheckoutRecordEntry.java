package business;

import java.io.Serializable;
import java.time.LocalDate;

final public class CheckoutRecordEntry implements Serializable {
    private static final long serialVersionUID = 1048181949054530481L;

    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private CheckoutRecord checkoutRecord;

    CheckoutRecordEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }
}