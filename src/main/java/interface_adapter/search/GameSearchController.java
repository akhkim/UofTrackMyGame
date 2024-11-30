package interface_adapter.search;

import view.GameSearchView;
import use_case.search.GameSearchInputBoundary;
import org.json.JSONObject;

public class GameSearchController {
    private final GameSearchInputBoundary inputBoundary;
    private final GameSearchView view;

    public GameSearchController(GameSearchInputBoundary inputBoundary, GameSearchView view) {
        this.inputBoundary = inputBoundary;
        this.view = view;
        this.view.setController(this);
    }

    public void searchByTitle() {
        String title = view.getTitleField().getText();
        String response = inputBoundary.searchByTitle(title);
        JSONObject jsonResponse = new JSONObject(response);
    }

    public void searchByFilters() {
        String upperPrice = view.getUpperPriceField().getText();
        String lowerPrice = view.getLowerPriceField().getText();
        String metacritic = view.getMetacriticField().getText();
        boolean onSale = view.getOnSaleCheckBox().isSelected();
        String sortBy = (String) view.getSortByComboBox().getSelectedItem();
        boolean desc = view.getDescToggleButton().isSelected();

        String response = inputBoundary.searchByFilters(upperPrice, lowerPrice, metacritic, onSale, sortBy, desc);
        JSONObject jsonResponse = new JSONObject(response);
        // view.displayResponse(jsonResponse.toString());
    }

    public void feelingLucky() {
        // Implement the logic for the "Feeling Lucky" feature
        String response = "Lucky search result";
        view.displayResponse(response);
    }
}
