package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccess;
import entity.GameSearchState;
import interface_adapter.search.GameSearchController;
import interface_adapter.search.GameSearchPresenter;
import interface_adapter.search.GameSearchViewModel;
import use_case.search.GameSearchInputBoundary;
import use_case.search.GameSearchInteractor;
import view.GameSearchView;
import view.ViewManager;
import interface_adapter.ViewManagerModel;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private GameSearchView gameSearchView;
    private GameSearchViewModel gameSearchViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addGameSearchView() {
        GameSearchState state = new GameSearchState();
        DataAccess dataAccess = new DataAccess();
        GameSearchInputBoundary interactor = new GameSearchInteractor(dataAccess);
        GameSearchPresenter presenter = new GameSearchPresenter(gameSearchView, interactor);
        gameSearchViewModel = new GameSearchViewModel(state, interactor);
        gameSearchView = new GameSearchView(gameSearchViewModel);
        GameSearchController controller = new GameSearchController(gameSearchViewModel, gameSearchView);
        cardPanel.add(gameSearchView, "GameSearchView");
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Game Search Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        viewManagerModel.setState("GameSearchView");
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
