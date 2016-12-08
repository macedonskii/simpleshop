package home.mad.simpleshop.model;

import com.google.gson.JsonObject;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import rx.Observable;

/**
 * Created by mad on 01.12.2016.
 */

public interface Model {

    Observable<List<ItemDTO>> getFavorites();

    Observable<List<ItemDTO>> getFavorites(String category);

    Observable<JsonObject> getCategories();

    Observable<List<ItemDTO>> getGoods(String category, String itemName);

    void addFavorite(ItemDTO item);

    void removeFavorite(long id);

}
