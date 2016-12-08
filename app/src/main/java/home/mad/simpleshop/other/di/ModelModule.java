package home.mad.simpleshop.other.di;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import home.mad.simpleshop.model.api.ApiInterface;
import home.mad.simpleshop.model.api.ApiModule;
import home.mad.simpleshop.model.sqlite.Database;
import home.mad.simpleshop.model.sqlite.DatabaseImpl;
import home.mad.simpleshop.other.Const;

/**
 * Created by mad on 05.12.2016.
 */
@Module
public class ModelModule {

    @Provides
    @Singleton
    ApiInterface getApiInterface() {
        return ApiModule.getApiInterface(Const.BASE_URL);
    }

    @Provides
    @Singleton
    Database getDatabase(Context context) {
        return new DatabaseImpl(context);
    }

    @Provides
    @Singleton
    SharedPreferences getSharedPreferences(Context context){return context.getSharedPreferences(Const.SHARED_PREFERENCES, Context.MODE_PRIVATE);}


}
