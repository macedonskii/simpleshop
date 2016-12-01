package home.mad.simpleshop.presenter.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import home.mad.simpleshop.view.fragments.FavoritesTabFragment;
import home.mad.simpleshop.view.fragments.SearchTabFragment;

/**
 * Created by mad on 01.12.2016.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> names;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> names) {
        super(fm);
        this.fragments = fragments;
        this.names = names;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.size() < position){
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names.size() < position ? names.get(position) : null;
    }
}
