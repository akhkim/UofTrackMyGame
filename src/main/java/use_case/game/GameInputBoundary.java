package use_case.game;

/**
 * Input Boundary for adding game to wishlist.
 */
public interface GameInputBoundary {
    void addToWishlist(String gameID, String title, String salePrice,
                       String normalPrice, String isOnSale, String savings,
                       String metacriticScore, String steamRatingText,
                       String steamRatingPercent, String steamRatingCount,
                       String dealRating, String thumb, String storeName);
}