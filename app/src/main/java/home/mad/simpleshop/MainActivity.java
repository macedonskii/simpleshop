package home.mad.simpleshop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import home.mad.simpleshop.view.ActivityCallback;
import home.mad.simpleshop.view.fragments.TabsFragment;

public class MainActivity extends AppCompatActivity implements ActivityCallback{

    final String TAG = getClass().getSimpleName();

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null)fragmentManager.beginTransaction().replace(R.id.container,new TabsFragment()).commit();
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
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
