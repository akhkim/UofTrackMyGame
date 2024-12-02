package use_case.wishlist;

public class WishlistOutputData {
    private final boolean success;
    private final String message;

    public WishlistOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
