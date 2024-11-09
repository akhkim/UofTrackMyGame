package interface_adapter.wishlist;
import entity.Wishlist;

public class WishlistPresenter {
    private WishlistViewModel viewModel;

    public WishlistPresenter(WishlistViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public String formatNotification(String criteria) {
        return "Notify when " + criteria;
    }
}
