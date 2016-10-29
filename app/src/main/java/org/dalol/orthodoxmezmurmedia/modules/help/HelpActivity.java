package org.dalol.orthodoxmezmurmedia.modules.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public class HelpActivity extends BaseActivity {

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showHome();
        setTitle("Help");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_help;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }
}
