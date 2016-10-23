package org.dalol.presenter.data.books;

import android.content.Context;

import com.google.gson.Gson;

import org.dalol.model.books.HolyContentBook;
import org.dalol.presenter.business.base.BaseProvider;
import org.dalol.presenter.utitlities.FileUtils;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public class HolyBooksContentProvider extends BaseProvider<HolyContentBook> {

    @Inject protected Context mContext;
    @Inject protected Gson mGson;
    @Inject protected String mContentToLoad;

    @Inject
    public HolyBooksContentProvider() {
    }

    @Override
    protected HolyContentBook doBackgroundWork() {
        String holyContent = FileUtils.loadJSONFromAsset(mContext, mContentToLoad);
        return mGson.fromJson(holyContent, HolyContentBook.class);
    }
}
