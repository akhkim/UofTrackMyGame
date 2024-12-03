
package use_case.game;

/**
 * The GameInteractor class implements the GameInputBoundary interface and handles
 * the business logic related to game interactions, such as adding games to the wishlist
 * and setting price alerts. It delegates the actual data persistence tasks to the
 * GameDataAccessInterface.
 */
public class GameInteractor implements GameInputBoundary {
    private final GameDataAccessInterface dataAccessInterface;

    public GameInteractor(GameDataAccessInterface dataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void addToWishlist(String gameID, String title, String salePrice,
                              String normalPrice, String isOnSale, String savings,
                              String metacriticScore, String steamRatingText,
                              String steamRatingPercent, String steamRatingCount,
                              String dealRating, String thumb, String storeName) {

        dataAccessInterface.saveToWishlist(gameID, title, salePrice, normalPrice, isOnSale, savings, metacriticScore,
                steamRatingText, steamRatingPercent, steamRatingCount, dealRating, thumb, storeName);

    }

    @Override
    public void setPriceAlert(String email, String gameID, String price) {
        dataAccessInterface.setPriceAlert(email, gameID, price);
    }
}
