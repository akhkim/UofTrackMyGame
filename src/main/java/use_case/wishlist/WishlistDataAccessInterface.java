package use_case.wishlist;

import entity.Game;
import entity.Wishlist;

import java.util.ArrayList;

public interface WishlistDataAccessInterface {
    void saveToWishlist(String gameID, String title, String salePrice,
                        String normalPrice, String isOnSale, String savings,
                        String metacriticScore, String steamRatingText,
                        String steamRatingPercent, String steamRatingCount,
                        String dealRating, String thumb, String storeName);

    void saveWishlist(ArrayList<Game> games);
    ArrayList<Game> loadWishlist();
    void removeGameFromWishlist(String gameTitle);
}
