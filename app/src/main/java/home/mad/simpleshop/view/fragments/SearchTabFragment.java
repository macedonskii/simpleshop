package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.CategoryDTO;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.SearchPresenter;
import home.mad.simpleshop.presenter.adapters.CategoriesAdapter;
import home.mad.simpleshop.view.SearchView;

/**
 * Created by mad on 01.12.2016.
 */

//public class SearchTabFragment extends BaseFragment implements SearchView {
public class SearchTabFragment extends BaseFragment implements SearchView {
    @Bind(R.id.submit)
    Button submit;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.item_name)
    EditText keywords;

    private SearchPresenter presenter;
    private CategoriesAdapter adapter;

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) presenter = new SearchPresenter(this);
        if (adapter == null) adapter = new CategoriesAdapter(getContext(), presenter.getCachedList());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_search, container, false);
        ButterKnife.bind(this,view);
        spinner.setAdapter(adapter);
        submit.setOnClickListener(l -> presenter.onClickSearch(adapter.getQueryName(spinner.getSelectedItemId()),keywords.getText().toString()));
        return view;
    }

    @Override
    public void showEmptyList() {
        Toast.makeText(getContext(), "Item list is empty!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListItems(List<ItemDTO> items, String category, String keywords) {
        activityCallback.showFragment(SearchResultFragment.getInstance().setItems(items).setCategory(category).setKeywords(keywords));
    }

    @Override
    public void setCategories(List<CategoryDTO> categories) {
        adapter.setItems(categories);
    }
}
