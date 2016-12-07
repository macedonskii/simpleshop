package home.mad.simpleshop.presenter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.ItemDTO;

/**
 * Created by mad on 05.12.2016.
 */

public class SearchResultAdapter extends BaseAdapter {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<ItemDTO> items;
    private LayoutInflater inflater;
    private ItemClick itemClick;
    private Map<Integer, ItemDTO> buttonsAssoc = new HashMap<>();
    private Map<Integer, ItemDTO> backgroundAssoc = new HashMap<>();



    public SearchResultAdapter(Context context, List<ItemDTO> items, ItemClick clickListener) {
        this.context = context;
        this.items = items;
        this.itemClick = clickListener;
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

        buttonsAssoc.put(holder.checkBox.hashCode(),item);
        backgroundAssoc.put(holder.background.hashCode(), item);
//        Log.d(TAG, "setValues: association size = " + buttonsAssoc.keySet().size());
        holder.title.setText(item.getTitle());
        Picasso.with(context).load(item.getImageMedium()).into(holder.image);
        holder.checkBox.setChecked(item.isFavorites());
        holder.checkBox.setOnCheckedChangeListener((CompoundButton compoundButton, boolean b) -> {
//            Log.d(getClass().getSimpleName(), "setValues() called with: item = " + item);
            itemClick.onFavoritesClick(buttonsAssoc.get(compoundButton.hashCode()),b);
        });
        holder.background.setOnClickListener((View v) -> {
            itemClick.onItemClick(backgroundAssoc.get(v.hashCode()));
        });



    }

    public void setList(List<ItemDTO> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    protected class Holder {

        @Bind(R.id.button)
        CheckBox checkBox;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.background)
        RelativeLayout background;

        public Holder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    public interface ItemClick{
        void onItemClick(ItemDTO item);
        void onFavoritesClick(ItemDTO item, boolean checked);
    }
}
