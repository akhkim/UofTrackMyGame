package use_case.search;

public class SearchInputData {
    private String gameName;
    private float minPrice;
    private float maxPrice;

    public SearchInputData(String gameName, float minPrice, float maxPrice) {
        this.gameName = gameName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getGameName() {
        return gameName;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }
}
