package use_case.results;

import entity.Game;

/**
 * The ResultsOutputData class is used to encapsulate the results of a game search.
 * This class serves as a data transfer object (DTO) that holds the game details
 * that will be presented to the user in the results view.
 */
public class ResultsOutputData {
    private final Game game;

    public ResultsOutputData(Game game) {
        this.game = game;
    }

    /**
     * Retrieves the game associated with this result.
     *
     * @return The game object containing the details of the game.
     */
    public Game getGame() {
        return game;
    }
}