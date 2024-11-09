package use_case.search;

public class SearchInteractor implements SearchInputBoundary {
    private final SearchUserDataAccessInterface searchUserDataAccessInterface;
    private final SearchOutputBoundary searchOutputBoundary;

    public SearchInteractor(
            SearchUserDataAccessInterface searchUserDataAccessInterface,
            SearchOutputBoundary searchOutputBoundary
    ) {
        this.searchUserDataAccessInterface = searchUserDataAccessInterface;
        this.searchOutputBoundary = searchOutputBoundary;
    }
    @Override
    public void execute(SearchInputData searchInputData) {
        SearchOutputData searchOutputData = new SearchOutputData(); // initialise with API call results
    }
}
