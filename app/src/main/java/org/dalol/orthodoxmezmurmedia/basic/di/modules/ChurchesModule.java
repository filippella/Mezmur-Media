package org.dalol.orthodoxmezmurmedia.basic.di.modules;

import android.content.Context;

import org.dalol.orthodoxmezmurmedia.application.MezmurApplication;
import org.dalol.orthodoxmezmurmedia.basic.di.scopes.PerJourney;
import org.dalol.presenter.presentation.churches.ChurchesView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/30/2016
 */
@Module
public class ChurchesModule {

    private final ChurchesView mView;

    public ChurchesModule(ChurchesView view) {
        mView = view;
    }

    @PerJourney
    @Provides
    ChurchesView provideView() {
        return mView;
    }

    @PerJourney
    @Provides
    Context provideContext() {
        return MezmurApplication.getInstance();
    }
}
