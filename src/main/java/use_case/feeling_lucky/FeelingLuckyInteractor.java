package use_case.feeling_lucky;

import entity.Game;
import entity.GameFactory;
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
        GameFactory gameFactory = new GameFactory();
        JSONArray gamesData = feelingLuckyDataAccessInterface.getRandomGames();
        JSONObject gameData = (JSONObject) gamesData.get((int) Math.round(Math.random() * gamesData.length()));
        Game game = gameFactory.create(gameData);
        feelingLuckyOutputBoundary.prepareSuccessView(game);
    }
}
