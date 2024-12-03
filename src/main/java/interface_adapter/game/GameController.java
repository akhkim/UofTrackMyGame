
package interface_adapter.game;

import use_case.game.GameInputBoundary;

/**
 * The {@code GameController} class serves as an intermediary between the user interface and the underlying business logic.
 * <p>
 * This class handles user requests related to managing games, including adding games to a wishlist and setting price alerts.
 * It delegates the actual logic to an instance of {@code GameInputBoundary}.
 * </p>
 */
public class GameController {
    private final GameInputBoundary interactor;

    public GameController(GameInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Adds a game to the user's wishlist.
     * <p>
     * This method invokes the corresponding method in the {@code GameInputBoundary} to add a game to the wishlist.
     * </p>
     *
     * @param gameID            the unique identifier for the game
     * @param title            the title of the game
     * @param salePrice        the sale price of the game
     * @param normalPrice      the normal price of the game
     * @param isOnSale         indicates if the game is on sale
     * @param savings          the amount saved on the game due to the sale
     * @param metacriticScore  the Metacritic score for the game
     * @param steamRatingText  the textual rating of the game on Steam
     * @param steamRatingPercent the percentage of positive ratings on Steam
     * @param steamRatingCount the number of ratings the game has on Steam
     * @param dealRating       the deal rating of the game
     * @param thumb            the URL of the game's thumbnail
     * @param storeName        the name of the store where the game is available
     */
    public void addToWishlist(String gameID, String title, String salePrice,
                              String normalPrice, String isOnSale, String savings,
                              String metacriticScore, String steamRatingText,
                              String steamRatingPercent, String steamRatingCount,
                              String dealRating, String thumb, String storeName) {

        interactor.addToWishlist(gameID, title, salePrice, normalPrice, isOnSale, savings, metacriticScore,
                steamRatingText, steamRatingPercent, steamRatingCount, dealRating, thumb, storeName);
    }

    /**
     * Sets a price alert for a specific game.
     * <p>
     * This method allows the user to receive a notification when the price of a specified game drops to a desired value.
     * It invokes the corresponding method in the {@code GameInputBoundary} to handle the alert logic.
     * </p>
     *
     * @param email    the email address to send the price alert to
     * @param gameID   the unique identifier of the game to track
     * @param price    the price threshold that triggers the alert
     */
    public void setPriceAlert(String email, String gameID, String price) {
        interactor.setPriceAlert(email, gameID, price);
    }
}
