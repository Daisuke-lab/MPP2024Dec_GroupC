package business;

public class CheckoutException extends Exception {
    private CheckoutErrorType errorType;

    public CheckoutException(CheckoutErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CheckoutErrorType getErrorType() {
        return errorType;
    }

    public enum CheckoutErrorType {
        MEMBER_NOT_FOUND("Library member not found."),
        BOOK_NOT_FOUND("Book not found."),
        NO_AVAILABLE_COPY("No available copies for this book."),
        UNEXPECTED_ERROR("An unexpected error occurred during checkout.");

        private final String message;

        private CheckoutErrorType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
