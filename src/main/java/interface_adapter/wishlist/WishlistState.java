package interface_adapter.wishlist;

import entity.Game;
import use_case.wishlist.WishlistDataAccessInterface;
import java.util.ArrayList;

public class WishlistState implements WishlistDataAccessInterface {
    private ArrayList<Game> games;

    public WishlistState() {
        this.games = new ArrayList<>();
    }

    @Override
    public ArrayList<Game> loadWishlist() {
        // Return the current games as the loaded wishlist
        return games;
    }

    @Override
    public void saveToWishlist(String gameID, String title, String salePrice, String normalPrice, String isOnSale, String savings, String metacriticScore, String steamRatingText, String steamRatingPercent, String steamRatingCount, String dealRating, String thumb, String storeName) {

    }

    @Override
    public void saveWishlist(ArrayList<Game> games) {
        // Update the internal list with the provided games
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void removeGame(Game game) {
        this.games.remove(game);
    }
}
