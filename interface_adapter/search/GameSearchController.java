package interface_adapter.search;

import view.GameSearchView;
import use_case.search.GameSearchInputBoundary;
import interface_adapter.ViewManagerModel;

public class GameSearchController {
    private final GameSearchView view;
    private final GameSearchInputBoundary interactor;
    private final ViewManagerModel viewManagerModel;

    public GameSearchController(GameSearchView view, GameSearchInputBoundary interactor, ViewManagerModel viewManagerModel) {
        this.view = view;
        this.interactor = interactor;
        this.viewManagerModel = viewManagerModel;
    }

    public void searchByTitle() {
        String title = view.getTitleField().getText();
        interactor.searchByTitle(title);
    }

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

    public void goToWishlist() {
        viewManagerModel.switchView("wishlist");
    }
}
