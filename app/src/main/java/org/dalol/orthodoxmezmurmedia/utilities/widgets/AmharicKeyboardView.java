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

package org.dalol.orthodoxmezmurmedia.utilities.widgets;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.utilities.common.KeyboardUtils;
import org.dalol.orthodoxmezmurmedia.utilities.custom.AmharicTextView;
import org.dalol.orthodoxmezmurmedia.utilities.helpers.ITypography;
import org.dalol.orthodoxmezmurmedia.utilities.helpers.KeyTypography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/10/2016
 */
public class AmharicKeyboardView extends LinearLayout {

    private static final String TAG = AmharicKeyboardView.class.getSimpleName();

    private static final Map<ITypography, List<ITypography>> KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1 = new ConcurrentHashMap<>();
    private static final Map<ITypography, List<ITypography>> KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_2 = new HashMap<>();
    private static final Map<ITypography, List<ITypography>> KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_3 = new HashMap<>();
    private static final Map<ITypography, List<ITypography>> KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_4 = new HashMap<>();

    private static final float PORTRAIT_HEIGHT_SCALE = 2.56f;
    private static final int LANDSCAPE_HEIGHT_SCALE = 2;

    private Window mWindow;
    private Activity mActivity;
    private ViewGroup mRootView;
    private boolean showing;

    private char[] mAmharicChars = {'\u134A', '\u120A', '\u1356'};

    private int mKeyboardHeight;
    private EditText mEditText;
    private List<View> mModifierViews = new ArrayList<>();
    private LinearLayout modifiersContainer;
    private Typeface mGeezTypeface;

    public AmharicKeyboardView(Context context) {
        this(context, null, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        handleLayoutParams();
    }

    /**
     * This method is used to setup the various object configurations
     *
     * @param context can be also referenced through {@link #getContext()}
     */
    private void initialize(Context context) {
        verifyInitMode();
        Collections.synchronizedMap(KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1);
        mActivity = (Activity) context;
        mWindow = mActivity.getWindow();
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        setBackgroundColor(Color.parseColor("#3b494c"));
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setVisibility(GONE);
        setWillNotDraw(true);
        //setBackgroundColor(Color.parseColor("#474745"));
//        setBackgroundColor(Color.parseColor("#636363"));
//        setBackgroundColor(Color.parseColor("#44546A"));
//        setBackgroundColor(Color.parseColor("#526A76"));
        //setBackgroundColor(Color.parseColor("#3299FF"));
//        setBackgroundColor(Color.parseColor("#666666"));
        //setBackgroundColor(Color.parseColor("#DADBE0"));


        mGeezTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/nyala.ttf");
        setBackgroundColor(Color.parseColor("#000000"));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked on the keyboard layout!", Toast.LENGTH_SHORT).show();
            }
        });
        mRootView = (ViewGroup) mWindow.getDecorView().findViewById(android.R.id.content);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver viewTreeObserver = mRootView.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    } else {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    }
                }
                createKeyboard();
            }
        });
    }

    private void verifyInitMode() {
        if (isInEditMode()) {
            return;
        }
    }

    private void createKeyboard() {
        removeAllViews();
        resolveKeyboardHeight();

        int ROW = 5;

        int keyHeight = Math.round(mKeyboardHeight / ROW);

        modifiersContainer = new LinearLayout(getContext());
        modifiersContainer.setOrientation(HORIZONTAL);
        modifiersContainer.setBackgroundColor(Color.parseColor("#636363"));
        modifiersContainer.setGravity(Gravity.CENTER);
        addView(modifiersContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyHeight));

        populateCharactersRow(KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1, keyHeight);
        populateCharactersRow(KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1, keyHeight);
        populateCharactersRow(KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1, keyHeight);
        populateCharactersRow(KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1, keyHeight);


//        for (Map.Entry<String, String> entry : test.entrySet()) {
//            entry.getKey();
//            entry.getValue();
//        }

//        populateCharactersRow(keyHeight);
//        populateCharactersRow(keyHeight);
//        populateCharactersRow(keyHeight);
//        populateCharactersRow(keyHeight);
    }

    private void populateCharactersRow(Map<ITypography, List<ITypography>> typographyListMap, int keyHeight) {

        LinearLayout keyContainer = new LinearLayout(getContext());
        keyContainer.setOrientation(HORIZONTAL);
        keyContainer.setGravity(Gravity.CENTER);


        for (Map.Entry<ITypography, List<ITypography>> listEntry : typographyListMap.entrySet()) {

            ITypography key = listEntry.getKey();
            List<ITypography> value = listEntry.getValue();

            Button child = new Button(getContext());
            child.setText(Character.toString(key.getFidelUnicode()));
            child.setTextSize(16f);
            child.setGravity(Gravity.CENTER);
            child.setTypeface(mGeezTypeface);
            child.setTag(value);

            child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
            //child.setVisibility(GONE);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    processKeyInput((Button) v);
                    Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            });

            FrameLayout childContainer = new FrameLayout(getContext());
            childContainer.setPadding(4, 4, 4, 4);

            FrameLayout.LayoutParams childParams =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            childParams.gravity = Gravity.CENTER;

            childContainer.addView(child, childParams);

            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = key.getColumnCount();
            params.gravity = Gravity.CENTER;
            keyContainer.addView(childContainer, params);

        }
        addView(keyContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyHeight));
    }


    private void populateCharactersRow(int keyHeight) {
        LinearLayout keyContainer = new LinearLayout(getContext());
        keyContainer.setOrientation(HORIZONTAL);
        keyContainer.setGravity(Gravity.CENTER);

        for (int i = 0; i < 11; i++) {
            Button child = new Button(getContext());
            child.setText(Integer.toString(new Random().nextInt(10)));
            child.setTextSize(16f);
            child.setGravity(Gravity.CENTER);
            child.setTypeface(mGeezTypeface);

            child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
            //child.setVisibility(GONE);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    processKeyInput((Button) v);
                    Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            });

            FrameLayout childContainer = new FrameLayout(getContext());
            childContainer.setPadding(4, 4, 4, 4);

            FrameLayout.LayoutParams childParams =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            childParams.gravity = Gravity.CENTER;

            childContainer.addView(child, childParams);

            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            keyContainer.addView(childContainer, params);
        }

        addView(keyContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyHeight));
    }

    private void populateKeys(int keyHeight) {

        LinearLayout modifiersContainer = new LinearLayout(getContext());
        modifiersContainer.setOrientation(HORIZONTAL);
        modifiersContainer.setBackgroundColor(Color.parseColor("#636363"));
        modifiersContainer.setGravity(Gravity.CENTER);

        for (int i = 0; i < 0; i++) {
            AmharicTextView child = new AmharicTextView(getContext());
            child.setText(Integer.toString(new Random().nextInt(10)));
            child.setTextSize(16f);
            child.setGravity(Gravity.CENTER);
//            FrameLayout.LayoutParams childParams =
//                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//            childParams.gravity = Gravity.CENTER;
            child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
            //child.setVisibility(GONE);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            });

//            FrameLayout childContainer = new FrameLayout(getContext());
//            childContainer.setPadding(6, 6, 6, 6);
//            childContainer.addView(child);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            if (i == 6) params.weight = 2;
            else params.weight = 1;
            params.gravity = Gravity.CENTER;
            params.setMargins(4, 4, 4, 4);
            modifiersContainer.addView(child, params);
        }
        addView(modifiersContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyHeight));
    }

    public void setEditText(EditText editText) {
        mEditText = editText;
        mEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                KeyboardUtils.hideSoftKeyboard(mActivity, v);
                showMyKeyboard();
                return true;
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showMyKeyboard();
                } else {
                    hideMyKeyboard();
                }
            }
        });
    }


    private void processKeyInput(Button button) {
        if (mEditText != null) {
            if (!mEditText.isFocused()) mEditText.requestFocus();
            Editable editableText = mEditText.getText();
            int start = mEditText.getSelectionStart();
            if (start == -1) return;


            //Object tag = button.getTag();

            editableText.insert(start, button.getText());

            List<ITypography> tag = (List<ITypography>) button.getTag();
            handleTags(tag);
            //handleKeyTouchExtra();
            //playClick(0);
            //mKeyClickListener.onTextChanged(editableText.toString());
        }
    }

    private void handleTags(List<ITypography> typographyList) {
        modifiersContainer.removeAllViews();

        for (int i = 0; i < typographyList.size(); i++) {
            ITypography typography = typographyList.get(i);
            Button child = new Button(getContext());
            child.setText(Character.toString(typography.getFidelUnicode()));
            child.setTextSize(16f);
            child.setGravity(Gravity.CENTER);
            child.setTypeface(mGeezTypeface);

//            FrameLayout.LayoutParams childParams =
//                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//            childParams.gravity = Gravity.CENTER;
            child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
            //child.setVisibility(GONE);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            });

//            FrameLayout childContainer = new FrameLayout(getContext());
//            childContainer.setPadding(6, 6, 6, 6);
//            childContainer.addView(child);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = typography.getColumnCount();
            params.gravity = Gravity.CENTER;
            params.setMargins(4, 4, 4, 4);
            modifiersContainer.addView(child, params);
        }
    }

    private void handleKeyTouchExtra() {
        modifiersContainer.removeAllViews();
        int rand = new Random().nextInt(8);
        for (int i = 0; i < rand; i++) {
            AmharicTextView child = new AmharicTextView(getContext());
            child.setText(Integer.toString(new Random().nextInt(10)));
            child.setTextSize(16f);
            child.setGravity(Gravity.CENTER);
//            FrameLayout.LayoutParams childParams =
//                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//            childParams.gravity = Gravity.CENTER;
            child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
            //child.setVisibility(GONE);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            });

//            FrameLayout childContainer = new FrameLayout(getContext());
//            childContainer.setPadding(6, 6, 6, 6);
//            childContainer.addView(child);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            if (i == 6) params.weight = 2;
            else params.weight = 1;
            params.gravity = Gravity.CENTER;
            params.setMargins(4, 4, 4, 4);
            modifiersContainer.addView(child, params);
        }
    }

    private void handleKeyTouch() {
        for (View view : mModifierViews) {
            view.setVisibility(VISIBLE);
            AmharicTextView textView = (AmharicTextView) view;
            textView.setText(Character.toString(mAmharicChars[new Random().nextInt(mAmharicChars.length - 1)]));
        }
    }

//    private void playClick(int keyCode) {
//        AudioManager am = (AudioManager) getContext().getSystemService(AUDIO_SERVICE);
//        switch (keyCode) {
//            case 32:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
//                break;
//            case Keyboard.KEYCODE_DONE:
//            case 10:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
//                break;
//            case Keyboard.KEYCODE_DELETE:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
//                break;
//            default:
//                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
//        }
//    }

    public boolean isShowing() {
        return showing;
    }

    public void showMyKeyboard() {
        if (showing) return;
        handleLayoutParams();
        setVisibility(VISIBLE);
        animateKeyboard(mKeyboardHeight, 0);
        mRootView.addView(AmharicKeyboardView.this);
        showing = true;
    }

    private void handleLayoutParams() {
        resolveKeyboardHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                mKeyboardHeight);
        params.gravity = Gravity.BOTTOM;
        setLayoutParams(params);
    }

    public void hideMyKeyboard() {
        animateKeyboard(0, mKeyboardHeight);
        mRootView.removeView(AmharicKeyboardView.this);
        setVisibility(GONE);
        showing = false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean keyEventConsumed = false;
        if (showing) {
            hideMyKeyboard();
            keyEventConsumed = true;
        }
        return keyEventConsumed;
    }

    /**
     * This method is used to animate the keyboard up and down
     *
     * @param from the initialDelta
     * @param to   the destinationDelta
     */
    private void animateKeyboard(float from, float to) {
        Animation animation = new TranslateAnimation(0, 0, from, to);
        animation.setDuration(150L);
        animation.setFillAfter(false);
        startAnimation(animation);
    }

    /**
     * This method is responsible to handle the keyboard height based on the current orientation
     */
    private void resolveKeyboardHeight() {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int orientation = resources.getConfiguration().orientation;

        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                mKeyboardHeight = Math.round(displayMetrics.heightPixels / PORTRAIT_HEIGHT_SCALE);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                mKeyboardHeight = Math.round(displayMetrics.heightPixels / LANDSCAPE_HEIGHT_SCALE);
                break;
        }
    }

    static {
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.HA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.HA,
                KeyTypography.HU,
                KeyTypography.HI,
                KeyTypography.HAA,
                KeyTypography.HEE,
                KeyTypography.HE,
                KeyTypography.HO,
                KeyTypography.HOA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.LA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.LA,
                KeyTypography.LU,
                KeyTypography.LI,
                KeyTypography.LAA,
                KeyTypography.LEE,
                KeyTypography.LE,
                KeyTypography.LO,
                KeyTypography.LWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.HHA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.HHA,
                KeyTypography.HHU,
                KeyTypography.HHI,
                KeyTypography.HHAA,
                KeyTypography.HHEE,
                KeyTypography.HHE,
                KeyTypography.HHO,
                KeyTypography.HHWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.MA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.MA,
                KeyTypography.MU,
                KeyTypography.MI,
                KeyTypography.MAA,
                KeyTypography.MEE,
                KeyTypography.ME,
                KeyTypography.MO,
                KeyTypography.MWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.SZA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.SZA,
                KeyTypography.SZU,
                KeyTypography.SZI,
                KeyTypography.SZAA,
                KeyTypography.SZEE,
                KeyTypography.SZE,
                KeyTypography.SZO,
                KeyTypography.SZWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.RA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.RA,
                KeyTypography.RU,
                KeyTypography.RI,
                KeyTypography.RAA,
                KeyTypography.REE,
                KeyTypography.RE,
                KeyTypography.RO,
                KeyTypography.RWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.SA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.SA,
                KeyTypography.SU,
                KeyTypography.SI,
                KeyTypography.SAA,
                KeyTypography.SEE,
                KeyTypography.SE,
                KeyTypography.SO,
                KeyTypography.SWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.SHA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.SHA,
                KeyTypography.SHU,
                KeyTypography.SHI,
                KeyTypography.SHAA,
                KeyTypography.SHEE,
                KeyTypography.SHE,
                KeyTypography.SHO,
                KeyTypography.SHWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.QA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.QA,
                KeyTypography.QU,
                KeyTypography.QI,
                KeyTypography.QAA,
                KeyTypography.QEE,
                KeyTypography.QE,
                KeyTypography.QO,
                KeyTypography.QWAA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.BA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.BA,
                KeyTypography.BU,
                KeyTypography.BI,
                KeyTypography.BAA,
                KeyTypography.BEE,
                KeyTypography.BE,
                KeyTypography.BO,
                KeyTypography.BWA
        )));
        KEYBOARD_TYPOGRAPHY_LIST_MAP_ROW_1.put(KeyTypography.VA, new ArrayList<ITypography>(Arrays.asList(
                KeyTypography.VA,
                KeyTypography.VU,
                KeyTypography.VI,
                KeyTypography.VAA,
                KeyTypography.VEE,
                KeyTypography.VE,
                KeyTypography.VO,
                KeyTypography.VWA
        )));
    }
}
