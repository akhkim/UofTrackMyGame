package interface_adapter.wishlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistController {
    private WishlistViewModel viewModel;

    public WishlistController(WishlistViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void addGameToWishlist(String gameName, String notifyCriteria) {
        viewModel.addGame(gameName, notifyCriteria);
    }

    public void removeGameFromWishlist(String gameName) {
        viewModel.removeGame(gameName);
    }
}

