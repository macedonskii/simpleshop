package home.mad.simpleshop.other.di;

import javax.inject.Singleton;

import dagger.Component;
import home.mad.simpleshop.model.dto.ModelImpl;
import home.mad.simpleshop.presenter.BasePresenter;

/**
 * Created by mad on 05.12.2016.
 */
@Singleton
@Component(modules = {PresenterModule.class, ModelModule.class})
public interface AppComponent {

    void inject(BasePresenter presenter);

    void inject(ModelImpl model);

}
