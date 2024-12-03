package use_case.recommendation;

import entity.Game;
import entity.GameFactory;
import org.json.JSONArray;
import use_case.search.GameSearchDataAccessInterface;

import java.util.ArrayList;

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

