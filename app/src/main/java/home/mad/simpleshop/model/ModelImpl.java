package home.mad.simpleshop.model;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import home.mad.simpleshop.model.api.ApiInterface;
import home.mad.simpleshop.model.dto.ItemDTO;
import home.mad.simpleshop.model.mapers.JsonMaper;
import home.mad.simpleshop.model.mapers.ConfirmFavorites;
import home.mad.simpleshop.model.sqlite.Database;
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

    @Inject
    Database database;

    public ModelImpl(){
        App.getGraph().inject(this);
    }

    @Override
    public Observable<JsonObject> getCategories() {
        return api.getCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<ItemDTO>> getFavorites() {
        return database.getFavorites(null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<ItemDTO>> getGoods(String category, String itemName, int offset) {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put(Const.CATEGORY, category);
        values.put(Const.KEYWORDS, itemName);
        values.put(Const.INCLUDES, Const.IMAGES);
        if (offset != 0) values.put(Const.OFFSET, String.valueOf(offset));
        return api.getItems(values).map(new JsonMaper()).zipWith(getFavorites(category),new ConfirmFavorites()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void addFavorite(ItemDTO item) {
        database.addFavorite(item);
    }

    @Override
    public Observable<List<ItemDTO>> getFavorites(String category) {
        return database.getFavorites(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void removeFavorite(long id) {
        database.removeFavorite(id);
    }

    @Override
    public Observable<Boolean> isItemRemoved(long listingId) {
        return database.isItemRemoved(listingId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Boolean> isItemAdd(long listingId) {
        return database.isItemAdd(listingId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
