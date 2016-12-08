package home.mad.simpleshop.model.api;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by mad on 02.12.2016.
 */

public interface ApiInterface {

    @GET("/v2/listings/active")
    Observable<JsonObject> getItems(@QueryMap Map<String, String> options);

    @GET("/v2/taxonomy/categories")
    Observable<JsonObject>  getCategories();


}
