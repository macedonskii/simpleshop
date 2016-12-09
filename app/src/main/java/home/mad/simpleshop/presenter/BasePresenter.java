package home.mad.simpleshop.presenter;

import android.content.SharedPreferences;

import javax.inject.Inject;

import home.mad.simpleshop.model.Model;
import home.mad.simpleshop.other.App;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mad on 01.12.2016.
 */

public abstract class BasePresenter implements Presenter {

    protected String TAG = getClass().getSimpleName();
    @Inject
    protected Model model;
    @Inject
    protected SharedPreferences preferences;
//    protected Model model = new ModelImpl();
    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    public BasePresenter(){
        App.getGraph().inject(this);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }


}
