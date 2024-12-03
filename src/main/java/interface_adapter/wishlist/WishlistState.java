package interface_adapter.wishlist;

import entity.Game;
import java.util.ArrayList;

public class WishlistState {
    private ArrayList<Game> games = new ArrayList<>();

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

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
