package home.mad.simpleshop.presenter;

import android.util.Log;

import com.google.gson.JsonObject;

import home.mad.simpleshop.view.SearchView;
import home.mad.simpleshop.view.fragments.SearchTabFragment;
import rx.Observable;
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
        Subscription subscribe = model.getGoods(category, keywords).subscribe(new Observer<JsonObject>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(JsonObject jsonObject) {
                Log.d(TAG, "onNext() called with: jsonObject = [" + jsonObject + "]");
            }
        });
        compositeSubscription.add(subscribe);

    }


}
