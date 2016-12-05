package home.mad.simpleshop.model;

import com.google.gson.JsonObject;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import rx.Observable;

/**
 * Created by mad on 01.12.2016.
 */

public interface Model {

    List<ItemDTO> getFavorites();

    Observable<JsonObject> getCategories();

    Observable<JsonObject> getGoods(String category, String itemName);

}
