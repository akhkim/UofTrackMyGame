package use_case.game;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface GameInputBoundary {
    void fetchGameDetails(GameInputData inputData);
}