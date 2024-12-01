package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccess;
import interface_adapter.search.GameSearchController;
import interface_adapter.search.GameSearchPresenter;
import interface_adapter.search.GameSearchState;
import interface_adapter.search.GameSearchViewModel;
import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsOutputBoundary;
import use_case.search.GameSearchDataAccessInterface;
import use_case.search.GameSearchInputBoundary; 
import use_case.search.GameSearchInteractor;
import use_case.search.GameSearchOutputBoundary;
import view.GameSearchView;
import view.GameView;
import view.ResultsView;
import view.ViewManager;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsPresenter;
import interface_adapter.results.ResultsState;
import interface_adapter.game.GameViewModel;
import use_case.results.ResultsInteractor;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private GameSearchView gameSearchView;
    private GameSearchViewModel gameSearchViewModel;
    private ResultsViewModel resultsViewModel;
    private GameViewModel gameViewModel = new GameViewModel();
    private ResultsView resultsView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addGameSearchView() {
        GameSearchState searchState = new GameSearchState();
        ResultsState resultsState = new ResultsState();
        gameSearchViewModel = new GameSearchViewModel(searchState);
        resultsViewModel = new ResultsViewModel();

        GameSearchDataAccessInterface gateway = new DataAccess();

        GameSearchOutputBoundary presenter = new GameSearchPresenter(resultsViewModel, viewManagerModel);

        GameSearchInputBoundary interactor = new GameSearchInteractor(gateway, presenter);

        gameSearchView = new GameSearchView(gameSearchViewModel);

        GameSearchController controller = new GameSearchController(gameSearchView, interactor);

        cardPanel.add(gameSearchView, gameSearchView.getViewName());
        return this;
    }

    public AppBuilder addResultsView() {
        resultsView = new ResultsView(resultsViewModel);
        cardPanel.add(resultsView, resultsView.getViewName());
        return this;
    }

    public AppBuilder addResultsUseCase() {
        final ResultsOutputBoundary resultsOutputBoundary = new ResultsPresenter(resultsViewModel, gameViewModel, viewManagerModel);
        final ResultsInputBoundary resultsInteractor = new ResultsInteractor(resultsOutputBoundary);
    
        final ResultsController resultsController = new ResultsController(resultsInteractor);
        resultsView.setResultsController(resultsController);
        return this;
    }

    public AppBuilder addGamesView() {
        gameView = new GameView(gameViewModel);
        cardPanel.add(gameView, gameView.getViewName());
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
