package home.mad.simpleshop.presenter;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.other.Const;
import home.mad.simpleshop.presenter.adapters.AbstractAdapter;
import home.mad.simpleshop.presenter.adapters.SearchResultAdapter;
import home.mad.simpleshop.view.SearchResultView;
import rx.Observer;
import rx.Subscription;

public class SearchResultPresenter extends BasePresenter implements AbstractAdapter.ItemClick, SearchResultAdapter.ListLoader {
    private SearchResultView view;
    String keyword, category;
    int offset;
    private List<ItemDTO> items;

    public SearchResultPresenter() {
        offset = Const.OFFSET_VALUE;
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

    public void onSwipeRefresh() {
        offset = 0;
        Subscription subscribe = model.getGoods(category, keyword, offset).subscribe(new Observer<List<ItemDTO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
                view.stopRefresh();
            }

            @Override
            public void onNext(List<ItemDTO> itemDTOs) {
                offset = 25;
                view.stopRefresh();
                view.onListLoad(itemDTOs);
            }
        });

        compositeSubscription.add(subscribe);
    }

    public void downloadNextPart(){
        Subscription subscribe = model.getGoods(category, keyword, offset).subscribe(new Observer<List<ItemDTO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ItemDTO> items) {
                SearchResultPresenter.this.offset += Const.OFFSET_VALUE;
                view.onNextPartDownloaded(items);

            }
        });
        compositeSubscription.add(subscribe);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setKeywords(String keyword) {
        this.keyword = keyword;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    @Override
    public void startLoading() {
        downloadNextPart();
    }
}
