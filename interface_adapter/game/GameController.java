package interface_adapter.game;

import entity.Game;
import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;

public class GameController {
    private final GameInputBoundary interactor;

    public GameController(GameInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void showGameDetails(Game game) {
        GameInputData inputData = new GameInputData(game);
        interactor.fetchGameDetails(inputData);
    }
}
