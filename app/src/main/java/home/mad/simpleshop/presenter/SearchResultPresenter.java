package home.mad.simpleshop.presenter;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.adapters.SearchResultAdapter;
import home.mad.simpleshop.view.SearchResultView;
import home.mad.simpleshop.view.SearchView;
import home.mad.simpleshop.view.fragments.SearchResultFragment;

/**
 * Created by mad on 05.12.2016.
 */
public class SearchResultPresenter extends BasePresenter implements SearchResultAdapter.ItemClick {
    private SearchResultView view;

    public SearchResultPresenter(SearchResultView view) {
        this.view = view;
    }

    @Override
    public void onFavoritesClick(ItemDTO item, boolean checked) {
        item.setFavorites(checked);
        if (checked) {
            model.addFavorite(item);
        } else {
            model.removeFavorite(item.getListingId());
        }
    }

    @Override
    public void onItemClick(ItemDTO item) {
        view.onItemClick(item);
    }

    public void setView(SearchResultView view) {
        this.view = view;
    }
}
