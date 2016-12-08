package home.mad.simpleshop.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
 * Created by mad on 08.12.2016.
 */

public class SearchVTAdapter extends RecyclerView.Adapter<SearchVTAdapter.ViewHolder> {
    private Context context;
    private List<ItemDTO> items;
    private LayoutInflater inflater;
    private SearchResultAdapter.ItemClick itemClick;
    private Map<Integer, ItemDTO> buttonsAssoc = new HashMap<>();
    private Map<Integer, ItemDTO> backgroundAssoc = new HashMap<>();

    public SearchVTAdapter(Context context, List<ItemDTO> items, SearchResultAdapter.ItemClick itemClick) {
        this.context = context;
        this.itemClick = itemClick;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.grid_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setValues(holder, items.get(position));
    }

    private void setValues(ViewHolder holder, ItemDTO item) {
        buttonsAssoc.put(holder.checkBox.hashCode(), item);
        backgroundAssoc.put(holder.background.hashCode(), item);
        holder.title.setText(item.getTitle());
        Picasso.with(context).load(item.getImageMedium()).into(holder.image);
        holder.checkBox.setChecked(item.isFavorites());
        holder.checkBox.setOnClickListener(view -> itemClick.onFavoritesClick(buttonsAssoc.get(view.hashCode()), ((CheckBox) view).isChecked()));
        holder.background.setOnClickListener((View v) -> {
            itemClick.onItemClick(backgroundAssoc.get(v.hashCode()));
        });
    }

    public void setList(List<ItemDTO> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addList(List<ItemDTO> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.button)
        CheckBox checkBox;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.background)
        RelativeLayout background;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
