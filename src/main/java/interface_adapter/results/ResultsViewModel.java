
package interface_adapter.results;

import interface_adapter.ViewModel;

/**
 * The {@code ResultsViewModel} class represents the view model for the results view in the application.
 * It is responsible for managing the state of the results and updating the view accordingly.
 * This class extends {@code ViewModel<ResultsState>} and provides the necessary mechanisms
 * to interact with the results data in the view layer.
 */
public class ResultsViewModel extends ViewModel<ResultsState> {
    public ResultsViewModel() {
        super("ResultsView");
        setState(new ResultsState());
    }
}