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
    public WishlistOutputData removeGameFromWishlist(WishlistInputData inputData) {

        String gameID = inputData.getGameID();
        dataAccess.removeGameFromWishlist(gameID);
        return presenter.presentSuccess("Game removed: " + gameID);

        }

    public ArrayList<Game> getWishlistGames() {
        try {
            ArrayList<Game> games = dataAccess.loadWishlist();
            System.out.println("Retrieved " + games.size() + " games from the wishlist.");
            return games;
        }
        catch (Exception e) {
            presenter.presentError("Error loading wishlist: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
