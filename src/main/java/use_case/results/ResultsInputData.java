// JSON TO GAME CLASS HERE, MAKE AN ARRAY OF GAME OBJECTS

package use_case.results;

import java.util.ArrayList;
import java.util.List;

/**
 * The Input Data for the Results Use Case.
 */
public class ResultsInputData {

    private final List<String> gameTitles;

    public ResultsInputData(List<String> gameTitles) {
        this.gameTitles = new ArrayList<>(gameTitles);
    }

    List<String> getGameTitles() {
        return new ArrayList<>(gameTitles);
    }
}
