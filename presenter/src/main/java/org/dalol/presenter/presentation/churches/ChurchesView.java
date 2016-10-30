package org.dalol.presenter.presentation.churches;

import org.dalol.model.churches.Church;
import org.dalol.presenter.presentation.base.BaseView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/30/2016
 */
public interface ChurchesView extends BaseView {

    void onLoadChurches(Church[] churches);
}
