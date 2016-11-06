package org.dalol.orthodoxmezmurmedia.modules.player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.modules.home.MezmurDashboardActivity;

import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public class MezmursPlayer extends BaseActivity {

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showHome();
        setTitle("Mezmur Player");
    }

    @OnClick(R.id.start_playing_button)
    void onPlayClick() {
        startService(new Intent(this, MezmurPlayerService.class));
    }

    @OnClick(R.id.stop_playing_button)
    void onStopClick() {
        stopService(new Intent(this, MezmurPlayerService.class));
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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                //NavUtils.navigateUpFromSameTask(this);;
//                //Intent upIntent = NavUtils.getParentActivityIntent(this);
//
//                Intent upIntent = new Intent(this, MezmurDashboardActivity.class);
//
//                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
//                    // This activity is not part of the application's task, so
//                    // create a new task
//                    // with a synthesized back stack.
//                    TaskStackBuilder
//                            .from(this)
//                            .addNextIntent(new Intent(this, MezmurDashboardActivity.class))
//                            .addNextIntent(upIntent).startActivities();
//                    finish();
//                } else {
//                    // This activity is part of the application's task, so simply
//                    // navigate up to the hierarchical parent activity.
//                    NavUtils.navigateUpTo(this, upIntent);
//                }
//
////                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
////                    TaskStackBuilder.create(this)
////                            .addNextIntentWithParentStack(upIntent)
////                            .startActivities();
////                } else {
////                    NavUtils.navigateUpTo(this, upIntent);
////                }
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }
}
