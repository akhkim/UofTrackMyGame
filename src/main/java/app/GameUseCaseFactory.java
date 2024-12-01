//package app;
//import interface_adapter.game.GameController;
//import interface_adapter.game.GamePresenter;
//import interface_adapter.game.GameViewModel;
//import use_case.game.GameInteractor;
//import interface_adapter.game.GameState;
//import interface_adapter.ViewManagerModel;
//
//public class GameUseCaseFactory {
//    private final GameState gameState;
//    private final GameViewModel gameViewModel;
//    private final ViewManagerModel viewManagerModel;
//
//    public GameUseCaseFactory(GameState gameState) {
//        this.gameState = gameState;
//        this.gameViewModel = new GameViewModel(gameState);
//    }
//
//    public GameController createGameController() {
//        GamePresenter presenter = new GamePresenter(gameViewModel, );
//        GameInteractor interactor = new GameInteractor(presenter, gameState);
//        return new GameController(interactor);
//    }
//
//    public GameViewModel getGameViewModel() {
//        return gameViewModel;
//    }
//}