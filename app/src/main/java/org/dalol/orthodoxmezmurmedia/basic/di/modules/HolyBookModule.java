package org.dalol.orthodoxmezmurmedia.basic.di.modules;

import android.content.Context;

import org.dalol.orthodoxmezmurmedia.application.MezmurApplication;
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

    private final HolyBookView mView;

    public HolyBookModule(HolyBookView view) {
        mView = view;
    }

    @Provides
    @PerJourney
    HolyBookView provideView() {
        return mView;
    }
}
