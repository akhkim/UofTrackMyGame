package interface_adapter.game;

import use_case.game.GameInputBoundary;

public class GameController {
    private final GameInputBoundary interactor;

    public GameController(GameInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void addToWishlist(String gameID, String title, String salePrice,
                              String normalPrice, String isOnSale, String savings,
                              String metacriticScore, String steamRatingText,
                              String steamRatingPercent, String steamRatingCount,
                              String dealRating, String thumb, String storeName) {

        interactor.addToWishlist(gameID, title, salePrice, normalPrice, isOnSale, savings, metacriticScore,
                steamRatingText, steamRatingPercent, steamRatingCount, dealRating, thumb, storeName);
    }

    public void setPriceAlert(String email, String gameID, String price) {
        interactor.setPriceAlert(email, gameID, price);
    }
}
