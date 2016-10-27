package org.dalol.orthodoxmezmurmedia.basic.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/27/2016
 */
public class BaseDialog extends AlertDialog {

    protected BaseDialog(@NonNull Context context) {
        super(context);
        initialize(context);
    }

    protected BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initialize(context);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initialize(context);
    }

    private void initialize(Context context) {
        setContentView(getContentView());
    }

    public int getContentView() {
        return 0;
    }
}
