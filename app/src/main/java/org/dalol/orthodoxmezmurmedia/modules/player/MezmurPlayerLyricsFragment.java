/*
 * Copyright (c) 2016 Orthodox Mezmur Media
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.orthodoxmezmurmedia.modules.player;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.dalol.model.callback.OnPlayerMenuClickListener;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.utilities.widgets.ZoomableWebView;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/26/2016
 */
public class MezmurPlayerLyricsFragment extends BaseFragment {

    @BindView(R.id.mezmur_lyrics_content) protected ZoomableWebView mMezmurLyrics;

    public static MezmurPlayerLyricsFragment newInstance() {
        
        Bundle args = new Bundle();

        MezmurPlayerLyricsFragment fragment = new MezmurPlayerLyricsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        setHasOptionsMenu(true);

        mMezmurLyrics.setContent("በጥንታዊቷ ቤተክርስትያናችን ከሚገኙና፤ ሰይጣን ዲያብሎስን ከምንዋጋበት መሣሪያዎቻችን መካከል አንዱ፤ መቁጠሪያ ነው፡፡\n" +
                "\n" +
                "\"በአንተ ጠላቶቻችንን እንዎጋቸዋለን በስምሀም በላያችን የቆሙትን እናዋርዳቸዋለን መዝ. 43 (44):5\"\n" +
                "\n" +
                "በቤተክርስቲያናችን ካህናትና በንሰሐ አባቶቻችንና በተባረከ መቁጠሪያ በእግዚአብሔር አምላከ በኢየሱስ ክርስቶስ፣ በአመቤታችን ድንገል ማርያምና በቅዱሳን መላእክት ስም፤ ሁለቱ ትከሻዎቻችንን መኃል ጀርባችንን እንዲሁም፤ ሕመም የሚሰማን ቦታ ስንቀጠቅጥ፤\n" +
                "\n" +
                "• የማቃጠል ወይም የመለብለብ የመውረር ወይም የመንዘር\n" +
                "\n" +
                "• የመበላት ወይም የማሳከክ፤ ከአንዱ ሰውነት ክፍላችን ወደ ሌላው የመዞርና እንደ ድንጋይ በድን መሆን አንዲሁም\n" +
                "\n" +
                "• ጭንቅላታችንን ሁለት ከፍሎ፤ የራስ ምታት ዓይነት ስሜት ከተሰማን ሰይጣን በውስጣችን አለ ማለት ነው።\n" +
                "\n" +
                "ስለሆነም እግዚአብሔርን መንገድ ይዘን እየፆምን እየጸለይን እየተንበረከክን እየሰገድን እና ሥጋና ደሙን አየወስደን ከላይ እንደተገለጸው በመቁጠሪያችን እየቀጠቀጥን ክፉ መንፈሶችን በመታገል የእግዚአብሔር ልጆች መሆን እንችላለን። ከእግዚአብሔርን የሰጠን የተስፋው ኃይል፤ እንዲህ ይላል፤ \"እነሆ እባቡንና ጊንጡን ትረግጡ ዘንድ፡ በጠላትም ኃይል ሁሉ ላይ ሥልጣን ሰጥቻችኋለሁ የሚጐዳችሁም ምንም የለም።ሙ ሉቃስ 10:19\"");
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_mezmur_player_lyrics;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mezmur_player, menu);
        MenuItem menuItem = menu.findItem(R.id.action_swap_player_option);
        menuItem.setIcon(R.mipmap.ic_queue_music_white_24dp);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_swap_player_option:
                OnPlayerMenuClickListener listener = (OnPlayerMenuClickListener) getActivity();
                listener.onShowMezmurListFragment();
                Toast.makeText(getContext(), "Show Mezmur Queue!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
