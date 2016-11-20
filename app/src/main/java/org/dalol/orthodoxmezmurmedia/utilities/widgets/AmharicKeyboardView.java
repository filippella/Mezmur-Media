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
import android.util.Log;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    private Window mWindow;
    private Activity mActivity;
    private ViewGroup mRootView;
    private boolean mShowing;

    private int mKeyboardHeight;
    private EditText mEditText;
    private LinearLayout modifiersContainer;
    private Typeface mGeezTypeface;
    private Runnable mCallBack;
    private boolean processing;
    private boolean enableModifierFlag;
    private boolean shouldVibrate;

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

    public void setShouldVibrate(boolean shouldVibrate) {
        this.shouldVibrate = shouldVibrate;
    }

    /**
     * This method is used to setup the various object configurations
     *
     * @param context can be also referenced through {@link #getContext()}
     */
    private void initialize(Context context) {
        verifyInitMode();
        mActivity = (Activity) context;
        mWindow = mActivity.getWindow();
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setOrientation(VERTICAL);
        setVisibility(GONE);
        setWillNotDraw(true);
//        setBackgroundColor(Color.parseColor("#44546A"));
//        setBackgroundColor(Color.parseColor("#526A76"));
//        setBackgroundColor(Color.parseColor("#D2D5DB"));
//        setBackgroundColor(Color.parseColor("#A1A6AA"));
        //setBackgroundColor(Color.parseColor("#EDEEF0"));
        setBackgroundColor(Color.parseColor("#a1a6aa"));
        //setBackgroundColor(Color.parseColor("#3299FF"));
//        setBackgroundColor(Color.parseColor("#666666"));
        //setBackgroundColor(Color.parseColor("#DADBE0"));

        mGeezTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/nyala.ttf");
        //setBackgroundColor(Color.parseColor("#206CC3"));
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
//        modifiersContainer.setBackgroundColor(Color.parseColor("#384248"));
//        modifiersContainer.setBackgroundColor(Color.parseColor("#B7BFCA"));
        //modifiersContainer.setBackgroundColor(Color.parseColor("#8A97A7"));
        modifiersContainer.setBackgroundColor(Color.parseColor("#7996ad"));
        modifiersContainer.setGravity(Gravity.CENTER);
        modifiersContainer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getAction()) {
                    case KeyEvent.ACTION_UP:
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            Toast.makeText(getContext(), "Clicked back!", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        modifiersContainer.setFocusableInTouchMode(true);
        modifiersContainer.requestFocus();
        addView(modifiersContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyHeight));


        AmharicKeyboardManager manager = new AmharicKeyboardManager();
        List<KeyboardRow> properties = manager.getProperties();
        for (int i = 0; i < properties.size(); i++) {
            KeyboardRow keyboardRow = properties.get(i);
            populateKeyboardRow(keyboardRow, keyHeight);
        }
    }

    private void populateKeyboardRow(KeyboardRow keyboardRow, int keyHeight) {
        Context context = getContext();
        LinearLayout keyContainer = new LinearLayout(context);
        keyContainer.setOrientation(HORIZONTAL);
        //keyContainer.setPadding(5, 0, 5, 0);
        keyContainer.setGravity(Gravity.CENTER);

        List<KeyboardKey> keyList = keyboardRow.getKeyList();
        if (keyList != null) {
            for (int i = 0; i < keyList.size(); i++) {
                KeyboardKey keyboardKey = keyList.get(i);
                if (keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_NORMAL) {
                    Button child = new Button(context);
                    child.setText(keyboardKey.getCharCode());
                    child.setTextSize(15f);
                    child.setTextColor(ContextCompat.getColorStateList(context, R.color.amharic_key_text_color_selector));
                    child.setGravity(Gravity.CENTER);
                    child.setTypeface(mGeezTypeface);
                    child.setTag(keyboardKey);
                    handleChild(child, keyboardKey.getColumnCount(), keyContainer);
                } else if (keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_BACKSPACE ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_SPACE ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_NEW_LINE ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_EVENT_ENTER ||
                        keyboardKey.getKeyCommand() == KeyboardKey.KEY_HIDE_KEYBOARD) {
                    ImageView child = new ImageView(context);
                    child.setImageResource(keyboardKey.getCommandImage());
                    child.setPadding(25, 25, 25, 25);
                    child.setTag(keyboardKey);
                    handleChild(child, keyboardKey.getColumnCount(), keyContainer);
                }
            }
        }
        addView(keyContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyHeight));
    }

    private void handleChild(View child, int columnCount, LinearLayout keyContainer) {
        child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.amharic_key_bg));
        //child.setVisibility(GONE);

        child.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "processing... down");
                        if(shouldVibrate) {
                            Vibrator vb = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vb.vibrate(20);
                        }
                        downView = v;
                        handler.removeCallbacks(handlerRunnable);
                        handler.postAtTime(handlerRunnable, downView, SystemClock.uptimeMillis() + initialInterval);
                        //clickListener.onClick(v);

                       break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "processing... move");
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.d(TAG, "processing... up or cancel");
                        handler.removeCallbacksAndMessages(downView);
                        downView = null;
                        normalInterval = 100;
                        break;
                }
                return false;
            }
        });

        child.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        child.setOnClickListener(clickListener);

        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(5, 5, 5, 5);
        params.weight = columnCount;
        keyContainer.addView(child, params);
    }

    private final View.OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean skipDelete = false;

            if (mEditText.hasSelection()) {
                // if true, the text in the EditText is selected

                int startSelection=mEditText.getSelectionStart();
                int endSelection=mEditText.getSelectionEnd();

                Editable editable = mEditText.getText();
                editable.delete(startSelection, endSelection);

                skipDelete = true;
            }

            KeyboardKey tag = (KeyboardKey) v.getTag();
            if (tag.getKeyCommand() == KeyboardKey.KEY_EVENT_NORMAL) {
                processKeyInput((Button) v);
                enableModifierFlag = true;
            } else if (tag.getKeyCommand() == KeyboardKey.KEY_EVENT_BACKSPACE && !skipDelete) {
                int start = mEditText.getSelectionStart();
                if (start == 0) return;
                mEditText.getText().delete(start - 1, start);
                if (start == 1) modifiersContainer.removeAllViews();
            } else if (tag.getKeyCommand() == KeyboardKey.KEY_EVENT_SPACE ||
                    tag.getKeyCommand() == KeyboardKey.KEY_NEW_LINE) {
                int start = mEditText.getSelectionStart();
                mEditText.getText().insert(start, tag.getCharCode());
            } else if (tag.getKeyCommand() == KeyboardKey.KEY_HIDE_KEYBOARD) {
                hideMyKeyboard();
            }

            //Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
        }
    };

    private View downView;
    private int normalInterval = 100;
    private final int initialInterval = 400;
    private Handler handler = new Handler();

    private Runnable handlerRunnable = new Runnable() {
        @Override
        public void run() {
            if (downView == null) {
                return;
            }
            handler.removeCallbacksAndMessages(downView);
            handler.postAtTime(this, downView, SystemClock.uptimeMillis() + normalInterval);
            clickListener.onClick(downView);
            if(normalInterval > 50) {
                normalInterval -= 10;
            }
        }
    };

    public void handleEditText(EditText editText) {
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

            KeyboardKey tag = (KeyboardKey) button.getTag();

            List<String> modifierList = tag.getKeyModifiers();
            handleTags(modifierList);
            //handleKeyTouchExtra();
            //playClick(0);
            //mKeyClickListener.onTextChanged(editableText.toString());
        }
    }

    private void handleTags(List<String> typographyList) {
        modifiersContainer.removeAllViews();

        for (int i = 0; i < typographyList.size(); i++) {
            String typography = typographyList.get(i);
            Button child = new Button(getContext());
            child.setText(typography);
            //child.setTextColor(Color.parseColor("#FF5722"));
            child.setTextColor(Color.WHITE);
            //child.setBackgroundColor(Color.parseColor("#3FBCEF"));
            child.setIncludeFontPadding(false);
            child.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.keyboard_modifierkey_bg));
            child.setTextSize(15f);
            child.setGravity(Gravity.CENTER);
            child.setTypeface(mGeezTypeface, Typeface.BOLD);

            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Button button = (Button) v;

                    if (!mEditText.isFocused()) mEditText.requestFocus();
                    Editable editableText = mEditText.getText();
                    int start = mEditText.getSelectionStart();
                    if (start == -1) return;

                    if(enableModifierFlag) {
                        enableModifierFlag = false;
                        editableText.replace(start-1, start, button.getText());
                    } else {
                        editableText.insert(start, button.getText());
                    }


                    //Object tag = button.getTag();

                   // editableText.insert(start, button.getText());

                    Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            });

            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            params.setMargins(5, 5, 5, 5);
            modifiersContainer.addView(child, params);
        }
    }

    public boolean isShowing() {
        return mShowing;
    }

    public void showMyKeyboard() {
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

    public void hideMyKeyboard() {
        animateKeyboard(0, mKeyboardHeight);
        setVisibility(GONE);
        mRootView.removeView(AmharicKeyboardView.this);
        mShowing = false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean keyEventConsumed = false;
        if (mShowing) {
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
}
