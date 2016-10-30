package org.dalol.orthodoxmezmurmedia.basic.di.components;

import org.dalol.orthodoxmezmurmedia.basic.di.modules.ChurchesModule;
import org.dalol.orthodoxmezmurmedia.basic.di.scopes.PerJourney;
import org.dalol.orthodoxmezmurmedia.modules.churches.ChurchListActivity;

import dagger.Component;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/30/2016
 */
@PerJourney
@Component(modules = ChurchesModule.class, dependencies = ApplicationComponent.class)
public interface ChurchesComponent {

    void inject(ChurchListActivity activity);
}
