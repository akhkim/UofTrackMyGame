package interface_adapter.results;

import entity.Game;
import java.util.ArrayList;

public class ResultsState {
    private ArrayList<Game> games = new ArrayList<>();
    private String error;

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
