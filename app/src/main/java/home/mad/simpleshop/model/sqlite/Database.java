package home.mad.simpleshop.model.sqlite;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import rx.Observable;

/**
 * Created by mad on 05.12.2016.
 */

public interface Database {

    Observable<List<ItemDTO>> getFavorites(String category);

    void addFavorite(ItemDTO item);

    void removeFavorite(long id);
}
