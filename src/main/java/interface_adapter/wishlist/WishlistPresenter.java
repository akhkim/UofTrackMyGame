package interface_adapter.wishlist;

import interface_adapter.ViewManagerModel;
import use_case.wishlist.*;

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
