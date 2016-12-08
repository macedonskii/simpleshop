package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.SearchResultPresenter;
import home.mad.simpleshop.presenter.adapters.SearchVTAdapter;
import home.mad.simpleshop.view.SearchResultView;

/**
 * Created by mad on 05.12.2016.
 */

public class SearchResultFragment extends BaseFragment implements SearchResultView {

    private List<ItemDTO> items;
    private SearchResultPresenter presenter;
    private SearchVTAdapter adapter;

    private String category;
    private String keywords;

    @Bind(R.id.contentView)
    RecyclerView contentView;

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            presenter = new SearchResultPresenter(this);
            adapter = new SearchVTAdapter(getContext(), items, presenter);
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
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        contentView.setLayoutManager(manager);


        contentView.setAdapter(adapter);
    }

    public static SearchResultFragment getInstance() {
        return new SearchResultFragment();
    }

    @Override
    public void onItemClick(ItemDTO item) {
        activityCallback.showFragment(FullItemFragment.getInstance().setItem(item));
    }

    public SearchResultFragment setItems(List<ItemDTO> items) {
        this.items = items;
        return this;
    }

    public SearchResultFragment setCategory(String category) {
        this.category = category;
        return this;
    }

    public SearchResultFragment setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }
}
