package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.view.ActivityCallback;
import home.mad.simpleshop.view.View;

public abstract class  BaseFragment extends Fragment implements View {

    protected String TAG = getClass().getSimpleName();
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
        setRetainInstance(true);

    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void showError(Throwable throwable) {
        activityCallback.showError(throwable);
    }
}
