package use_case.game;

import entity.Game;

/**
 * DAO for the Game Window and Add to Wishlist Use Cases.
 */
public interface GameDataAccessInterface {
    /**
     * Saves a game to the wishlist with all its details.
     */
    void saveToWishlist(String gameID, String title, String salePrice,
                       String normalPrice, String isOnSale, String savings,
                       String metacriticScore, String steamRatingText,
                       String steamRatingPercent, String steamRatingCount,
                       String dealRating, String thumb, String storeName);

    /**
     * Sets a price alert for a specific game.
     */
    void setPriceAlert(String email, String gameID, String price);
}
