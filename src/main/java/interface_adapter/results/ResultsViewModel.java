package interface_adapter.results;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class ResultsViewModel extends ViewModel<ResultsState> {

    public ResultsViewModel() {
        super("results");
        setState(new ResultsState());
    }

}