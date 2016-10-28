package org.dalol.presenter.business.common;

import org.dalol.presenter.business.base.BaseProvider;
import org.dalol.presenter.utitlities.FileUtils;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/28/2016
 */
public class JsonArrayDataProvider<T> extends BaseProvider<T[]> {

    @Inject
    public JsonArrayDataProvider() {
    }

    @Override
    protected T[] doBackgroundWork() {
        String content = FileUtils.loadJSONFromAsset(mContext, mFileName);
        return mGson.fromJson(content, mClass);
    }
}
