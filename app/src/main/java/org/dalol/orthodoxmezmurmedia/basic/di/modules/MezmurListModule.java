package org.dalol.orthodoxmezmurmedia.basic.di.modules;

import android.content.Context;

import org.dalol.orthodoxmezmurmedia.application.MezmurApplication;
import org.dalol.orthodoxmezmurmedia.basic.di.scopes.PerJourney;
import org.dalol.presenter.presentation.mezmur.MezmurListView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
@Module
public class MezmurListModule {

    private MezmurListView mMezmurListView;

    public MezmurListModule(MezmurListView listView) {
        mMezmurListView = listView;
    }

    @PerJourney
    @Provides
    MezmurListView provideView() {
        return mMezmurListView;
    }

    @PerJourney
    @Provides
    Context provideContext() {
        return MezmurApplication.getInstance();
    }
}
