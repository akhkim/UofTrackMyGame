package use_case.recommendation;

import java.util.ArrayList;

import org.json.JSONArray;

import entity.Game;
import entity.GameFactory;
import use_case.search.GameSearchDataAccessInterface;

/**
 * The RecommendationInteractor class is responsible for generating game recommendations
 * based on a given input game. It interacts with the game search data access interface
 * and the output boundary to prepare the recommendation results.
 */
public class RecommendationInteractor implements RecommendationInputBoundary {
    private RecommendationOutputBoundary recommendationOutputBoundary;
    private GameSearchDataAccessInterface gameSearchDataAccessInterface;

    public RecommendationInteractor(
            RecommendationOutputBoundary recommendationOutputBoundary,
            GameSearchDataAccessInterface gameSearchDataAccessInterface
    ) {
        this.recommendationOutputBoundary = recommendationOutputBoundary;
        this.gameSearchDataAccessInterface = gameSearchDataAccessInterface;
    }

    /**
     * Executes the recommendation logic. It uses the input data (game) to search for similar games
     * based on price and other parameters. It continues searching until at least 9 recommendations
     * are found or the search reaches a predefined price difference threshold.
     *
     * @param inputData The input data containing the game for which recommendations are to be made.
     */
    public void execute(RecommendationInputData inputData) {
        GameFactory gameFactory = new GameFactory();
        Game game = inputData.getGame();
        double price = Double.parseDouble(game.getSalePrice());
        double delta = 0.0;

        JSONArray arrayJSON;
        do {
            String results = gameSearchDataAccessInterface.searchByFilters(
                    Double.toString(price + delta),
                    Double.toString(price - delta),
                    "",
                    "1",
                    "metacriticScore",
                    "1"
            );
            arrayJSON = new JSONArray(results);
            delta += 0.01;

        } while (arrayJSON.length() < 9 && delta <= 1.0);

        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < Math.min(9, arrayJSON.length()); i++) {
            games.add(gameFactory.create(arrayJSON.getJSONObject(i)));
        }

        RecommendationOutputData recommendationOutputData = new RecommendationOutputData(games);
        recommendationOutputBoundary.prepareSuccessView(recommendationOutputData);
    }

}

