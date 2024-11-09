package entity;
public class GameSearchState {
    private String title;
    private String upperPrice = "10000"; // Default value
    private String lowerPrice;
    private String metacritic;
    private boolean onSale;
    private String sortBy;
    private boolean desc;

    // Getters and setters for each field
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(String upperPrice) {
        this.upperPrice = upperPrice;
    }

    public String getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(String lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public String getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(String metacritic) {
        this.metacritic = metacritic;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }
}
