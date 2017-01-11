package home.mad.simpleshop.presenter.adapters;

import android.content.Context;

import java.util.List;
import home.mad.simpleshop.model.dto.ItemDTO;

public class SearchResultAdapter extends AbstractAdapter {
    ListLoader loader;

    public SearchResultAdapter(Context context, List<ItemDTO> items, ItemClick itemClick, ListLoader loader) {
        super(context, items, itemClick);
        this.loader = loader;
    }

    @Override
    public void onFavoritesClick(ItemDTO item, boolean isChecked) {
        itemClick.onFavoritesClick(item, isChecked);
    }

    @Override
    public void onItemClick(ItemDTO item) {
        itemClick.onItemClick(item);
    }

    @Override
    public void startLoadNewList() {
        loader.startLoading();
    }

    public interface ListLoader{
        void startLoading();
    }
}
