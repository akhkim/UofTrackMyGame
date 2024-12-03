package interface_adapter.wishlist;

import java.util.ArrayList;

import entity.Game;
import use_case.wishlist.WishlistInputBoundary;
import use_case.wishlist.WishlistInteractor;

/**
 * The WishlistState class manages the state of the wishlist, including loading and saving game data.
 * It interacts with a WishlistInputBoundary to access and manipulate the wishlist data.
 */
public class WishlistState {
    private ArrayList<Game> games;
    private WishlistInputBoundary dataAccess;

    public WishlistState(WishlistInputBoundary dataAccess) {
        this.games = new ArrayList<>();
        this.dataAccess = dataAccess;
    }

    /**
     * Loads the current wishlist, returning the list of games stored in the wishlist.
     *
     * @return An ArrayList of Game objects representing the current wishlist.
     */
    public ArrayList<Game> loadWishlist() {
        // Return the current games as the loaded wishlist
        return games;
    }

    /**
     * Saves a game to the wishlist. This method takes various game details as parameters but does not implement saving functionality in this version.
     *
     * @param gameID The ID of the game.
     * @param title The title of the game.
     * @param salePrice The sale price of the game.
     * @param normalPrice The normal price of the game.
     * @param isOnSale A flag indicating if the game is on sale.
     * @param savings The savings on the game, if any.
     * @param metacriticScore The Metacritic score of the game.
     * @param steamRatingText The Steam rating text for the game.
     * @param steamRatingPercent The Steam rating percentage for the game.
     * @param steamRatingCount The number of ratings on Steam for the game.
     * @param dealRating The deal rating for the game.
     * @param thumb The thumbnail URL for the game.
     * @param storeName The name of the store where the game is available.
     */
    public void saveToWishlist(String gameID, String title, String salePrice, String normalPrice, String isOnSale,
                               String savings, String metacriticScore, String steamRatingText,
                               String steamRatingPercent, String steamRatingCount, String dealRating, String thumb,
                               String storeName) {

    }

    /**
     * Updates the internal list of games in the wishlist with the provided list.
     *
     * @param games The new list of games to set as the wishlist.
     */
    public void saveWishlist(ArrayList<Game> games) {
        // Update the internal list with the provided games
        this.games = games;
    }

    /**
     * Retrieves the current list of games from the wishlist using the data access layer.
     *
     * @return An ArrayList of Game objects representing the games currently in the wishlist.
     */
    public ArrayList<Game> getGames() {
        this.games = dataAccess.getWishlistGames();
        return games;
    }
}
