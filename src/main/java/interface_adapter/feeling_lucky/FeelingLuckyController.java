package interface_adapter.feeling_lucky;

import use_case.feeling_lucky.FeelingLuckyInputBoundary;

public class FeelingLuckyController {
    private final FeelingLuckyInputBoundary feelingLuckyUseCaseInteractor;
    public FeelingLuckyController(FeelingLuckyInputBoundary feelingLuckyUseCaseInteractor) {
        this.feelingLuckyUseCaseInteractor = feelingLuckyUseCaseInteractor;
    }

    public void execute() {
        feelingLuckyUseCaseInteractor.execute();
    }
}
