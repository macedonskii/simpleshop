package home.mad.simpleshop.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.other.Const;
import home.mad.simpleshop.presenter.adapters.SearchResultAdapter;
import home.mad.simpleshop.view.SearchResultView;
import rx.Observer;
import rx.Subscription;

/**
 * Created by mad on 05.12.2016.
 */
public class SearchResultPresenter extends BasePresenter implements SearchResultAdapter.ItemClick {
    private SearchResultView view;
    String keyword, category;
    int offset;
    private List<ItemDTO> items;

    public SearchResultPresenter() {
        offset = Const.OFFSET_VALUE;
    }

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
                if (itemDTOs.size() == 0){
                    view.onEmptyList();
                    return;
                }
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
}
