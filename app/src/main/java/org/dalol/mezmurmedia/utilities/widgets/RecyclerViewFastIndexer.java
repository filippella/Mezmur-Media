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

package org.dalol.mezmurmedia.utilities.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerViewFastIndexer extends LinearLayout {
    private static final int BUBBLE_ANIMATION_DURATION = 100;
    private static final int TRACK_SNAP_RANGE = 5;
    private static final String TAG = RecyclerViewFastIndexer.class.getSimpleName();

    private TextView bubble;
    private View handle;
    private RecyclerView recyclerView;
    private int height;
    private boolean isInitialized = false;
    private ObjectAnimator currentAnimator = null;


    int positionToCapture;

    private final RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
            updateBubbleAndHandlePosition();
        }
    };

    public interface BubbleTextGetter {
        String getTextToShowInBubble(int pos);
    }

    public RecyclerViewFastIndexer(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public RecyclerViewFastIndexer(final Context context) {
        super(context);
        init(context);
    }

    public RecyclerViewFastIndexer(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context) {
        if (isInitialized)
            return;
        isInitialized = true;
        setOrientation(HORIZONTAL);
        setClipChildren(false);
        setWillNotDraw(false);



        bubble = new TextView(context);
        bubble.setTextColor(Color.WHITE);
        bubble.setTextSize(48);


        GradientDrawable bubbleDrawable = new GradientDrawable();
        bubbleDrawable.setColor(0xFFce891e);
        bubbleDrawable.setSize(getCustomSize(88), getCustomSize(88));
        bubbleDrawable.setCornerRadii(new float[] {getCustomSize(44), getCustomSize(44),
                getCustomSize(44), getCustomSize(44),
                0, 0,
                getCustomSize(44), getCustomSize(44)});

        bubble.setBackgroundDrawable(bubbleDrawable);

        //bubble.setBackgroundResource(R.drawable.recycler_view_fast_scroller__bubble);
        bubble.setGravity(Gravity.CENTER);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT | Gravity.END;

        addView(bubble, params);

        if (bubble != null)
            bubble.setVisibility(INVISIBLE);


        handle = new ImageView(context);
        //handle.setBackgroundResource(R.drawable.recycler_view_fast_scroller__handle);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setSize(getCustomSize(4), getCustomSize(35));
        drawable.setColor(0xFFce891e);
        drawable.setCornerRadius(getCustomSize(5));

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setSize(getCustomSize(4), getCustomSize(35));
        drawable2.setColor(0xFFf3a124);
        drawable2.setCornerRadius(getCustomSize(5));


        StateListDrawable states = new StateListDrawable ();
        states.addState (new int[]{ android.R.attr.state_selected }, drawable);
        states.addState (new int[]{ }, drawable2);

        handle.setBackgroundDrawable(states);


        LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int dimension = getCustomSize(3);

        int doubleDimension = dimension * 2;
        params2.leftMargin = doubleDimension;
        params2.rightMargin = doubleDimension;

        handle.setPadding(dimension, 0, dimension, 0);
        handle.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    v.setBackgroundColor(Color.parseColor("#00891e"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#44891e"));
                }
            }
        });

        addView(handle, params2);

    }

    private int getCustomSize(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        updateBubbleAndHandlePosition();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < handle.getX() - (ViewCompat.getPaddingStart(handle) * 5))
                    return false;
                if (currentAnimator != null)
                    currentAnimator.cancel();
                if (bubble != null && bubble.getVisibility() == INVISIBLE)
                    showBubble();
                handle.setSelected(true);
            case MotionEvent.ACTION_MOVE:
                final float y = event.getY();
                setBubbleAndHandlePosition(y);
                setRecyclerViewPosition(y);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                handle.setSelected(false);
                hideBubble();
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void initWithRecyclerView(final RecyclerView recyclerView) {
        if (this.recyclerView != recyclerView) {
            if (this.recyclerView != null)
                this.recyclerView.removeOnScrollListener(onScrollListener);
            this.recyclerView = recyclerView;
            if (this.recyclerView == null)
                return;
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false) {
                @Override
                public void onLayoutChildren(final RecyclerView.Recycler recycler, final RecyclerView.State state) {
                    super.onLayoutChildren(recycler, state);
                    //TODO if the ITEMS are filtered, considered hiding the fast scroller here
                    final int firstVisibleItemPosition = findFirstVisibleItemPosition();
                    if (firstVisibleItemPosition != 0) {
                        // this avoids trying to handle un-needed calls
                        if (firstVisibleItemPosition == -1)
                            //not initialized, or no ITEMS shown, so hide fast-scroller
                            setVisibility(View.GONE);
                        return;
                    }
                    final int lastVisibleItemPosition = findLastVisibleItemPosition();
                    int itemsShown = lastVisibleItemPosition - firstVisibleItemPosition + 1;
                    //if all ITEMS are shown, hide the fast-scroller
                    setVisibility(recyclerView.getAdapter().getItemCount() > itemsShown ? View.VISIBLE : View.GONE);
                }
            });
            recyclerView.addOnScrollListener(onScrollListener);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#2099FF"));
        paint.setStyle(Paint.Style.FILL);

        //handle.getLeft() + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics())
        int bubbleRight = bubble.getRight();


        int center = (getWidth() - bubbleRight) / 2;

        canvas.drawRect(bubbleRight + center, 0, center+ bubbleRight + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.6f, getResources().getDisplayMetrics()), getHeight(), paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(onScrollListener);
            recyclerView = null;
        }
    }

    public void setColorHandle() {
        GradientDrawable drawable = (GradientDrawable) bubble.getBackground();
        drawable.setColor(Color.parseColor("#01cb56"));
    }

    private void setRecyclerViewPosition(float y) {
        if (recyclerView != null) {
            final int itemCount = recyclerView.getAdapter().getItemCount();
            float proportion;
            if (handle.getY() == 0)
                proportion = 0f;
            else if (handle.getY() + handle.getHeight() >= height - TRACK_SNAP_RANGE)
                proportion = 1f;
            else
                proportion = y / (float) height;
            final int targetPos = getValueInRange(0, itemCount - 1, (int) (proportion * (float) itemCount));
            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(targetPos, 0);
            final String bubbleText = ((BubbleTextGetter) recyclerView.getAdapter()).getTextToShowInBubble(targetPos);
            if (bubble != null)
                bubble.setText(bubbleText);
        }
    }

    private int getValueInRange(int min, int max, int value) {
        int minimum = Math.max(min, value);
        return Math.min(minimum, max);
    }

    private void updateBubbleAndHandlePosition() {
        if (bubble == null || handle.isSelected())
            return;

        final int verticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        final int verticalScrollRange = recyclerView.computeVerticalScrollRange();
        float proportion = (float) verticalScrollOffset / ((float) verticalScrollRange - height);
        setBubbleAndHandlePosition(height * proportion);
    }

    private void setBubbleAndHandlePosition(float y) {
        final int handleHeight = handle.getHeight();
        handle.setY(getValueInRange(0, height - handleHeight, (int) (y - handleHeight / 2)));
        if (bubble != null) {
            int bubbleHeight = bubble.getHeight();
            bubble.setY(getValueInRange(0, height - bubbleHeight - handleHeight / 2, (int) (y - bubbleHeight)));
        }
    }

    private void showBubble() {
        if (bubble == null)
            return;
        bubble.setVisibility(VISIBLE);
        if (currentAnimator != null)
            currentAnimator.cancel();

        bubble.setPivotX(bubble.getRight());
        bubble.setPivotY(bubble.getBottom());


        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(SCALE_X, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(SCALE_Y, 0f, 1f);
        currentAnimator = ObjectAnimator.ofPropertyValuesHolder(bubble, pvhX, pvhY);
        currentAnimator.setDuration(BUBBLE_ANIMATION_DURATION);

       // currentAnimator = ObjectAnimator.ofFloat(bubble, "alpha", 0f, 1f).setDuration(BUBBLE_ANIMATION_DURATION);
        currentAnimator.start();
    }

    private void hideBubble() {
        if (bubble == null)
            return;
        if (currentAnimator != null)
            currentAnimator.cancel();


        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(SCALE_X, 1f, 0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(SCALE_Y, 1f, 0f);
        currentAnimator = ObjectAnimator.ofPropertyValuesHolder(bubble, pvhX, pvhY);
        currentAnimator.setDuration(BUBBLE_ANIMATION_DURATION);

        //currentAnimator = ObjectAnimator.ofFloat(bubble, "alpha", 1f, 0f).setDuration(BUBBLE_ANIMATION_DURATION);
        currentAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                bubble.setVisibility(INVISIBLE);
                currentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                bubble.setVisibility(INVISIBLE);
                currentAnimator = null;
            }
        });
        currentAnimator.start();
    }
}
