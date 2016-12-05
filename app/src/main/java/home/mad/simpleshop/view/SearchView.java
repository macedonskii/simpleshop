package home.mad.simpleshop.view;

import java.util.List;

import home.mad.simpleshop.model.dto.ItemsDTO;

public interface SearchView extends View {

    void showEmptyList();

    void showListItems(List<ItemsDTO> items);

}
