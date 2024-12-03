package interface_adapter.wishlist;

import use_case.wishlist.WishlistInputBoundary;
import use_case.wishlist.WishlistInteractor;
import entity.Game;
import java.util.ArrayList;

public class WishlistState {
    private ArrayList<Game> games;
    private WishlistInputBoundary dataAccess;

    public WishlistState(WishlistInputBoundary dataAccess) {
        this.games = new ArrayList<>();
        this.dataAccess = dataAccess; // Use the injected dataAccess (could be WishlistInteractor)
    }

    public ArrayList<Game> getGames() {
        this.games = dataAccess.getWishlistGames();
        return games;
    }
}
