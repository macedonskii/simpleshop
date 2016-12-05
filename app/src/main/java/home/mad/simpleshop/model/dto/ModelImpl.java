package home.mad.simpleshop.model.dto;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import home.mad.simpleshop.model.Model;
import home.mad.simpleshop.model.api.ApiInterface;
import home.mad.simpleshop.other.App;
import home.mad.simpleshop.other.Const;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mad on 04.12.2016.
 */

public class ModelImpl implements Model {
    @Inject
    ApiInterface api;

    public ModelImpl(){
        App.getGraph().inject(this);
    }

    @Override
    public Observable<JsonObject> getCategories() {
        return api.getCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public List<ItemDTO> getFavorites() {
        return new ArrayList<>();
    }

    //    https://openapi.etsy.com/v2/listings/active?api_key=l6pdqjuf7hdf97h1yvzadfce&category=paper_goods&keywords=terminator&includes=MainImage
    @Override
    public Observable<JsonObject> getGoods(String category, String itemName) {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put(Const.CATEGORY, (category != null && category.length() != 0) ? category : "paper_goods");
        values.put(Const.KEYWORDS, (itemName != null && itemName.length() != 0) ? itemName : "terminator");
        values.put(Const.INCLUDES, Const.IMAGES);
        return api.getItems(values).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
