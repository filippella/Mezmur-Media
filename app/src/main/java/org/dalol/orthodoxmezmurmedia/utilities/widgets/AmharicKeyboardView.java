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
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.utilities.common.KeyboardUtils;
import org.dalol.orthodoxmezmurmedia.utilities.keyboard.AmharicKeyboardManager;
import org.dalol.orthodoxmezmurmedia.utilities.keyboard.KeyboardKey;
import org.dalol.orthodoxmezmurmedia.utilities.keyboard.KeyboardRow;

import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/10/2016
 */
public class AmharicKeyboardView extends LinearLayout {

    private static final String TAG = AmharicKeyboardView.class.getSimpleName();

    private static final float PORTRAIT_HEIGHT_SCALE = 2.56f;
    private static final int LANDSCAPE_HEIGHT_SCALE = 2;
    private static final int ROW_COUNT = 5;
    private final static int INITIAL_INTERVAL = 400;

    private Window mWindow;
    private Activity mActivity;
    private ViewGroup mRootView;
    private boolean mShowing;

    private int mKeyboardHeight;
    private EditText mEditText;
    private LinearLayout modifiersContainer;
    private Typeface mCharTypeface;

    private boolean mEnableModifierFlag;
    private boolean shouldVibrate;
    private List<KeyboardRow> mKeyboardRows;

    private View mPressedKeyView;
    private int mNormalInterval = 100;
    private Handler mHandler = new Handler();

    public AmharicKeyboardView(Context context) {
        this(context, null, 0);
    }

    public AmharicKeyboardView(Context context, List<KeyboardRow> keyboardRows) {
        super(context);
        initialize(context, keyboardRows);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        AmharicKeyboardManager manager = new AmharicKeyboardManager();
        initialize(context, manager.getKeyboardStructure());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        AmharicKeyboardManager manager = new AmharicKeyboardManager();
        initialize(context, manager.getKeyboardStructure());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        handleLayoutParams();
    }

    public void setCharTypeface(Typeface typeface) {
        mCharTypeface = typeface;
    }

    public void setShouldVibrate(boolean shouldVibrate) {
        this.shouldVibrate = shouldVibrate;
    }

    /**
     * This method is used to setup the various object configurations
     *
     * @param context      can be also referenced through {@link #getContext()}
     * @param keyboardRows
     */
    private void initialize(Context context, List<KeyboardRow> keyboardRows) {
        mKeyboardRows = keyboardRows;
        verifyInitMode();
        mActivity = (Activity) context;
        mWindow = mActivity.getWindow();
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setOrientation(VERTICAL);
        setVisibility(GONE);
        setWillNotDraw(true);
        setBackgroundColor(Color.parseColor("#a1a6aa"));

        //mCharTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/nyala.ttf");
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
                createKeyboard(mKeyboardRows);
            }
        });
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Consume event[intercept touch for not letting pass and click views under the keyboard]
                return true;
            }
        });
    }

    private void verifyInitMode() {
        if (isInEditMode()) {
            return;
        }
    }

    private void createKeyboard(List<KeyboardRow> keyboardRows) {
        removeAllViews();
        resolveKeyboardHeight();

        int keyHeight = Math.round(mKeyboardHeight / ROW_COUNT);

        modifiersContainer = new LinearLayout(getContext());
        modifiersContainer.setOrientation(HORIZONTAL);
        modifiersContainer.setBackgroundColor(Color.parseColor("#7996ad"));
        modifiersContainer.setGravity(Gravity.CENTER);
        addView(modifiersContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyHeight));

        for (int i = 0; i < keyboardRows.size(); i++) {
            KeyboardRow keyboardRow = keyboardRows.get(i);
            populateKeyboardRow(keyboardRow, keyHeight);
        }
    }

    private void populateKeyboardRow(KeyboardRow keyboardRow, int keyHeight) {
        Context context = getContext();
        LinearLayout keyContainer = new LinearLayout(context);
        keyContainer.setOrientation(HORIZONTAL);
        keyContainer.setGravity(Gravity.CENTER);

        List<KeyboardKey> keyList = keyboardRow.getKeyList();
        if (keyList != null) {
            for (int i = 0; i < keyList.size(); i++) {
                KeyboardKey keyboardKey = keyList.get(i);
                if (keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_NORMAL) {
                    TextView key = new TextView(context);
                    key.setGravity(Gravity.CENTER);
                    key.setTypeface(mCharTypeface, Typeface.BOLD);
                    key.setText(keyboardKey.getCharCode());
                    key.setTextSize(16f);
                    key.setTextColor(ContextCompat.getColorStateList(context, R.color.amharic_key_text_color_selector));
                    key.setTag(keyboardKey);
                    key.setIncludeFontPadding(false);
                    keyContainer.setBaselineAligned(false);
                    handleChild(key, keyboardKey.getColumnCount(), keyContainer, keyHeight);
                } else if (keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_BACKSPACE ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_SPACE ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_NEW_LINE ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_ENTER ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_HIDE_KEYBOARD) {
                    ImageView child = new ImageView(context);
                    child.setImageResource(keyboardKey.getCommandImage());
                    int padding = getCustomSize(6);
                    child.setPadding(padding, padding, padding, padding);
                    child.setTag(keyboardKey);
                    handleChild(child, keyboardKey.getColumnCount(), keyContainer, keyHeight);
                }
            }
        }
        addView(keyContainer, new LayoutParams(LayoutParams.MATCH_PARENT, keyHeight));
    }

    private int getCustomSize(float size) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics()));
    }

    private void handleChild(View child, int columnCount, LinearLayout keyContainer, int keyHeight) {
        child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.amharic_key_bg));
        child.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (shouldVibrate) {
                            Vibrator vb = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                            if (vb != null) {
                                vb.vibrate(20);
                            }
                        }
                        mPressedKeyView = v;
                        mHandler.removeCallbacks(mKeyPressRunnable);
                        mHandler.postAtTime(mKeyPressRunnable, mPressedKeyView, SystemClock.uptimeMillis() + INITIAL_INTERVAL);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                        mHandler.removeCallbacksAndMessages(mPressedKeyView);
                        mPressedKeyView = null;
                        mNormalInterval = 100;
                        break;
                }
                return false;
            }
        });
        child.setOnClickListener(mKeyClickListener);
        int margin = getCustomSize(1.5f);
        int tempKeyHeight = keyHeight - (margin * 2);
        LayoutParams params = new LayoutParams(0, tempKeyHeight, columnCount);
        params.setMargins(margin, margin, margin, margin);
        keyContainer.addView(child, params);
    }

    private final View.OnClickListener mKeyClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mEditText == null) {
                return;
            }
            boolean skipDelete = false;
            if (mEditText.hasSelection()) {
                int startSelection = mEditText.getSelectionStart();
                int endSelection = mEditText.getSelectionEnd();
                Editable editable = mEditText.getText();
                editable.delete(startSelection, endSelection);
                skipDelete = true;
            }
            KeyboardKey tag = (KeyboardKey) v.getTag();
            if (tag.getKeyCommand() == KeyboardKey.KEY_EVENT_NORMAL) {
                processKeyInput((TextView) v);
                mEnableModifierFlag = true;
            } else if (tag.getKeyCommand() == KeyboardKey.KEY_EVENT_BACKSPACE && !skipDelete) {
                int start = mEditText.getSelectionStart();
                if (start == 0) return;
                mEditText.getText().delete(start - 1, start);
                if (mEditText.getText().toString().isEmpty()) modifiersContainer.removeAllViews();
            } else if (tag.getKeyCommand() == KeyboardKey.KEY_EVENT_SPACE ||
                    tag.getKeyCommand() == KeyboardKey.KEY_NEW_LINE) {
                int start = mEditText.getSelectionStart();
                mEditText.getText().insert(start, tag.getCharCode());
            } else if (tag.getKeyCommand() == KeyboardKey.KEY_HIDE_KEYBOARD) {
                hideCustomKeyboard();
            }
        }
    };

    public void handleEditText(EditText editText) {
        mEditText = editText;
        if (mEditText == null) {
            throw new NullPointerException("EditText should not be null!");
        }
        initializeEditText();
    }

    private void initializeEditText() {
        mEditText.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                KeyboardUtils.hideSoftKeyboard(mActivity, v);
                showCustomKeyboard();
                return true;
            }
        });

        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showCustomKeyboard();
                } else {
                    hideCustomKeyboard();
                }
            }
        });
    }

    private void processKeyInput(TextView textView) {
        if (mEditText != null) {
            if (!mEditText.isFocused()) mEditText.requestFocus();
            Editable editableText = mEditText.getText();
            int start = mEditText.getSelectionStart();
            if (start == -1) return;
            editableText.insert(start, textView.getText());
            KeyboardKey tag = (KeyboardKey) textView.getTag();
            List<String> modifierList = tag.getKeyModifiers();
            handleModifiers(modifierList);
        }
    }

    private void handleModifiers(List<String> typographyList) {
        modifiersContainer.removeAllViews();

        for (int i = 0; i < typographyList.size(); i++) {
            String typography = typographyList.get(i);
            TextView modifierKey = new TextView(getContext());
            modifierKey.setText(typography);
            modifierKey.setTextColor(Color.WHITE);
            modifierKey.setIncludeFontPadding(false);
            modifierKey.setTypeface(mCharTypeface, Typeface.BOLD);
            modifierKey.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            modifierKey.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.keyboard_modifierkey_bg));
            modifierKey.setTextSize(18f);
            modifierKey.setGravity(Gravity.CENTER);

            modifierKey.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEditText == null) {
                        return;
                    }
                    TextView textView = (TextView) v;
                    if (!mEditText.isFocused()) mEditText.requestFocus();
                    Editable editableText = mEditText.getText();
                    int start = mEditText.getSelectionStart();
                    if (start == 0) return;

                    CharSequence textViewText = textView.getText();
                    if (mEnableModifierFlag) {
                        mEnableModifierFlag = false;
                        editableText.replace(start - 1, start, textViewText);
                    } else {
                        editableText.insert(start, textViewText);
                    }
                }
            });

            LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            int margin = getCustomSize(1.5f);
            params.setMargins(margin, margin, margin, margin);
            modifiersContainer.addView(modifierKey, params);
        }
    }

    public boolean isShowing() {
        return mShowing;
    }

    public void showCustomKeyboard() {
        if (mShowing) return;
        handleLayoutParams();
        setVisibility(VISIBLE);
        animateKeyboard(mKeyboardHeight, 0);
        mRootView.addView(AmharicKeyboardView.this);
        mShowing = true;
    }

    private void handleLayoutParams() {
        resolveKeyboardHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                mKeyboardHeight);
        params.gravity = Gravity.BOTTOM;
        setLayoutParams(params);
    }

    public void hideCustomKeyboard() {
        animateKeyboard(0, mKeyboardHeight);
        setVisibility(GONE);
        mRootView.removeView(AmharicKeyboardView.this);
        mShowing = false;
    }

    private Runnable mKeyPressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mPressedKeyView == null) {
                return;
            }
            mHandler.removeCallbacksAndMessages(mPressedKeyView);
            mHandler.postAtTime(this, mPressedKeyView, SystemClock.uptimeMillis() + mNormalInterval);
            mKeyClickListener.onClick(mPressedKeyView);
            if (mNormalInterval > 50) {
                mNormalInterval -= 10;
            }
        }
    };

    /**
     * This method is used to animate the keyboard up and down
     *
     * @param from the initialDelta
     * @param to   the destinationDelta
     */
    private void animateKeyboard(float from, float to) {
        Animation animation = new TranslateAnimation(0, 0, from, to);
        animation.setDuration(250L);
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

    public void clearControl() {
        if (mEditText == null) {
            throw new NullPointerException("EditText should not be null!");
        }
        mEditText.setOnTouchListener(null);
        mEditText.setOnFocusChangeListener(null);
        hideCustomKeyboard();
        KeyboardUtils.showSoftKeyboard(mActivity, mEditText);
    }

    public void gainControl() {
        if (mEditText == null) {
            throw new NullPointerException("EditText should not be null!");
        }
        KeyboardUtils.hideSoftKeyboard(mActivity, mEditText);
        initializeEditText();
        showCustomKeyboard();
    }
}
