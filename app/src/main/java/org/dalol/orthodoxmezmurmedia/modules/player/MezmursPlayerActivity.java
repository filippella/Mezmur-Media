package org.dalol.orthodoxmezmurmedia.modules.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.utilities.widgets.ZoomableWebView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public class MezmursPlayerActivity extends BaseActivity {

    @BindView(R.id.mezmur_lyrics_content) protected ZoomableWebView mMezmurLyrics;
    @BindView(R.id.mezmur_progress_slider) protected SeekBar mMezmurProgress;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showHome();
        setTitle("Mezmur Player");

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mMezmurProgress.getLayoutParams();
        params.setMargins(0,0,0,0);
        mMezmurProgress.setPadding(0,0,0,0);

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

    @OnClick(R.id.start_playing_button)
    void onPlayClick() {
        Intent intent = new Intent(this, MezmurPlayerService.class);
        intent.setAction("play");
        startService(intent);
    }

    @OnClick(R.id.stop_playing_button)
    void onStopClick() {
        Intent intent = new Intent(this, MezmurPlayerService.class);
        intent.setAction("stop");
        startService(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mezmurs_player;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "New Intent Detected", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MezmurPlayerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mezmur_player, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_mezmur_queue:
                Toast.makeText(this, "Show Mezmur Queue!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    private MezmurPlayerService mService;
    private boolean mBound;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            MezmurPlayerService.MezmurServiceBinder binder = (MezmurPlayerService.MezmurServiceBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
