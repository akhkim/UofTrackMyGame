
package interface_adapter.recommendation;

import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import use_case.recommendation.RecommendationOutputBoundary;
import use_case.recommendation.RecommendationOutputData;

/**
 * The {@code RecommendationPresenter} class is responsible for preparing and presenting the results of the
 * recommendation use case. It communicates the processed recommendation data to the view layer and updates
 * the state of the view as necessary.
 */
public class RecommendationPresenter implements RecommendationOutputBoundary {
    private ResultsViewModel resultsViewModel;
    private ViewManagerModel viewManagerModel;

    public RecommendationPresenter(
            ResultsViewModel gameViewModel,
            ViewManagerModel viewManagerModel
    ) {
        this.resultsViewModel = gameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RecommendationOutputData outputData) {
        ResultsState resultsState = new ResultsState();
        resultsState.setGames(outputData.getGames());
        resultsViewModel.setState(resultsState);
        resultsViewModel.firePropertyChanged();

        viewManagerModel.switchView(resultsViewModel.getViewName());
        resultsViewModel.firePropertyChanged();
    }
}

