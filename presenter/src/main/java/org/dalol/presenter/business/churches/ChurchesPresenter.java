/*
 * Copyright (c) 2016 Amharic Mezmur Lyrics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.presenter.business.churches;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dalol.model.churches.Church;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public class ChurchesPresenter {


    public void getChurches(ChurchListener listener) {

        AssetManager assetManager = listener.getAppResources().getAssets();
        try {
            InputStream stream = assetManager.open("churches.json");

            BufferedReader in =  new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String str;

            StringBuffer buffer = new StringBuffer();

            while ((str=in.readLine()) != null) {
                buffer.append(str);
            }

            in.close();

//            Type type = new TypeToken<List<Church>>(){}.getType();
//
//            List<Church> churches = new Gson().fromJson(buffer.toString(), type);
//
//            for (int i = 0; i < churches.size(); i++) {
//                Church mezmur = churches.get(i);
//                listener.onChurch(mezmur);
//            }

        } catch (IOException e) {
            Log.d("MezmurListsActivity", "Total Count of mezmur UNKNOWN - > " +e.getMessage());
            e.printStackTrace();
        }
    }

    public interface ChurchListener {

        Resources getAppResources();

        void onChurch(Church church);
    }
}
