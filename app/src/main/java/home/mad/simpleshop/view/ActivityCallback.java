package home.mad.simpleshop.view;

import android.support.v4.app.Fragment;


public interface ActivityCallback {

    void showFragment(Fragment fragment);

    void hideProgressBar();

    void showProgressBar();

    void showError(Throwable throwable);
}
