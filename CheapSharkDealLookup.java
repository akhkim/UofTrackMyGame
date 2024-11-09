import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CheapSharkDealLookup {

    public static void main(String[] args) {
        // Example calls
        String responseByTitle = searchByTitle("batman");
        System.out.println("Response by Title: " + responseByTitle);

        String responseByFilters = searchByFilters("15", "5", "80", "1", "Metacritic", "0");
        System.out.println("Response by Filters: " + responseByFilters);
    }

    /**
     * Searches for games by title using the CheapShark API.
     *
     * @param title the title of the game to search for
     * @return the API response as a String
     */
    public static String searchByTitle(String title) {
        String baseUrl = "https://www.cheapshark.com/api/1.0/games";
        Map<String, String> params = new HashMap<>();
        params.put("title", title);

        return executeRequest(baseUrl, params);
    }

    /**
     * Searches for deals using various filters with the CheapShark API.
     *
     * @param upperPrice the upper price limit
     * @param lowerPrice the lower price limit
     * @param metacritic the metacritic score
     * @param onSale     whether the game is on sale
     * @param sortBy     the sorting criteria
     * @param desc       the sorting direction
     * @return the API response as a String
     */
    public static String searchByFilters(String upperPrice, String lowerPrice, String metacritic, String onSale, String sortBy, String desc) {
        String baseUrl = "https://www.cheapshark.com/api/1.0/deals";
        Map<String, String> params = new HashMap<>();

        // Setting default value for upperPrice if it is null or empty
        if (upperPrice == null || upperPrice.isEmpty()) {
            upperPrice = "10000";
        }

        params.put("upperPrice", upperPrice);
        params.put("lowerPrice", lowerPrice);
        params.put("metacritic", metacritic);
        params.put("onSale", onSale);
        params.put("sortBy", sortBy);
        params.put("desc", desc);

        return executeRequest(baseUrl, params);
    }

    /**
     * Executes an HTTP GET request to the specified URL with the given parameters.
     *
     * @param baseUrl the base URL for the API request
     * @param params  the query parameters to include in the request
     * @return the API response as a String
     */
    private static String executeRequest(String baseUrl, Map<String, String> params) {
        StringBuilder urlWithParams = new StringBuilder(baseUrl);
        if (!params.isEmpty()) {
            urlWithParams.append("?");
            params.forEach((key, value) -> urlWithParams.append(key).append("=").append(value).append("&"));
            urlWithParams.setLength(urlWithParams.length() - 1);
        }

        try {
            URI uri = new URI(urlWithParams.toString());
            URL url = uri.toURL();
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

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
