package home.mad.simpleshop.model;

import com.google.gson.JsonObject;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import rx.Observable;



public interface Model {

    Observable<List<ItemDTO>> getFavorites();

    Observable<List<ItemDTO>> getFavorites(String category);

    Observable<JsonObject> getCategories();

    Observable<List<ItemDTO>> getGoods(String category, String itemName, int offset);

    void addFavorite(ItemDTO item);

    void removeFavorite(long id);

    Observable<Boolean> isItemRemoved(long listingId);

    Observable<Boolean> isItemAdd(long listingId);
}
