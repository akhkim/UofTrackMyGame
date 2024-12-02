package use_case.recommendation;

import data_access.DataAccess;
import entity.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendationInteractorTest {

    @Test
    void outputSizeTest() {
        Game game = new Game(
                "220",
                "Fallout: A Post Nuclear Role Playing Game",
                "2.49",
                "9.99",
                "1",
                "75.075075",
                "0",
                "Overwhelmingly Positive",
                "95",
                "13739",
                "3.1",
                "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/38400/capsule_sm_120.jpg?t=1572025441",
                "https://store.steampowered.com"
        );
        final double PRICE = 2.49;

        RecommendationInputData inputData = new RecommendationInputData(game);
        RecommendationOutputBoundary presenter = outputData -> {
            ArrayList<Game> games = outputData.getGames();
            assertEquals(9, games.size());
        };

        RecommendationInputBoundary interactor  = new RecommendationInteractor(presenter, new DataAccess());
        interactor.execute(inputData);
    }

    @Test
    void similarPricesTest() {
        Game game = new Game(
                "220",
                "Fallout: A Post Nuclear Role Playing Game",
                "2.49",
                "9.99",
                "1",
                "75.075075",
                "0",
                "Overwhelmingly Positive",
                "95",
                "13739",
                "3.1",
                "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/38400/capsule_sm_120.jpg?t=1572025441",
                "https://store.steampowered.com"
        );
        final double PRICE = 2.49;
        final double THRESHOLD = 1.0;

        RecommendationInputData inputData = new RecommendationInputData(game);
        RecommendationOutputBoundary presenter = outputData -> {
            ArrayList<Game> games = outputData.getGames();
            for (Game game1 : games) {
                assertTrue(Math.abs(PRICE - Double.parseDouble(game1.getSalePrice())) < THRESHOLD);
            }
        };

        RecommendationInputBoundary interactor  = new RecommendationInteractor(presenter, new DataAccess());
        interactor.execute(inputData);
    }
}
