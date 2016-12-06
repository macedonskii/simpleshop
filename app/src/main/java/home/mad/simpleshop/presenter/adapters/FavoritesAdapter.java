package home.mad.simpleshop.presenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;

/**
 * Created by mad on 02.12.2016.
 */

public class FavoritesAdapter extends BaseAdapter {

    List<ItemDTO> items;
    LayoutInflater layoutInflater;
    Context context;

    public FavoritesAdapter(List<ItemDTO> items, Context context) {
        this.items = items;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_view_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        setValues(holder, items.get(i));

        return view;
    }

    private void setValues(ViewHolder holder, ItemDTO item) {
//        holder.imageView.setImageResource(R.drawable.sample_0);
    }

    class ViewHolder {
        @Bind(R.id.button)
        CheckBox button;

        @Bind(R.id.image)
        ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
