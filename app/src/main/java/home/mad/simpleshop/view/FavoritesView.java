package home.mad.simpleshop.view;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;

/**
 * Created by mad on 01.12.2016.
 */

public interface FavoritesView extends View {
    void showEmptyList();

    void showFavoritesList(List<ItemDTO> items);

    void onItemClick(ItemDTO item);


    void removeItem(ItemDTO tmp);
}
