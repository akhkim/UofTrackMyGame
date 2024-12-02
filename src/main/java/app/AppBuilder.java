package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccess;
import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.search.GameSearchController;
import interface_adapter.search.GameSearchPresenter;
import interface_adapter.search.GameSearchViewModel;
import use_case.game.GameInputBoundary;
import use_case.game.GameInteractor;
import use_case.game.GameOutputBoundary;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsOutputBoundary;
import use_case.search.GameSearchInputBoundary;
import use_case.search.GameSearchInteractor;
import use_case.search.GameSearchOutputBoundary;
import view.GameSearchView;
import view.GameView;
import view.ResultsView;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsPresenter;
import interface_adapter.game.GameViewModel;
import use_case.results.ResultsInteractor;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
   
    private GameSearchView gameSearchView;
    private GameSearchViewModel gameSearchViewModel;
    private ResultsView resultsView;
    private ResultsViewModel resultsViewModel;
    private GameView gameView;
    private GameViewModel gameViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addGameSearchView() {
        gameSearchViewModel = new GameSearchViewModel();
        gameSearchView = new GameSearchView(gameSearchViewModel);
        cardPanel.add(gameSearchView, gameSearchView.getViewName());
        return this;
    }

    public AppBuilder addResultsView() {
        resultsViewModel = new ResultsViewModel();
        resultsView = new ResultsView(resultsViewModel);
        cardPanel.add(resultsView, resultsView.getViewName());
        return this;
    }

    public AppBuilder addGameView() {
        gameViewModel = new GameViewModel();
        gameView = new GameView(gameViewModel);
        cardPanel.add(gameView, gameView.getViewName());
        return this;
    }

    public AppBuilder addGameSearchUseCase() {
        GameSearchOutputBoundary gameSearchPresenter = new GameSearchPresenter(
                resultsViewModel,
                viewManagerModel
        );
        GameSearchInputBoundary gameSearchInteractor = new GameSearchInteractor(
                new DataAccess(),
                gameSearchPresenter
        );
        GameSearchController controller = new GameSearchController(
                gameSearchView,
                gameSearchInteractor,
                viewManagerModel
        );
        gameSearchView.setController(controller);
        return this;
    }

    public AppBuilder addResultsUseCase() {
        final ResultsOutputBoundary resultsPresenter = new ResultsPresenter(resultsViewModel, gameViewModel, viewManagerModel);
        final ResultsInputBoundary resultsInteractor = new ResultsInteractor(resultsPresenter);
        final ResultsController resultsController = new ResultsController(resultsInteractor);
        resultsView.setResultsController(resultsController);
        return this;
    }

    public AppBuilder addHomeUseCase(){
        HomeOutputBoundary homePresenter = new HomePresenter(viewManagerModel);
        HomeInputBoundary homeInteractor = new HomeInteractor(homePresenter);
        HomeController homeController = new HomeController(homeInteractor);
        resultsView.setHomeController(homeController);
        return this;
    }

    public AppBuilder addGameUseCase(){
        GameOutputBoundary gamePresenter = new GamePresenter(gameViewModel, viewManagerModel);
        GameInputBoundary gameInteractor = new GameInteractor(gamePresenter);
        GameController gameController = new GameController(gameInteractor);
        gameView.setController(gameController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("UofTrackMyGame");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        viewManagerModel.setState("GameSearchView");
        viewManagerModel.firePropertyChanged();

        return application;
    }
}