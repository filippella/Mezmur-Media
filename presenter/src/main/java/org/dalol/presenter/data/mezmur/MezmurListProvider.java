package org.dalol.presenter.data.mezmur;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dalol.model.mezmur.Mezmur;
import org.dalol.model.mezmur.MezmurListItem;
import org.dalol.presenter.business.base.BaseProvider;
import org.dalol.presenter.utitlities.FileUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public class MezmurListProvider extends BaseProvider<List<Mezmur>> {

    private MezmurListItem mListItem;

    @Inject protected Context mContext;
    @Inject protected Gson mGson;

    @Inject
    public MezmurListProvider() {
    }

    @Override
    protected List<Mezmur> doBackgroundWork() {
        String mezmurJson = FileUtils.loadJSONFromAsset(mContext, "mezmur_data.json");

        List<String> mezmurIdList = mListItem.getMezmurIdList();
        List<Mezmur> mezmurs = mGson.fromJson(mezmurJson, new TypeToken<List<Mezmur>>() {
        }.getType());

        List<Mezmur> mezmurList = new ArrayList<>();
        for (int i = 0; i < mezmurIdList.size(); i++) {
            String mezmurId = mezmurIdList.get(i);
            for (int j = 0; j < mezmurs.size(); j++) {
                Mezmur mezmur = mezmurs.get(j);
                String mid = mezmur.getMid();
                if(mezmur != null && mid != null && mid.equals(mezmurId)) {
                    mezmurList.add(mezmur);
                }
            }
        }
        return mezmurList;
    }

    public void setListItem(MezmurListItem listItem) {
            mListItem = listItem;
    }
}
