package use_case.results;

import entity.Game;

public class ResultsOutputData {
    private final Game game;

    public ResultsOutputData(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}