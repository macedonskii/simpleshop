package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemsDTO;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.adapters.FavoritesAdapter;
import home.mad.simpleshop.view.FavoritesView;

/**
 * Created by mad on 01.12.2016.
 */

public class FavoritesTabFragment extends Fragment implements FavoritesView{


    GridView gridView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_favorites, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        ArrayList<ItemsDTO> items = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            items.add(new ItemsDTO());
        }
        gridView.setAdapter(new FavoritesAdapter(items, getContext()));
        return view;
    }

//    @Override
//    protected Presenter getPresenter() {
//        return null;
//    }
}
