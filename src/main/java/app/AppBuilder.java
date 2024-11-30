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
import use_case.search.GameSearchDataAccessInterface;
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
        GameSearchDataAccessInterface gateway = new DataAccess();
        GameSearchInputBoundary interactor = new GameSearchInteractor(gateway);
        GameSearchPresenter presenter = new GameSearchPresenter(null, null);
        gameSearchViewModel = new GameSearchViewModel(state, interactor, presenter);
        gameSearchView = new GameSearchView(gameSearchViewModel);
        presenter = new GameSearchPresenter(gameSearchView, gameSearchViewModel);
        gameSearchViewModel.setPresenter(presenter);
        GameSearchController controller = new GameSearchController(interactor, gameSearchView);
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
