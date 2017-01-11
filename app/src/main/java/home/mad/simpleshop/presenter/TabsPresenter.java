package home.mad.simpleshop.presenter;

import home.mad.simpleshop.view.TabsView;
import home.mad.simpleshop.view.fragments.TabsFragment;

public class TabsPresenter extends BasePresenter{
    TabsView view;
    public TabsPresenter(TabsView view) {
        this.view = view;
    }

    public void setView(TabsView view) {
        this.view = view;
    }
}
