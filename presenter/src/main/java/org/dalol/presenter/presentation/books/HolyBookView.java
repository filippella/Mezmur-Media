package org.dalol.presenter.presentation.books;

import org.dalol.model.books.HolyContentBook;
import org.dalol.presenter.presentation.base.BaseView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public interface HolyBookView extends BaseView {

    /**
     * This method is used to display the holy content in the view
     *
     * @param contentBook
     */
    void onLoadContent(HolyContentBook contentBook);
}
