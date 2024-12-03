package use_case.recommendation;

import data_access.DataAccess;
import entity.Game;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search.GameSearchDataAccessInterface;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendationInteractorTest {
    Game game;
    RecommendationInputData inputData;

    @BeforeEach
    void init() {
        game = new Game(
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
        inputData = new RecommendationInputData(game);
    }


    @Test
    void successTest() {
        RecommendationOutputBoundary presenter = outputData -> {
            ArrayList<Game> games = outputData.getGames();
            assertEquals(9, games.size());
            for (Game game1 : games) {
                assertEquals(Double.parseDouble(game.getSalePrice()), Double.parseDouble(game1.getSalePrice()));
            }
        };

        GameSearchDataAccessInterface dataAccess = new DataAccess() {
            @Override
            public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, String onSale, String sortBy, String desc) {
                final double[] prices = {2.49, 2.49, 2.49, 2.49, 2.49, 2.49, 2.49, 2.49, 2.49, 2.49, 2.49};
                JSONArray array = new JSONArray();
                for (double price : prices) {
                    if (price <= Double.parseDouble(upperPrice) && price >= Double.parseDouble(lowerPrice)) {
                        JSONObject game = new JSONObject();
                        game.put("salePrice", Double.toString(price));
                        array.put(game);
                    }
                }
                return array.toString(4);
            }
        };

        RecommendationInputBoundary interactor = new RecommendationInteractor(presenter, dataAccess);
        interactor.execute(inputData);
    }


    @Test
    void successIncreaseDeltaTest() {
        RecommendationOutputBoundary presenter = outputData -> {
            ArrayList<Game> games = outputData.getGames();
            assertEquals(9, games.size());
            for (Game game1 : games) {
                assertEquals(Double.parseDouble(game.getSalePrice()), Double.parseDouble(game1.getSalePrice()), 0.02);
            }
        };

        GameSearchDataAccessInterface dataAccess = new DataAccess() {
            @Override
            public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, String onSale, String sortBy, String desc) {
                final double[] prices = {2.49, 2.50, 2.48, 2.48, 2.48, 2.48, 2.49, 2.49, 2.50, 2.49};
                JSONArray array = new JSONArray();
                for (double price : prices) {
                    if (price <= Double.parseDouble(upperPrice) && price >= Double.parseDouble(lowerPrice)) {
                        JSONObject game = new JSONObject();
                        game.put("salePrice", Double.toString(price));
                        array.put(game);
                    }
                }
                return array.toString(4);
            }
        };

        RecommendationInputBoundary interactor = new RecommendationInteractor(presenter, dataAccess);
        interactor.execute(inputData);
    }


    @Test
    void successMaxDeltaTest() {
        RecommendationOutputBoundary presenter = outputData -> {
            ArrayList<Game> games = outputData.getGames();
            assertEquals(2, games.size());
            for (Game game1 : games) {
                assertEquals(Double.parseDouble(game.getSalePrice()), Double.parseDouble(game1.getSalePrice()), 0.02);
            }
        };

        GameSearchDataAccessInterface dataAccess = new DataAccess() {
            @Override
            public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, String onSale, String sortBy, String desc) {
                final double[] prices = {2.49, 2.49, 10.49, 0.00};
                JSONArray array = new JSONArray();
                for (double price : prices) {
                    if (price <= Double.parseDouble(upperPrice) && price >= Double.parseDouble(lowerPrice)) {
                        JSONObject game = new JSONObject();
                        game.put("salePrice", Double.toString(price));
                        array.put(game);
                    }
                }
                return array.toString(4);
            }
        };

        RecommendationInputBoundary interactor = new RecommendationInteractor(presenter, dataAccess);
        interactor.execute(inputData);
    }
}
