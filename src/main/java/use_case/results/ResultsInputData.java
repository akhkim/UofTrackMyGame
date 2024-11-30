// JSON TO GAME CLASS HERE, MAKE AN ARRAY OF GAME OBJECTS

package use_case.results;

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
        return this.game;
    }

}
