package use_case.wishlist;

import entity.Game;
import java.util.ArrayList;

public interface WishlistDataAccessInterface {
    void saveWishlist(ArrayList<Game> games);
    ArrayList<Game> loadWishlist();
}
