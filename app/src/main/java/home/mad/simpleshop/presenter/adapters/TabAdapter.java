package home.mad.simpleshop.presenter.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import home.mad.simpleshop.view.fragments.FavoritesTabFragment;
import home.mad.simpleshop.view.fragments.SearchTabFragment;


public class TabAdapter extends FragmentPagerAdapter {
    final String TAG = getClass().getSimpleName();

    private List<Fragment> fragments;
    private List<String> names;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> names) {
        super(fm);
        this.fragments = fragments;
        this.names = names;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem() called with: position = [" + position + "]");
        if (fragments.size() > position){
            return fragments.get(position);
        }
        throw new RuntimeException("Position > fragments count");
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "getPageTitle() called with: position = [" + position + "]");
        return names.size() > position ? names.get(position) : null;
    }
}
