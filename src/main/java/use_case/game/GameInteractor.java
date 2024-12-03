package use_case.game;

import use_case.game.GameDataAccessInterface;
import entity.Game;

import java.util.ArrayList;
import java.util.Collections;

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
