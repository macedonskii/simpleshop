package home.mad.simpleshop.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.other.custom.CustomGrigManager;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.SearchResultPresenter;
import home.mad.simpleshop.presenter.adapters.SearchVTAdapter;
import home.mad.simpleshop.view.SearchResultView;

/**
 * Created by mad on 05.12.2016.
 */

public class SearchResultFragment extends BaseFragment implements SearchResultView {


    private SearchResultPresenter presenter = new SearchResultPresenter();
    private SearchVTAdapter adapter;

    @Bind(R.id.contentView)
    RecyclerView contentView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            presenter.setView(this);
            adapter = new SearchVTAdapter(getContext(), presenter.getItems(), presenter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridLayoutManager manager = new GridLayoutManager(getContext(), calculateNoOfColumns(getContext()));
        contentView.setLayoutManager(manager);
        contentView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onSwipeRefresh());
    }

    public static SearchResultFragment getInstance() {
        return new SearchResultFragment();
    }

    @Override
    public void onItemClick(ItemDTO item) {
        activityCallback.showFragment(FullItemFragment.getInstance().setItem(item));
    }

    public SearchResultFragment setItems(List<ItemDTO> items) {
        presenter.setItems(items);
        return this;
    }

    public SearchResultFragment setCategory(String category) {
        presenter.setCategory(category);
        return this;
    }

    public SearchResultFragment setKeywords(String keywords) {
        presenter.setKeywords(keywords);
        return this;
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / context.getResources().getDimension(R.dimen.column_width_grid_view));
        return Math.max(1, noOfColumns);
    }

    @Override
    public void onEmptyList() {
        // TODO: 08.12.2016 show all!
    }

    @Override
    public void onListLoad(List<ItemDTO> itemDTOs) {
        adapter.setList(itemDTOs);
    }

    @Override
    public void onNextPartDownloaded(List<ItemDTO> items) {
        adapter.addList(items);
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

}
