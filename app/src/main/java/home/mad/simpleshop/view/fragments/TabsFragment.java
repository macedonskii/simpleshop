package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import home.mad.simpleshop.R;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.TabsPresenter;
import home.mad.simpleshop.presenter.adapters.TabAdapter;
import home.mad.simpleshop.view.TabsView;

public class TabsFragment extends BaseFragment implements TabsView {

    TabsPresenter presenter = new TabsPresenter(this);
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public TabsFragment(){

    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager();
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void setupViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        fragments.add(new SearchTabFragment());
        fragments.add(new FavoritesTabFragment());
        names.add(getString(R.string.search_fragment_name));
        names.add(getString(R.string.favorites_fragment_name));
        TabAdapter adapter = new TabAdapter(getChildFragmentManager(), fragments, names);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showToolbarButton().setHomeAsUpIndicator(R.drawable.ic_menu);

    }
}
