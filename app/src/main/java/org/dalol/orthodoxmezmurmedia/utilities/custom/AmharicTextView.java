package org.dalol.orthodoxmezmurmedia.utilities.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public class AmharicTextView extends TextView {

    private static final String FONTS_NYALA_TTF = "fonts/nyala.ttf";

    public AmharicTextView(Context context) {
        super(context);
        initialize(context);
    }

    public AmharicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public AmharicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmharicTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        if(isInEditMode()) {
            return;
        }
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), FONTS_NYALA_TTF);
        setTypeface(typeface);
    }
}
