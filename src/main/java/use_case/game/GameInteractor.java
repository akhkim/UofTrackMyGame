package use_case.game;

import entity.Game;
import interface_adapter.game.GameState;

public class GameInteractor implements GameInputBoundary {
    private final GameOutputBoundary outputBoundary;

    public GameInteractor(GameOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void fetchGameDetails(GameInputData inputData) {
        // Fetch the game object from GameState using gameID
        Game game = inputData.getGame();

        if (game != null) {
            // Prepare output data
            GameOutputData outputData = new GameOutputData(game);

            outputBoundary.presentGameDetails(outputData);
        }
    }
}
