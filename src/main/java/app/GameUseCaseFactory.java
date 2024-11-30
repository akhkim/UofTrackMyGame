package app;

import entity.GameFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import use_case.game.GameInputBoundary;
import use_case.game.GameInteractor;
import use_case.game.GameOutputBoundary;
import use_case.game.GameDataAccessInterface;
import view.GameView;

/**
 * This class contains the static factory function for creating the GameView.
 */
public class GameUseCaseFactory {

    /** Prevent instantiation. */
    private GameUseCaseFactory(){

    }

    /**
     * Factory function for creating the GameView.
     * @param viewManagerModel the ViewManagerModel to inject into the GameView
     * @param gameViewModel the GameViewModel to inject into the GameView
     * @param gameDataAccessObject the GameDataAccessInterface to inject into the GameView
     * @return the
     */
    public static GameView create(
            ViewManagerModel viewManagerModel, GameViewModel gameViewModel,
            GameDataAccessInterface gameDataAccessObject){
        //TODO: need wishlist view model here?
        final GameController gameController = createGameUseCase(viewManagerModel, gameViewModel, gameDataAccessObject);

        return new GameView(gameViewModel,gameController);
    }

    private static GameController createGameUseCase(ViewManagerModel viewManagerModel,
                                                    GameViewModel gameViewModel,
                                                    GameDataAccessInterface gameDataAccessObject){
        final GameOutputBoundary gameOutputBoundary = new GamePresenter(viewManagerModel, gameViewModel);
        final GameFactory gameFactory = new GameFactory();
        final GameInputBoundary gameInteractor = new GameInteractor(
                gameDataAccessObject, gameOutputBoundary, gameFactory);

        return new GameController(gameInteractor);
    }
}
