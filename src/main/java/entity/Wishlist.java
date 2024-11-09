package entity;

import java.util.*;

public class Wishlist {
    private String name;
    private ArrayList<Game> games;

    // Constructor
    public Wishlist(String name) {
        this.name = name;
        this.games = new ArrayList<>();
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
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
