package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;

/**
 * Controller for the Game Window Use Case.
 */
public class GameController {

    private final GameInputBoundary userGameUseCaseInteractor;

    public GameController(GameInputBoundary userGameUseCaseInteractor) {
        this.userGameUseCaseInteractor = userGameUseCaseInteractor;
    }

    /**
     * Executes the open Game Window Use Case.
     */
    public void gameWindow(String title, String salePrice,
                           String normalPrice, String isOnSale, String savings,
                           String metacriticScore, String steamRatingText,
                           String steamRatingPercent, String steamRatingCount,
                           String dealRating, String thumb, String gameID) {
        final GameInputData gameInputData = new GameInputData(title, salePrice, normalPrice, isOnSale, savings,
                metacriticScore, steamRatingText, steamRatingPercent, steamRatingCount, dealRating, thumb, gameID);
        userGameUseCaseInteractor.gameWindow(gameInputData);
    }

    /**
     * Executes the add to Wishlist Use Case.
     */
    public void addToWishlist(String title, String salePrice,
                              String normalPrice, String isOnSale, String savings,
                              String metacriticScore, String steamRatingText,
                              String steamRatingPercent, String steamRatingCount,
                              String dealRating, String thumb, String gameID,
                              String email, String thresholdPrice) {
        final GameInputData gameInputData = new GameInputData(title, salePrice, normalPrice, isOnSale, savings,
                metacriticScore, steamRatingText, steamRatingPercent, steamRatingCount, dealRating, thumb, gameID);
        userGameUseCaseInteractor.addToWishlist(gameInputData, email, thresholdPrice);
    }

}
