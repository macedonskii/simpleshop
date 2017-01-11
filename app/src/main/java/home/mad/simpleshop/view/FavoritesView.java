package home.mad.simpleshop.view;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;



public interface FavoritesView extends View {
    void showEmptyList();

    void showFavoritesList(List<ItemDTO> items);

    void onItemClick(ItemDTO item);

}
