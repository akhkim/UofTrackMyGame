package use_case.recommendation;

import entity.Game;
import entity.GameFactory;
import org.json.JSONArray;
import use_case.wishlist.WishlistDataAccessInterface;

import java.util.ArrayList;

public class RecommendationInteractor implements RecommendationInputBoundary {
    private RecommendationDataAccessInterface recommendationDataAccessInterface;
    private RecommendationOutputBoundary recommendationOutputBoundary;

    public RecommendationInteractor(
            RecommendationDataAccessInterface recommendationDataAccessInterface,
            RecommendationOutputBoundary recommendationOutputBoundary
    ) {
        this.recommendationDataAccessInterface = recommendationDataAccessInterface;
        this.recommendationOutputBoundary = recommendationOutputBoundary;
    }

    public void execute() {
        GameFactory gameFactory = new GameFactory();
        ArrayList<Game> wishlistGames = recommendationDataAccessInterface.loadWishlist();

        double sumPrice = 0;
        for (Game game : wishlistGames) {
            sumPrice += Float.parseFloat(game.getNormalPrice());
        }
        double avgPrice = sumPrice / wishlistGames.size();
        double thresholdRange = 0.0;

        JSONArray arrayJSON;
        do {
            String results = recommendationDataAccessInterface.searchByFilters(
                    Double.toString(avgPrice + thresholdRange),
                    Double.toString(avgPrice - thresholdRange),
                    "",
                    "1",
                    "metacriticScore",
                    "1"
            );
            arrayJSON = new JSONArray(results);
            thresholdRange += 0.1;
        } while (arrayJSON.length() < 3);

        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            games.add(gameFactory.create(arrayJSON.getJSONObject(i)));
        }

        RecommendationOutputData recommendationOutputData = new RecommendationOutputData(games);
        recommendationOutputBoundary.prepareSuccessView(recommendationOutputData);
    }

}

