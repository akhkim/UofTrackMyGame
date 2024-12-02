package interface_adapter.recommendation;

import use_case.recommendation.RecommendationOutputBoundary;
import use_case.recommendation.RecommendationOutputData;

public class RecommendationPresenter implements RecommendationOutputBoundary {
    private RecommendationViewModel recommendationViewModel;
    private RecommendationState recommendationState;

    public RecommendationPresenter(RecommendationViewModel recommendationViewModel) {
        this.recommendationViewModel = recommendationViewModel;
    }

    @Override
    public void prepareSuccessView(RecommendationOutputData outputData) {
        recommendationState.setGames(outputData.getGames());
        recommendationViewModel.firePropertyChanged();

        // does not change ViewManagerModel since view does not change
    }
}

