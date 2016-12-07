package home.mad.simpleshop.view;

import home.mad.simpleshop.model.dto.ItemDTO;

/**
 * Created by mad on 05.12.2016.
 */
public interface SearchResultView extends View{

    void onItemClick(ItemDTO item);
}
