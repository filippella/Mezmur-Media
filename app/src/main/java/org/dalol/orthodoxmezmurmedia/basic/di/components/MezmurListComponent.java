package org.dalol.orthodoxmezmurmedia.basic.di.components;

import org.dalol.orthodoxmezmurmedia.basic.di.modules.MezmurListModule;
import org.dalol.orthodoxmezmurmedia.basic.di.scopes.PerJourney;
import org.dalol.orthodoxmezmurmedia.modules.mezmur.MezmurListsActivity;

import dagger.Component;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
@PerJourney
@Component(modules = MezmurListModule.class, dependencies = ApplicationComponent.class)
public interface MezmurListComponent {

    void inject(MezmurListsActivity activity);
}
