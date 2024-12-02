package interface_adapter.recommendation;

import entity.Game;

import java.util.ArrayList;

public class RecommendationState {
    private ArrayList<Game> games = new ArrayList<>();

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}
