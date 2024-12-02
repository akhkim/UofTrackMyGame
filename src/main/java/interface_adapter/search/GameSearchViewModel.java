package interface_adapter.search;

import interface_adapter.ViewModel;

public class GameSearchViewModel extends ViewModel<GameSearchState> {

    public GameSearchViewModel() {
        super("GameSearchView");
        this.setState(new GameSearchState());
    }
}
