
package interface_adapter.game;

import java.util.HashMap;
import java.util.Map;

import entity.Game;

/**
 * The {@code GameState} class manages the state of the game-related data.
 * <p>
 * This class provides methods to store and retrieve {@code Game} objects, set the current game, and handle error messages.
 * It maintains a collection of games indexed by their unique game IDs, along with a reference to a single game and an error message.
 * </p>
 */
public class GameState {
    private final Map<String, Game> games = new HashMap<>();
    private Game game;
    private String error;

    /**
     * Adds a game to the collection of games.
     * <p>
     * The game is stored in the {@code games} map using its unique game ID as the key.
     * </p>
     *
     * @param gameToAdd the {@code Game} object to be added to the collection
     */
    public void addGame(Game gameToAdd) {
        games.put(gameToAdd.getGameID(), gameToAdd);
    }

    /**
     * Retrieves a game from the collection based on its game ID.
     * <p>
     * This method looks up the game in the {@code games} map using the provided game ID.
     * </p>
     *
     * @param gameID the unique identifier for the game
     * @return the {@code Game} object corresponding to the provided game ID, or {@code null} if not found
     */
    public Game getGameById(String gameID) {
        return games.get(gameID);
    }

    /**
     * Sets the current game.
     * <p>
     * This method updates the {@code game} field to the specified {@code Game} object.
     * </p>
     *
     * @param game the {@code Game} object to set as the current game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Retrieves the current game.
     * <p>
     * This method returns the {@code Game} object that is currently set as the active game.
     * </p>
     *
     * @return the current {@code Game} object, or {@code null} if no game is set
     */
    public Game getGame() {
        return game;
    }

    /**
     * Retrieves the error message.
     * <p>
     * This method returns the error message stored in the {@code error} field.
     * </p>
     *
     * @return the error message, or {@code null} if no error is set
     */
    public String getError() {
        return error;
    }

    /**
     * Sets an error message.
     * <p>
     * This method sets the {@code error} field to the provided error message.
     * </p>
     *
     * @param error the error message to be stored
     */
    public void setError(String error) {
        this.error = error;
    }

}