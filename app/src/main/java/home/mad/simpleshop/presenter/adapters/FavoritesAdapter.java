package home.mad.simpleshop.presenter.adapters;

import android.content.Context;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;


public class FavoritesAdapter extends AbstractAdapter {

    public FavoritesAdapter(Context context, List<ItemDTO> items, ItemClick itemClick) {
        super(context, items, itemClick);
    }

    @Override
    public void onFavoritesClick(ItemDTO item, boolean isChecked) {
        removeItem(item);
        itemClick.onFavoritesClick(item, isChecked);
    }

    @Override
    public void onItemClick(ItemDTO item) {
        itemClick.onItemClick(item);
    }

    @Override
    public void startLoadNewList() {

    }
}
