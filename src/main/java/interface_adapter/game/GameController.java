package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;

public class GameController {
    private final GameInputBoundary interactor;

    public GameController(GameInputBoundary interactor) {
        this.interactor = interactor;
    }

    // Method to show game details
    public void showGameDetails(String gameID) {
        GameInputData inputData = new GameInputData(gameID);
        interactor.fetchGameDetails(inputData);
    }
}
