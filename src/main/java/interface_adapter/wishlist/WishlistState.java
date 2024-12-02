package interface_adapter.wishlist;

import entity.Game;
import java.util.ArrayList;

public class WishlistState {
    private ArrayList<Game> games;

    public WishlistState() {
        this.games = new ArrayList<>();
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}
