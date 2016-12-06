package home.mad.simpleshop.other.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mad on 06.12.2016.
 */
@Module
public class ContextModule {
    private Context context;


    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }
}
