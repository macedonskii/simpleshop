package home.mad.simpleshop.view;

/**
 * Created by mad on 01.12.2016.
 */

public interface View {

    void showLoadingState();

    void hideLoadingState();

    void showError(Exception exception);
}
