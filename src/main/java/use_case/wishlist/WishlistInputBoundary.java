package use_case.wishlist;

import entity.Game;

import java.util.ArrayList;

public interface WishlistInputBoundary {
    void removeGameFromWishlist(WishlistInputData inputData);
    void getWishlistGames();
}
