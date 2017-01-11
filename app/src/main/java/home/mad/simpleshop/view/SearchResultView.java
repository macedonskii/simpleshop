package home.mad.simpleshop.view;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;

public interface SearchResultView extends View{

    void onItemClick(ItemDTO item);

    void onListLoad(List<ItemDTO> itemDTOs);



    void stopRefresh();

    void onNextPartDownloaded(List<ItemDTO> items);
}
