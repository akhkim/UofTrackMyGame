package use_case.game;

public class GameInputData {
    private final String gameID;

    public GameInputData(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}