package home.mad.simpleshop.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.other.custom.CheckableFloatingActionButton;
import home.mad.simpleshop.presenter.FullFragmentPresenter;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.view.FullItemView;

/**
 * Created by mad on 07.12.2016.
 */

public class FullItemFragment extends BaseFragment implements FullItemView {

    private FullFragmentPresenter presenter = new FullFragmentPresenter();
    @Bind(R.id.backdrop)
    ImageView image;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.button)
    CheckableFloatingActionButton button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_item, container, false);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter.getItem() == null) throw new RuntimeException("Item to show == null");
        Picasso.with(getContext()).load(presenter.getItem().getImageBig()).into(image);
        description.setText(presenter.getItem().getDescription());
        title.setText(presenter.getItem().getTitle());
        price.setText(presenter.getItem().getPriceString());
        button.setChecked(presenter.getItem().isFavorites());
        button.setOnCheckListener((CheckableFloatingActionButton button, boolean b) -> presenter.onButtonCheckClick(b));
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    public static FullItemFragment getInstance() {
        return new FullItemFragment();
    }

    public FullItemFragment setItem(ItemDTO item) {
        presenter.setItem(item);
        return this;
    }
}
