package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.SearchResultPresenter;
import home.mad.simpleshop.presenter.adapters.SearchResultAdapter;
import home.mad.simpleshop.view.SearchResultView;

/**
 * Created by mad on 05.12.2016.
 */

public class SearchResultFragment extends BaseFragment implements SearchResultView {

    private List<ItemDTO> items;
    private SearchResultPresenter presenter;

    @Bind(R.id.searchGridView)
    GridView gridView;

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        if (presenter == null) {
            presenter = new SearchResultPresenter(this);
        }
        gridView.setAdapter(new SearchResultAdapter(getContext(), items, presenter));
        return view;
    }

    public static SearchResultFragment getInstance() {
        return new SearchResultFragment();
    }

    public SearchResultFragment setItems(List<ItemDTO> items) {
        this.items = items;
        return this;
    }

    @Override
    public void onItemClick(ItemDTO item) {
        activityCallback.showFragment(FullItemFragment.getInstance().setItem(item));
    }
}
