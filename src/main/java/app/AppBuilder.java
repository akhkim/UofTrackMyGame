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
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationPresenter;
import interface_adapter.search.GameSearchController;
import interface_adapter.search.GameSearchPresenter;
import interface_adapter.search.GameSearchViewModel;
import interface_adapter.wishlist.WishlistController;
import interface_adapter.wishlist.WishlistPresenter;
import interface_adapter.wishlist.WishlistState;
import interface_adapter.wishlist.WishlistViewModel;
import use_case.game.GameInputBoundary;
import use_case.game.GameInteractor;
import use_case.game.GameOutputBoundary;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInteractor;
import use_case.recommendation.RecommendationOutputBoundary;
import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsOutputBoundary;
import use_case.search.GameSearchInputBoundary;
import use_case.search.GameSearchInteractor;
import use_case.search.GameSearchOutputBoundary;
import use_case.wishlist.WishlistDataAccessInterface;
import use_case.wishlist.WishlistInputBoundary;
import use_case.wishlist.WishlistInteractor;
import use_case.wishlist.WishlistOutputBoundary;
import view.*;
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

    // DO NOT DELETE viewManager – it listens to property change events fired by viewManagerModel
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
   
    private GameSearchView gameSearchView;
    private GameSearchViewModel gameSearchViewModel;
    private ResultsView resultsView;
    private ResultsViewModel resultsViewModel;
    private GameView gameView;
    private GameViewModel gameViewModel;
    private WishlistView wishlistView;
    private WishlistViewModel wishlistViewModel;
    private WishlistState wishlistState;


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

    public AppBuilder addWishlistView() {
        // Initialize the ViewManagerModel (no dependency issues here)
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Step 1: Initialize WishlistState with the temporary interactor (we won't use the presenter yet)
        WishlistInputBoundary interactor = new WishlistInteractor(new DataAccess(), null);  // Presenter is set to null for now
        WishlistState wishlistState = new WishlistState(interactor);  // Initialize WishlistState

        // Step 2: Initialize WishlistViewModel with WishlistState
        WishlistViewModel wishlistViewModel = new WishlistViewModel(wishlistState);  // Now we can initialize WishlistViewModel

        // Step 3: Now that WishlistViewModel is available, initialize WishlistPresenter with the ViewModel
        WishlistPresenter presenter = new WishlistPresenter(wishlistViewModel, viewManagerModel);

        // Step 4: Update the interactor to use the presenter (set it properly now that it's initialized)
        interactor = new WishlistInteractor(new DataAccess(), presenter);  // Set the real presenter here

        // Step 5: Initialize WishlistController with the correct interactor
        WishlistController controller = new WishlistController(interactor);

        // Step 6: Create the WishlistView with the initialized WishlistViewModel and WishlistController
        wishlistView = new WishlistView(wishlistViewModel, controller);

        // Step 7: Add the wishlist view to the card panel
        cardPanel.add(wishlistView, wishlistViewModel.getViewName());

        return this; // Return the AppBuilder instance for method chaining
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
        gameView.setGameController(gameController);
        return this;
    }

    public AppBuilder addRecommendationUseCase(){
        RecommendationOutputBoundary recommendationPresenter = new RecommendationPresenter(resultsViewModel, viewManagerModel);
        RecommendationInputBoundary recommendationInteractor = new RecommendationInteractor(
                recommendationPresenter,
                new DataAccess()
        );
        RecommendationController controller = new RecommendationController(recommendationInteractor);
        gameView.setRecommendationController(controller);

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