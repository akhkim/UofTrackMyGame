
package interface_adapter.wishlist;

import java.util.ArrayList;

import entity.Game;
import interface_adapter.ViewModel;

/**
 * The WishlistViewModel class represents the ViewModel for managing the state and presentation of the wishlist.
 * It extends the ViewModel class and provides methods to retrieve game titles from the wishlist,
 * and manage success messages and status.
 */
public class WishlistViewModel extends ViewModel<WishlistState> {
    private WishlistState state;
    private String message;
    private boolean success;

    public WishlistViewModel(WishlistState state) {
        super("wishlist");
        this.state = state;
    }

    /**
     * Retrieves the list of game titles from the current wishlist state.
     * This method iterates through the games in the wishlist and returns their titles as a list of strings.
     *
     * @return An ArrayList of strings representing the game titles in the wishlist.
     */
    public ArrayList<String> getGameTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Game game : state.getGames()) {
            String title = game.getGameID();
            titles.add(title);
            System.out.println("Game Title: " + title);
        }
        return titles;
    }

    /**
     * Sets a message in the view model. This can be used to display success or error messages.
     *
     * @param message The message to set in the view model.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets the success status in the view model. This can be used to track if the operation was successful.
     *
     * @param success A boolean value indicating whether the operation was successful.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
