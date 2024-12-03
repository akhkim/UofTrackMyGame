package interface_adapter.wishlist;

import java.util.ArrayList;

import entity.Game;

/**
 * The WishlistState class manages the state of the wishlist, including loading and saving game data.
 * It interacts with a WishlistInputBoundary to access and manipulate the wishlist data.
 */
public class WishlistState {
    private ArrayList<Game> games = new ArrayList<>();

    /**
     * Set games.
     * @param games the games.
     */
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    /**
     * Retrieves the current list of games from the wishlist using the data access layer.
     *
     * @return An ArrayList of Game objects representing the games currently in the wishlist.
     */
    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * Get game titles.
     * @return game titles.
     */
    public ArrayList<String> getGameTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Game game : games) {
            String title = game.getGameID();
            titles.add(title);
            System.out.println("Game Title: " + title);  // Debugging line to print titles
        }
        return titles;
    }
}
