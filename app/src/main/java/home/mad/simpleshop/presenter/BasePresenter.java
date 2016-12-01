package home.mad.simpleshop.presenter;

import home.mad.simpleshop.model.Model;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mad on 01.12.2016.
 */

public abstract class BasePresenter implements Presenter {

//    @Inject
    protected Model model;
    private CompositeSubscription compositeSubscription;

    public BasePresenter(){

    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
