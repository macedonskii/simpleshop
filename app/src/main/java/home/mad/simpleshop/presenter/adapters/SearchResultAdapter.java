package home.mad.simpleshop.presenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;

/**
 * Created by mad on 05.12.2016.
 */

public class SearchResultAdapter extends BaseAdapter {
    private Context context;
    private List<ItemDTO> items;
    private LayoutInflater inflater;

    public SearchResultAdapter(Context context, List<ItemDTO> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ItemDTO getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.grid_view_item,viewGroup,false);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        setValues(holder, getItem(i));
        return view;
    }

    private void setValues(Holder holder, ItemDTO item) {
        holder.title.setText(item.getTitle());
        Picasso.with(context).load(item.getImageMedium()).into(holder.image);

    }

    protected class Holder {

        @Bind(R.id.button)
        ImageButton button;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.image)
        ImageView image;

        public Holder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
