package home.mad.simpleshop.presenter;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.adapters.SearchResultAdapter;
import home.mad.simpleshop.view.FavoritesView;
import rx.Observer;
import rx.Subscription;

/**
 * Created by mad on 06.12.2016.
 */
public class FavoritesPresenter extends BasePresenter implements SearchResultAdapter.ItemClick{
    FavoritesView view;

    public FavoritesPresenter(FavoritesView view) {
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

    public void setView(FavoritesView view) {
        this.view = view;
    }

    public void onViewCreated() {
        Subscription subscribe = model.getFavorites().subscribe(new Observer<List<ItemDTO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ItemDTO> items) {
                if (items.size() == 0){
                    view.showEmptyList();
                }else{
                    view.showFavoritesList(items);
                }
            }
        });
        compositeSubscription.add(subscribe);
    }


}
