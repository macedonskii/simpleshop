package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.other.custom.ViewHelper;
import home.mad.simpleshop.presenter.FavoritesPresenter;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.adapters.FavoritesAdapter;
import home.mad.simpleshop.view.FavoritesView;


public class FavoritesTabFragment extends BaseFragment implements FavoritesView {


    @BindView(R.id.contentView)
    RecyclerView contentView;
    @BindView(R.id.emptyListTV)
    TextView emptyList;
    private FavoritesPresenter presenter;
    FavoritesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FavoritesPresenter(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_favorites, container, false);
        ButterKnife.bind(this, view);
        if (adapter == null)
            adapter = new FavoritesAdapter(getContext(), new ArrayList<>(), presenter);
        presenter.loadItems();
        contentView.setLayoutManager(new GridLayoutManager(getContext(), ViewHelper.calculateNoOfColumns(getContext())));
        contentView.setAdapter(adapter);
        return view;
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void showEmptyList() {
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(ItemDTO item) {
        activityCallback.showFragment(FullItemFragment.getInstance().setItem(item));
    }

    @Override
    public void showFavoritesList(List<ItemDTO> items) {
        emptyList.setVisibility(View.GONE);
        adapter.setList(items);
    }



}
