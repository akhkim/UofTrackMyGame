package entity;

public class Game {
    private String title;
    private String salePrice;
    private String normalPrice;
    private String isOnSale;
    private String savings;
    private String metacriticScore;
    private String steamRatingText;
    private String steamRatingPercent;
    private String steamRatingCount;
    private String dealRating;
    private String thumb;

    public Game(String title, String salePrice, 
                     String normalPrice, String isOnSale, String savings,
                     String metacriticScore, String steamRatingText, 
                     String steamRatingPercent, String steamRatingCount,
                     String dealRating, String thumb) {
        this.title = title;
        this.salePrice = salePrice;
        this.normalPrice = normalPrice;
        this.isOnSale = isOnSale;
        this.savings = savings;
        this.metacriticScore = metacriticScore;
        this.steamRatingText = steamRatingText;
        this.steamRatingPercent = steamRatingPercent;
        this.steamRatingCount = steamRatingCount;
        this.dealRating = dealRating;
        this.thumb = thumb;
    }

    public String getTitle(){
        return title; 
    }

    public String getSalePrice(){
        return salePrice; 
    }

    public String getNormalPrice(){
        return normalPrice; 
    }

    public String getIsOnSale(){
        return isOnSale; 
    }

    public String getSavings(){
        return savings; 
    }

    public String getMetacriticScore(){
        return metacriticScore; 
    }

    public String getSteamRatingText(){
        return steamRatingText; 
    }

    public String getSteamRatingPercent(){
        return steamRatingPercent; 
    }

    public String getSteamRatingCount(){
        return steamRatingCount; 
    }

    public String getDealRating(){
        return dealRating; 
    }

    public String getThumb(){
        return thumb; 
    }
}