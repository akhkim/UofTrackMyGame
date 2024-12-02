package interface_adapter.search;

import interface_adapter.ViewModel;

public class GameSearchViewModel extends ViewModel<GameSearchState> {

    public GameSearchViewModel(GameSearchState state) {
        super("GameSearchView");
        this.setState(state);
    }
}
