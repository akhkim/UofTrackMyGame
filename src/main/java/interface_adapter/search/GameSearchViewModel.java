
package interface_adapter.search;

import interface_adapter.ViewModel;

/**
 * The {@code GameSearchViewModel} class represents the view model for the game search view.
 * <p>
 * This class holds the state related to game search, including the search criteria
 * such as title, price range, metacritic score, and other filter options. It extends
 * from the {@link ViewModel} class, providing a way to manage and update the state
 * of the game search view.
 * </p>
 */
public class GameSearchViewModel extends ViewModel<GameSearchState> {

    public GameSearchViewModel() {
        super("GameSearchView");
        this.setState(new GameSearchState());
    }
}
