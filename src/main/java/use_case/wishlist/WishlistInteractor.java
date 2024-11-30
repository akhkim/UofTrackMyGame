package use_case.wishlist;

import entity.Game;
import entity.Wishlist;
import java.util.ArrayList;

public class WishlistInteractor implements WishlistInputBoundary {
    private final WishlistDataAccessInterface dataAccess;
    private final WishlistOutputBoundary presenter;
    private Wishlist wishlist;

    public WishlistInteractor(WishlistDataAccessInterface dataAccess, WishlistOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.wishlist = Wishlist.create();
        loadWishlistFromDataAccess();
    }

    @Override
    public WishlistOutputData addGameToWishlist(WishlistInputData inputData) {
        String gameTitle = inputData.getGameTitle();
        Game game = new Game(gameTitle, "", "", "", "", "", "", "", "", "", "");
        wishlist.addGame(game);

        // Save the updated wishlist to the data layer
        dataAccess.saveWishlist(wishlist.getGames());

        // Return output data via the presenter
        return presenter.presentSuccess("Game added: " + gameTitle);
    }

    @Override
    public WishlistOutputData removeGameFromWishlist(WishlistInputData inputData) {
        String gameTitle = inputData.getGameTitle();
        Game gameToRemove = null;

        // Find the game in the wishlist
        for (Game game : wishlist.getGames()) {
            if (game.getTitle().equals(gameTitle)) {
                gameToRemove = game;
                break;
            }
        }

        if (gameToRemove != null) {
            wishlist.removeGame(gameToRemove);
            dataAccess.saveWishlist(wishlist.getGames());
            return presenter.presentSuccess("Game removed: " + gameTitle);
        }

        return presenter.presentError("Game not found: " + gameTitle);
    }

    private void loadWishlistFromDataAccess() {
        ArrayList<Game> savedGames = dataAccess.loadWishlist();
        if (savedGames != null) {
            wishlist.setGames(savedGames);
        }
    }
}
