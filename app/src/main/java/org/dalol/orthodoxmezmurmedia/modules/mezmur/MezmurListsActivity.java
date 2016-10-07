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

package org.dalol.orthodoxmezmurmedia.modules.mezmur;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dalol.orthodoxmezmurmedia.Cheeses;
import org.dalol.orthodoxmezmurmedia.RecyclerAdapter;
import org.dalol.orthodoxmezmurmedia.StickyHeaderUtils;
import org.dalol.orthodoxmezmurmedia.mvp.model.mezmur.Mezmur;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.business.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.utilities.helpers.RecyclerViewFastIndexer;
import org.dalol.orthodoxmezmurmedia.utilities.widgets.FakeCustomKeyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/21/2016
 */
public class MezmurListsActivity extends BaseActivity {

    private static final String TAG = MezmurListsActivity.class.getSimpleName();
//
    @BindView(R.id.recyclerView) protected RecyclerView mRecyclerView;
    @BindView(R.id.fastscroller) protected RecyclerViewFastIndexer fastScroller;
    @BindView(R.id.editText_merzumr_search_quesry) protected EditText mSearchQueryField;
    private RecyclerAdapter adapter;
    private FakeCustomKeyboard mView;
    private char[] mAmharicChars = {'\u134A', '\u120A', '\u1356'};


    //    @BindView(R.id.viewpager) protected ViewPager mViewPager;
//    @BindView(R.id.drawerLayout) protected DrawerLayout mDrawerLayout;
//    @BindView(R.id.nav_view) protected NavigationView mNavigationView;
//
//    private ActionBarDrawerToggle mDrawerToggle;
//    private List<MezmurSubsriber> subscriber = new ArrayList<>();
//    private EditText searchEditText;
//    //private KeyboardHandlerDelegate delegate;
//    private KeyboardView mInputView;
//
    @OnClick(R.id.searchMezmurFromListButton)
    protected void onSearchClick() {
        Toast.makeText(MezmurListsActivity.this, "Searching from list...", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.changeKeyboardInput)
    protected void onChangeKeyboardInput() {
        Toast.makeText(MezmurListsActivity.this, "Changing keyboard input...", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//
        showHome();

        mSearchQueryField.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mSearchQueryField.onTouchEvent(event);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //mSearchQueryField.setCursorVisible(true);

//                        mSearchQueryField.setFocusableInTouchMode(true);
//                        mSearchQueryField.requestFocus();
                        Toast.makeText(v.getContext(), "Showing custom Keyboard", Toast.LENGTH_SHORT).show();
                        return true;
                }
                hideKeboard(getCurrentFocus());
                //hideKeboard(getCurrentFocus());
                return true;
            }
        });

        mSearchQueryField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    mView.showKeyboard();
                } else {
                    mView.hideKeyboard();
                }
            }
        });

        mView = new FakeCustomKeyboard(MezmurListsActivity.this) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                //canvas.drawColor(Color.GREEN);
            }
        };

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable editable = mSearchQueryField.getText();
                int start = mSearchQueryField.getSelectionStart();
                editable.insert(start, Character.toString(mAmharicChars[new Random().nextInt(mAmharicChars.length)]));
                Toast.makeText(MezmurListsActivity.this, "Clicked on the keyboard!", Toast.LENGTH_SHORT).show();
            }
        });

        Button child = new Button(this);
        child.setText("X");
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(150, 150);
        params.leftMargin = 0;
        params.topMargin = 0;
        child.setLayoutParams(params);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = mSearchQueryField.getSelectionStart();
                mSearchQueryField.getText().delete(start - 1, start);
            }
        });
        mView.addView(child, params);


//        mView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                switch (event.getAction()) {
//                    case KeyEvent.ACTION_UP:
//                        if (isShowing && keyCode == KeyEvent.KEYCODE_BACK) {
//                            hideFakeKeyboard();
//                            return true;
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
//        mView.setFocusable(true);
//        mView.setFocusableInTouchMode(true);
//        mView.requestFocus();


        //mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(getLayoutInflater(), Arrays.asList(Cheeses.sCheeseStrings));
        adapter.setClickListener(new RecyclerAdapter.ItemClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MezmurListsActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.pictures_popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_open:

                                return true;
                            case R.id.menu_export:

                                return true;
                            case R.id.menu_uninstall:

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);

                RecyclerAdapter adapter = (RecyclerAdapter) parent.getAdapter();

                if (adapter != null && adapter.getItemCount() > 1) {

                    View topView = parent.getChildAt(0);
                    View secondView = parent.getChildAt(1);

                    TextView firstRowIndex = (TextView) topView.findViewById(R.id.indicatorCharacter);
                    TextView secondRowIndex = (TextView) secondView.findViewById(R.id.indicatorCharacter);



                    View headerView = getHeaderOf(parent, topView);

                    if (headerView == null) {
                        return;
                    }

                    if (StickyHeaderUtils.isHeaderView(parent, adapter, secondView)) {
                        if (secondView.getTop() <= headerView.getHeight()) {
                            c.translate(0, secondView.getTop() - headerView.getHeight());
                        }
                    }
                    //if (headerView != null) {
                    headerView.findViewById(R.id.stickyContainer).draw(c);
                    //}
                    //headerView.draw(c);
                    c.save();
                    c.translate(0, 0);
                }
            }
        });

        //final RecyclerViewFastIndexer fastScroller = (RecyclerViewFastIndexer) view.findViewById(R.id.fastscroller);
        fastScroller.initWithRecyclerView(mRecyclerView);
        fastScroller.setColorHandle();

//        //setTitleBarTint();
//
//
//        setTitle("መዝሙር ደብተሬ");
//        //delegate = new KeyboardHandlerDelegate(this);
//
//        View v = getLayoutInflater().inflate(R.layout.input, null);
//        mInputView = (KeyboardView) v.findViewById(R.id.keyboard);
//       // mInputView.setOnKeyboardActionListener(this);
//
////        mInputView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
////            @Override
////            public void onPress(int i) {
////
////            }
////
////            @Override
////            public void onRelease(int i) {
////
////            }
////
////            @Override
////            public void onKey(int primaryCode, int[] ints) {
////                playClick(primaryCode);
////                long eventTime = System.currentTimeMillis();
////                KeyEvent event = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
////
////                dispatchKeyEvent(event);
////            }
////
////            @Override
////            public void onText(CharSequence charSequence) {
////
////            }
////
////            @Override
////            public void swipeLeft() {
////
////            }
////
////            @Override
////            public void swipeRight() {
////
////            }
////
////            @Override
////            public void swipeDown() {
////
////            }
////
////            @Override
////            public void swipeUp() {
////
////            }
////        });
//
////
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        mInputView.setLayoutParams(params);
//
//               // FrameLayout rootView = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
////        rootView.removeAllViews();
//
//        RelativeLayout relativeLayout = new RelativeLayout(this);
//        relativeLayout.removeAllViews();
//        relativeLayout.addView( v);
//
//
//        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
//        content.addView(relativeLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//        //View.inflate(this, R.layout.input, rootView);
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager assetManager = getAssets();
                try {
                    InputStream stream = assetManager.open("mezmurJson.json");

                    BufferedReader in =  new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                    String str;

                    StringBuffer buffer = new StringBuffer();

                    while ((str=in.readLine()) != null) {
                        buffer.append(str);
                    }

                    in.close();

                    Type type = new TypeToken<List<Mezmur>>(){}.getType();

                    List<Mezmur> mezmurs = new Gson().fromJson(buffer.toString(), type);
                    Log.d("MezmurListsActivity", "Total Count of mezmur - > " + mezmurs.size());


                    for (int i = 0; i < mezmurs.size(); i++) {
                        Mezmur mezmur = mezmurs.get(i);
                        addMezmur(mezmur);
                    }

                } catch (IOException e) {
                    Log.d("MezmurListsActivity", "Total Count of mezmur UNKNOWN - > " +e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
//
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.openDrawer, R.string.closeDrawer) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                //getSupportActionBar().setTitle("Navigation!");
//                invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                //getSupportActionBar().setTitle("Title");
//                invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//            }
//        };
//
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                mDrawerLayout.closeDrawers();
//                if (menuItem.isChecked()) {
//                    //return true;
//                } else {
//                    menuItem.setChecked(true);
//                }
//
//                switch (menuItem.getItemId()) {
//                    case R.id.menu_about:
//                        showAbout();
//                        Toast.makeText(getApplicationContext(), "About Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_rate:
//                        Toast.makeText(getApplicationContext(), "About Selected", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_settings:
//                        startActivity(new Intent(MezmurListsActivity.this, SettingsActivity.class));
//                        return true;
//                    default:
//                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
//                        return true;
//                }
//            }
//        });
//
//            //setViewElevation(mToolbar, 15f);
//
//
//        final DisplayMetrics metrics = getResources().getDisplayMetrics();
//        final float screenWidthInDp = metrics.widthPixels / metrics.density;
//        final float screenHeightInDp = metrics.heightPixels / metrics.density;
//
//        if(screenWidthInDp <= 360) {
//            mNavigationView.getLayoutParams().width =
//                    (int) (metrics.widthPixels - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, metrics));
//        }
//
////        mNavigationView.getLayoutParams().width =
////                (int) (metrics.widthPixels - CommonUtils.convertDpToPixel(DP, getApplicationContext()));
//
//
//        mDrawerToggle.setDrawerIndicatorEnabled(true);
//        mDrawerLayout.addDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();
//
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new TestTab(), "HOME");
//        adapter.addFragment(new TestTab(), "RECENT");
//        mViewPager.setAdapter(adapter);
//
//        mTabLayout.setupWithViewPager(mViewPager);
//
//
    }
//
//    private void showAbout() {
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setView(R.layout.layout_about)
//                .setCancelable(true)
//                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//        dialog.show();
//    }
//
//    private void playClick(int keyCode){
//        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        switch(keyCode){
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
//            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
//        }
//    }
//


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean b = mView.dispatchKeyEvent(event);
        return b ? b : super.dispatchKeyEvent(event);
    }

    private void hideKeboard(View v) {
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            mSearchQueryField.setCursorVisible(true);
        }

        mView.showKeyboard();
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_start_playlist:
                Toast.makeText(MezmurListsActivity.this, "Starting Playlist..", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addMezmur(final Mezmur mezmur) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                adapter.appen(mezmur);

//                for (MezmurSubsriber subsriber : subscriber) {
//                    subsriber.notifyMezmur(mezmur);
//                }
            }
        });
    }
//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mezmur_list_menu, menu);

//        final MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

//        searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//
//
//        //mInputView.setKeyboard(new LatinKeyboard(this, R.xml.qwerty));
//        CustomKeyboard mCustomKeyboard1 = new CustomKeyboard(this, mInputView);
//        mCustomKeyboard1.registerEditText(searchEditText);

        //delegate.init(mInputView, searchEditText);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        editText.setText("Filippo");
//        editText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                EditText edittext = (EditText) v;
//                int inType = edittext.getInputType();       // Backup the input type
//                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
//                edittext.onTouchEvent(event);               // Call native handler
//                edittext.setInputType(inType);              // Restore input type
//                return true; // Consume touch event
//            }
//        });
//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(!b ) ((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
//
//                //Toast.makeText(MezmurListsActivity.this, "Focused -> " + b, Toast.LENGTH_SHORT).show();
//            }
//        });

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });
//
//        MenuItemCompat.setOnActionExpandListener(item,
//                new MenuItemCompat.OnActionExpandListener() {
//                    @Override
//                    public boolean onMenuItemActionCollapse(MenuItem item) {
//                        // Do something when collapsed
//                        //adapter.setFilter(mCountryModel);
//                        return true; // Return true to collapse action view
//                    }
//
//                    @Override
//                    public boolean onMenuItemActionExpand(MenuItem item) {
//                        // Do something when expanded
//                        return true; // Return true to expand action view
//                    }
//                });
        return super.onCreateOptionsMenu(menu);
    }

        @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDependency() {

    }

//    public void subscribe(MezmurSubsriber tab) {
//        this.subscriber.add(tab);
//    }
//
//    public EditText getSearchEditText() {
//        return searchEditText;
//    }
//
//    class ViewPagerAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapter(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }

    private View getHeaderOf(RecyclerView parent, View view) {
        if (StickyHeaderUtils.isHeaderView(parent, adapter, view)) {
            return view;
        } else {
            int position = parent.getChildPosition(view);
            RecyclerAdapter adapter = (RecyclerAdapter) parent.getAdapter();
            for (int i = position; i >= 0; i--) {
                if (adapter.shouldStartTranslate(i)) {
                    RecyclerAdapter.Holder viewHolder = adapter.onCreateViewHolder(parent, 0);
                    adapter.onBindViewHolder(viewHolder, i);

                    View header = viewHolder.itemView;

                    if (header.getLayoutParams() == null) {
                        header.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT));
                    }

                    int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
                    int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);

                    int childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width);
                    int childHeight = ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);
                    header.measure(childWidth, childHeight);
                    header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
                    return header;
                }
            }
        }
        return null;
    }
}
