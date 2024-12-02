package interface_adapter.recommendation;

import interface_adapter.ViewModel;

public class RecommendationViewModel extends ViewModel<RecommendationState> {

    public RecommendationViewModel() {
        super(""); // viewName is empty because it will never be read.
        setState(new RecommendationState());
    }
}