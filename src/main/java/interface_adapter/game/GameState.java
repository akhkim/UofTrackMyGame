package interface_adapter.game;

import entity.Game;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private final Map<String, Game> games = new HashMap<>();
    private Game game;
    private String error;

    public void addGame(Game game) {
        games.put(game.getGameID(), game);
    }

    public Game getGameById(String gameID) {
        return games.get(gameID);
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    public String getError(){
        return error;
    }

    public void setError(String error){
        this.error = error;
    }

}
