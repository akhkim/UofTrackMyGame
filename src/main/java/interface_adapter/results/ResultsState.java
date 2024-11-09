package interface_adapter.results;

import entity.Game;
import java.util.ArrayList;
import java.util.List;

public class ResultsState {
    private List<Game> games = new ArrayList<>();
    
    public ResultsState(ResultsState copy) {
        this.games = new ArrayList<>(copy.games);
    }

    public ResultsState() {}

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}