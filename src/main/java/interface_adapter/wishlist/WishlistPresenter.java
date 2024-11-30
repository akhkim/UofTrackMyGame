package interface_adapter.wishlist;

import use_case.wishlist.*;

public class WishlistPresenter implements WishlistOutputBoundary {
    private final WishlistViewModel viewModel;

    public WishlistPresenter(WishlistViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public WishlistOutputData presentSuccess(String message) {
        viewModel.setMessage(message);
        viewModel.setSuccess(true);
        return new WishlistOutputData(true, message);
    }

    @Override
    public WishlistOutputData presentError(String message) {
        viewModel.setMessage(message);
        viewModel.setSuccess(false);
        return new WishlistOutputData(false, message);
    }
}
