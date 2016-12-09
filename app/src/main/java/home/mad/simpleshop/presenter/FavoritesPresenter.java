package home.mad.simpleshop.presenter;

import android.os.Bundle;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.adapters.AbstractAdapter;
import home.mad.simpleshop.view.FavoritesView;
import rx.Observer;
import rx.Subscription;

/**
 * Created by mad on 06.12.2016.
 */
public class FavoritesPresenter extends BasePresenter implements AbstractAdapter.ItemClick {
    private FavoritesView view;
    private ItemDTO tmp;
    private List<ItemDTO> items;

    public FavoritesPresenter(FavoritesView view) {
        this.view = view;
    }

    @Override
    public void onFavoritesClick(ItemDTO item, boolean checked) {
        item.setFavorites(checked);
        if (!checked) {
            model.removeFavorite(item.getListingId());
        }
    }

    @Override
    public void onItemClick(ItemDTO item) {
        tmp = item;
        view.onItemClick(item);
    }

    public void setView(FavoritesView view) {
        this.view = view;
    }

    public void onViewCreated() {

    }


    public void onSaveInstanceState(Bundle outState) {

    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        if (tmp != null) {
            Subscription subscribe = model.isItemRemoved(tmp.getListingId()).subscribe(new Observer<Boolean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    view.showError(e);
                }

                @Override
                public void onNext(Boolean result) {
                    if (!result){
                        view.removeItem(tmp);
                    }
                    tmp = null;
                }
            });
            compositeSubscription.add(subscribe);
        }
    }

    public void loadItems() {
        Subscription subscribe = model.getFavorites().subscribe(new Observer<List<ItemDTO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ItemDTO> items) {
                FavoritesPresenter.this.items = items;
                if (items.size() == 0) {
                    view.showEmptyList();
                } else {
                    view.showFavoritesList(items);
                }
            }
        });
        compositeSubscription.add(subscribe);
    }
}
