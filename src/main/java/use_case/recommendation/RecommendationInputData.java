package use_case.recommendation;

import entity.Game;

/**
 * The RecommendationInputData class encapsulates the data required for generating recommendations.
 * It holds the game object that serves as the basis for recommendation logic.
 */
public class RecommendationInputData {
    private Game game;

    public RecommendationInputData(Game game) {
        this.game = game;
    }

    /**
     * Returns the game associated with the recommendation input data.
     *
     * @return The game object.
     */
    public Game getGame() {
        return game;
    }
}
