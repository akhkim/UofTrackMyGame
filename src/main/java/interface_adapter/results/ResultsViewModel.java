package interface_adapter.results;

import interface_adapter.ViewModel;

public class ResultsViewModel extends ViewModel<ResultsState> {
    public ResultsViewModel() {
        super("ResultsView");
        setState(new ResultsState());
    }
}
