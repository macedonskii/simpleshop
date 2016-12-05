package home.mad.simpleshop.presenter;

import home.mad.simpleshop.view.SearchResultView;
import home.mad.simpleshop.view.fragments.SearchResultFragment;

/**
 * Created by mad on 05.12.2016.
 */
public class SearchResultPresenter extends BasePresenter{
    private SearchResultView view;
    public SearchResultPresenter(SearchResultView view) {
        this.view = view;
    }
}
