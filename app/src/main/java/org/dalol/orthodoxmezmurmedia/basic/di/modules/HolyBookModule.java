package org.dalol.orthodoxmezmurmedia.basic.di.modules;

import org.dalol.orthodoxmezmurmedia.basic.di.scopes.PerJourney;
import org.dalol.presenter.presentation.books.HolyBookView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
@Module
public class HolyBookModule {

    private final String mHolyContentJson;
    private final HolyBookView mView;

    public HolyBookModule(HolyBookView view, String holyContentJson) {
        mView = view;
        mHolyContentJson = holyContentJson;
    }

    @Provides
    @PerJourney
    HolyBookView provideView() {
        return mView;
    }

    @Provides
    @PerJourney
    String provideHolyContent() {
        return mHolyContentJson;
    }
}
