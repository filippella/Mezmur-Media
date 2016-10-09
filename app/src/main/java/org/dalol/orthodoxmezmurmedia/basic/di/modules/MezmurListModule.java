package org.dalol.orthodoxmezmurmedia.basic.di.modules;

import org.dalol.model.mezmur.MezmurListItem;
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
    private final MezmurListItem mListItem;

    public MezmurListModule(MezmurListView listView, MezmurListItem listItem) {
        mMezmurListView = listView;
        mListItem = listItem;
    }

    @PerJourney
    @Provides
    MezmurListView provideView() {
        return mMezmurListView;
    }

    @PerJourney
    @Provides
    MezmurListItem provideListItem() {
        return mListItem;
    }
}
