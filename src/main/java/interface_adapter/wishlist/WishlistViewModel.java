package interface_adapter.wishlist;

import entity.Game;
import interface_adapter.ViewModel;
import java.util.ArrayList;

public class WishlistViewModel extends ViewModel<WishlistState> {

    public WishlistViewModel() {
        super("wishlist");
        setState(new WishlistState());
    }
}
