package interface_adapter.search;

import view.GameSearchView;

public class GameSearchController {
    private final GameSearchViewModel viewModel;
    private final GameSearchView view;

    public GameSearchController(GameSearchViewModel viewModel, GameSearchView view) {
        this.viewModel = viewModel;
        this.view = view;
        this.view.setController(this);
    }

    public void searchByTitle() {
        viewModel.updateTitle(view.getTitleField().getText());
        String response = viewModel.searchByTitle();
        view.displayResponse(response);
    }

    public void searchByFilters() {
        viewModel.updateUpperPrice(view.getUpperPriceField().getText());
        viewModel.updateLowerPrice(view.getLowerPriceField().getText());
        viewModel.updateMetacritic(view.getMetacriticField().getText());
        viewModel.updateOnSale(view.getOnSaleCheckBox().isSelected());
        viewModel.updateSortBy((String) view.getSortByComboBox().getSelectedItem());
        viewModel.updateDesc(view.getDescToggleButton().isSelected());
        
        viewModel.searchByFilters();
    }

    public void feelingLucky() {
        // Implement the logic for the "Feeling Lucky" feature
        String response = viewModel.feelingLucky();
        view.displayResponse(response);
    }
}
