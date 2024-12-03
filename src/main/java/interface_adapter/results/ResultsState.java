
package interface_adapter.results;

import java.util.ArrayList;

import entity.Game;

/**
 * The {@code ResultsState} class represents the state of the results in the application.
 * It holds a list of games and an error message. This class is used to store the data
 * related to the results and allows interaction with the games and error states.
 */
public class ResultsState {
    private ArrayList<Game> games = new ArrayList<>();
    private String error;

    /**
     * Sets the list of games in the results state.
     * This method updates the games list, which holds the game objects related to the results.
     *
     * @param games the list of {@code Game} objects to set in the results state
     */
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    /**
     * Gets the list of games in the results state.
     * This method retrieves the current list of games stored in the results state.
     *
     * @return an {@code ArrayList} of {@code Game} objects representing the games in the results
     */
    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * Gets the error message stored in the results state.
     * This method retrieves the current error message, if any, related to the results.
     *
     * @return a {@code String} representing the error message, or {@code null} if no error exists
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message in the results state.
     * This method allows setting an error message that describes an issue with the results,
     * such as a failure to retrieve game data.
     *
     * @param error the error message to set in the results state
     */
    public void setError(String error) {
        this.error = error;
    }
}
