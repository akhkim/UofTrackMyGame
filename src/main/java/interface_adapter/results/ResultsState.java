package interface_adapter.results;

import entity.Game;
import java.util.ArrayList;

public class ResultsState {
    private ArrayList<Game> games = new ArrayList<>();
    private Game selectedGame = null;
    private String error = null;

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public Game getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(Game game) {
        this.selectedGame = game;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}