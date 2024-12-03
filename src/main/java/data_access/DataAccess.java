package data_access;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;

import entity.Game;
import entity.GameFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.search.GameSearchDataAccessInterface;
import use_case.wishlist.WishlistDataAccessInterface;
import use_case.game.GameDataAccessInterface;


public class DataAccess implements GameSearchDataAccessInterface, WishlistDataAccessInterface, GameDataAccessInterface {
    private static final String WISHLIST_PATH = "src/main/java/data/wishlist.json";
    private static final Map<String, String> storeMap = new HashMap<String, String>() {{
        put("1", "https://store.steampowered.com");
        put("2", "https://www.gamersgate.com");
        put("3", "https://www.greenmangaming.com/");
        put("4", "https://www.amazongames.com");
        put("5", "https://www.gamestop.ca");
        put("6", "https://www.direct2drive.com");
        put("7", "https://www.gog.com");
        put("8", "https://www.ea.com");
        put("9", "Get Games");
        put("10", "Shiny Loot");
        put("11", "https://www.humblebundle.com");
        put("12", "https://www.desura.games");
        put("13", "https://store.ubisoft.com");
        put("14", "http://indiegamestand.com");
        put("15", "https://www.fanatical.com");
        put("16", "https://www.strictlylimitedgames.com");
        put("17", "https://gamesrepublic.com");
        put("18", "https://store.silagames.com");
        put("19", "Playfield");
        put("20", "https://imperial.games");
        put("21", "https://www.wingamestore.com");
        put("22", "FunStockDigital");
        put("23", "https://www.gamebillet.com");
        put("24", "https://www.voidu.com");
        put("25", "https://store.epicgames.com");
        put("26", "Razer Game Store");
        put("27", "https://us.gamesplanet.com");
        put("28", "https://www.gamesload.com");
        put("29", "https://2game.com");
        put("30", "https://www.indiegala.com");
        put("31", "https://us.shop.battle.net");
        put("32", "https://allyouplay.com");
        put("33", "https://www.dlgamer.com");
        put("34", "https://www.noctre.com");
        put("35", "https://www.dreamgame.com");
    }};

    public String searchByTitle(String title) {
        System.out.println("Searching by title: " + title);
        String baseUrl = "https://www.cheapshark.com/api/1.0/deals";
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        String response = executeRequest(baseUrl, params);
        return addStoreNamesToResponse(response);
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
        String response = executeRequest(baseUrl, params);
        return addStoreNamesToResponse(response);
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

    private String addStoreNamesToResponse(String jsonResponse) {
        try {
            JSONArray games = new JSONArray(jsonResponse);
            for (int i = 0; i < games.length(); i++) {
                JSONObject game = games.getJSONObject(i);
                String storeID = game.getString("storeID");
                game.put("storeName", storeMap.getOrDefault(storeID, "Unknown Store"));
            }
            return games.toString();
        } catch (Exception e) {
            System.err.println("Error processing JSON response: " + e.getMessage());
            return jsonResponse;
        }
    }

    @Override
     public void saveToWishlist(String gameID, String title, String salePrice,
                                String normalPrice, String isOnSale, String savings,
                                String metacriticScore, String steamRatingText,
                                String steamRatingPercent, String steamRatingCount,
                                String dealRating, String thumb, String storeName){
        JSONObject jsonObject;
        JSONArray gamesArray;

        // Check if file exists and has content
        File file = new File(WISHLIST_PATH);
        if (file.exists() && file.length() > 0) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(WISHLIST_PATH)));
                jsonObject = new JSONObject(content);
                gamesArray = jsonObject.optJSONArray("games");
                if (gamesArray == null) {
                    gamesArray = new JSONArray();
                    jsonObject.put("games", gamesArray);
                }
            } catch (Exception e) {
                jsonObject = new JSONObject();
                gamesArray = new JSONArray();
                jsonObject.put("games", gamesArray);
            }
        } else {
            jsonObject = new JSONObject();
            gamesArray = new JSONArray();
            jsonObject.put("games", gamesArray);
        }

        // Create a new JSON object for this game
        JSONObject gameObject = new JSONObject();
        gameObject.put("gameID", gameID);
        gameObject.put("title", title);
        gameObject.put("salePrice", salePrice);
        gameObject.put("normalPrice", normalPrice);
        gameObject.put("isOnSale", isOnSale);
        gameObject.put("savings", savings);
        gameObject.put("metacriticScore", metacriticScore);
        gameObject.put("steamRatingText", steamRatingText);
        gameObject.put("steamRatingPercent", steamRatingPercent);
        gameObject.put("steamRatingCount", steamRatingCount);
        gameObject.put("dealRating", dealRating);
        gameObject.put("thumb", thumb);
        gameObject.put("storeName", storeName);

        // Add the game object to the array
        gamesArray.put(gameObject);

        // Write back to file with pretty printing
        try (FileWriter writer = new FileWriter(WISHLIST_PATH)) {
            writer.write(jsonObject.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
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

        try {
            // Create directories if they don't exist
            Files.createDirectories(Paths.get("data"));
            try (FileWriter file = new FileWriter(WISHLIST_PATH)) {
                file.write(gamesArray.toString(4));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Game> loadWishlist() {
        ArrayList<Game> gamesArray = new ArrayList<>();
        GameFactory gameFactory = new GameFactory();
        try {
            // Read the JSON file as a string
            String stringJSON = new String(Files.readAllBytes(Paths.get(WISHLIST_PATH)));

            // Parse it as a JSONObject
            JSONObject jsonObject = new JSONObject(stringJSON);

            // Get the "games" JSONArray from the JSONObject
            JSONArray gamesJSON = jsonObject.getJSONArray("games");

            // Iterate through the JSONArray and create Game objects
            for (int i = 0; i < gamesJSON.length(); i++) {
                gamesArray.add(gameFactory.create(gamesJSON.getJSONObject(i)));
            }
        } catch (NoSuchFileException e) {
            System.err.println("Wishlist file not found: " + WISHLIST_PATH);
        } catch (IOException e) {
            System.err.println("Error reading the wishlist: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing the wishlist JSON: " + e.getMessage());
        }
        return gamesArray;
    }


    @Override
    public void removeGameFromWishlist(String gameID) {  // Change parameter name to gameID
        try {
            // Read the JSON file as a string
            String stringJSON = new String(Files.readAllBytes(Paths.get(WISHLIST_PATH)));

            // Parse it as a JSONObject
            JSONObject jsonObject = new JSONObject(stringJSON);

            // Get the "games" JSONArray
            JSONArray gamesJSON = jsonObject.getJSONArray("games");
            JSONArray updatedGames = new JSONArray();

            // Filter the games by comparing the gameID instead of gameTitle
            for (int i = 0; i < gamesJSON.length(); i++) {
                JSONObject game = gamesJSON.getJSONObject(i);
                // Assuming each game object has a "gameID" field
                if (!game.getString("gameID").equalsIgnoreCase(gameID)) {  // Compare using gameID
                    updatedGames.put(game);
                }
            }

            // Update the "games" key in the JSONObject
            jsonObject.put("games", updatedGames);

            // Write the updated JSONObject back to the file
            try (FileWriter file = new FileWriter(WISHLIST_PATH)) {
                file.write(jsonObject.toString(4)); // Pretty print with an indent of 4
            }
        } catch (IOException e) {
            System.err.println("Error updating wishlist: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing or updating wishlist JSON: " + e.getMessage());
        }
    }




    /**
     * Sets a price alert for a specific game
     * @param email User's email address
     * @param gameID Game's ID
     * @param price Target price
     * @return true if alert was set successfully, false otherwise
     */
    public void setPriceAlert(String email, String gameID, String price) {
        String baseUrl = "https://www.cheapshark.com/api/1.0/alerts";
        Map<String, String> params = new HashMap<>();
        params.put("action", "set");
        params.put("email", email);
        params.put("gameID", gameID);
        params.put("price", price);

        try {
            String response = executeRequest(baseUrl, params);
            System.out.println(response);
        } catch (Exception e) {
            System.err.println("Error setting price alert: " + e.getMessage());
        }
    }
}
