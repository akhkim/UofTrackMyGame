package use_case.game;

import entity.Game;

public class GameOutputData {
    private final Game game;

    public GameOutputData(Game game) {
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    public String getTitle() {
        return game.getTitle();
    }

    public String getSalePrice() {
        return game.getSalePrice();
    }

    public String getMetacriticScore() {
        return game.getMetacriticScore();
    }

    public String getDealRating() {
        return game.getDealRating();
    }
}
