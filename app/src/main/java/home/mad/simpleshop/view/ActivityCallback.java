package home.mad.simpleshop.view;

import android.support.v4.app.Fragment;

/**
 * Created by mad on 01.12.2016.
 */

public interface ActivityCallback {

    void showFragment(Fragment fragment);

    void hideProgressBar();

    void showProgressBar();

    void showError();
}
