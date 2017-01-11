package home.mad.simpleshop.presenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import home.mad.simpleshop.R;
import home.mad.simpleshop.model.dto.CategoryDTO;

public class CategoriesAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<CategoryDTO> items;

    public CategoriesAdapter(Context context, List<CategoryDTO> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CategoryDTO getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = inflater.inflate(R.layout.spinner_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(getItem(i).getLongName());
        return view;
    }

    public String getQueryName(long position){
        return getItem((int) position).getName();
    }

    public void setItems(List<CategoryDTO> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    protected class ViewHolder{
        @Bind(R.id.textItem)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }


}
