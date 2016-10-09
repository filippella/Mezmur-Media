package org.dalol.presenter.presentation.mezmur;

import org.dalol.model.mezmur.Mezmur;
import org.dalol.presenter.presentation.base.BaseView;

import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public interface MezmurListView extends BaseView {

    void onPopulateMezmurList(List<Mezmur> mezmurs);
}
