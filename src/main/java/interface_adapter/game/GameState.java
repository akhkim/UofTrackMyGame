package interface_adapter.game;

import entity.Game;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private final Map<String, Game> games = new HashMap<>();

    public void addGame(Game game) {
        games.put(game.getGameID(), game);
    }

    public Game getGameById(String gameID) {
        return games.get(gameID);
    }
}