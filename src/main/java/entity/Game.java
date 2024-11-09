package entity;

import java.util.Map;

public class Game {
    private String name;
    private Map<String, Float> prices;
    private Map<String, Float> dealRatings;
    private float rating;

    private float priceThreshold;

    public Game(String name, Map<String, Float> prices, Map<String, Float> dealRatings, float rating, float priceThreshold) {
        this.name = name;
        this.prices = prices;
        this.dealRatings = dealRatings;
        this.rating = rating;
        this.priceThreshold = priceThreshold;
    }

    public String getName() {
        return name;
    }

    public Map<String, Float> getPrices() {
        return prices;
    }

    public void setPrice(String platform, float price) {
        prices.put(platform, price);
    }

    public Map<String, Float> getDealRatings() {
        return dealRatings;
    }

    public float getRating() {
        return rating;
    }

    public float getPriceThreshold() {
        return priceThreshold;
    }

    public void setPriceThreshold(Float priceThreshold) {
        this.priceThreshold = priceThreshold;
    }


}
