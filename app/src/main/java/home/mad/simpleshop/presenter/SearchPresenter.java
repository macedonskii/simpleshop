package home.mad.simpleshop.presenter;


import java.util.ArrayList;
import java.util.List;

import home.mad.simpleshop.model.dto.CategoryDTO;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.model.mapers.CategoryMaper;
import home.mad.simpleshop.model.mapers.JsonMaper;
import home.mad.simpleshop.other.Const;
import home.mad.simpleshop.view.SearchView;
import rx.Observer;
import rx.Subscription;

/**
 * Created by mad on 04.12.2016.
 */

public class SearchPresenter extends BasePresenter {


    SearchView view;
    private List<CategoryDTO> cachedList;

    public SearchPresenter(SearchView view) {
        super();
        this.view = view;
        cachedList = loadCategoriesList();
        loadCategories();
    }

    public void onClickSearch(String category, String keywords){
        Subscription subscribe = model.getGoods(category, keywords).
                map(new JsonMaper()).subscribe(new Observer<List<ItemDTO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
            }

            @Override
            public void onNext(List<ItemDTO> items) {
                if (items.size() == 0){
                    view.showEmptyList();
                }else{
                    view.showListItems(items);
                }
            }
        });
        compositeSubscription.add(subscribe);

    }

    private void loadCategories(){
        Subscription subscribe = model.getCategories().map(new CategoryMaper()).subscribe(new Observer<List<CategoryDTO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
            }

            @Override
            public void onNext(List<CategoryDTO> categories) {
                saveCategoriesList(categories);
                cachedList = categories;
                view.setCategories(categories);
            }
        });
        compositeSubscription.add(subscribe);
    }

    public List<CategoryDTO> getCachedList() {
        return cachedList;
    }

    private void saveCategoriesList(List<CategoryDTO> list){
        StringBuilder builder = new StringBuilder();
        for (CategoryDTO dto : list) {
            builder.append(dto.getLongName()).append("-").append(dto.getName()).append(";");
        }
        preferences.edit().putString(Const.Preferences.KEY_CATEGORY_LIST,builder.toString()).commit();
    }

    private List<CategoryDTO> loadCategoriesList(){
        ArrayList<CategoryDTO> list = new ArrayList<>();
        String string = preferences.getString(Const.Preferences.KEY_CATEGORY_LIST,"");
        if (!string.equals("")){
            String [] array = string.split(";");
            String [] tmp;
            for (int i = 0; i < array.length; i++) {

                tmp = array[i].split("-");
                if (tmp.length == 2) list.add(new CategoryDTO(tmp[0],tmp[1]));
            }
        }
        return list;
    }
}
