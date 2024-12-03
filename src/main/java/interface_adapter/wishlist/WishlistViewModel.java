package interface_adapter.wishlist;

import entity.Game;
import interface_adapter.ViewModel;
import java.util.ArrayList;

public class WishlistViewModel extends ViewModel<WishlistState> {
    private WishlistState state;
    private String message;
    private boolean success;

    public WishlistViewModel(WishlistState state) {
        super("wishlist");
        this.state = state;
    }

    public ArrayList<Game> getGames() {
        return new ArrayList<>(state.getGames()); // Assuming state.getGames() returns a collection of Game objects
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
