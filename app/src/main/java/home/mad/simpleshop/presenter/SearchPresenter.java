package home.mad.simpleshop.presenter;


import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.model.mapers.JsonMaper;
import home.mad.simpleshop.view.SearchView;
import rx.Observer;
import rx.Subscription;

/**
 * Created by mad on 04.12.2016.
 */

public class SearchPresenter extends BasePresenter {


    SearchView view;
    public SearchPresenter(SearchView view) {
        super();
        this.view = view;
    }

    public void setView(SearchView view) {
        this.view = view;
    }

    public void onClickSearch(String category, String keywords){
        Subscription subscribe = model.getGoods(category, keywords).
                map(new JsonMaper()).subscribe(new Observer<List<ItemDTO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
//                SearchPresenter.this.view.showError(e.);
            }

            @Override
            public void onNext(List<ItemDTO> items) {
                if (items.size() == 0){
                    view.showEmptyList();
                }else{
                    view.showListItems(items);
                }
            }
        });
        compositeSubscription.add(subscribe);

    }


}
