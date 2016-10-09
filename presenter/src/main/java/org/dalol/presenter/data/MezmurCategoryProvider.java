package org.dalol.presenter.data;

import android.content.Context;

import com.google.gson.Gson;

import org.dalol.model.mezmur.MezmurCategory;
import org.dalol.presenter.business.base.BaseProvider;
import org.dalol.presenter.data.common.FileUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public class MezmurCategoryProvider extends BaseProvider<List<MezmurCategory>> {

    @Inject protected Context mContext;
    @Inject protected Gson mGson;

    @Inject
    public MezmurCategoryProvider() {
    }

    @Override
    protected List<MezmurCategory> doBackgroundWork() {
        String json = FileUtils.loadJSONFromAsset(mContext, "mezmur_categories.json");
        MezmurCategory[] mezmurCategories = mGson.fromJson(json, MezmurCategory[].class);
        return Arrays.asList(mezmurCategories);
    }
}
