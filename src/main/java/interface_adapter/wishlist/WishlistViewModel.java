
package interface_adapter.wishlist;

import interface_adapter.ViewModel;

/**
 * The WishlistViewModel class represents the ViewModel for managing the state and presentation of the wishlist.
 * It extends the ViewModel class and provides methods to retrieve game titles from the wishlist,
 * and manage success messages and status.
 */
public class WishlistViewModel extends ViewModel<WishlistState> {

    public WishlistViewModel() {
        super("wishlist");
        setState(new WishlistState());
    }
}
