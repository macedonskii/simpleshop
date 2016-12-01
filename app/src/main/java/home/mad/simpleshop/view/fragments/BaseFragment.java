package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.view.ActivityCallback;
import home.mad.simpleshop.view.View;

/**
 * Created by mad on 01.12.2016.
 */

public abstract class  BaseFragment extends Fragment implements View {

    protected ActivityCallback activityCallback;

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null){
            getPresenter().onStop();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            activityCallback = (ActivityCallback) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().getClass().getSimpleName() + " must implement " + ActivityCallback.class.getSimpleName());
        }
    }

    @Override
    public void showLoadingState() {
        activityCallback.showProgressBar();
    }

    @Override
    public void hideLoadingState() {
        activityCallback.hideProgressBar();
    }

    @Override
    public void showError() {
        activityCallback.showError();
    }
}
