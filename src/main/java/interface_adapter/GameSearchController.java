package interface_adapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameSearchView;

public class GameSearchController {
    private GameSearchViewModel viewModel;
    private GameSearchView view;

    public GameSearchController(GameSearchViewModel viewModel, GameSearchView view) {
        this.viewModel = viewModel;
        this.view = view;
        setupListeners();
    }

    private void setupListeners() {
        view.getSearchByTitleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.updateTitle(view.getTitleField().getText());
                String response = viewModel.searchByTitle();
                view.displayResponse(response);
            }
        });

        view.getSearchByFiltersButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.updateUpperPrice(view.getUpperPriceField().getText());
                viewModel.updateLowerPrice(view.getLowerPriceField().getText());
                viewModel.updateMetacritic(view.getMetacriticField().getText());
                viewModel.updateOnSale(view.getOnSaleCheckBox().isSelected());
                viewModel.updateSortBy((String) view.getSortByComboBox().getSelectedItem());
                viewModel.updateDesc(view.getDescToggleButton().isSelected());

                String response = viewModel.searchByFilters();
                view.displayResponse(response);
            }
        });
    }
}
