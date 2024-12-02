package use_case.game;

import entity.Game;

public class GameInputData {
    private final Game game;

    public GameInputData(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
