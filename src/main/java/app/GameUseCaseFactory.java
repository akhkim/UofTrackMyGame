package app;
import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import use_case.game.GameInteractor;
import interface_adapter.game.GameState;

public class GameUseCaseFactory {
    private final GameState gameState;
    private final GameViewModel gameViewModel;

    public GameUseCaseFactory(GameState gameState) {
        this.gameState = gameState;
        this.gameViewModel = new GameViewModel();
    }

    public GameController createGameController() {
        // Create Presenter
        GamePresenter presenter = new GamePresenter(gameViewModel);

        // Create Interactor
        GameInteractor interactor = new GameInteractor(presenter, gameState);

        // Create Controller
        return new GameController(interactor);
    }

    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }
}