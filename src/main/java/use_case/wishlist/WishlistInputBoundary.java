package use_case.wishlist;

import entity.Game;

import java.util.ArrayList;

public interface WishlistInputBoundary {
    WishlistOutputData removeGameFromWishlist(WishlistInputData inputData);
    ArrayList<Game> getWishlistGames();
}
