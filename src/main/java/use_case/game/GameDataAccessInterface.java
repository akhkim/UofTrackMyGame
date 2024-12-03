
package use_case.game;

/**
 * DAO for the Game Window and Add to Wishlist Use Cases.
 */
public interface GameDataAccessInterface {
    /**
     * Saves a game to the wishlist with all its details.
     * This method allows adding a game to the wishlist by storing its relevant details.
     * The game details include ID, title, sale price, normal price, sale status, savings,
     * Metacritic score, Steam rating text, Steam rating percentage, Steam rating count,
     * deal rating, thumbnail image URL, and store name.
     *
     * @param gameID The unique identifier of the game.
     * @param title The title of the game.
     * @param salePrice The current sale price of the game.
     * @param normalPrice The regular price of the game.
     * @param isOnSale A flag indicating if the game is on sale.
     * @param savings The amount or percentage saved on the game.
     * @param metacriticScore The Metacritic score of the game, if available.
     * @param steamRatingText The text representation of the Steam rating, if available.
     * @param steamRatingPercent The percentage rating on Steam, if available.
     * @param steamRatingCount The number of ratings on Steam, if available.
     * @param dealRating The deal rating, indicating the quality of the sale.
     * @param thumb The thumbnail image URL of the game.
     * @param storeName The name of the store offering the game.
     */
    void saveToWishlist(String gameID, String title, String salePrice,
                       String normalPrice, String isOnSale, String savings,
                       String metacriticScore, String steamRatingText,
                       String steamRatingPercent, String steamRatingCount,
                       String dealRating, String thumb, String storeName);

    /**
     * Sets a price alert for a specific game.
     * This method allows users to set a price alert, notifying them when the price of a game
     * reaches a specified threshold. The user will be notified via the provided email.
     *
     * @param email The email address to send the price alert notification to.
     * @param gameID The unique identifier of the game for which the price alert is set.
     * @param price The target price at which the alert should be triggered.
     */
    void setPriceAlert(String email, String gameID, String price);
}
