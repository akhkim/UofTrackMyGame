package use_case.feeling_lucky;

import entity.Game;

public interface FeelingLuckyOutputBoundary {
    void prepareSuccessView(Game game);

    void prepareFailView(String errorMessage);
}
