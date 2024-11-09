package use_case.results;

import entity.Game;
import java.util.List;

public class ResultsOutputData {
    private final List<Game> games;

    public ResultsOutputData(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }
}