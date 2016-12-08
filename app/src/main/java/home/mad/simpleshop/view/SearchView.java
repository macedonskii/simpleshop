package home.mad.simpleshop.view;

import java.util.List;

import home.mad.simpleshop.model.dto.CategoryDTO;
import home.mad.simpleshop.model.dto.ItemDTO;

public interface SearchView extends View {

    void showEmptyList();

    void showListItems(List<ItemDTO> items, String category, String keywords);

    void setCategories(List<CategoryDTO> categories);
}
