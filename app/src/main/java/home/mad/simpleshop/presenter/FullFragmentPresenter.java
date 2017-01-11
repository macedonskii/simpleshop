package home.mad.simpleshop.presenter;

import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.view.FullItemView;
import home.mad.simpleshop.view.fragments.FullItemFragment;


public class FullFragmentPresenter extends BasePresenter {
    FullItemView view;
    private ItemDTO item;

    public FullFragmentPresenter() {

    }

    public void setView(FullItemView view) {
        this.view = view;
    }

    public void onButtonCheckClick(boolean b) {
        item.setFavorites(b);
        if (b){
            model.addFavorite(item);
        }else{
            model.removeFavorite(item.getListingId());
        }
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public ItemDTO getItem() {
        return item;
    }
}
