package use_case.game;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface GameInputBoundary {

    /**
     * Executes the game use case.
     * @param gameInputData the input data
     */
    void execute(GameInputData gameInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
