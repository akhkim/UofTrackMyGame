package use_case.feeling_lucky;

import entity.Game;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeelingLuckyInteractor implements FeelingLuckyInputBoundary{
    private final FeelingLuckyDataAccessInterface feelingLuckyDataAccessInterface;
    private final FeelingLuckyOutputBoundary feelingLuckyOutputBoundary;

    public FeelingLuckyInteractor(
        FeelingLuckyDataAccessInterface feelingLuckyDataAccessInterface,
        FeelingLuckyOutputBoundary feelingLuckyOutputBoundary
    ) {
        this.feelingLuckyDataAccessInterface = feelingLuckyDataAccessInterface;
        this.feelingLuckyOutputBoundary = feelingLuckyOutputBoundary;
    }

    public void execute() {
        JSONArray gamesData = feelingLuckyDataAccessInterface.getRandomGames();
        JSONObject gameData = (JSONObject) gamesData.get((int) Math.round(Math.random() * gamesData.length()));
        Game game = new Game(
                (String) gameData.get("title"),
                (String) gameData.get("salePrice"),
                (String) gameData.get("normalPrice"),
                (String) gameData.get("isOnSale"),
                (String) gameData.get("savings"),
                (String) gameData.get("metacriticScore"),
                (String) gameData.get("steamRatingText"),
                (String) gameData.get("steamRatingPercent"),
                (String) gameData.get("steamRatingCount"),
                (String) gameData.get("dealRating"),
                (String) gameData.get("thumb")
        );
        feelingLuckyOutputBoundary.prepareSuccessView(game);
    }
}
