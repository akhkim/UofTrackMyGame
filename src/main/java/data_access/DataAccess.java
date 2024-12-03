package data_access;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Game;
import entity.GameFactory;
import use_case.game.GameDataAccessInterface;
import use_case.search.GameSearchDataAccessInterface;
import use_case.wishlist.WishlistDataAccessInterface;

/**
 * The {@code DataAccess} class implements the interfaces {@link GameSearchDataAccessInterface},
 * {@link WishlistDataAccessInterface}, and {@link GameDataAccessInterface} to manage game data,
 * including searching by title, applying filters, saving and loading from a wishlist,
 * and setting price alerts.
 * <p>
 * It handles interactions with a remote API (CheapShark) for game search, and uses local
 * file storage (JSON format) for wishlist management.
 * </p>
 */
public class DataAccess implements GameSearchDataAccessInterface, WishlistDataAccessInterface, GameDataAccessInterface {
    private static final String WISHLIST_PATH = "src/main/java/data/wishlist.json";
    private static final Map<String, String> STORE_MAP = new HashMap<String, String>() {{
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

    /**
     * Searches for games by title.
     *
     * @param title the title of the game to search for
     * @return a JSON string containing the search results with store names added
     */
    public String searchByTitle(String title) {
        System.out.println("Searching by title: " + title);
        String baseUrl = "https://www.cheapshark.com/api/1.0/deals";
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        String response = executeRequest(baseUrl, params);
        return addStoreNamesToResponse(response);
    }

    /**
     * Searches for games with the specified filters.
     *
     * @param upperPrice the upper price filter
     * @param lowerPrice the lower price filter
     * @param metacritic the metacritic score filter
     * @param onSale whether to filter by sale status
     * @param sortBy the sorting criteria
     * @param desc whether to sort in descending order
     * @return a JSON string containing the search results with store names added
     */
    public String searchByFilters(String upperPrice, String lowerPrice, String metacritic, String onSale, String sortBy,
                                  String desc) {
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

    /**
     * Executes an HTTP GET request to the given URL with the specified parameters.
     *
     * @param baseUrl the base URL for the request
     * @param params the parameters to include in the request
     * @return the response from the server as a string
     */
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
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Adds store names to the game search response based on the store ID.
     *
     * @param jsonResponse the original JSON response containing game deals
     * @return the modified JSON response with store names included
     */
    private String addStoreNamesToResponse(String jsonResponse) {
        try {
            JSONArray games = new JSONArray(jsonResponse);
            for (int i = 0; i < games.length(); i++) {
                JSONObject game = games.getJSONObject(i);
                String storeID = game.getString("storeID");
                game.put("storeName", STORE_MAP.getOrDefault(storeID, "Unknown Store"));
            }
            return games.toString();
        }
        catch (Exception exception) {
            System.err.println("Error processing JSON response: " + exception.getMessage());
            return jsonResponse;
        }
    }

    /**
     * Saves a game to the user's wishlist.
     *
     * @param gameID the ID of the game
     * @param title the title of the game
     * @param salePrice the sale price of the game
     * @param normalPrice the normal price of the game
     * @param isOnSale whether the game is on sale
     * @param savings the savings amount
     * @param metacriticScore the metacritic score of the game
     * @param steamRatingText the Steam rating text
     * @param steamRatingPercent the percentage of Steam ratings
     * @param steamRatingCount the number of Steam ratings
     * @param dealRating the deal rating
     * @param thumb the thumbnail image URL
     * @param storeName the name of the store where the game is available
     */
    @Override
    public void saveToWishlist(String gameID, String title, String salePrice,
                            String normalPrice, String isOnSale, String savings,
                            String metacriticScore, String steamRatingText,
                            String steamRatingPercent, String steamRatingCount,
                            String dealRating, String thumb, String storeName) {
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
            } catch (Exception exception) {
                jsonObject = new JSONObject();
                gamesArray = new JSONArray();
                jsonObject.put("games", gamesArray);
            }
        } else {
            jsonObject = new JSONObject();
            gamesArray = new JSONArray();
            jsonObject.put("games", gamesArray);
        }

        // Check if the game is already in the wishlist
        boolean gameExists = false;
        for (int i = 0; i < gamesArray.length(); i++) {
            JSONObject game = gamesArray.getJSONObject(i);
            if (gameID.equals(game.optString("gameID"))) {
                gameExists = true;
                break;
            }
        }

        // If the game is not in the wishlist, add it
        if (!gameExists) {
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

            gamesArray.put(gameObject);

            try (FileWriter writer = new FileWriter(WISHLIST_PATH)) {
                writer.write(jsonObject.toString(2));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Loads the wishlist from the local JSON file and returns a list of {@code Game} objects.
     *
     * @return an ArrayList of {@code Game} objects representing the games in the wishlist
     */
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
        }
        catch (NoSuchFileException exception) {
            System.err.println("Wishlist file not found: " + WISHLIST_PATH);
        }
        catch (IOException exception) {
            System.err.println("Error reading the wishlist: " + exception.getMessage());
        }
        catch (JSONException exception) {
            System.err.println("Error parsing the wishlist JSON: " + exception.getMessage());
        }
        return gamesArray;
    }

    /**
     * Removes a game from the wishlist based on the game ID.
     *
     * @param gameID the ID of the game to remove
     */
    @Override
    public void removeGameFromWishlist(String gameID) {
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
                if (!game.getString("gameID").equalsIgnoreCase(gameID)) {
                    updatedGames.put(game);
                }
            }

            // Update the "games" key in the JSONObject
            jsonObject.put("games", updatedGames);

            // Write the updated JSONObject back to the file
            try (FileWriter file = new FileWriter(WISHLIST_PATH)) {
                file.write(jsonObject.toString(4));
            }
        }
        catch (IOException exception) {
            System.err.println("Error updating wishlist: " + exception.getMessage());
        }
        catch (JSONException exception) {
            System.err.println("Error parsing or updating wishlist JSON: " + exception.getMessage());
        }
    }

    /**
     * Sets a price alert for a specific game.
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
        }
        catch (Exception exeption) {
            System.err.println("Error setting price alert: " + exeption.getMessage());
        }
    }
}
