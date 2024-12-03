
package interface_adapter.wishlist;

import entity.Game;
import interface_adapter.ViewManagerModel;
import use_case.wishlist.*;

import java.util.ArrayList;

/**
 * The WishlistPresenter class is responsible for presenting the state of the wishlist to the view layer.
 * It interacts with the `WishlistViewModel` and `ViewManagerModel` to update the view with the relevant data.
 */
public class WishlistPresenter implements WishlistOutputBoundary {
    private final WishlistViewModel wishlistViewModel;
    private final ViewManagerModel viewManagerModel;

    public WishlistPresenter(WishlistViewModel wishlistViewModel, ViewManagerModel viewManagerModel) {
        this.wishlistViewModel = wishlistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentSuccess(ArrayList<Game> games) {
        WishlistState wishlistState = new WishlistState();
        wishlistState.setGames(games);
        wishlistViewModel.setState(wishlistState);
        wishlistViewModel.firePropertyChanged();

        viewManagerModel.switchView(wishlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public WishlistOutputData presentError(String message) {
        viewManagerModel.firePropertyChanged();

        return new WishlistOutputData(false, message);
    }
}
