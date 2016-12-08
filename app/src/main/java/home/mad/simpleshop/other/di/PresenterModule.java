package home.mad.simpleshop.other.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import home.mad.simpleshop.model.Model;
import home.mad.simpleshop.model.ModelImpl;

/**
 * Created by mad on 05.12.2016.
 */
@Module
public class PresenterModule {
    @Provides
    @Singleton
    Model getModel(){return new ModelImpl();}
}
