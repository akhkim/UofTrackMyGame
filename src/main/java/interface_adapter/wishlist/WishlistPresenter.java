
package interface_adapter.wishlist;

import interface_adapter.ViewManagerModel;
import use_case.wishlist.WishlistOutputBoundary;
import use_case.wishlist.WishlistOutputData;

/**
 * The WishlistPresenter class is responsible for presenting the state of the wishlist to the view layer.
 * It interacts with the `WishlistViewModel` and `ViewManagerModel` to update the view with the relevant data.
 */
public class WishlistPresenter implements WishlistOutputBoundary {
    private final WishlistViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public WishlistPresenter(WishlistViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public WishlistOutputData presentSuccess(String message) {
        viewModel.setMessage(message);
        viewModel.setSuccess(true);
        viewManagerModel.firePropertyChanged();

        viewManagerModel.switchView(viewManagerModel.getViewName());
        return new WishlistOutputData(true, message);
    }

    @Override
    public WishlistOutputData presentError(String message) {
        viewModel.setMessage(message);
        viewModel.setSuccess(false);
        viewManagerModel.firePropertyChanged();

        return new WishlistOutputData(false, message);
    }
}
