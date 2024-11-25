package use_case.select_game;

public interface ResultsOutputBoundary {
    void prepareSuccessView(ResultsOutputData data);
    void prepareFailView(String error);
}