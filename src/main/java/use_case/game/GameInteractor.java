package use_case.game;

import entity.Game;
import entity.GameFactory;

/**
 * The Game Interactor.
 */
public class GameInteractor implements GameInputBoundary{
    private final GameDataAccessInterface gameDataAccessObject;
    private final GameOutputBoundary gamePresenter;
    private final GameFactory gameFactory;

    public GameInteractor(GameDataAccessInterface gameDataAccessObject,
                          GameOutputBoundary gameOutputBoundary, GameFactory gameFactory) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.gamePresenter = gameOutputBoundary;
        this.gameFactory = gameFactory;
    }

    @Override
    public void gameWindow(GameInputData gameInputData) {
        final Game game = gameFactory.create(gameInputData.getTitle(), gameInputData.getSalePrice(),
                gameInputData.getNormalPrice(), gameInputData.getIsOnSale(), gameInputData.getSavings(),
                gameInputData.getMetacriticScore(), gameInputData.getSteamRatingText(),
                gameInputData.getSteamRatingPercent(), gameInputData.getSteamRatingCount(),
                gameInputData.getDealRating(), gameInputData.getThumb(), gameInputData.getGameID());
        gameDataAccessObject.save(game);

        final GameOutputData gameOutputData = new GameOutputData(game.getTitle());
        gamePresenter.prepareSuccessView(gameOutputData);
    }

    @Override
    public void addToWishlist(GameInputData gameInputData, String email, String thresholdPrice){
        gamePresenter.switchToWishlistView();
    }
}
