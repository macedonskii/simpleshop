package home.mad.simpleshop.presenter;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.adapters.AbstractAdapter;
import home.mad.simpleshop.view.FavoritesView;
import rx.Observer;
import rx.Subscription;


public class FavoritesPresenter extends BasePresenter implements AbstractAdapter.ItemClick {
    private FavoritesView view;
    private List<ItemDTO> items;

    public FavoritesPresenter(FavoritesView view) {
        this.view = view;
    }

    @Override
    public void onFavoritesClick(ItemDTO item, boolean checked) {
        item.setFavorites(checked);
        if (!checked) {
            model.removeFavorite(item.getListingId());
            if (items.size() == 0){
                view.showEmptyList();
            }
        }
    }

    @Override
    public void onItemClick(ItemDTO item) {

        view.onItemClick(item);
    }

    public void setView(FavoritesView view) {
        this.view = view;
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
