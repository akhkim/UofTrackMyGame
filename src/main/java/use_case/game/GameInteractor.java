package use_case.game;

import entity.Game;
import interface_adapter.game.GameState;

public class GameInteractor implements GameInputBoundary {
    private final GameOutputBoundary outputBoundary;
    private final GameState gameState;

    public GameInteractor(GameOutputBoundary outputBoundary, GameState gameState) {
        this.outputBoundary = outputBoundary;
        this.gameState = gameState;
    }

    @Override
    public void fetchGameDetails(GameInputData inputData) {
        // Fetch the game object from GameState using gameID
        Game game = gameState.getGameById(inputData.getGameID());

        if (game != null) {
            // Prepare output data
            GameOutputData outputData = new GameOutputData(
                    game.getTitle(),
                    game.getSalePrice(),
                    game.getMetacriticScore(),
                    game.getDealRating()
            );

            outputBoundary.presentGameDetails(outputData);
        }
    }
}
