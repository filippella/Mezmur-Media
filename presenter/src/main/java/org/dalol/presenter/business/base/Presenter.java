package org.dalol.presenter.business.base;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public interface Presenter {

    void onCreate();

    void onViewReady();

    void onDestroy();
}
