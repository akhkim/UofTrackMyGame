package use_case.wishlist;

public interface WishlistOutputBoundary {
    WishlistOutputData presentSuccess(String message);
    WishlistOutputData presentError(String message);
}
