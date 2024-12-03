package use_case.recommendation;

import java.util.ArrayList;

import entity.Game;

/**
 * The RecommendationOutputData class encapsulates the data representing
 * the recommended games, which is passed between layers in the recommendation process.
 * This class is used to store the list of recommended games that will be presented to the user.
 */
public class RecommendationOutputData {
    private ArrayList<Game> games;

    public RecommendationOutputData(ArrayList<Game> games) {
        this.games = games;
    }

    /**
     * Gets the list of recommended games.
     *
     * @return An ArrayList containing the recommended games.
     */
    public ArrayList<Game> getGames() {
        return games;
    }
}