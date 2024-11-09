package interface_adapter.results;

import use_case.results.ResultsOutputBoundary;
import use_case.results.ResultsOutputData;

public class ResultsPresenter implements ResultsOutputBoundary {
    private final ResultsViewModel resultsViewModel;

    public ResultsPresenter(ResultsViewModel resultsViewModel) {
        this.resultsViewModel = resultsViewModel;
    }

    @Override
    public void prepareSuccessView(ResultsOutputData data) {
        ResultsState state = new ResultsState();
        state.setGames(data.getGames());
        resultsViewModel.setState(state);
    }

    @Override
    public void prepareFailView(String error) {
        ResultsState state = new ResultsState();
        resultsViewModel.setState(state);
    }
}