
package interface_adapter.wishlist;

import use_case.wishlist.WishlistInputBoundary;
import use_case.wishlist.WishlistInputData;

/**
 * The WishlistController class is responsible for handling the removal of games from a user's wishlist.
 * It acts as an intermediary between the user input and the interactor layer, delegating the removal
 * of games to the corresponding use case logic.
 */
public class WishlistController {
    private final WishlistInputBoundary interactor;

    public WishlistController(WishlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void getGamesFromWishlist() {
        interactor.getWishlistGames();
    }

    /**
     * Removes a game from the user's wishlist by invoking the remove game use case logic.
     *
     * @param gameTitle The title of the game to be removed from the wishlist.
     */
    public void removeGame(String gameTitle) {
        WishlistInputData inputData = new WishlistInputData(gameTitle);
        interactor.removeGameFromWishlist(inputData);
    }

}
