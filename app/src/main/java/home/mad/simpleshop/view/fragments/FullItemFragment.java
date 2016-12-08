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
import home.mad.simpleshop.presenter.FullFragmentPresenter;
import home.mad.simpleshop.presenter.Presenter;
import home.mad.simpleshop.view.FullItemView;

/**
 * Created by mad on 07.12.2016.
 */

public class FullItemFragment extends BaseFragment implements FullItemView {
    private ItemDTO item;
    private FullFragmentPresenter presenter;
    @Bind(R.id.backdrop)
    ImageView image;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.price)
    TextView price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_item, container, false);
        ButterKnife.bind(this,view);
        if (presenter == null){
            presenter = new FullFragmentPresenter(this);
        }else{
            presenter.setView(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (item == null) throw new RuntimeException("Item to show == null");
        Picasso.with(getContext()).load(item.getImageBig()).into(image);
        description.setText(item.getDescription());
        title.setText(item.getTitle());
        price.setText(item.getPriceString());
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    public static FullItemFragment getInstance() {
        return new FullItemFragment();
    }

    public FullItemFragment setItem(ItemDTO item) {
        this.item = item;
        return this;
    }
}
