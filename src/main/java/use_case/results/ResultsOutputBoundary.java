package use_case.results;

public interface ResultsOutputBoundary {
    void prepareSuccessView(ResultsOutputData data);
    void prepareFailView(String error);
}