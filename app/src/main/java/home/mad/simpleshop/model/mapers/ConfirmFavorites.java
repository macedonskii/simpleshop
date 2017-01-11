package home.mad.simpleshop.model.mapers;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import rx.functions.Func2;



public class ConfirmFavorites implements Func2<List<ItemDTO>, List<ItemDTO>, List<ItemDTO>> {
    @Override
    public List<ItemDTO> call(List<ItemDTO> items1, List<ItemDTO> items2) {
        for (ItemDTO item : items1) {
            for (ItemDTO itemDTO : items2) {
                if (item.getListingId() == itemDTO.getListingId()) {
                    item.setFavorites(true);
                    break;
                }
            }
        }
        return items1;
    }
}
