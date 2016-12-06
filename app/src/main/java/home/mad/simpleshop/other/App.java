package home.mad.simpleshop.other;

import android.app.Application;

import home.mad.simpleshop.other.di.AppComponent;
import home.mad.simpleshop.other.di.ContextModule;
import home.mad.simpleshop.other.di.DaggerAppComponent;

/**
 * Created by mad on 05.12.2016.
 */

public class App extends Application{

    private static AppComponent graph;

    public static AppComponent getGraph(){
        return graph;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        graph = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }
}
