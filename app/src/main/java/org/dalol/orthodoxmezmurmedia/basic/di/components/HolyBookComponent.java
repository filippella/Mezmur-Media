package org.dalol.orthodoxmezmurmedia.basic.di.components;

import org.dalol.orthodoxmezmurmedia.basic.di.modules.HolyBookModule;
import org.dalol.orthodoxmezmurmedia.basic.di.scopes.PerJourney;
import org.dalol.orthodoxmezmurmedia.modules.holybooks.HolyBooksActivity;

import dagger.Component;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
@PerJourney
@Component(modules = HolyBookModule.class, dependencies = ApplicationComponent.class)
public interface HolyBookComponent {

    void inject(HolyBooksActivity activity);
}
