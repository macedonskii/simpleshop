package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.presenter.SearchPresenter;
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

    SearchPresenter presenter;

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) presenter = new SearchPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_search, container, false);
        ButterKnife.bind(this,view);
        ArrayList<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");

        spinner.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,list) );
        submit.setOnClickListener(l -> presenter.onClickSearch(spinner.getSelectedItem().toString(),keywords.getText().toString()));
//        submit.setOnClickListener(l -> presenter.onClickSearch("",""));
        return view;
    }

    @Override
    public void showEmptyList() {
        Toast.makeText(getContext(), "Item list is empty!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListItems(List<ItemDTO> items) {
        activityCallback.showFragment(SearchResultFragment.getInstance().setItems(items));
    }

}
