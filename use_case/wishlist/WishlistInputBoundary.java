package use_case.wishlist;

public interface WishlistInputBoundary {
    WishlistOutputData addGameToWishlist(WishlistInputData inputData);
    WishlistOutputData removeGameFromWishlist(WishlistInputData inputData);
}
