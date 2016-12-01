package home.mad.simpleshop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import home.mad.simpleshop.view.ActivityCallback;
import home.mad.simpleshop.view.fragments.TabsFragment;

public class MainActivity extends AppCompatActivity implements ActivityCallback{

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) fragmentManager.beginTransaction().replace(R.id.container,new TabsFragment()).addToBackStack(null).commit();
        
    }

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
