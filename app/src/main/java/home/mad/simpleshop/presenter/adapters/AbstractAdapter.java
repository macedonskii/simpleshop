package home.mad.simpleshop.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


public abstract class AbstractAdapter extends RecyclerView.Adapter<AbstractAdapter.ViewHolder> {
    protected Context context;
    protected List<ItemDTO> items;
    protected LayoutInflater inflater;
    protected ItemClick itemClick;
    protected Map<Integer, ItemDTO> buttonsAssoc = new HashMap<>();
    protected Map<Integer, ItemDTO> backgroundAssoc = new HashMap<>();

    public AbstractAdapter(Context context, List<ItemDTO> items, ItemClick itemClick) {
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
        Log.d("Adapter", "onBindViewHolder() called with: position = [" + position + "]");
        if (position == getItemCount() - 1)
            startLoadNewList();
    }

    private void setValues(ViewHolder holder, ItemDTO item) {
        buttonsAssoc.put(holder.checkBox.hashCode(), item);
        backgroundAssoc.put(holder.background.hashCode(), item);
        holder.title.setText(item.getTitle());
        Picasso.with(context).load(item.getImageMedium()).into(holder.image);
        holder.checkBox.setChecked(item.isFavorites());
        holder.checkBox.setOnClickListener(view -> onFavoritesClick(buttonsAssoc.get(view.hashCode()), ((CheckBox) view).isChecked()));
        holder.background.setOnClickListener((View v) -> {
            onItemClick(backgroundAssoc.get(v.hashCode()));
        });


    }

    public abstract void onFavoritesClick(ItemDTO item, boolean isChecked);

    public abstract void onItemClick(ItemDTO item);

    public abstract void startLoadNewList();

    public void setList(List<ItemDTO> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addList(List<ItemDTO> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(ItemDTO item) {
        int i = items.indexOf(item);
        removeItem(i);
    }

    public interface ItemClick {
        void onItemClick(ItemDTO item);

        void onFavoritesClick(ItemDTO item, boolean checked);
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