
package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.GameSearchInputBoundary;
import view.GameSearchView;

/**
 * The {@code GameSearchController} class is responsible for handling user interactions
 * in the game search feature. It manages the communication between the view layer
 * (represented by {@code GameSearchView}) and the use case layer (represented by
 * {@code GameSearchInputBoundary}).
 * The controller handles search requests based on either game title or filters,
 * as well as navigation to the wishlist view.
 */
public class GameSearchController {
    private final GameSearchView view;
    private final GameSearchInputBoundary interactor;

    public GameSearchController(GameSearchView view, GameSearchInputBoundary interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    /**
     * Initiates a game search based on the title provided in the view's title field.
     */
    public void searchByTitle() {
        String title = view.getTitleField().getText();
        interactor.searchByTitle(title);
    }

    /**
     * Initiates a game search based on the filters provided in the view.
     * The filters include price range, Metacritic score, sale status, sorting option,
     * and descending order toggle.
     */
    public void searchByFilters() {
        interactor.searchByFilters(
            view.getUpperPriceField().getText(),
            view.getLowerPriceField().getText(),
            view.getMetacriticField().getText(),
            view.getOnSaleCheckBox().isSelected(),
            (String) view.getSortByComboBox().getSelectedItem(),
            view.getDescToggleButton().isSelected()
        );
    }

}
