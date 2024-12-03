package use_case.wishlist;

import entity.Game;
import entity.Wishlist;

import java.util.ArrayList;

public interface WishlistDataAccessInterface {
    ArrayList<Game> loadWishlist();
    void removeGameFromWishlist(String gameTitle);
}
