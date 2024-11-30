package use_case.select_game;

import entity.Game;

/**
 * The Input Data for the Results Use Case.
 */
public class ResultsInputData {

    private final Game game;

    public ResultsInputData(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}