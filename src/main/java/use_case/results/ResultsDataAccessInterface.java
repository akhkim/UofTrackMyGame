package use_case.results;

public interface ResultsDataAccessInterface {
    String getResults(String title);
    void saveResults(String title, String data);
}