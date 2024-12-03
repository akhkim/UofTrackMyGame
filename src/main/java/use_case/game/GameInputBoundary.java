
package use_case.game;

/**
 * Interface for Game Input Boundary.
 */
public interface GameInputBoundary {
    /**
     * Adds a game to the wishlist with all its details.
     * This method allows the addition of a game to the wishlist by storing its relevant details,
     * such as the game's ID, title, prices, sale status, ratings, and other necessary information.
     *
     * @param gameID The unique identifier of the game.
     * @param title The title of the game.
     * @param salePrice The current sale price of the game.
     * @param normalPrice The regular price of the game.
     * @param isOnSale A flag indicating whether the game is on sale or not.
     * @param savings The amount or percentage saved on the game.
     * @param metacriticScore The Metacritic score of the game, if available.
     * @param steamRatingText The text representation of the Steam rating, if available.
     * @param steamRatingPercent The percentage rating on Steam, if available.
     * @param steamRatingCount The number of ratings on Steam, if available.
     * @param dealRating The rating or score indicating the quality of the deal.
     * @param thumb The URL of the thumbnail image of the game.
     * @param storeName The name of the store offering the game.
     */
    void addToWishlist(String gameID, String title, String salePrice,
                       String normalPrice, String isOnSale, String savings,
                       String metacriticScore, String steamRatingText,
                       String steamRatingPercent, String steamRatingCount,
                       String dealRating, String thumb, String storeName);

    /**
     * Sets a price alert for a specific game.
     * This method allows users to set a price alert, notifying them when the price of a specific
     * game reaches a certain threshold. The user will be notified via the provided email address
     * when the game's price meets the desired value.
     *
     * @param email The email address to send the price alert notification to.
     * @param gameID The unique identifier of the game for which the price alert is set.
     * @param price The target price at which the alert should be triggered.
     */
    void setPriceAlert(String email, String gameID, String price);
}