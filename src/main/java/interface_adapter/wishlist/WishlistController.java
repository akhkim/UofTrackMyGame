package interface_adapter.wishlist;

import use_case.wishlist.*;

public class WishlistController {
    private final WishlistInputBoundary interactor;

    public WishlistController(WishlistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void getGamesFromWishlist() {
        interactor.getWishlistGames();
    }

    public void removeGame(String gameTitle) {
        WishlistInputData inputData = new WishlistInputData(gameTitle);
        interactor.removeGameFromWishlist(inputData);
    }

}
