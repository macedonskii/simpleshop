package home.mad.simpleshop.presenter;

import home.mad.simpleshop.view.FullItemView;
import home.mad.simpleshop.view.fragments.FullItemFragment;

/**
 * Created by mad on 07.12.2016.
 */
public class FullFragmentPresenter extends BasePresenter {
    FullItemView view;

    public FullFragmentPresenter(FullItemView view) {
        this.view = view;
    }

    public void setView(FullItemView view) {
        this.view = view;
    }
}
