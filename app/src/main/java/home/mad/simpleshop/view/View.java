package home.mad.simpleshop.view;


public interface View {

    void showLoadingState();

    void hideLoadingState();

    void showError(Throwable throwable);
}
