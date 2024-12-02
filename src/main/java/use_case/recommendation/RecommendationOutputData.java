package use_case.recommendation;

import entity.Game;

import java.util.ArrayList;

public class RecommendationOutputData {
    private ArrayList<Game> games;

    public RecommendationOutputData(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}