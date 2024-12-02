//package entity;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class WishlistFactory {
//    public Wishlist create(JSONArray jsonWishlist) {
//        Wishlist wishlist = Wishlist.create();
//        for (int i = 0; i < jsonWishlist.length(); i++) {
//            JSONObject gameJson = jsonWishlist.getJSONObject(i);
//            wishlist.addGame(new GameFactory().create(gameJson));
//        }
//        return wishlist;
//    }
//}