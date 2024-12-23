package use_case.wishlist;

import entity.Game;
import java.util.ArrayList;

public class WishlistInteractor implements WishlistInputBoundary {
    private final WishlistDataAccessInterface dataAccess;
    private final WishlistOutputBoundary presenter;

    public WishlistInteractor(WishlistDataAccessInterface dataAccess, WishlistOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;

        // Load games during initialization
        ArrayList<Game> games = dataAccess.loadWishlist();
        if (games.isEmpty()) {
            System.err.println("No games found in the wishlist!");
        } else {
            System.out.println("Loaded " + games.size() + " games into the wishlist.");
        }
    }

    @Override
    public void removeGameFromWishlist(WishlistInputData inputData) {
        String gameID = inputData.getGameID(); // Correct method to get gameID
        dataAccess.removeGameFromWishlist(gameID); // Remove game by ID
        getWishlistGames();
    }

    public void getWishlistGames() {
        try {
            ArrayList<Game> games = dataAccess.loadWishlist();
            System.out.println("Retrieved " + games.size() + " games from the wishlist.");
            presenter.presentSuccess(games);
        }
        catch (Exception e) {
            presenter.presentError("Error loading wishlist: " + e.getMessage());

        }
    }
}
