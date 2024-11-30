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

    public ArrayList<String> getGameTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Game game : state.getGames()) {
            titles.add(game.getTitle());
        }
        return titles;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
