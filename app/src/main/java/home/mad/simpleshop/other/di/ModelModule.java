package home.mad.simpleshop.other.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import home.mad.simpleshop.model.api.ApiInterface;
import home.mad.simpleshop.model.api.ApiModule;
import home.mad.simpleshop.other.Const;

/**
 * Created by mad on 05.12.2016.
 */
@Module
public class ModelModule {

    @Provides
    @Singleton
    ApiInterface getApiInterface(){return ApiModule.getApiInterface(Const.BASE_URL);}
}
