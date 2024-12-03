package interface_adapter.wishlist;

import entity.Game;
import java.util.ArrayList;

public class WishlistState {
    private ArrayList<Game> games;

    public WishlistState() {
        this.games = new ArrayList<>();
    }

    public ArrayList<Game> loadWishlist() {
        // Return the current games as the loaded wishlist
        return games;
    }

    public void saveToWishlist(String gameID, String title, String salePrice, String normalPrice, String isOnSale, String savings, String metacriticScore, String steamRatingText, String steamRatingPercent, String steamRatingCount, String dealRating, String thumb, String storeName) {

    }

    public void saveWishlist(ArrayList<Game> games) {
        // Update the internal list with the provided games
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}
