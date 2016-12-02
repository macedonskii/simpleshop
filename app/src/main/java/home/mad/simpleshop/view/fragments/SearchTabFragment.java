package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import home.mad.simpleshop.R;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.view.SearchView;

/**
 * Created by mad on 01.12.2016.
 */

//public class SearchTabFragment extends BaseFragment implements SearchView {
public class SearchTabFragment extends Fragment implements SearchView {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_search, container, false);
        return view;
    }

}
