package data_access;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;

import entity.Game;
import entity.GameFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.search.GameSearchDataAccessInterface;
import use_case.wishlist.WishlistDataAccessInterface;

public class DataAccess implements GameSearchDataAccessInterface, WishlistDataAccessInterface {

    public String searchByTitle(String title) {
        System.out.println("Searching by title: " + title);
        String baseUrl = "https://www.cheapshark.com/api/1.0/deals";
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        return executeRequest(baseUrl, params);
    }

    public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, String onSale, String sortBy, String desc) {
        String baseUrl = "https://www.cheapshark.com/api/1.0/deals";
        Map<String, String> params = new HashMap<>();
        params.put("upperPrice", upperPrice);
        params.put("lowerPrice", lowerPrice);
        params.put("metacritic", metacritic);
        params.put("onSale", onSale);
        params.put("sortBy", sortBy);
        params.put("desc", desc);
        return executeRequest(baseUrl, params);
    }

    private String executeRequest(String baseUrl, Map<String, String> params) {
        try {
            StringBuilder urlWithParams = new StringBuilder(baseUrl);
            if (!params.isEmpty()) {
                urlWithParams.append("?");
                boolean first = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (!first) {
                        urlWithParams.append("&");
                    }
                    urlWithParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                                .append("=")
                                .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    first = false;
                }
            }

            URI uri = new URI(urlWithParams.toString());
            URL url = uri.toURL();
            
            // Debug print
            System.out.println("Requesting URL: " + url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // System.out.println("Response: " + response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveWishlist(ArrayList<Game> games) {
        JSONArray gamesArray = new JSONArray();
        for (Game game : games) {
            JSONObject gameJSON = new JSONObject();
            gameJSON.put("title", game.getTitle());
            gameJSON.put("salePrice", game.getSalePrice());
            gameJSON.put("normalPrice", game.getNormalPrice());
            gameJSON.put("isOnSale", game.getIsOnSale());
            gameJSON.put("savings", game.getSavings());
            gameJSON.put("metacriticScore", game.getMetacriticScore());
            gameJSON.put("steamRatingText", game.getSteamRatingText());
            gameJSON.put("steamRatingPercent", game.getSteamRatingPercent());
            gameJSON.put("steamRatingCount", game.getSteamRatingCount());
            gameJSON.put("dealRating", game.getDealRating());
            gameJSON.put("thumb", game.getThumb());
            gameJSON.put("gameID", game.getGameID());
            gamesArray.put(gameJSON);
        }

        try (FileWriter file = new FileWriter("wishlist.json")) {
            file.write(gamesArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Game> loadWishlist() {
        ArrayList<Game> gamesArray = new ArrayList<>();
        GameFactory gameFactory = new GameFactory();
        try {
            String stringJSON = new String(Files.readAllBytes(Paths.get("wishlist.json")));
            JSONArray gamesJSON = new JSONArray(stringJSON);
            for (int i = 0; i < gamesJSON.length(); i++){
                gamesArray.add(gameFactory.create(gamesJSON.getJSONObject(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gamesArray;
    }

    /**
     * Sets a price alert for a specific game
     * @param email User's email address
     * @param gameID Game's ID
     * @param price Target price
     * @return true if alert was set successfully, false otherwise
     */
    public boolean setPriceAlert(String email, String gameID, String price) {
        String baseUrl = "https://www.cheapshark.com/api/1.0/alerts";
        Map<String, String> params = new HashMap<>();
        params.put("action", "set");
        params.put("email", email);
        params.put("gameID", gameID);
        params.put("price", price);

        try {
            String response = executeRequest(baseUrl, params);
            return "true".equals(response.trim().toLowerCase());
        } catch (Exception e) {
            System.err.println("Error setting price alert: " + e.getMessage());
            return false;
        }
    }
}
