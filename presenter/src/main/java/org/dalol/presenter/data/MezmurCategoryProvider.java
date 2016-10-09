package org.dalol.presenter.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dalol.model.mezmur.Mezmur;
import org.dalol.model.mezmur.MezmurCategory;
import org.dalol.model.mezmur.MezmurDataBinder;
import org.dalol.model.mezmur.MezmurListItem;
import org.dalol.presenter.business.base.BaseProvider;
import org.dalol.presenter.data.common.FileUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public class MezmurCategoryProvider extends BaseProvider<List<MezmurListItem>> {

    @Inject protected Context mContext;
    @Inject protected Gson mGson;

    @Inject
    public MezmurCategoryProvider() {
    }

    @Override
    protected List<MezmurListItem> doBackgroundWork() {
        String dataToCategoryBinder = FileUtils.loadJSONFromAsset(mContext, "mezmur_category_to_data_binder.json");
        String categoriesJson = FileUtils.loadJSONFromAsset(mContext, "mezmur_categories.json");
        String mezmurJson = FileUtils.loadJSONFromAsset(mContext, "mezmur_data.json");

        List<MezmurListItem> mezmurListItems = new ArrayList<>();

        MezmurDataBinder[] mezmurDataBinders = mGson.fromJson(dataToCategoryBinder, MezmurDataBinder[].class);
        MezmurCategory[] mezmurCategories = mGson.fromJson(categoriesJson, MezmurCategory[].class);

        List<Mezmur> mezmurs = mGson.fromJson(mezmurJson, new TypeToken<List<Mezmur>>() {
        }.getType());


        for (int i = 0; i < mezmurCategories.length; i++) {

            MezmurCategory category = mezmurCategories[i];

            String cid = category.getCid();
            String morder = category.getMorder();
            String name = category.getName();

            MezmurListItem listItem = new MezmurListItem(cid, name, morder);
            for (int j = 0; j < mezmurDataBinders.length; j++) {
                MezmurDataBinder mezmurDataBinder = mezmurDataBinders[j];
                String dataBinderCid = mezmurDataBinder.getCid();
                if (dataBinderCid != null && dataBinderCid.equals(cid)) {
                    listItem.addMezmurId(mezmurDataBinder.getMid());
                }
            }
            mezmurListItems.add(listItem);
        }


//        for (Mezmur mezmur : mezmurs) {
//            for (MezmurDataBinder dataBinder : mezmurDataBinders) {
//                if (dataBinder.getMid() == mezmur.getMid()) {
//                    for (MezmurCategory mezmurCategory : mezmurCategories) {
//                        String cid = mezmurCategory.getCid();
//                        if (cid == dataBinder.getCid()) {
//                            mezmurListItems.add(new MezmurListItem(cid, mezmurCategory.getName(), mezmurCategory.getMorder(), mezmur));
//                        }
//                    }
//                    break;
//                }
//            }
//        }
        return mezmurListItems;
    }
}
