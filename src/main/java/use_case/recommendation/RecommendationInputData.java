package use_case.recommendation;

import entity.Game;

public class RecommendationInputData {
    Game game;

    public RecommendationInputData(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
