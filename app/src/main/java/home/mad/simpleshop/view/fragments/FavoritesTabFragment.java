package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.FavoritesPresenter;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.adapters.SearchResultAdapter;
import home.mad.simpleshop.view.FavoritesView;

/**
 * Created by mad on 01.12.2016.
 */

public class FavoritesTabFragment extends BaseFragment implements FavoritesView {


    GridView gridView;
    private FavoritesPresenter presenter;
    SearchResultAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_favorites, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        if (presenter == null) {
            presenter = new FavoritesPresenter(this);
        } else {
            presenter.setView(this);
        }
        if (adapter == null) adapter = new SearchResultAdapter(getContext(), new ArrayList<>(), presenter);
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void showEmptyList() {

    }

    @Override
    public void onItemClick(ItemDTO item) {
        activityCallback.showFragment(FullItemFragment.getInstance().setItem(item));
    }

    @Override
    public void showFavoritesList(List<ItemDTO> items) {
        adapter.setList(items);
    }
}
