package home.mad.simpleshop;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import home.mad.simpleshop.presenter.adapters.TabAdapter;
import home.mad.simpleshop.view.ActivityCallback;
import home.mad.simpleshop.view.fragments.FavoritesTabFragment;
import home.mad.simpleshop.view.fragments.SearchTabFragment;
import home.mad.simpleshop.view.fragments.TabsFragment;

public class MainActivity extends AppCompatActivity implements ActivityCallback{


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        setupViewPager();
//
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null)fragmentManager.beginTransaction().replace(R.id.container,new TabsFragment()).addToBackStack(null).commit();
    }

//    public void setupViewPager() {
//        ArrayList<Fragment> fragments = new ArrayList<>();
//        ArrayList<String> names = new ArrayList<>();
//        fragments.add(new SearchTabFragment());
//        fragments.add(new FavoritesTabFragment());
//        names.add("search");
//        names.add("favorites");
//        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments, names);
//        viewPager.setAdapter(adapter);
//    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
    }

    @Override
    public void showError() {

    }
}
