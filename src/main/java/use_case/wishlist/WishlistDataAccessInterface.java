package use_case.wishlist;

import java.util.ArrayList;

import entity.Game;

public interface WishlistDataAccessInterface {
    ArrayList<Game> loadWishlist();
    void removeGameFromWishlist(String gameTitle);
}
