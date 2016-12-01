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

import butterknife.Bind;
import home.mad.simpleshop.R;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.TabsPresenter;
import home.mad.simpleshop.presenter.adapters.TabAdapter;
import home.mad.simpleshop.view.TabsView;

/**
 * Created by mad on 01.12.2016.
 */

public class TabsFragment extends BaseFragment implements TabsView {

    ViewPager viewPager;
    TabLayout tabLayout;


    TabsPresenter presenter = new TabsPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager();

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    public void setupViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SearchTabFragment());
        fragments.add(new FavoritesTabFragment());
        ArrayList<String> names = new ArrayList<>();
        names.add("search");
        names.add("favorites");
        TabAdapter adapter = new TabAdapter(getActivity().getSupportFragmentManager(), fragments, names);
        viewPager.setAdapter(adapter);
    }
}
