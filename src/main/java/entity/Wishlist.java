package entity;

import java.util.*;

public class Wishlist {
    private ArrayList<Game> games;
    
    // Private constructor - only accessible through create method
    private Wishlist() {
        this.games = new ArrayList<>();
    }
    
    void addGame(Game game) {
        this.games.add(game);
    }
    
    static Wishlist create() {
        return new Wishlist();
    }
    
    public ArrayList<Game> getGames() {
        return games;
    }

    // Setter for games
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    // Method to remove a game from the wishlist
    public void removeGame(Game game) {
        this.games.remove(game);
    }
}
