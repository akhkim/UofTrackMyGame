package interface_adapter.game;

/**
 * The state for the Game View Model.
 */
public class GameState {
    private String title = "";
    private String titleError;
    private String priceThreshold = "";
    private String priceThresholdError;

    public String getTitle() {
        return title;
    }

    public String getTitleError() {
        return titleError;
    }

    public String priceThreshold() {
        return priceThreshold;
    }

    public String getPriceThresholdError() {
        return priceThresholdError;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public void setPriceThreshold(String priceThreshold) {
        this.priceThreshold = priceThreshold;
    }

    public void setPriceThresholdError(String priceThresholdError) {
        this.priceThresholdError = priceThresholdError;
    }

    @Override
    public String toString() {
        return "GameState{"
                + "title='" + title + '\''
                + ", priceThreshold='" + priceThreshold + '\''
                + '}';
    }
}
