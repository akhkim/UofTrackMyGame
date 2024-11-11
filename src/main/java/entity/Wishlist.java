package entity;

import java.util.*;

public class Wishlist {
    private ArrayList<Game> games;

    // Constructor
    public Wishlist() {
        this.games = new ArrayList<>();
    }

    // Getter for games
    public ArrayList<Game> getGames() {
        return games;
    }

    // Setter for games
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    // Method to add a game to the wishlist
    public void addGame(Game game) {
        this.games.add(game);
    }

    // Method to remove a game from the wishlist
    public void removeGame(Game game) {
        this.games.remove(game);
    }
}
